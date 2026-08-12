package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.repositories.FindingRepository;
import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.NonConformityObservationService;
import ch.cern.eam.wshub.core.services.equipment.NonconformityService;
import ch.cern.eam.wshub.core.services.equipment.entities.NonConformityObservation;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;

public class NonConformityObservationServiceImpl implements NonConformityObservationService {

    private ApplicationData applicationData;

    private Tools tools;

    private NonconformityService nonconformityService;

    private FindingRepository findingRepository;

    public NonConformityObservationServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public NonConformityObservationServiceImpl(ApplicationData applicationData, Tools tools, FindingRepository findingRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.findingRepository = findingRepository;
    }

    @Override
    public String createNonConformityObservation(InforContext context, NonConformityObservation nonConformityObservation) throws InforException {
        return null;
    }

    @Override
    public NonConformityObservation readNonConformityObservation(InforContext context, String nonconformityObsPk) throws InforException {
        ch.cern.eam.wshub.core.services.workorders.entities.Finding finding = findingRepository.findById(nonconformityObsPk).orElse(null);
        if (finding != null) {
            NonConformityObservation obs = new NonConformityObservation();
            obs.setObservationPk(finding.getCode());
            obs.setDescription(finding.getDesc());
            return obs;
        }
        return null;
    }

    @Override
    public String updateNonConformityObservation(InforContext context, NonConformityObservation nonConformityObservation) throws InforException {
        return null;
    }

    @Override
    public String deleteNonConformityObservation(InforContext context, String number) throws InforException {
        findingRepository.deleteById(number);
        return number;
    }
}
