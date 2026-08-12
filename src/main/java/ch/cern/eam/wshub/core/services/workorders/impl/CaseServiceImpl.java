package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.workorders.CaseService;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.services.workorders.entities.InforCase;
import ch.cern.eam.wshub.core.repositories.InforCaseRepository;
import java.util.Optional;
import javax.xml.ws.Holder;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.toCodeString;

public class CaseServiceImpl implements CaseService {

    private Tools tools;

    private ApplicationData applicationData;

    private InforCaseRepository inforCaseRepository;

    public CaseServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public CaseServiceImpl(ApplicationData applicationData, Tools tools, InforCaseRepository inforCaseRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforCaseRepository = inforCaseRepository;
    }

    public InforCase readCase(InforContext context, String caseID) throws InforException {
        return inforCaseRepository.findById(caseID).orElse(null);
    }

    public String createCase(InforContext context, InforCase caseMT) throws InforException {
        InforCase saved = inforCaseRepository.save(caseMT);
        return saved.getCode();
    }

    public String deleteCase(InforContext context, String caseID) throws InforException {
        inforCaseRepository.deleteById(caseID);
        return caseID;
    }

    public synchronized String updateCase(InforContext context, InforCase caseMT) throws InforException {
        InforCase saved = inforCaseRepository.save(caseMT);
        return saved.getCode();
        //
        // FETCH IT FIRST
        //
        // INIT
        //
        // UPDATE
        //
    }
}
