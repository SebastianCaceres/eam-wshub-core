package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.comments.CommentService;
import ch.cern.eam.wshub.core.services.comments.impl.CommentServiceImpl;
import ch.cern.eam.wshub.core.services.workorders.StandardWorkOrderService;
import ch.cern.eam.wshub.core.services.workorders.entities.StandardWorkOrder;
import ch.cern.eam.wshub.core.services.workorders.entities.WorkOrder;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import net.datastream.schemas.mp_entities.workorder_001.UserDefinedFields;
import net.datastream.schemas.mp_fields.STDWOID_Type;
import net.datastream.schemas.mp_functions.SessionType;
import net.datastream.schemas.mp_functions.mp0023_001.MP0023_AddWorkOrder_001;
import net.datastream.schemas.mp_functions.mp7079_001.MP7079_AddStandardWorkOrder_001;
import net.datastream.schemas.mp_functions.mp7080_001.MP7080_SyncStandardWorkOrder_001;
import net.datastream.schemas.mp_functions.mp7082_001.MP7082_GetStandardWorkOrder_001;
import net.datastream.schemas.mp_results.mp0023_001.MP0023_AddWorkOrder_001_Result;
import net.datastream.schemas.mp_results.mp7079_001.MP7079_AddStandardWorkOrder_001_Result;
import net.datastream.schemas.mp_results.mp7082_001.MP7082_GetStandardWorkOrder_001_Result;
import net.datastream.wsdls.inforws.InforWebServicesPT;
import javax.xml.ws.Holder;
import ch.cern.eam.wshub.core.repositories.StandardWorkOrderRepository;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.toCodeString;

public class StandardWorkOrderServiceImpl implements StandardWorkOrderService {

    private Tools tools;

    private InforWebServicesPT inforws;

    private ApplicationData applicationData;

    private StandardWorkOrderRepository standardWorkOrderRepository;

    public StandardWorkOrderServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient) {
        this(applicationData, tools, inforWebServicesToolkitClient, null);
    }

    public StandardWorkOrderServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient, StandardWorkOrderRepository standardWorkOrderRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforws = inforWebServicesToolkitClient;
        this.standardWorkOrderRepository = standardWorkOrderRepository;
    }

    public StandardWorkOrder readStandardWorkOrder(InforContext context, String number) throws InforException {
        if (standardWorkOrderRepository != null) {
            StandardWorkOrder found = standardWorkOrderRepository.findById(number).orElse(null);
            if (found != null) {
                return found;
            }
        }
        return tools.getInforFieldTools().transformInforObject(new StandardWorkOrder(), readStandardWorkOrderInfor(context, number), context);
    }

    public net.datastream.schemas.mp_entities.standardworkorder_001.StandardWorkOrder readStandardWorkOrderInfor(InforContext context, String number) throws InforException {
        //
        // Fetch WO
        //
        MP7082_GetStandardWorkOrder_001 getStandardWorkOrder = new MP7082_GetStandardWorkOrder_001();
        getStandardWorkOrder.setSTANDARDWO(new STDWOID_Type());
        getStandardWorkOrder.getSTANDARDWO().setORGANIZATIONID(tools.getOrganization(context));
        getStandardWorkOrder.getSTANDARDWO().setSTDWOCODE(number);
        MP7082_GetStandardWorkOrder_001_Result result = tools.performInforOperation(context, inforws::getStandardWorkOrderOp, getStandardWorkOrder);
        return result.getResultData().getStandardWorkOrder();
    }

    public String createStandardWorkOrder(InforContext context, StandardWorkOrder standardWorkOrder) throws InforException {
        StandardWorkOrder saved = standardWorkOrderRepository.save(standardWorkOrder);
        return saved.getCode();
    }

    public String updateStandardWorkOrder(InforContext context, StandardWorkOrder standardWorkOrder) throws InforException {
        StandardWorkOrder saved = standardWorkOrderRepository.save(standardWorkOrder);
        return saved.getCode();
        // Check Custom fields. If they change, or now we have them
        //        inforStandardWorkOrder.setUSERDEFINEDAREA(tools.getCustomFieldsTools().getInforCustomFields(
        //            context,
        //            toCodeString(inforStandardWorkOrder.getCLASSID()),
        //            inforStandardWorkOrder.getUSERDEFINEDAREA(),
        //            standardWorkOrder.getClassCode(),
        //
        // CALL INFOR WEB SERVICE
    }
}
