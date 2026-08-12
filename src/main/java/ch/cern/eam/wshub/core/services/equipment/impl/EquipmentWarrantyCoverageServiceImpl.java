package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.EquipmentWarrantyCoverageService;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentWarranty;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.annotations.BooleanType;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.repositories.EquipmentWarrantyRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EquipmentWarrantyCoverageServiceImpl implements EquipmentWarrantyCoverageService {

    private Tools tools;

    private ApplicationData applicationData;

    private EquipmentWarrantyRepository equipmentWarrantyRepository;

    public EquipmentWarrantyCoverageServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public EquipmentWarrantyCoverageServiceImpl(ApplicationData applicationData, Tools tools, EquipmentWarrantyRepository equipmentWarrantyRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.equipmentWarrantyRepository = equipmentWarrantyRepository;
    }

    public String createEquipmentWarrantyCoverage(InforContext context, EquipmentWarranty equipmentWarrantyParam) throws InforException {
        EquipmentWarranty saved = equipmentWarrantyRepository.save(equipmentWarrantyParam);
        return saved.getSequenceNumber();
    }

    public String updateEquipmentWarrantyCoverage(InforContext context, EquipmentWarranty equipmentWarrantyParam) throws InforException {
        EquipmentWarranty saved = equipmentWarrantyRepository.save(equipmentWarrantyParam);
        return saved.getSequenceNumber();
        //
        //
    }

    @Override
    public EquipmentWarranty readEquipmentWarranty(InforContext context, String equipmentCode, String warrantyCode) throws InforException {
        return equipmentWarrantyRepository.findByEquipmentCodeAndWarrantyCode(equipmentCode != null ? equipmentCode.trim().toUpperCase() : null, warrantyCode).orElse(null);
    }
}
