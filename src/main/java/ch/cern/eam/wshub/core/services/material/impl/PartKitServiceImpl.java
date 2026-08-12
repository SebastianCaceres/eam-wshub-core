package ch.cern.eam.wshub.core.services.material.impl;

import java.math.BigDecimal;
import javax.xml.ws.Holder;
import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.material.PartKitService;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import org.openapplications.oagis_segments.QUANTITY;
import ch.cern.eam.wshub.core.services.material.entities.BuildKitParam;
import ch.cern.eam.wshub.core.services.material.entities.PartKitTemplate;

public class PartKitServiceImpl implements PartKitService {

    private Tools tools;

    private ApplicationData applicationData;

    public PartKitServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public String addPartKitTemplate(InforContext context, PartKitTemplate partKitParam) throws InforException {
        return null;
    }

    public String createKitSession(InforContext context, BuildKitParam buildKitParam) throws InforException {
        return null;
        // CREATE KIT SESSION
    }

    public String buildKit(InforContext context, String kitSessionId) throws InforException {
        return null;
    }
}
