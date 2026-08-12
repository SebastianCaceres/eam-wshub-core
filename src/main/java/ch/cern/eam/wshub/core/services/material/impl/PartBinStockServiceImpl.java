package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.material.PartBinStockService;
import ch.cern.eam.wshub.core.services.material.entities.PartStock;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import javax.xml.ws.Holder;

public class PartBinStockServiceImpl implements PartBinStockService {

    private Tools tools;

    private ApplicationData applicationData;

    public PartBinStockServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    public String addPartStock(InforContext context, PartStock partStockParam) throws InforException {
        return null;
    }

    public String updatePartStock(InforContext context, PartStock partStockParam) throws InforException {
        return null;
        //
        // GET IT FIRST
        //
        // UPDATE AFTERWARDS
    }
}
