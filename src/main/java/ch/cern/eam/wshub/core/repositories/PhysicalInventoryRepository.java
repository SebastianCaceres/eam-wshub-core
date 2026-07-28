package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.material.entities.PhysicalInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalInventoryRepository extends JpaRepository<PhysicalInventory, String> {
}
