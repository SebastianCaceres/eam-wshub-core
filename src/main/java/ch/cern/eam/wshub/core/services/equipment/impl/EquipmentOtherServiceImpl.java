package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.repositories.EquipmentDepreciationRepository;
import ch.cern.eam.wshub.core.services.equipment.EquipmentOtherService;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentCampaign;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentDepreciation;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import net.datastream.schemas.mp_entities.campaignequipment_001.CampaignEquipment;
import net.datastream.schemas.mp_entities.depreciation_001.Depreciation;
import net.datastream.schemas.mp_entities.depreciation_001.RemainingUsefulLife;
import net.datastream.schemas.mp_entities.depreciationdefault_001.DepreciationDefault;
import net.datastream.schemas.mp_fields.*;
import net.datastream.schemas.mp_functions.SessionType;
import net.datastream.schemas.mp_functions.mp3015_001.MP3015_GetDepreciationDefault_001;
import net.datastream.schemas.mp_functions.mp3016_001.MP3016_GetDepreciation_001;
import net.datastream.schemas.mp_functions.mp3017_001.MP3017_AddDepreciation_001;
import net.datastream.schemas.mp_functions.mp3018_001.MP3018_SyncDepreciation_001;
import net.datastream.schemas.mp_functions.mp3291_001.ChangeEquipmentNumber;
import net.datastream.schemas.mp_functions.mp3291_001.MP3291_ChangeEquipmentNumber_001;
import net.datastream.schemas.mp_functions.mp5039_001.MP5039_AddCampaignEquipment_001;
import net.datastream.schemas.mp_results.mp3016_001.MP3016_GetDepreciation_001_Result;
import net.datastream.wsdls.inforws.InforWebServicesPT;
import javax.persistence.EntityManager;
import javax.xml.ws.Holder;
import java.math.BigDecimal;
import java.math.BigInteger;

public class EquipmentOtherServiceImpl implements EquipmentOtherService {

    private Tools tools;

    private InforWebServicesPT inforws;

    private ApplicationData applicationData;

    private EquipmentDepreciationRepository equipmentDepreciationRepository;

