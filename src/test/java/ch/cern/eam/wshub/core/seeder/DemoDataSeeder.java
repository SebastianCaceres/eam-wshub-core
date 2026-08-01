package ch.cern.eam.wshub.core.seeder;

import ch.cern.eam.wshub.core.repositories.*;
import ch.cern.eam.wshub.core.services.administration.entities.EAMUser;
import ch.cern.eam.wshub.core.services.equipment.entities.Equipment;
import ch.cern.eam.wshub.core.services.material.entities.Part;
import ch.cern.eam.wshub.core.services.workorders.entities.WorkOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DemoDataSeeder {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EAMUserRepository eamUserRepository;

    @Transactional
    public void seedDemoData() {
        // 1. Seed WorkOrder WO-1001
        if (!workOrderRepository.existsById("WO-1001")) {
            WorkOrder wo = new WorkOrder();
            wo.setNumber("WO-1001");
            wo.setDescription("Repair water leak");
            wo.setStatusCode("R");
            wo.setOrganization("CERN");
            wo.setEquipmentCode("AST-1001");
            workOrderRepository.save(wo);
        }

        // 2. Seed Equipment AST-1001
        if (!equipmentRepository.existsById("AST-1001")) {
            Equipment eq = new Equipment();
            eq.setCode("AST-1001");
            eq.setDescription("Main Water Pump");
            eq.setTypeCode("A");
            equipmentRepository.save(eq);
        }

        // 3. Seed Part PART-1001
        if (!partRepository.existsById("PART-1001")) {
            Part part = new Part();
            part.setCode("PART-1001");
            part.setDescription("Water Valve Gasket");
            partRepository.save(part);
        }

        // 4. Seed User ADMIN
        if (!eamUserRepository.existsById("admin")) {
            EAMUser user = new EAMUser();
            user.setUserCode("admin");
            user.setUserDesc("Administrator");
            eamUserRepository.save(user);
        }
    }
}
