package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.SystemService;
import ch.cern.eam.wshub.core.services.equipment.entities.Equipment;
import ch.cern.eam.wshub.core.services.userdefinedscreens.UserDefinedListService;
import ch.cern.eam.wshub.core.services.userdefinedscreens.entities.EntityId;
import ch.cern.eam.wshub.core.services.userdefinedscreens.impl.UserDefinedListServiceImpl;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.repositories.EquipmentRepository;
import java.util.HashMap;
import java.util.Optional;
import static ch.cern.eam.wshub.core.services.equipment.impl.EquipmentHierarchyTools.createPrimarySystemParent;
import static ch.cern.eam.wshub.core.services.equipment.impl.EquipmentHierarchyTools.createLocationParent;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.*;

public class SystemServiceImpl implements SystemService {

    private Tools tools;

    private ApplicationData applicationData;

    private UserDefinedListService userDefinedListService;

    private EquipmentRepository equipmentRepository;

    public SystemServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public SystemServiceImpl(ApplicationData applicationData, Tools tools, EquipmentRepository equipmentRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment readSystemDefault(InforContext context, String organization) throws InforException {
        return equipmentRepository.findById(organization).orElse(null);
    }

    public Equipment readSystem(InforContext context, String systemCode, String organization) throws InforException {
        return equipmentRepository.findByCodeAndSystemTypeCode(systemCode, "S").orElse(null);
    }


    public String updateSystem(InforContext context, Equipment systemParam) throws InforException {
        systemParam.setSystemTypeCode("S");
        Equipment saved = equipmentRepository.save(systemParam);
        return saved.getCode();
    }

    public String createSystem(InforContext context, Equipment systemParam) throws InforException {
        systemParam.setSystemTypeCode("S");
        Equipment saved = equipmentRepository.save(systemParam);
        return saved.getCode();
    }

    public String deleteSystem(InforContext context, String systemCode, String organization) throws InforException {
        equipmentRepository.deleteById(systemCode);
        return systemCode;
    }
}