    public EquipmentOtherServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient, EquipmentDepreciationRepository equipmentDepreciationRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforws = inforWebServicesToolkitClient;
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
        //
        // GET THE DEPRECIATION VALUE FIRST
        //
        if (equipmentDepreciation.getDepreciationPK() == null) {
            if (equipmentDepreciation.getEquipmentCode() == null) {
                throw tools.generateFault("Equipment Code is mandatory field");
            }
            if (equipmentDepreciationRepository != null) {
                java.util.List<EquipmentDepreciation> results = equipmentDepreciationRepository.findByEquipmentCode(equipmentDepreciation.getEquipmentCode().trim().toUpperCase());
                if (!results.isEmpty()) {
                    equipmentDepreciation.setDepreciationPK(results.get(0).getDepreciationPK());
                } else {
                    throw tools.generateFault("Couldn't fetch depreciation record for this equipment.");
                }
            } else {
                EntityManager em = tools.getEntityManager();
                try {
                    equipmentDepreciation.setDepreciationPK(em.createNamedQuery(EquipmentDepreciation.GETDEPRECIATION, EquipmentDepreciation.class).setParameter("equipmentCode", equipmentDepreciation.getEquipmentCode().trim().toUpperCase()).getSingleResult().getDepreciationPK());
                } catch (Exception e) {
                    throw tools.generateFault("Couldn't fetch depreciation record for this equipment.");
                } finally {
                    em.close();
                }
            }
        }
        MP3016_GetDepreciation_001 getdep = new MP3016_GetDepreciation_001();
        getdep.setDEPRECIATIONPK(tools.getDataTypeTools().encodeQuantity(equipmentDepreciation.getDepreciationPK(), "Depreciation PK"));
        MP3016_GetDepreciation_001_Result result = tools.performInforOperation(context, inforws::getDepreciationOp, getdep);
        //
        // UPDATE DEPRECIATION
        //
        Depreciation depreciation = tools.getInforFieldTools().transformWSHubObject(result.getResultData().getDepreciation(), equipmentDepreciation, context);
        // ORIGINAL VALUE
        if (equipmentDepreciation.getOriginalValue() != null) {
            depreciation.setORIGINALVALUE(tools.getDataTypeTools().encodeAmount(equipmentDepreciation.getOriginalValue(), "Original Value"));
        }
        // RESIDUAL VALUE
        if (equipmentDepreciation.getResidualValue() != null) {
            depreciation.setRESIDUALVALUE(tools.getDataTypeTools().encodeAmount(equipmentDepreciation.getResidualValue(), "Residual Value"));
        }
        // ESTIMATED USEFUL LIFE
        if (equipmentDepreciation.getEstimatedUsefulLifeUOM() != null) {
            depreciation.getRemainingUsefulLife().setUOMID(new UOMID_Type());
            depreciation.getRemainingUsefulLife().getUOMID().setUOMCODE(equipmentDepreciation.getEstimatedUsefulLifeUOM().toUpperCase());
            BigDecimal amount = tools.getDataTypeTools().decodeAmount(depreciation.getRemainingUsefulLife().getESTIMATEDLIFE());
            depreciation.getRemainingUsefulLife().setESTIMATEDLIFE(tools.getDataTypeTools().encodeAmount(amount, "Estiamted Life Time"));
        }
        if (equipmentDepreciation.getEstimatedUsefulLife() != null) {
            depreciation.getRemainingUsefulLife().setESTIMATEDLIFE(tools.getDataTypeTools().encodeAmount(equipmentDepreciation.getEstimatedUsefulLife(), "Estiamted Life Time"));
        }
        // DEPRECIATION METHOD
        if (equipmentDepreciation.getDepreciationMethod() != null) {
            depreciation.setDEPRECIATIONMETHOD(equipmentDepreciation.getDepreciationMethod());
        }
        // DEPRECIATION TYPE
        if (equipmentDepreciation.getDepreciationType() != null) {
            depreciation.setEQUIPMENTDEPTYPE(equipmentDepreciation.getDepreciationType().toUpperCase());
        }
        // DEPRECIATION CATEGORY
        if (equipmentDepreciation.getDepreciationCategory() != null) {
            depreciation.setDEPRECIATIONCATEGORYID(new DEPRECIATIONCATEGORYID_Type());
            depreciation.getDEPRECIATIONCATEGORYID().setDEPRECIATIONCATEGORYCODE(equipmentDepreciation.getDepreciationCategory());
        }
        // FROM DATE
        if (equipmentDepreciation.getFromDate() != null) {
            depreciation.setFROMDATE(tools.getDataTypeTools().formatDate(equipmentDepreciation.getFromDate(), "From Date"));
        }
        // CHANGE VALUE
        if (equipmentDepreciation.getChangeValue() != null) {
            depreciation.setCHANGEVALUE(tools.getDataTypeTools().encodeAmount(equipmentDepreciation.getChangeValue(), "Change Value"));
        }
        // CHANGE LIFE
        if (equipmentDepreciation.getChangeLife() != null) {
            depreciation.setCHANGELIFE(tools.getDataTypeTools().encodeAmount(equipmentDepreciation.getChangeLife(), "Change Life"));
        }
        // CHANGE ESTIMATED LIFETIME OUTPUT
        if (equipmentDepreciation.getChangeEstimatedLifetimeOutput() != null) {
            depreciation.setCHANGEESTLIFETIMEOUTPUT(tools.getDataTypeTools().encodeAmount(equipmentDepreciation.getChangeEstimatedLifetimeOutput(), "Change Estimated Lifetime Output"));
        }
        MP3018_SyncDepreciation_001 syncdep = new MP3018_SyncDepreciation_001();
        syncdep.setDepreciation(depreciation);
        tools.performInforOperation(context, inforws::syncDepreciationOp, syncdep);
        return "OK";
    }

    public String updateEquipmentCode(InforContext context, String equipmentCode, String equipmentNewCode, String equipmentType) throws InforException {
        MP3291_ChangeEquipmentNumber_001 changeeqpnum = new MP3291_ChangeEquipmentNumber_001();
        changeeqpnum.setChangeEquipmentNumber(new ChangeEquipmentNumber());
        //
        changeeqpnum.getChangeEquipmentNumber().setCURRENTEQUIPMENTID(new EQUIPMENTID_Type());
        changeeqpnum.getChangeEquipmentNumber().getCURRENTEQUIPMENTID().setORGANIZATIONID(tools.getOrganization(context));
        changeeqpnum.getChangeEquipmentNumber().getCURRENTEQUIPMENTID().setEQUIPMENTCODE(equipmentCode);
        //
        changeeqpnum.getChangeEquipmentNumber().setNEWEQUIPMENTID(new EQUIPMENTID_Type());
        changeeqpnum.getChangeEquipmentNumber().getNEWEQUIPMENTID().setORGANIZATIONID(tools.getOrganization(context));
        changeeqpnum.getChangeEquipmentNumber().getNEWEQUIPMENTID().setEQUIPMENTCODE(equipmentNewCode);
        tools.performInforOperation(context, inforws::changeEquipmentNumberOp, changeeqpnum);
        return "OK";
    }

    public String createEquipmentCampaign(InforContext context, EquipmentCampaign equipmentCampaign) throws InforException {
        CampaignEquipment campaignEquipment = new CampaignEquipment();
        campaignEquipment.setCAMPAIGNEQUIPMENTID(new CAMPAIGNEQUIPMENTID_Type());
        //
        // CAMPAIGN ID
        //
        campaignEquipment.getCAMPAIGNEQUIPMENTID().setCAMPAIGNID(new CAMPAIGNID_Type());
        campaignEquipment.getCAMPAIGNEQUIPMENTID().getCAMPAIGNID().setCAMPAIGNCODE(equipmentCampaign.getCampaign());
        campaignEquipment.getCAMPAIGNEQUIPMENTID().getCAMPAIGNID().setORGANIZATIONID(tools.getOrganization(context));
        //
        //
        //
        campaignEquipment.getCAMPAIGNEQUIPMENTID().setEQUIPMENTID(new EQUIPMENTID_Type());
        campaignEquipment.getCAMPAIGNEQUIPMENTID().getEQUIPMENTID().setEQUIPMENTCODE(equipmentCampaign.getEquipment());
        campaignEquipment.getCAMPAIGNEQUIPMENTID().getEQUIPMENTID().setORGANIZATIONID(tools.getOrganization(context));
        MP5039_AddCampaignEquipment_001 addCampaignEquipment = new MP5039_AddCampaignEquipment_001();
        addCampaignEquipment.setCampaignEquipment(campaignEquipment);
        tools.performInforOperation(context, inforws::addCampaignEquipmentOp, addCampaignEquipment);
        return null;
    }
}
