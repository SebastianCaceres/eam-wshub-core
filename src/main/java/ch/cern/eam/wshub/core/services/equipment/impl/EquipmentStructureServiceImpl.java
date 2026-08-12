package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.EquipmentStructureService;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentStructure;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.annotations.BooleanType;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import javax.xml.ws.Holder;

public class EquipmentStructureServiceImpl implements EquipmentStructureService {

    private Tools tools;

    private ApplicationData applicationData;

    public EquipmentStructureServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    public String addEquipmentToStructure(InforContext context, EquipmentStructure equipmentStructure) throws InforException {
        return null;
    }

    public String removeEquipmentFromStructure(InforContext context, EquipmentStructure equipmentStructure) throws InforException {
        return null;
    }

    public String updateEquipmentStructure(InforContext context, EquipmentStructure equipmentStructure) throws InforException {
        return null;
        //
        // check if existing parent hierarchy will be updates
    }
}
