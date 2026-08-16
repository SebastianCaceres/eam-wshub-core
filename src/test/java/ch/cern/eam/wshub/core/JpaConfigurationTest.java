package ch.cern.eam.wshub.core;

import ch.cern.eam.wshub.core.repositories.PartRepository;
import ch.cern.eam.wshub.core.repositories.WorkOrderRepository;
import ch.cern.eam.wshub.core.services.material.entities.Part;
import ch.cern.eam.wshub.core.services.workorders.entities.WorkOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestApplication.class)
public class JpaConfigurationTest {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private ch.cern.eam.wshub.core.repositories.EquipmentRepository equipmentRepository;

    @Test
    public void testJavaxPersistenceContextBootstrap() {
        assertNotNull(workOrderRepository);
        assertNotNull(partRepository);
        assertNotNull(equipmentRepository);
    }

    @Test
    public void testBasicEntityPersistence() {
        // Test Part persistence
        Part part = new Part();
        part.setCode("PART-001");
        part.setDescription("Test Part");
        partRepository.save(part);

        assertTrue(partRepository.findById("PART-001").isPresent());
        assertEquals("Test Part", partRepository.findById("PART-001").get().getDescription());

        // Test WorkOrder persistence
        WorkOrder workOrder = new WorkOrder();
        workOrder.setNumber("WO-100");
        workOrder.setDescription("Test Work Order");
        workOrderRepository.save(workOrder);

        assertTrue(workOrderRepository.findById("WO-100").isPresent());
        assertEquals("Test Work Order", workOrderRepository.findById("WO-100").get().getDescription());
    }

    @Test
    public void testWorkOrderEquipmentCascadingAndPMLock() throws Exception {
        ch.cern.eam.wshub.core.services.workorders.impl.WorkOrderServiceImpl woService = 
            new ch.cern.eam.wshub.core.services.workorders.impl.WorkOrderServiceImpl(null, null, workOrderRepository, equipmentRepository);

        // 1. Create Equipment with department, cost code, location
        ch.cern.eam.wshub.core.services.equipment.entities.Equipment eq = new ch.cern.eam.wshub.core.services.equipment.entities.Equipment();
        eq.setCode("EQ-TEST-1");
        eq.setDepartmentCode("DEPT-ENG");
        eq.setCostCode("COST-999");
        eq.setLocationCode("LOC-BUILDING-1");
        equipmentRepository.save(eq);

        // 2. Create WO referencing equipment but leaving department, cost code, location empty
        WorkOrder wo = new WorkOrder();
        wo.setEquipmentCode("EQ-TEST-1");
        wo.setDescription("Test Cascading WO");

        String woNum = woService.createWorkOrder(null, wo);
        WorkOrder savedWO = workOrderRepository.findById(woNum).orElseThrow();

        assertEquals("DEPT-ENG", savedWO.getDepartmentCode());
        assertEquals("COST-999", savedWO.getCostCode());
        assertEquals("LOC-BUILDING-1", savedWO.getLocationCode());

        // 3. Test PM Work Order Lock (Status A)
        savedWO.setStatusCode("A");
        workOrderRepository.save(savedWO);

        WorkOrder updateWO = new WorkOrder();
        updateWO.setNumber(woNum);
        updateWO.setDescription("Updated Description");

        assertThrows(ch.cern.eam.wshub.core.tools.InforException.class, () -> {
            woService.updateWorkOrder(null, updateWO);
        });
    }

    @Test
    public void testAssetStatusDAndCommissionDate() throws Exception {
        ch.cern.eam.wshub.core.services.equipment.impl.AssetServiceImpl assetService = 
            new ch.cern.eam.wshub.core.services.equipment.impl.AssetServiceImpl(null, null, equipmentRepository);

        // 1. Test create Asset defaults Commission Date
        ch.cern.eam.wshub.core.services.equipment.entities.Equipment asset = new ch.cern.eam.wshub.core.services.equipment.entities.Equipment();
        asset.setCode("AST-TEST-1");
        asset.setDescription("Test Asset Defaults");
        assetService.createAsset(null, asset);

        ch.cern.eam.wshub.core.services.equipment.entities.Equipment savedAsset = equipmentRepository.findById("AST-TEST-1").orElseThrow();
        assertNotNull(savedAsset.getComissionDate());

        // 2. Test status 'D' nullifies parent hierarchy
        savedAsset.setHierarchyAssetCode("PARENT-AST");
        savedAsset.setStatusCode("D");
        assetService.updateAsset(null, savedAsset);

        ch.cern.eam.wshub.core.services.equipment.entities.Equipment updatedAsset = equipmentRepository.findById("AST-TEST-1").orElseThrow();
        assertNull(updatedAsset.getHierarchyAssetCode());
    }
}
