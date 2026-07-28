package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentWarranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentWarrantyRepository extends JpaRepository<EquipmentWarranty, String> {
}
