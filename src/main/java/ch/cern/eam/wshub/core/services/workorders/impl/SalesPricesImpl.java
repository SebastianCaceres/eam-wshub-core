package ch.cern.eam.wshub.core.services.workorders.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.workorders.SalesPriceService;
import ch.cern.eam.wshub.core.services.workorders.entities.SalesPrice;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.isEmpty;

public class SalesPricesImpl implements SalesPriceService {

    private Tools tools;

    private ApplicationData applicationData;

    public SalesPricesImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    public String createSalesPrice(InforContext context, SalesPrice salesPrice) throws InforException {
        return null;
    }

    public String updateSalesPrice(InforContext context, SalesPrice salesPrice) throws InforException {
        return null;
    }
}
