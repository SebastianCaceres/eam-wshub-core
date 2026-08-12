package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.workorders.StandardWorkOrderChildService;
import ch.cern.eam.wshub.core.services.workorders.entities.StandardWorkOrder;
import ch.cern.eam.wshub.core.services.workorders.entities.StandardWorkOrderChild;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;

public class StandardWorkOrderChildServiceImpl implements StandardWorkOrderChildService {

    private Tools tools;

    private ApplicationData applicationData;

    public StandardWorkOrderChildServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public StandardWorkOrder readStandardWorkOrderChildDefault(InforContext context, String standardWOCode) throws InforException {
        return null;
    }

    @Override
    public StandardWorkOrderChild createStandardWorkOrderChild(InforContext context, StandardWorkOrderChild standardWorkOrderChild) throws InforException {
        return null;
    }

    @Override
    public StandardWorkOrderChild readStandardWorkOrderChild(InforContext context, StandardWorkOrderChild standardWorkOrderChild) throws InforException {
        return null;
    }

    @Override
    public StandardWorkOrderChild updateStandardWorkOrderChild(InforContext context, StandardWorkOrderChild standardWorkOrderChild) throws InforException {
        return null;
    }

    @Override
    public StandardWorkOrderChild deleteStandardWorkOrderChild(InforContext context, StandardWorkOrderChild standardWorkOrderChild) throws InforException {
        return null;
    }
}
