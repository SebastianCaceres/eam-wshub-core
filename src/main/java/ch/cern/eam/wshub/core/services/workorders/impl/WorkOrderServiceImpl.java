package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.comments.CommentService;
import ch.cern.eam.wshub.core.services.comments.impl.CommentServiceImpl;
import ch.cern.eam.wshub.core.services.entities.BatchResponse;
import ch.cern.eam.wshub.core.services.comments.entities.Comment;
import ch.cern.eam.wshub.core.services.workorders.StandardWorkOrderService;
import ch.cern.eam.wshub.core.services.workorders.WorkOrderService;
import ch.cern.eam.wshub.core.services.workorders.entities.StandardWorkOrder;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.repositories.WorkOrderRepository;
import ch.cern.eam.wshub.core.services.workorders.entities.WorkOrder;
import net.datastream.schemas.mp_fields.WOID_Type;
import net.datastream.schemas.mp_functions.mp0024_001.MP0024_GetWorkOrder_001;
import net.datastream.schemas.mp_results.mp0024_001.MP0024_GetWorkOrder_001_Result;
import net.datastream.wsdls.inforws.InforWebServicesPT;
import static ch.cern.eam.wshub.core.tools.Tools.extractOrganizationCode;
import java.util.List;
import static ch.cern.eam.wshub.core.tools.Tools.extractEntityCode;

public class WorkOrderServiceImpl implements WorkOrderService {

    private Tools tools;

    private InforWebServicesPT inforws;

    private ApplicationData applicationData;

    private CommentService comments;

    private StandardWorkOrderService standardWorkOrderService;

    private WorkOrderRepository workOrderRepository;

