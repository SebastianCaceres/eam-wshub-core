package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.material.entities.PhysicalInventoryRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalInventoryRowRepository extends JpaRepository<PhysicalInventoryRow, String> {
}
