package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.repositories.WorkOrderRepository;
import ch.cern.eam.wshub.core.services.entities.BatchResponse;
import ch.cern.eam.wshub.core.services.grids.GridsService;
import ch.cern.eam.wshub.core.services.grids.entities.*;
import ch.cern.eam.wshub.core.services.grids.impl.GridsServiceImpl;
import ch.cern.eam.wshub.core.services.workorders.MECService;
import ch.cern.eam.wshub.core.services.workorders.entities.MEC;
import ch.cern.eam.wshub.core.services.workorders.entities.WorkOrder;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static ch.cern.eam.wshub.core.tools.Tools.extractEntityCode;

public class MECServiceImpl implements MECService {

    private Tools tools;

    private ApplicationData applicationData;

    private GridsService gridsService;

    private WorkOrderRepository workOrderRepository;

    public MECServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public MECServiceImpl(ApplicationData applicationData, Tools tools, WorkOrderRepository workOrderRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.workOrderRepository = workOrderRepository;
    }

    @Override
    public String addWorkOrderEquipment(InforContext context, MEC mecToAdd) throws InforException {
        MECService.validateInput(mecToAdd);
        WorkOrder childWorkOrder = new WorkOrder();
        childWorkOrder.setParentWO(mecToAdd.getWorkorderID());
        childWorkOrder.setEquipmentCode(mecToAdd.getEquipmentCode());
        childWorkOrder.setLocationCode(mecToAdd.getLocationID());
        childWorkOrder.setCostCode(mecToAdd.getCostCode());
        try {
            WorkOrder saved = workOrderRepository.save(childWorkOrder);
            return saved.getNumber();
        } catch (Exception e) {
            throw tools.generateFault("Failed to add MEC work order equipment via JPA: " + e.getMessage());
        }
    }

    @Override
    public BatchResponse<String> addWorkOrderEquipmentBatch(InforContext context, List<MEC> mecsToAdd) throws InforException {
        return tools.batchOperation(context, this::addWorkOrderEquipment, mecsToAdd);
    }

    @Override
    public String deleteWorkOrderMEC(InforContext context, String parentWorkorderID, String mecID) throws InforException {
        MECService.validateInput(parentWorkorderID, mecID);
        try {
            workOrderRepository.deleteById(extractEntityCode(mecID));
            return "OK";
        } catch (Exception e) {
            throw tools.generateFault("Failed to delete MEC work order equipment via JPA: " + e.getMessage());
        }
    }

    @Override
    public List<String> getWorkOrderMecIDList(InforContext context, String workorderID) throws InforException {
        MECService.validateInput(workorderID);
        GridRequest gridRequest = new GridRequest(MECService.GRID_ID, GridRequest.GRIDTYPE.LIST, 50);
        gridRequest.addParam("param.workordernum", workorderID);
        gridRequest.addParam("param.organization", tools.getOrganizationCode(context));
        gridRequest.addParam("param.workorderrtype", MECService.GRID_WO_TYPE);
        gridRequest.addParam("param.tenant", tools.getTenant(context));
        GridRequestResult res = gridsService.executeQuery(context, gridRequest);
        List<GridField> targetColumn = res.getGridFields().stream().filter(gridField -> gridField.getName().equals(MECService.MEC_ID_COLUMN_NAME)).collect(Collectors.toList());
        if (targetColumn.isEmpty()) {
            throw Tools.generateFault("Column with relatedWorkorderID (ID of the MEC) is not in dataspy");
        }
        int targetIndex = targetColumn.get(0).getOrder();
        return Arrays.stream(res.getRows()).map(gridRequestRow -> gridRequestRow.getCell()[targetIndex].getContent()).collect(Collectors.toList());
    }

    @Override
    public WorkOrder getWorkOrderMecInfor(InforContext context, String workorderID) throws InforException {
        return workOrderRepository.findById(extractEntityCode(workorderID)).orElseThrow(() -> tools.generateFault("MEC Work order not found: " + workorderID));
    }

    @Override
    public String syncWorkOrderEquipment(InforContext context, MEC updatedMEC) throws InforException {
        MECService.validateInput(updatedMEC);
        WorkOrder originalMecInfor = this.getWorkOrderMecInfor(context, updatedMEC.getRelatedWorkorderID());
        if (updatedMEC.getEquipmentCode() != null) {
            originalMecInfor.setEquipmentCode(updatedMEC.getEquipmentCode());
        }
        if (updatedMEC.getLocationID() != null) {
            originalMecInfor.setLocationCode(updatedMEC.getLocationID());
        }
        if (updatedMEC.getCostCode() != null) {
            originalMecInfor.setCostCode(updatedMEC.getCostCode());
        }
        try {
            WorkOrder saved = workOrderRepository.save(originalMecInfor);
            return saved.getNumber();
        } catch (Exception e) {
            throw tools.generateFault("Failed to sync MEC work order equipment via JPA: " + e.getMessage());
        }
    }
}
