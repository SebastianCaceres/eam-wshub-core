package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import ch.cern.eam.wshub.core.services.material.PurchaseOrdersService;
import ch.cern.eam.wshub.core.services.material.entities.PurchaseOrder;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.tools.InforException;

public class PurchaseOrdersImpl implements PurchaseOrdersService {

    private Tools tools;

    private ApplicationData applicationData;

    public PurchaseOrdersImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public String updatePurchaseOrder(InforContext context, PurchaseOrder purchaseOrderParam) throws InforException {
        return null;
        //
        //
        // SET ALL PROPERTIES
        //
        // CALL INFOR WEB SERVICE
    }


}
