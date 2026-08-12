package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.contractmanagement.entities.EquipmentReservationAdjustment;
import ch.cern.eam.wshub.core.services.entities.BatchResponse;
import ch.cern.eam.wshub.core.services.equipment.EquipmentReservationService;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentReservation;
import ch.cern.eam.wshub.core.services.grids.GridsService;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequest;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequestResult;
import ch.cern.eam.wshub.core.services.grids.impl.GridsServiceImpl;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.GridTools;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import java.util.Date;
import java.util.List;

public class EquipmentReservationServiceImpl implements EquipmentReservationService {

    private ApplicationData applicationData;

    private Tools tools;

    private GridsService gridsService;

    public EquipmentReservationServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public String createEquipmentReservation(InforContext context, EquipmentReservation reservationParam) throws InforException {
        return null;
    }

    @Override
    public EquipmentReservation readEquipmentReservation(InforContext context, String customerRentalCode) throws InforException {
        return null;
    }

    @Override
    public String updateEquipmentReservation(InforContext context, EquipmentReservation reservationParam) throws InforException {
        return null;
    }

    @Override
    public String deleteEquipmentReservation(InforContext context, String customerRentalCode) throws InforException {
        return null;
    }

    @Override
    public List<EquipmentReservationAdjustment> readEquipmentReservationAdjustments(InforContext context, String customerRentalCode) throws InforException {
        GridRequest gridRequest = new GridRequest("WSCREN_CAD", GridRequest.GRIDTYPE.LIST);
        gridRequest.setUserFunctionName("WSCREN");
        gridRequest.addParam("parameter.customerrentalcode", customerRentalCode);
        gridRequest.addParam("parameter.organization", tools.getOrganizationCode(context));
        gridRequest.sortBy("adjustmentid", "DESC");
        GridRequestResult gridRequestResult = gridsService.executeQuery(context, gridRequest);
        return GridTools.convertGridResultToObject(EquipmentReservationAdjustment.class, null, gridRequestResult);
    }

    //
    // BATCH WEB SERVICES
    //
    public BatchResponse<String> createEquipmentReservationBatch(InforContext context, List<EquipmentReservation> equipmentReservationList) {
        return tools.batchOperation(context, this::createEquipmentReservation, equipmentReservationList);
    }

    public BatchResponse<EquipmentReservation> readEquipmentReservationBatch(InforContext context, List<String> equipmentReservationCodes) {
        return tools.batchOperation(context, this::readEquipmentReservation, equipmentReservationCodes);
    }

    public BatchResponse<String> updateEquipmentReservationBatch(InforContext context, List<EquipmentReservation> equipmentReservationList) {
        return tools.batchOperation(context, this::updateEquipmentReservation, equipmentReservationList);
    }

    public BatchResponse<String> deleteEquipmentReservationBatch(InforContext context, List<String> equipmentReservationCodes) {
        return tools.batchOperation(context, this::deleteEquipmentReservation, equipmentReservationCodes);
    }
}
