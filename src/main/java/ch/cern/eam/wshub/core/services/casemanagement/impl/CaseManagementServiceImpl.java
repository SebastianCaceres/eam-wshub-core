package ch.cern.eam.wshub.core.services.casemanagement.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.casemanagement.CaseManagementService;
import ch.cern.eam.wshub.core.services.casemanagement.entities.EAMCaseManagement;
import ch.cern.eam.wshub.core.services.entities.CustomField;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CaseManagementServiceImpl implements CaseManagementService {

    private Tools tools;

    private ApplicationData applicationData;

    public CaseManagementServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    public EAMCaseManagement readCase(InforContext context, String caseCode) throws InforException {
        return null;
    }

    public String createCase(InforContext context, EAMCaseManagement eamCaseManagement) throws InforException {
        return null;
    }

    public String deleteCase(InforContext context, String caseCode) throws InforException {
        return null;
    }

    public synchronized String updateCase(InforContext context, EAMCaseManagement eamCaseManagement) throws InforException {
        return null;
    }
}
