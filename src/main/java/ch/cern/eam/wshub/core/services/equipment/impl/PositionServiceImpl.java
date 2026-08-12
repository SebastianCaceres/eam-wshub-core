package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.PositionService;
import ch.cern.eam.wshub.core.services.equipment.entities.Equipment;
import ch.cern.eam.wshub.core.services.userdefinedscreens.UserDefinedListService;
import ch.cern.eam.wshub.core.services.userdefinedscreens.entities.EntityId;
import ch.cern.eam.wshub.core.services.userdefinedscreens.impl.UserDefinedListServiceImpl;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import java.util.HashMap;
import java.util.List;
import ch.cern.eam.wshub.core.repositories.EquipmentRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import static ch.cern.eam.wshub.core.services.equipment.impl.EquipmentHierarchyTools.*;
import static ch.cern.eam.wshub.core.services.equipment.impl.EquipmentHierarchyTools.readHierarchyType;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.*;

public class PositionServiceImpl implements PositionService {

    private Tools tools;

    private ApplicationData applicationData;

    private UserDefinedListService userDefinedListService;

    private EquipmentRepository equipmentRepository;

    public PositionServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public PositionServiceImpl(ApplicationData applicationData, Tools tools, EquipmentRepository equipmentRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.equipmentRepository = equipmentRepository;
    }

    public String createPosition(InforContext context, Equipment positionParam) throws InforException {
        positionParam.setSystemTypeCode("P");
        Equipment saved = equipmentRepository.save(positionParam);
        return saved.getCode();
    }

    public String deletePosition(InforContext context, String positionCode, String organization) throws InforException {
        equipmentRepository.deleteById(positionCode);
        return positionCode;
    }

    public Equipment readPositionDefault(InforContext context, String organization) throws InforException {
        return equipmentRepository.findById(organization).orElse(null);
    }

    public Equipment readPosition(InforContext context, String positionCode, String organization) throws InforException {
        return equipmentRepository.findByCodeAndSystemTypeCode(positionCode, "P").orElse(null);
    }

    public String updatePosition(InforContext context, Equipment positionParam) throws InforException {
        positionParam.setSystemTypeCode("P");
        Equipment saved = equipmentRepository.save(positionParam);
        return saved.getCode();
        //
        //
    }
}
