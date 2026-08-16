package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.EquipmentStructureService;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentStructure;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.annotations.BooleanType;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;

import ch.cern.eam.wshub.core.repositories.EquipmentChildrenRepository;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentChildren;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentChildrenId;

public class EquipmentStructureServiceImpl implements EquipmentStructureService {

    private Tools tools;

    private ApplicationData applicationData;

    private EquipmentChildrenRepository equipmentChildrenRepository;

    public EquipmentStructureServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    public EquipmentStructureServiceImpl(ApplicationData applicationData, Tools tools, EquipmentChildrenRepository equipmentChildrenRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.equipmentChildrenRepository = equipmentChildrenRepository;
    }

    public String addEquipmentToStructure(InforContext context, EquipmentStructure equipmentStructure) throws InforException {
        if (equipmentChildrenRepository != null && equipmentStructure != null) {
            EquipmentChildren ec = new EquipmentChildren();
            ec.setParentCode(equipmentStructure.getParentCode());
            ec.setChildCode(equipmentStructure.getChildCode());
            ec.setParentType(equipmentStructure.getParentType() != null ? equipmentStructure.getParentType() : "A");
            ec.setChildType(equipmentStructure.getChildType() != null ? equipmentStructure.getChildType() : "A");
            ec.setCostRollUp(Boolean.TRUE.equals(equipmentStructure.getCostRollUp()) ? "+" : "-");
            ec.setDependent(Boolean.TRUE.equals(equipmentStructure.getDependent()) ? "+" : "-");
            equipmentChildrenRepository.save(ec);
            return equipmentStructure.getChildCode();
        }
        return null;
    }

    public String removeEquipmentFromStructure(InforContext context, EquipmentStructure equipmentStructure) throws InforException {
        if (equipmentChildrenRepository != null && equipmentStructure != null) {
            EquipmentChildrenId id = new EquipmentChildrenId();
            id.setParentCode(equipmentStructure.getParentCode());
            id.setChildCode(equipmentStructure.getChildCode());
            id.setParentType(equipmentStructure.getParentType() != null ? equipmentStructure.getParentType() : "A");
            id.setChildType(equipmentStructure.getChildType() != null ? equipmentStructure.getChildType() : "A");
            equipmentChildrenRepository.deleteById(id);
            return equipmentStructure.getChildCode();
        }
        return null;
    }

    public String updateEquipmentStructure(InforContext context, EquipmentStructure equipmentStructure) throws InforException {
        if (equipmentStructure != null) {
            removeEquipmentFromStructure(context, equipmentStructure);
            if (equipmentStructure.getNewParentCode() != null) {
                equipmentStructure.setParentCode(equipmentStructure.getNewParentCode());
                if (equipmentStructure.getNewParentType() != null) {
                    equipmentStructure.setParentType(equipmentStructure.getNewParentType());
                }
            }
            return addEquipmentToStructure(context, equipmentStructure);
        }
        return null;
    }
}
