package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.repositories.EquipmentRepository;
import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.AssetService;
import ch.cern.eam.wshub.core.services.equipment.entities.Equipment;
import ch.cern.eam.wshub.core.services.userdefinedscreens.UserDefinedListService;
import ch.cern.eam.wshub.core.services.userdefinedscreens.entities.EntityId;
import ch.cern.eam.wshub.core.services.userdefinedscreens.impl.UserDefinedListServiceImpl;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.*;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class AssetServiceImpl implements AssetService {

    private Tools tools;

    private ApplicationData applicationData;

    private UserDefinedListService userDefinedListService;

    private EquipmentRepository equipmentRepository;

    public AssetServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public AssetServiceImpl(ApplicationData applicationData, Tools tools, EquipmentRepository equipmentRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment readAssetDefault(InforContext context, String organization) throws InforException {
        Equipment asset = new Equipment();
        asset.setOrganization(organization);
        asset.setTypeCode("A");
        return asset;
    }

    public Equipment readAsset(InforContext context, String assetCode, String organization) throws InforException {
        return equipmentRepository.findById(assetCode).orElse(null);
    }

    public String updateAsset(InforContext context, Equipment assetParam) throws InforException {
        assetParam.setSystemTypeCode("A");
        if (assetParam.getCode() != null && !equipmentRepository.existsById(assetParam.getCode())) {
            throw tools.generateFault("Asset not found: " + assetParam.getCode());
        }
        // Status 'D' (Hors service definitif) nullifies parent hierarchy
        if ("D".equalsIgnoreCase(assetParam.getStatusCode()) || "D".equalsIgnoreCase(assetParam.getSystemStatusCode())) {
            assetParam.setHierarchyAssetCode(null);
            assetParam.setHierarchyPositionCode(null);
            assetParam.setHierarchySystemCode(null);
        }
        Equipment saved = equipmentRepository.save(assetParam);
        return saved.getCode();
    }

    public String createAsset(InforContext context, Equipment assetParam) throws InforException {
        assetParam.setSystemTypeCode("A");
        if (assetParam.getCode() == null || assetParam.getCode().trim().isEmpty()) {
            assetParam.setCode("AST-" + (System.currentTimeMillis() / 1000));
        }
        if (assetParam.getDescription() == null || assetParam.getDescription().trim().isEmpty()) {
            throw tools.generateFault("Asset description is required");
        }
        if (assetParam.getComissionDate() == null) {
            assetParam.setComissionDate(new java.util.Date());
        }
        Equipment saved = equipmentRepository.save(assetParam);
        return saved.getCode();
    }

    public String deleteAsset(InforContext context, String assetCode, String organization) throws InforException {
        equipmentRepository.deleteById(assetCode);
        return assetCode;
    }
}
