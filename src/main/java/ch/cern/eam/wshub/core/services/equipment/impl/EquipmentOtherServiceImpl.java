package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.repositories.EquipmentDepreciationRepository;
import ch.cern.eam.wshub.core.services.equipment.EquipmentOtherService;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentCampaign;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentDepreciation;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import javax.persistence.EntityManager;
import javax.xml.ws.Holder;
import java.math.BigDecimal;
import java.math.BigInteger;

public class EquipmentOtherServiceImpl implements EquipmentOtherService {

    private Tools tools;

    private ApplicationData applicationData;

    private EquipmentDepreciationRepository equipmentDepreciationRepository;

    public EquipmentOtherServiceImpl(ApplicationData applicationData, Tools tools, EquipmentDepreciationRepository equipmentDepreciationRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.equipmentDepreciationRepository = equipmentDepreciationRepository;
    }

    public String createEquipmentDepreciation(InforContext context, EquipmentDepreciation equipmentDepreciation) throws InforException {
        EquipmentDepreciation saved = equipmentDepreciationRepository.save(equipmentDepreciation);
        return saved.getEquipmentCode();
        // DEPRECIATION TYPE
        // Possible types: select * from r5descriptions where des_entity =
    }

    @Override
    public EquipmentDepreciation readEquipmentDepreciation(InforContext context, String equipmentCode) throws InforException {
        java.util.List<EquipmentDepreciation> results = equipmentDepreciationRepository.findByEquipmentCode(equipmentCode.trim().toUpperCase());
        return !results.isEmpty() ? results.get(0) : null;
    }

    public String updateEquipmentDepreciation(InforContext context, EquipmentDepreciation equipmentDepreciation) throws InforException {
        return null;
        //
        // GET THE DEPRECIATION VALUE FIRST
        //
        // UPDATE DEPRECIATION
    }

    public String updateEquipmentCode(InforContext context, String equipmentCode, String equipmentNewCode, String equipmentType) throws InforException {
        return null;
    }

    public String createEquipmentCampaign(InforContext context, EquipmentCampaign equipmentCampaign) throws InforException {
        return null;
        //
        // CAMPAIGN ID
        //
        //
    }
}
