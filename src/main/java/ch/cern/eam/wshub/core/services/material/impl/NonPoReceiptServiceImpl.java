package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.grids.impl.GridsServiceImpl;
import ch.cern.eam.wshub.core.services.material.NonPoReceiptPartService;
import ch.cern.eam.wshub.core.services.material.NonPoReceiptService;
import ch.cern.eam.wshub.core.services.material.entities.NoPoReceipt;
import ch.cern.eam.wshub.core.services.material.entities.NoPoReceiptPart;
import ch.cern.eam.wshub.core.services.material.entities.TransactionLineId;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import org.openapplications.oagis_segments.DATETIME;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NonPoReceiptServiceImpl implements NonPoReceiptService {

    private Tools tools;

    private ApplicationData applicationData;

    private NonPoReceiptPartService nonPoReceiptPartService;

    public NonPoReceiptServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public String createNoPoReceipt(InforContext context, NoPoReceipt receipt) throws InforException {
        return null;
    }

    @Override
    public NoPoReceipt readNoPoReceipt(InforContext context, String receiptCode) throws InforException {
        return null;
    }

    @Override
    public String updateNoPoReceipt(InforContext context, NoPoReceipt receipt) throws InforException {
        return null;
    }

    @Override
    public String deleteNoPoReceipt(InforContext context, String receiptCode) throws InforException {
        return null;
    }
}
