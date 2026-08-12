package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.material.PartStoreService;
import ch.cern.eam.wshub.core.services.material.entities.PartStore;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import javax.xml.ws.Holder;

public class PartStoreServiceImpl implements PartStoreService {

    private Tools tools;

    private ApplicationData applicationData;

    public PartStoreServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    public String updatePartStore(InforContext context, PartStore partStoreParam) throws InforException {
        return null;
        //
        // DO THE UPDATE NOW
    }

    public String addPartStore(InforContext context, PartStore partStoreParam) throws InforException {
        return null;
    }
}
