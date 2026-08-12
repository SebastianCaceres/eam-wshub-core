package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.LinearReferenceService;
import ch.cern.eam.wshub.core.services.equipment.entities.LinearReference;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;

public class LinearReferenceServiceImpl implements LinearReferenceService {

    private Tools tools;

    private ApplicationData applicationData;

    public LinearReferenceServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    public String updateEquipmentLinearReference(InforContext context, LinearReference linearReference) throws InforException {
        return null;
        //
        // GET THE LINEAR REFERENCE ID
        //
        // UPDATE THE LINEAR REFERENCE
    }

    public String deleteEquipmentLinearReference(InforContext context, String linearReferenceID) throws InforException {
        return null;
        //
        //
    }

    public String createEquipmentLinearReference(InforContext context, LinearReference linearReference) throws InforException {
        return null;
        //
        //
    }
}
