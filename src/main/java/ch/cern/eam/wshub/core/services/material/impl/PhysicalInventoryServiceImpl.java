package ch.cern.eam.wshub.core.services.material.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.repositories.PhysicalInventoryRepository;
import ch.cern.eam.wshub.core.repositories.PhysicalInventoryRowRepository;
import ch.cern.eam.wshub.core.services.material.PhysicalInventoryService;
import ch.cern.eam.wshub.core.services.material.entities.PhysicalInventory;
import ch.cern.eam.wshub.core.services.material.entities.PhysicalInventoryRow;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import java.math.BigInteger;

public class PhysicalInventoryServiceImpl implements PhysicalInventoryService {

    private Tools tools;

    private ApplicationData applicationData;

    private PhysicalInventoryRepository physicalInventoryRepository;

    private PhysicalInventoryRowRepository physicalInventoryRowRepository;

    public PhysicalInventoryServiceImpl(ApplicationData applicationData, Tools tools, PhysicalInventoryRepository physicalInventoryRepository, PhysicalInventoryRowRepository physicalInventoryRowRepository) {
        this.applicationData = applicationData;
        this.tools = tools;
        this.physicalInventoryRepository = physicalInventoryRepository;
        this.physicalInventoryRowRepository = physicalInventoryRowRepository;
    }

    @Override
    public PhysicalInventory createPhysicalInventory(InforContext context, PhysicalInventory physicalInventory) throws InforException {
        return null;
    }

    @Override
    public PhysicalInventory readPhysicalInventory(InforContext context, String code) throws InforException {
        return physicalInventoryRepository.findById(code).orElse(null);
    }

    @Override
    public PhysicalInventory updatePhysicalInventory(InforContext context, PhysicalInventory physicalInventory) throws InforException {
        return null;
    }

    @Override
    public PhysicalInventoryRow readPhysicalInventoryLine(InforContext context, PhysicalInventoryRow row) throws InforException {
        java.util.List<PhysicalInventoryRow> rows = physicalInventoryRowRepository.findByPhysicalInventoryCode(row.getPhysicalInventoryCode());
        return rows.stream().filter(r -> r.getLineNumber().equals(row.getLineNumber())).findFirst().orElse(null);
    }

    @Override
    public PhysicalInventoryRow updatePhysicalInventoryLine(InforContext context, PhysicalInventoryRow row) throws InforException {
        return null;
    }

    @Override
    public PhysicalInventory readDefaultPhysicalInventory(InforContext context, String storeCode) throws InforException {
        return null;
    }
}
