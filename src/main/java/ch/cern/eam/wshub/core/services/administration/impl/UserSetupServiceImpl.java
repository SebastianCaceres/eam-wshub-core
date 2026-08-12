package ch.cern.eam.wshub.core.services.administration.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.administration.UserSetupService;
import ch.cern.eam.wshub.core.services.entities.BatchResponse;
import ch.cern.eam.wshub.core.services.administration.entities.EAMUser;
import ch.cern.eam.wshub.core.services.entities.Department;
import ch.cern.eam.wshub.core.services.grids.GridsService;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequest;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequestResult;
import ch.cern.eam.wshub.core.services.grids.impl.GridsServiceImpl;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import static ch.cern.eam.wshub.core.tools.GridTools.extractSingleResult;
import static ch.cern.eam.wshub.core.tools.GridTools.convertGridResultToMap;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.repositories.EAMUserRepository;
import javax.xml.ws.Holder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.toCodeString;

public class UserSetupServiceImpl implements UserSetupService {

    private Tools tools;

    private ApplicationData applicationData;

    private GridsService gridsService;

    private EAMUserRepository eamUserRepository;

    public UserSetupServiceImpl(ApplicationData applicationData, Tools tools) {
    }

    public UserSetupServiceImpl(ApplicationData applicationData, Tools tools, EAMUserRepository eamUserRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.eamUserRepository = eamUserRepository;
    }

    public String login(InforContext context, String userCode) throws InforException {
        return userCode;
    }

    public EAMUser readUserSetup(InforContext context, String userCode) throws InforException {
        return null;
    }

    public String createUserSetup(InforContext context, EAMUser userParam) throws InforException {
        EAMUser saved = eamUserRepository.save(userParam);
        return saved.getUserCode();
    }

    public String updateUserSetup(InforContext context, EAMUser userParam) throws InforException {
        EAMUser saved = eamUserRepository.save(userParam);
        return saved.getUserCode();
    }

    public BatchResponse<String> updateUserSetupBatch(InforContext context, List<EAMUser> eamUsers) throws InforException {
        List<Callable<String>> callableList = eamUsers.stream().<Callable<String>>map(eamUser -> () -> updateUserSetup(context, eamUser)).collect(Collectors.toList());
        return tools.processCallables(callableList);
    }

    public String deleteUserSetup(InforContext context, String userCode) throws InforException {
        eamUserRepository.deleteById(userCode);
        return userCode;
    }
}
