package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.material.PartManufacturerService;
import ch.cern.eam.wshub.core.services.material.entities.PartManufacturer;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.annotations.BooleanType;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import javax.xml.ws.Holder;

public class PartManufacturerServiceImpl implements PartManufacturerService {

    private Tools tools;

    private ApplicationData applicationData;

    public PartManufacturerServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    public String addPartManufacturer(InforContext context, PartManufacturer partManufacturerParam) throws InforException {
        return null;
        //
        // CALL INFOR WS
    }

    public String updatePartManufacturer(InforContext context, PartManufacturer partManufacturerParam) throws InforException {
        return null;
        //CALL INFOR WS
    }

    public String deletePartManufacturer(InforContext context, PartManufacturer partManufacturerParam) throws InforException {
        return null;
    }
}
