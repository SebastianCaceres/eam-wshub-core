package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.material.entities.PhysicalInventoryRow;
import ch.cern.eam.wshub.core.services.material.entities.PhysicalInventoryRowPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PhysicalInventoryRowRepository extends JpaRepository<PhysicalInventoryRow, PhysicalInventoryRowPK> {
    List<PhysicalInventoryRow> findByPhysicalInventoryCode(String physicalInventoryCode);
}
