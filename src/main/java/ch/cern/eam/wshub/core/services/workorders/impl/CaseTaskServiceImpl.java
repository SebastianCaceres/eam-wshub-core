package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.workorders.CaseTaskService;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.services.workorders.entities.InforCaseTask;
import ch.cern.eam.wshub.core.repositories.InforCaseTaskRepository;
import java.util.Optional;

public class CaseTaskServiceImpl implements CaseTaskService {

    private Tools tools;

    private ApplicationData applicationData;

    private InforCaseTaskRepository inforCaseTaskRepository;

    public CaseTaskServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public CaseTaskServiceImpl(ApplicationData applicationData, Tools tools, InforCaseTaskRepository inforCaseTaskRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.inforCaseTaskRepository = inforCaseTaskRepository;
    }

    public InforCaseTask readCaseTask(InforContext context, String caseTaskID) throws InforException {
        return inforCaseTaskRepository.findById(caseTaskID).orElse(null);
    }

    public String createCaseTask(InforContext context, InforCaseTask caseTaskMT) throws InforException {
        InforCaseTask saved = inforCaseTaskRepository.save(caseTaskMT);
        return saved.getTaskCode();
    }

    public String updateCaseTask(InforContext context, InforCaseTask caseTaskMT) throws InforException {
        InforCaseTask saved = inforCaseTaskRepository.save(caseTaskMT);
        return saved.getTaskCode();
        //
        // Fetch Case Task
        //
        // UPDATE
    }

    public String deleteCaseTask(InforContext context, String caseTaskID) throws InforException {
        inforCaseTaskRepository.deleteById(caseTaskID);
        return caseTaskID;
    }
}
