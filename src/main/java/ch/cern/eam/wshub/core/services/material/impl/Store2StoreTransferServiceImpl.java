package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.material.Store2StoreTransferService;
import ch.cern.eam.wshub.core.services.material.entities.Store2StoreTransferDTO;
import ch.cern.eam.wshub.core.services.material.entities.StoreTransactionPartLine;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.repositories.Store2StoreTransferDTORepository;
import ch.cern.eam.wshub.core.repositories.StoreTransactionPartLineRepository;
import java.util.ArrayList;
import java.util.List;

public class Store2StoreTransferServiceImpl implements Store2StoreTransferService {

    private Tools tools;

    private ApplicationData applicationData;

    private Store2StoreTransferDTORepository store2StoreTransferDTORepository;

    private StoreTransactionPartLineRepository storeTransactionPartLineRepository;

    public Store2StoreTransferServiceImpl(ApplicationData applicationData, Tools tools, Store2StoreTransferDTORepository store2StoreTransferDTORepository, StoreTransactionPartLineRepository storeTransactionPartLineRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.store2StoreTransferDTORepository = store2StoreTransferDTORepository;
        this.storeTransactionPartLineRepository = storeTransactionPartLineRepository;
    }

    @Override
    public String store2storeTransfer(final InforContext context, final Store2StoreTransferDTO store2StoreTransferDTO) throws InforException {
        return null;
    }

    @Override
    public Store2StoreTransferDTO readStore2StoreTransfer(InforContext context, Long transferId) throws InforException {
        if (store2StoreTransferDTORepository == null) {
            throw tools.generateFault("Database connection required for store transfer reads");
        }
        return store2StoreTransferDTORepository.findById(transferId).orElseThrow(() -> tools.generateFault("Store transfer not found: " + transferId));
    }
}
