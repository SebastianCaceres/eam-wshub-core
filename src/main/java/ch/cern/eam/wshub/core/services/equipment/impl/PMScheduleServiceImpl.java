package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.PMScheduleService;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentPMSchedule;
import ch.cern.eam.wshub.core.services.equipment.entities.ReleasedPMSchedule;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.annotations.BooleanType;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isNotEmpty;
import ch.cern.eam.wshub.core.repositories.EquipmentPMScheduleRepository;
import javax.persistence.EntityManager;
import javax.xml.ws.Holder;

public class PMScheduleServiceImpl implements PMScheduleService {

    private Tools tools;

    private ApplicationData applicationData;

    private EquipmentPMScheduleRepository equipmentPMScheduleRepository;

    public PMScheduleServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public PMScheduleServiceImpl(ApplicationData applicationData, Tools tools, EquipmentPMScheduleRepository equipmentPMScheduleRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.equipmentPMScheduleRepository = equipmentPMScheduleRepository;
    }

    public String createEquipmentPMSchedule(InforContext context, EquipmentPMSchedule pmSchedule) throws InforException {
        EquipmentPMSchedule saved = equipmentPMScheduleRepository.save(pmSchedule);
        return saved.getPmCode();
        //
        //
    }

    public String deleteEquipmentPMSchedule(InforContext context, EquipmentPMSchedule pmSchedule) throws InforException {
        return null;
        //
        // Fetch PM Schedule Sequence Number and Revision
        //
        // Delete PM Schedule
    }

    public String updateEquipmentPMSchedule(InforContext context, EquipmentPMSchedule pmSchedule) throws InforException {
        return null;
        //
        // Fetch PM Schedule Sequence Number and Revision
        //
        // Fetch Equipment PM Schedule first
        //
        // Update it
    }

    public String updateReleasedPMSchedule(InforContext context, ReleasedPMSchedule releasedPMSchedule) throws InforException {
        return null;
    }
}
