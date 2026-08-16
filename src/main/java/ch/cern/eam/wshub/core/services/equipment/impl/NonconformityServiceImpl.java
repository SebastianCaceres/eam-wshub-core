package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.entities.EntityOrganizationCodePair;
import ch.cern.eam.wshub.core.services.equipment.NonconformityService;
import ch.cern.eam.wshub.core.services.equipment.entities.NonConformity;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.repositories.NonConformityRepository;
import java.util.Optional;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isEmpty;

public class NonconformityServiceImpl implements NonconformityService {

    private ApplicationData applicationData;

    private Tools tools;

    private NonConformityRepository nonConformityRepository;

    public NonconformityServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public NonconformityServiceImpl(ApplicationData applicationData, Tools tools, NonConformityRepository nonConformityRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.nonConformityRepository = nonConformityRepository;
    }

    @Override
    public NonConformity readNonconformityDefault(InforContext context, String organization) throws InforException {
        NonConformity ncr = new NonConformity();
        return ncr;
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
