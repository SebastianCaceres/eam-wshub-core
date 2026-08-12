package ch.cern.eam.wshub.core.services.contractmanagement.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.contractmanagement.EquipmentReservationAdjustmentService;
import ch.cern.eam.wshub.core.services.contractmanagement.entities.EquipmentReservationAdjustment;
import ch.cern.eam.wshub.core.services.equipment.EquipmentReservationService;
import ch.cern.eam.wshub.core.services.equipment.impl.EquipmentReservationServiceImpl;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class EquipmentReservationAdjustmentServiceImpl implements EquipmentReservationAdjustmentService {

    private final Tools tools;

    private ApplicationData applicationData;

    private EquipmentReservationService equipmentReservationService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public EquipmentReservationAdjustmentServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public String createEquipmentReservationAdjustment(InforContext context, EquipmentReservationAdjustment equipmentReservationAdjustment) throws InforException {
        return null;
        // Since EAM always returns a 0 as the primary key of the created Equipment Reservation Adjustment, we have to use an alternative method to retrieve it
        // The solution might return incorrect results if multiple clients are creating requests at the same time, but it's still
        //better than the alternative of always not having the ID (for CERN's use case)
        //        List<EquipmentReservationAdjustment> equipmentReservationAdjustments = equipmentReservationService.readEquipmentReservationAdjustments(context, equipmentReservationAdjustment.getCustomerRentalCode());
        //        equipmentReservationAdjustments.removeIf(s ->
        //                !Objects.equals(dateFormat.format(s.getDate()), dateFormat.format(equipmentReservationAdjustment.getDate()))
        //                || (s.getRate() == null ? equipmentReservationAdjustment.getRate() != null
        //                        : s.getRate().compareTo(equipmentReservationAdjustment.getRate()) != 0)
        //                || !Objects.equals(s.getAdjustmentCode(), equipmentReservationAdjustment.getAdjustmentCode())
        //            );
        //        if (equipmentReservationAdjustments.size() == 1) {
        //            return equipmentReservationAdjustments.get(0).getCode();
        //        }
    }

    @Override
    public EquipmentReservationAdjustment readEquipmentReservationAdjustment(InforContext context, String number) throws InforException {
        return null;
    }

    @Override
    public String updateEquipmentReservationAdjustment(InforContext context, EquipmentReservationAdjustment equipmentReservationAdjustment) throws InforException {
        return null;
    }

    @Override
    public String deleteEquipmentReservationAdjustment(InforContext context, String number) throws InforException {
        return null;
    }
}
