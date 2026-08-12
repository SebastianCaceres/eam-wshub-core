package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.entities.EntityOrganizationCodePair;
import ch.cern.eam.wshub.core.services.equipment.NonconformityService;
import ch.cern.eam.wshub.core.services.equipment.entities.NonConformity;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import net.datastream.schemas.mp_fields.ORGANIZATIONID_Type;
import net.datastream.schemas.mp_fields.STANDARDENTITYID_Type;
import net.datastream.schemas.mp_functions.mp3396_001.MP3396_GetNonconformityDefault_001;
import net.datastream.schemas.mp_functions.mp3397_001.MP3397_AddNonconformity_001;
import net.datastream.schemas.mp_functions.mp3398_001.MP3398_SyncNonconformity_001;
import net.datastream.schemas.mp_functions.mp3399_001.MP3399_DeleteNonconformity_001;
import net.datastream.schemas.mp_functions.mp3400_001.MP3400_GetNonconformity_001;
import net.datastream.schemas.mp_results.mp3396_001.MP3396_GetNonconformityDefault_001_Result;
import net.datastream.schemas.mp_results.mp3397_001.MP3397_AddNonconformity_001_Result;
import net.datastream.schemas.mp_results.mp3398_001.MP3398_SyncNonconformity_001_Result;
import net.datastream.schemas.mp_results.mp3400_001.MP3400_GetNonconformity_001_Result;
import net.datastream.wsdls.inforws.InforWebServicesPT;
import ch.cern.eam.wshub.core.repositories.NonConformityRepository;
import java.util.Optional;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isEmpty;

public class NonconformityServiceImpl implements NonconformityService {

    private ApplicationData applicationData;

    private Tools tools;

    private InforWebServicesPT inforws;

    private NonConformityRepository nonConformityRepository;

    public NonconformityServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient) {
        this(applicationData, tools, inforWebServicesToolkitClient, null);
    }

    public NonconformityServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient, NonConformityRepository nonConformityRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforws = inforWebServicesToolkitClient;
        this.nonConformityRepository = nonConformityRepository;
    }

    @Override
    public NonConformity readNonconformityDefault(InforContext context, String organization) throws InforException {
        return nonConformityRepository.findById(organization).orElse(null);
    }

    @Override
    public String createNonconformity(InforContext context, NonConformity nonconformityParam) throws InforException {
        NonConformity saved = nonConformityRepository.save(nonconformityParam);
        return saved.getCode();
    }

    @Override
    public NonConformity readNonconformity(InforContext context, String nonconformityCode) throws InforException {
        String code = Tools.extractEntityOrganizationCodePair(nonconformityCode).getEntityCode();
        return nonConformityRepository.findById(code).orElse(null);
    }

    private net.datastream.schemas.mp_entities.nonconformity_001.Nonconformity readNonconformityInfor(InforContext context, String nonconformityCode) throws InforException {
        MP3400_GetNonconformity_001 getNonconformity = new MP3400_GetNonconformity_001();
        EntityOrganizationCodePair entityOrganizationCodePair = Tools.extractEntityOrganizationCodePair(nonconformityCode);
        getNonconformity.setNONCONFORMITYID(new STANDARDENTITYID_Type());
        getNonconformity.getNONCONFORMITYID().setSTANDARDENTITYCODE(entityOrganizationCodePair.getEntityCode());
        getNonconformity.getNONCONFORMITYID().setORGANIZATIONID(tools.getOrganization(context, entityOrganizationCodePair.getOrganizationCode()));
        MP3400_GetNonconformity_001_Result result = tools.performInforOperation(context, inforws::getNonconformityOp, getNonconformity);
        return result.getResultData().getNonconformity();
    }

    @Override
    public String updateNonconformity(InforContext context, NonConformity nonconformityParam) throws InforException {
        NonConformity saved = nonConformityRepository.save(nonconformityParam);
        return saved.getCode();
    }

    @Override
    public String deleteNonconformity(InforContext context, String nonconformityCode) throws InforException {
        nonConformityRepository.deleteById(nonconformityCode);
        return nonconformityCode;
    }
}
