package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.workorders.InspectionService;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.services.workorders.entities.Aspect;
import ch.cern.eam.wshub.core.services.workorders.entities.AspectPoint;
import ch.cern.eam.wshub.core.services.workorders.entities.Point;
import java.math.BigDecimal;

public class InspectionServiceImpl implements InspectionService {

    private Tools tools;

    private ApplicationData applicationData;

    public InspectionServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    //
    //
    public String addAspect(InforContext context, Aspect aspect) throws InforException {
        return null;
    }
}