    public WorkOrderServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient) {
        this(applicationData, tools, inforWebServicesToolkitClient, null);
    }

    public WorkOrderServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient, WorkOrderRepository workOrderRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforws = inforWebServicesToolkitClient;
        this.workOrderRepository = workOrderRepository;
        this.comments = new CommentServiceImpl(applicationData, tools, inforWebServicesToolkitClient);
        this.standardWorkOrderService = new StandardWorkOrderServiceImpl(applicationData, tools, inforWebServicesToolkitClient);
    }

    /**
     * @deprecated Bridge method for MECServiceImpl which still uses the raw Infor
     *             SOAP result. This will be removed once MECServiceImpl is migrated
     *             to read directly from the JPA WorkOrder entity.
     */
    @Deprecated
    public MP0024_GetWorkOrder_001_Result readWorkOrderInfor(InforContext context, String number, String organization) throws InforException {
        MP0024_GetWorkOrder_001 getWorkOrder = new MP0024_GetWorkOrder_001();
        getWorkOrder.setWORKORDERID(new WOID_Type());
        getWorkOrder.getWORKORDERID().setJOBNUM(number);
        getWorkOrder.getWORKORDERID().setORGANIZATIONID(tools.getOrganization(context, organization));
        return tools.performInforOperation(context, inforws::getWorkOrderOp, getWorkOrder);
    }

    //
    // BATCH WEB SERVICES
    //
    public BatchResponse<String> createWorkOrderBatch(InforContext context, List<WorkOrder> workOrderParam) {
        return tools.batchOperation(context, this::createWorkOrder, workOrderParam);
    }

    public BatchResponse<WorkOrder> readWorkOrderBatch(InforContext context, List<String> workOrderNumbers) {
        return tools.batchOperation(context, this::readWorkOrder, workOrderNumbers);
    }

    public BatchResponse<String> updateWorkOrderBatch(InforContext context, List<WorkOrder> workOrders) {
        return tools.batchOperation(context, this::updateWorkOrder, workOrders);
    }

    public BatchResponse<String> deleteWorkOrderBatch(InforContext context, List<String> workOrderNumbers) {
        return tools.batchOperation(context, this::deleteWorkOrder, workOrderNumbers);
    }

    //
    // WORK ORDER CRUD
    //
    public WorkOrder readWorkOrder(InforContext context, String number) throws InforException {
        String woNumber = extractEntityCode(number);
        return workOrderRepository.findById(woNumber).orElseThrow(() -> tools.generateFault("Work order not found: " + woNumber));
    }

    public WorkOrder readWorkOrderDefault(InforContext context, String number) throws InforException {
        if (workOrderRepository != null && number != null) {
            java.util.Optional opt = workOrderRepository.findById(number);
            if (opt.isPresent())
                return (WorkOrder) opt.get();
        }
        // Return a blank WorkOrder with sensible defaults — no SOAP needed
        WorkOrder workOrder = new WorkOrder();
        workOrder.setStatusCode("R");
        workOrder.setTypeCode("PM");
        return workOrder;
    }

    /*
		Creates a work order. If workorderParam.copyFrom is set, copies fields
		from an existing WO (read from the JPA repository). If a standardWO code
		is supplied, its description / class / priority / type / problem are merged
		in where the caller left those fields null.

		DEFAULT VALUE IMPLEMENTATION NOTES
		The behavior at CERN is to apply the defaults after the fields from
		workorderParam are applied. EAM Light implements default value logic in its frontend.
	*/
    public String createWorkOrder(InforContext context, WorkOrder workorderParam) throws InforException {
        // COPY FROM existing work order
        if (workorderParam.getCopyFrom() != null) {
            String sourceNumber = extractEntityCode(workorderParam.getCopyFrom());
            WorkOrder source = workOrderRepository.findById(sourceNumber).orElseThrow(() -> tools.generateFault("Source work order not found for copyFrom: " + sourceNumber));
            mergeFromSource(source, workorderParam);
        }
        // STANDARD WORK ORDER defaults
        if (workorderParam.getStandardWO() != null && !workorderParam.getStandardWO().trim().isEmpty()) {
            StandardWorkOrder swo = standardWorkOrderService.readStandardWorkOrder(context, workorderParam.getStandardWO());
            if (workorderParam.getDescription() == null)
                workorderParam.setDescription(swo.getDesc());
            if (workorderParam.getClassCode() == null)
                workorderParam.setClassCode(swo.getWoClassCode());
            if (workorderParam.getPriorityCode() == null)
                workorderParam.setPriorityCode(swo.getPriorityCode());
            if (workorderParam.getTypeCode() == null)
                workorderParam.setTypeCode(swo.getWorkOrderTypeCode());
            if (workorderParam.getProblemCode() == null)
                workorderParam.setProblemCode(swo.getProblemCode());
            if (workorderParam.getCustomFields() == null)
                workorderParam.setCustomFields(swo.getCustomFields());
            if (workorderParam.getUserDefinedFields() == null)
                workorderParam.setUserDefinedFields(swo.getUserDefinedFields());
        }
        WorkOrder saved = workOrderRepository.save(workorderParam);
        String workOrderNumber = saved.getNumber();
        // Attach initial comment if provided
        if (workorderParam.getComment() != null && !workorderParam.getComment().trim().isEmpty()) {
            Comment comment = new Comment();
            comment.setEntityCode("EVNT");
            comment.setEntityKeyCode(workOrderNumber);
            comment.setText(workorderParam.getComment());
            comment.setTypeCode("*");
            comments.createComment(context, comment);
        }
        return workOrderNumber;
    }

    public String updateWorkOrder(InforContext context, WorkOrder workorderParam) throws InforException {
        String woNumber = extractEntityCode(workorderParam.getNumber());
        if (!workOrderRepository.existsById(woNumber)) {
            throw tools.generateFault("Work order not found: " + woNumber);
        }
        WorkOrder saved = workOrderRepository.save(workorderParam);
        return saved.getNumber();
    }

    public String deleteWorkOrder(InforContext context, String workOrderNumber) throws InforException {
        String woNumber = extractEntityCode(workOrderNumber);
        if (!workOrderRepository.existsById(woNumber)) {
            throw tools.generateFault("Work order not found: " + woNumber);
        }
        workOrderRepository.deleteById(woNumber);
        return workOrderNumber;
    }

    public String updateWorkOrderStatus(InforContext context, String workOrderNumber, String statusCode) throws InforException {
        String woNumber = extractEntityCode(workOrderNumber);
        WorkOrder wo = workOrderRepository.findById(woNumber).orElseThrow(() -> tools.generateFault("Work order not found: " + woNumber));
        wo.setStatusCode(statusCode);
        workOrderRepository.save(wo);
        return workOrderNumber;
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------
    /**
     * Copies non-null fields from a source WorkOrder into the target (copyFrom logic).
     * Fields explicitly set by the caller on workorderParam take precedence.
     */
    private void mergeFromSource(WorkOrder source, WorkOrder target) {
        if (target.getDescription() == null)
            target.setDescription(source.getDescription());
        if (target.getClassCode() == null)
            target.setClassCode(source.getClassCode());
        if (target.getPriorityCode() == null)
            target.setPriorityCode(source.getPriorityCode());
        if (target.getTypeCode() == null)
            target.setTypeCode(source.getTypeCode());
        if (target.getProblemCode() == null)
            target.setProblemCode(source.getProblemCode());
        if (target.getEquipmentCode() == null)
            target.setEquipmentCode(source.getEquipmentCode());
        if (target.getDepartmentCode() == null)
            target.setDepartmentCode(source.getDepartmentCode());
        if (target.getStatusCode() == null)
            target.setStatusCode(source.getStatusCode());
        if (target.getCustomFields() == null)
            target.setCustomFields(source.getCustomFields());
        if (target.getUserDefinedFields() == null)
            target.setUserDefinedFields(source.getUserDefinedFields());
        // Always new number — JPA will auto-assign or caller provides
        target.setNumber(null);
    }
}
