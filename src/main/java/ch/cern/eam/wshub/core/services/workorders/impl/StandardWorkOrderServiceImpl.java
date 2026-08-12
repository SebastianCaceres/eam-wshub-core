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
import javax.xml.ws.Holder;
import ch.cern.eam.wshub.core.repositories.StandardWorkOrderRepository;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.toCodeString;

public class StandardWorkOrderServiceImpl implements StandardWorkOrderService {

    private Tools tools;

    private ApplicationData applicationData;

    private StandardWorkOrderRepository standardWorkOrderRepository;

    public StandardWorkOrderServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public StandardWorkOrderServiceImpl(ApplicationData applicationData, Tools tools, StandardWorkOrderRepository standardWorkOrderRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.standardWorkOrderRepository = standardWorkOrderRepository;
    }

    public StandardWorkOrder readStandardWorkOrder(InforContext context, String number) throws InforException {
        return standardWorkOrderRepository.findById(number).orElse(null);
    }

    public net.datastream.schemas.mp_entities.standardworkorder_001.StandardWorkOrder readStandardWorkOrderInfor(InforContext context, String number) throws InforException {
        return null;
        //
        // Fetch WO
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
