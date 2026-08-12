package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.EquipmentGenerationService;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentGenerationEntity;
import org.openapplications.oagis_segments.QUANTITY;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.decodeQuantity;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.encodeQuantity;
import javax.xml.ws.Holder;
import java.math.BigDecimal;

public class EquipmentGenerationServiceImpl implements EquipmentGenerationService {

    private Tools tools;

    private ApplicationData applicationData;

    public EquipmentGenerationServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public String createEquipmentGeneration(InforContext context, EquipmentGenerationEntity equipmentGeneration) throws InforException {
        return null;
    }

    @Override
    public String updateEquipmentGeneration(InforContext context, EquipmentGenerationEntity equipmentGeneration) throws InforException {
        return equipmentGeneration.getEquipmentGenerationCode();
    }

    @Override
    public String deleteEquipmentGeneration(InforContext context, String equipmentGenerationCode) throws InforException {
        return null;
    }

    @Override
    public String createEquipmentGenerationPreview(InforContext context, String equipmentGenerationCode) throws InforException {
        return null;
    }

    @Override
    public String generateEquipmentGeneration(InforContext context, String equipmentGenerationCode) throws InforException {
        return null;
    }

    @Override
    public EquipmentGenerationEntity readEquipmentGeneration(InforContext context, String equipmentGenerationCode) throws InforException {
        return null;
    }

    @Override
    public EquipmentGenerationEntity readEquipmentGenerationDefault(InforContext context, String equipmentGenerationCode) throws InforException {
        return null;
    }
}
