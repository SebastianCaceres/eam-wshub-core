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
import net.datastream.schemas.mp_entities.assetequipment_001.*;
import net.datastream.schemas.mp_fields.*;
import net.datastream.schemas.mp_functions.mp0301_001.MP0301_AddAssetEquipment_001;
import net.datastream.schemas.mp_functions.mp0302_001.MP0302_GetAssetEquipment_001;
import net.datastream.schemas.mp_functions.mp0303_001.MP0303_SyncAssetEquipment_001;
import net.datastream.schemas.mp_functions.mp0304_001.MP0304_DeleteAssetEquipment_001;
import net.datastream.schemas.mp_functions.mp0305_001.MP0305_GetAssetEquipmentDefault_001;
import net.datastream.schemas.mp_functions.mp0327_001.MP0327_GetAssetParentHierarchy_001;
import net.datastream.schemas.mp_results.mp0301_001.MP0301_AddAssetEquipment_001_Result;
import net.datastream.schemas.mp_results.mp0302_001.MP0302_GetAssetEquipment_001_Result;
import net.datastream.schemas.mp_results.mp0305_001.MP0305_GetAssetEquipmentDefault_001_Result;
import net.datastream.schemas.mp_results.mp0327_001.MP0327_GetAssetParentHierarchy_001_Result;
import net.datastream.wsdls.inforws.InforWebServicesPT;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.*;
import static ch.cern.eam.wshub.core.services.equipment.impl.EquipmentHierarchyTools.*;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class AssetServiceImpl implements AssetService {

    private Tools tools;

    private InforWebServicesPT inforws;

    private ApplicationData applicationData;

    private UserDefinedListService userDefinedListService;

    private EquipmentRepository equipmentRepository;

    public AssetServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient) {
        this(applicationData, tools, inforWebServicesToolkitClient, null);
    }

    public AssetServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient, EquipmentRepository equipmentRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforws = inforWebServicesToolkitClient;
        this.equipmentRepository = equipmentRepository;
        this.userDefinedListService = new UserDefinedListServiceImpl(applicationData, tools, inforWebServicesToolkitClient);
    }

    public Equipment readAssetDefault(InforContext context, String organization) throws InforException {
        return equipmentRepository.findById(organization).orElse(null);
    }

    public Equipment readAsset(InforContext context, String assetCode, String organization) throws InforException {
        return equipmentRepository.findById(assetCode).orElse(null);
    }

    private AssetParentHierarchy readInforAssetHierarchy(InforContext context, String assetCode, String organization) throws InforException {
        MP0327_GetAssetParentHierarchy_001 getassetph = new MP0327_GetAssetParentHierarchy_001();
        getassetph.setASSETID(new EQUIPMENTID_Type());
        getassetph.getASSETID().setORGANIZATIONID(tools.getOrganization(context, organization));
        getassetph.getASSETID().setEQUIPMENTCODE(assetCode);
        MP0327_GetAssetParentHierarchy_001_Result result = tools.performInforOperation(context, inforws::getAssetParentHierarchyOp, getassetph);
        return result.getResultData().getAssetParentHierarchy();
    }

    private AssetEquipment readInforAsset(InforContext context, String assetCode, String organization) throws InforException {
        MP0302_GetAssetEquipment_001 getAsset = new MP0302_GetAssetEquipment_001();
        getAsset.setASSETID(new EQUIPMENTID_Type());
        getAsset.getASSETID().setORGANIZATIONID(tools.getOrganization(context, organization));
        getAsset.getASSETID().setEQUIPMENTCODE(assetCode);
        MP0302_GetAssetEquipment_001_Result getAssetResult = tools.performInforOperation(context, inforws::getAssetEquipmentOp, getAsset);
        getAssetResult.getResultData().getAssetEquipment().setAssetParentHierarchy(readInforAssetHierarchy(context, assetCode, organization));
        return getAssetResult.getResultData().getAssetEquipment();
    }

    public String updateAsset(InforContext context, Equipment assetParam) throws InforException {
        Equipment saved = equipmentRepository.save(assetParam);
        return saved.getCode();
        //
        // UPDATE EQUIPMENT
    }

    public String createAsset(InforContext context, Equipment assetParam) throws InforException {
        Equipment saved = equipmentRepository.save(assetParam);
        return saved.getCode();
    }

    public String deleteAsset(InforContext context, String assetCode, String organization) throws InforException {
        equipmentRepository.deleteById(assetCode);
        return assetCode;
    }

    private void initializeAssetObject(AssetEquipment assetInfor, Equipment assetParam, InforContext context) throws InforException {
        // == null means Asset creation
        if (assetInfor.getASSETID() == null) {
            assetInfor.setASSETID(new EQUIPMENTID_Type());
            assetInfor.getASSETID().setORGANIZATIONID(tools.getOrganization(context, assetParam.getOrganization()));
            assetInfor.getASSETID().setEQUIPMENTCODE(assetParam.getCode().toUpperCase().trim());
        }
        if (assetParam.getDescription() != null) {
            assetInfor.getASSETID().setDESCRIPTION(assetParam.getDescription());
        }
        // HIERARCHY
        if (assetParam.getHierarchyAssetCode() != null || assetParam.getHierarchyPositionCode() != null || assetParam.getHierarchyPrimarySystemCode() != null || assetParam.getHierarchyLocationCode() != null) {
            try {
                initializeAssetHierarchy(assetInfor, assetParam, context);
            } catch (Exception e) {
                tools.log(Level.SEVERE, e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void initializeAssetHierarchy(AssetEquipment assetInfor, Equipment assetParam, InforContext context) throws InforException {
        AssetParentHierarchy hierarchy = new AssetParentHierarchy();
        hierarchy.setASSETID(new EQUIPMENTID_Type());
        hierarchy.getASSETID().setEQUIPMENTCODE(assetParam.getCode());
        hierarchy.getASSETID().setORGANIZATIONID(tools.getOrganization(context, assetParam.getOrganization()));
        hierarchy.setTYPE(new TYPE_Type());
        hierarchy.getTYPE().setTYPECODE("A");
        // Fetch all possible parent types that are present in only one object that indicates the current hierarchy type
        ASSETPARENT_Type assetParent = readAssetParent(assetInfor.getAssetParentHierarchy());
        POSITIONPARENT_Type positionParent = readPositionParent(assetInfor.getAssetParentHierarchy());
        SYSTEMPARENT_Type primarySystemParent = readPrimarySystemParent(assetInfor.getAssetParentHierarchy());
        LOCATIONPARENT_Type locationParent = readLocationParent(assetInfor.getAssetParentHierarchy());
        List<SYSTEMPARENT_Type> systemParents = readSystemsParent(assetInfor.getAssetParentHierarchy());
        HIERARCHY_TYPE currentHierarchyType = readHierarchyType(assetInfor.getAssetParentHierarchy());
        // Incorporate user changes into the parent types
        assetParent = createAssetParent(tools.getOrganizationCode(context, assetParam.getHierarchyAssetOrg()), assetParam.getHierarchyAssetCode(), assetParam.getHierarchyAssetCostRollUp(), assetParent);
        positionParent = createPositionParent(tools.getOrganizationCode(context, assetParam.getHierarchyPositionOrg()), assetParam.getHierarchyPositionCode(), assetParam.getHierarchyPositionCostRollUp(), positionParent);
        primarySystemParent = createPrimarySystemParent(tools.getOrganizationCode(context, assetParam.getHierarchyPrimarySystemOrg()), assetParam.getHierarchyPrimarySystemCode(), assetParam.getHierarchyPrimarySystemCostRollUp(), primarySystemParent);
        locationParent = createLocationParent(tools.getOrganizationCode(context), assetParam.getHierarchyLocationCode(), locationParent);
        // Init new hierarchy
        switch(getNewHierarchyType(assetParam, currentHierarchyType)) {
            case ASSET_DEP:
                hierarchy.setAssetDependency(createAssetDependencyForAsset(assetParent, positionParent, primarySystemParent, systemParents));
                break;
            case POSITION_DEP:
                hierarchy.setPositionDependency(createPositionDependencyForAsset(assetParent, positionParent, primarySystemParent, systemParents));
                break;
            case PRIM_SYSTEM_DEP:
                hierarchy.setPrimarySystemDependency(createPrimarySystemDependencyForAsset(assetParent, positionParent, primarySystemParent, systemParents));
                break;
            case LOCATION_DEP:
                hierarchy.setLocationDependency(createLocationDependencyForAsset(assetParent, positionParent, primarySystemParent, systemParents, locationParent));
                break;
            default:
                hierarchy.setNonDependentParents(createNonDependentParentsForAsset(assetParent, positionParent, primarySystemParent, systemParents));
        }
        assetInfor.setAssetParentHierarchy(hierarchy);
    }
}
