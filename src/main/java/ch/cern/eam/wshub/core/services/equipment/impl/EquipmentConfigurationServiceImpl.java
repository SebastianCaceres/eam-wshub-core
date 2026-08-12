package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.entities.BatchResponse;
import ch.cern.eam.wshub.core.services.equipment.EquipmentConfigurationService;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentConfigurationEntity;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentConfigurationId;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.DataTypeTools;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentConfigurationDefault;
import java.math.BigDecimal;
import java.util.List;

public class EquipmentConfigurationServiceImpl implements EquipmentConfigurationService {

    private Tools tools;

    private ApplicationData applicationData;

    public EquipmentConfigurationServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public String createEquipmentConfiguration(InforContext context, EquipmentConfigurationEntity equipmentConfiguration) throws InforException {
        return null;
    }

    @Override
    public String updateEquipmentConfiguration(InforContext context, EquipmentConfigurationEntity equipmentConfiguration) throws InforException {
        return null;
    }

    @Override
    public EquipmentConfigurationEntity readEquipmentConfiguration(InforContext context, EquipmentConfigurationId equipmentConfigurationId) throws InforException {
        return null;
    }

    @Override
    public EquipmentConfigurationDefault readEquipmentDefaultConfiguration(InforContext context) throws InforException {
        return null;
    }

    @Override
    public String deleteEquipmentConfiguration(InforContext context, EquipmentConfigurationId equipmentConfigurationId) throws InforException {
        return null;
    }

    @Override
    public BatchResponse<String> createEquipmentConfigurationBatch(InforContext context, List<EquipmentConfigurationEntity> equipmentConfigurationList) throws InforException {
        return null;
    }

    @Override
    public BatchResponse<EquipmentConfigurationEntity> readEquipmentConfigurationBatch(InforContext context, List<EquipmentConfigurationId> equipmentConfigurationIdList) throws InforException {
        return null;
    }

    @Override
    public BatchResponse<String> updateEquipmentConfigurationBatch(InforContext context, List<EquipmentConfigurationEntity> equipmentConfigurationList) throws InforException {
        return null;
    }

    @Override
    public BatchResponse<String> deleteEquipmentConfigurationBatch(InforContext context, List<EquipmentConfigurationId> equipmentConfigurationIdList) throws InforException {
        return null;
    }
}
