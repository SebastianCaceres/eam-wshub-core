package ch.cern.eam.wshub.core.services.userdefinedscreens.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.userdefinedscreens.UserDefinedScreenService;
import ch.cern.eam.wshub.core.services.userdefinedscreens.entities.UDTRow;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.encodeAmount;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.encodeInforDate;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserDefinedScreenServiceImpl implements UserDefinedScreenService {

    private Tools tools;

    private ApplicationData applicationData;

    public UserDefinedScreenServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    public String createUserDefinedScreenRow(InforContext inforContext, String screenName, UDTRow udtRow) throws InforException {
        return null;
    }

    public String updateUserDefinedScreenRow(InforContext inforContext, String screenName, UDTRow fieldsToUpdate, UDTRow filters) throws InforException {
        return null;
    }

    public String deleteUserDefinedScreenRow(InforContext inforContext, String screenName, UDTRow udtRow) throws InforException {
        return null;
    }
    //
    // HELPER METHODS
}
