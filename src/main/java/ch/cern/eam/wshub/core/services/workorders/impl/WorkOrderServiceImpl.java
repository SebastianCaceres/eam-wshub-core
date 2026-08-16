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
import static ch.cern.eam.wshub.core.tools.Tools.extractOrganizationCode;
import java.util.List;
import static ch.cern.eam.wshub.core.tools.Tools.extractEntityCode;

public class WorkOrderServiceImpl implements WorkOrderService {

    private Tools tools;

    private ApplicationData applicationData;

    private CommentService comments;

    private StandardWorkOrderService standardWorkOrderService;

    private WorkOrderRepository workOrderRepository;

    private ch.cern.eam.wshub.core.repositories.EquipmentRepository equipmentRepository;

    public WorkOrderServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public WorkOrderServiceImpl(ApplicationData applicationData, Tools tools, WorkOrderRepository workOrderRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.workOrderRepository = workOrderRepository;
    }

    public WorkOrderServiceImpl(ApplicationData applicationData, Tools tools, WorkOrderRepository workOrderRepository, ch.cern.eam.wshub.core.repositories.EquipmentRepository equipmentRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.workOrderRepository = workOrderRepository;
        this.equipmentRepository = equipmentRepository;
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
        WorkOrder wo = new WorkOrder();
        wo.setStatusCode("R");
        wo.setTypeCode("JOB");
        return wo;
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
        // Auto-generate Work Order Number if empty or null
        if (workorderParam.getNumber() == null || workorderParam.getNumber().trim().isEmpty()) {
            workorderParam.setNumber(String.valueOf(System.currentTimeMillis() / 1000));
        }
        if (workorderParam.getCreatedDate() == null) {
            workorderParam.setCreatedDate(new java.util.Date());
        }
        if (workorderParam.getReportedDate() == null) {
            workorderParam.setReportedDate(new java.util.Date());
        }
        // COPY FROM existing work order
        if (workorderParam.getCopyFrom() != null) {
            String sourceNumber = extractEntityCode(workorderParam.getCopyFrom());
            WorkOrder source = workOrderRepository.findById(sourceNumber).orElseThrow(() -> tools.generateFault("Source work order not found for copyFrom: " + sourceNumber));
            mergeFromSource(source, workorderParam);
        }
        // STANDARD WORK ORDER defaults
        if (workorderParam.getStandardWO() != null && !workorderParam.getStandardWO().trim().isEmpty() && standardWorkOrderService != null) {
            StandardWorkOrder swo = standardWorkOrderService.readStandardWorkOrder(context, workorderParam.getStandardWO());
            if (swo != null) {
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
        }
        // EQUIPMENT INHERITANCE defaults (Department, Cost Code, Location)
        if (workorderParam.getEquipmentCode() != null && !workorderParam.getEquipmentCode().trim().isEmpty() && equipmentRepository != null) {
            equipmentRepository.findById(workorderParam.getEquipmentCode()).ifPresent(eq -> {
                if (workorderParam.getDepartmentCode() == null || workorderParam.getDepartmentCode().trim().isEmpty()) {
                    workorderParam.setDepartmentCode(eq.getDepartmentCode());
                }
                if (workorderParam.getCostCode() == null || workorderParam.getCostCode().trim().isEmpty()) {
                    workorderParam.setCostCode(eq.getCostCode());
                }
                if (workorderParam.getLocationCode() == null || workorderParam.getLocationCode().trim().isEmpty()) {
                    workorderParam.setLocationCode(eq.getHierarchyLocationCode());
                }
            });
        }
        WorkOrder saved = workOrderRepository.save(workorderParam);
        String workOrderNumber = saved.getNumber();
        // Attach initial comment if provided
        if (workorderParam.getComment() != null && !workorderParam.getComment().trim().isEmpty() && comments != null) {
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
        WorkOrder existing = workOrderRepository.findById(woNumber)
                .orElseThrow(() -> tools.generateFault("Work order not found: " + woNumber));

        // State Machine Rule: Cannot update a closed Work Order
        if ("CL".equalsIgnoreCase(existing.getStatusCode()) && !"CL".equalsIgnoreCase(workorderParam.getStatusCode())) {
            throw tools.generateFault("Cannot re-open or update a Closed Work Order: " + woNumber);
        }

        // PM Work Order Rule: Lock PM Work Orders (Status 'A')
        if ("A".equalsIgnoreCase(existing.getStatusCode())) {
            if (tools != null) {
                throw tools.generateFault("This is a PM Work Order (Status A) and is locked for editing: " + woNumber);
            } else {
                throw new InforException("This is a PM Work Order (Status A) and is locked for editing: " + woNumber, null, null);
            }
        }

        // Completion Rule: Set completedDate if status changes to Completed or Closed
        if (("C".equalsIgnoreCase(workorderParam.getStatusCode()) || "CL".equalsIgnoreCase(workorderParam.getStatusCode())) 
                && workorderParam.getCompletedDate() == null) {
            workorderParam.setCompletedDate(new java.util.Date());
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
        // Always new number � JPA will auto-assign or caller provides
        target.setNumber(null);
    }
}
