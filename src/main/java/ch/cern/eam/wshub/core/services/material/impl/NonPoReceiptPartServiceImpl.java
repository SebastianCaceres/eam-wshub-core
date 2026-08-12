package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.entities.BatchResponse;
import ch.cern.eam.wshub.core.services.grids.GridsService;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequest;
import ch.cern.eam.wshub.core.services.grids.entities.GridRequestResult;
import ch.cern.eam.wshub.core.services.grids.impl.GridsServiceImpl;
import ch.cern.eam.wshub.core.services.material.NonPoReceiptPartService;
import ch.cern.eam.wshub.core.services.material.entities.NoPoReceiptPart;
import ch.cern.eam.wshub.core.services.material.entities.TransactionLineId;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.GridTools;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NonPoReceiptPartServiceImpl implements NonPoReceiptPartService {

    private Tools tools;

    private ApplicationData applicationData;

    private GridsService gridsService;

    public NonPoReceiptPartServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public Long createNoPoReceiptPart(InforContext context, NoPoReceiptPart transactionLine) throws InforException {
        return null;
    }

    @Override
    public NoPoReceiptPart readNoPoReceiptPart(InforContext context, BigInteger transactionLineId, String transactionCode) throws InforException {
        return readNoPoReceiptPart(context, new TransactionLineId(transactionCode, transactionLineId));
    }

    public NoPoReceiptPart readNoPoReceiptPart(InforContext context, TransactionLineId transactionLineId) throws InforException {
        return null;
    }

    @Override
    public Long updateNoPoReceiptPart(InforContext context, NoPoReceiptPart transactionLine) throws InforException {
        return null;
    }

    @Override
    public Long deleteNoPoReceiptPart(InforContext context, BigInteger transactionLineId, String transactionCode) throws InforException {
        return deleteNoPoReceiptPart(context, new TransactionLineId(transactionCode, transactionLineId));
    }

    public Long deleteNoPoReceiptPart(InforContext context, TransactionLineId transactionLineId) throws InforException {
        return null;
    }

    @Override
    public List<NoPoReceiptPart> getNoPoReceiptParts(InforContext context, String transactionCode) throws InforException {
        GridRequest gridRequest = new GridRequest("SSCOMP_PAR", GridRequest.GRIDTYPE.LIST);
        gridRequest.setUserFunctionName("SSCOMP");
        gridRequest.addParam("param.nonporeceiptcode", transactionCode);
        GridRequestResult gridRequestResult = gridsService.executeQuery(context, gridRequest);
        return GridTools.convertGridResultToObject(NoPoReceiptPart.class, null, gridRequestResult);
    }

    @Override
    public BatchResponse<Long> createNoPoReceiptPartBatch(InforContext context, List<NoPoReceiptPart> transactionLines) throws InforException {
        return tools.batchOperation(context, this::createNoPoReceiptPart, transactionLines);
    }

    @Override
    public BatchResponse<NoPoReceiptPart> readNoPoReceiptPartBatch(InforContext context, List<TransactionLineId> transactionLineIds) {
        return tools.batchOperation(context, this::readNoPoReceiptPart, transactionLineIds);
    }

    @Override
    public BatchResponse<Long> updateNoPoReceiptPartBatch(InforContext context, List<NoPoReceiptPart> transactionLines) throws InforException {
        return tools.batchOperation(context, this::updateNoPoReceiptPart, transactionLines);
    }

    @Override
    public BatchResponse<Long> deleteNoPoReceiptPartBatch(InforContext context, List<TransactionLineId> transactionLineIds) throws InforException {
        return tools.batchOperation(context, this::deleteNoPoReceiptPart, transactionLineIds);
    }
}
