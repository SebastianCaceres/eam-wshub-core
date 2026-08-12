package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.EquipmentWarrantyCoverageService;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentWarranty;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.annotations.BooleanType;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import net.datastream.schemas.mp_entities.warrantycoverage_001.CoverageByDate;
import net.datastream.schemas.mp_fields.EQUIPMENTID_Type;
import net.datastream.schemas.mp_fields.WARRANTYID_Type;
import net.datastream.schemas.mp_functions.SessionType;
import net.datastream.schemas.mp_functions.mp0344_001.MP0344_AddWarrantyCoverage_001;
import net.datastream.schemas.mp_functions.mp0345_001.MP0345_SyncWarrantyCoverage_001;
import net.datastream.schemas.mp_functions.mp3238_001.MP3238_GetWarrantyCoverage_001;
import net.datastream.schemas.mp_results.mp0344_001.MP0344_AddWarrantyCoverage_001_Result;
import net.datastream.schemas.mp_results.mp0345_001.MP0345_SyncWarrantyCoverage_001_Result;
import net.datastream.schemas.mp_results.mp3238_001.MP3238_GetWarrantyCoverage_001_Result;
import net.datastream.wsdls.inforws.InforWebServicesPT;
import ch.cern.eam.wshub.core.repositories.EquipmentWarrantyRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.xml.ws.Holder;

public class EquipmentWarrantyCoverageServiceImpl implements EquipmentWarrantyCoverageService {

    private Tools tools;

    private InforWebServicesPT inforws;

    private ApplicationData applicationData;

    private EquipmentWarrantyRepository equipmentWarrantyRepository;

    public EquipmentWarrantyCoverageServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient) {
        this(applicationData, tools, inforWebServicesToolkitClient, null);
    }

    public EquipmentWarrantyCoverageServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient, EquipmentWarrantyRepository equipmentWarrantyRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforws = inforWebServicesToolkitClient;
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
