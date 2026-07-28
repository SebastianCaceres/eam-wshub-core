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

    @Test
    public void testJavaxPersistenceContextBootstrap() {
        assertNotNull(workOrderRepository);
        assertNotNull(partRepository);
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
}
