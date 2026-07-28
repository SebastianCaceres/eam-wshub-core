package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentWarranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentWarrantyRepository extends JpaRepository<EquipmentWarranty, String> {

    @Query(name = EquipmentWarranty.GETEQPWARRANTY)
    Optional<EquipmentWarranty> findByEquipmentCodeAndWarrantyCode(@Param("equipmentCode") String equipmentCode, @Param("warrantyCode") String warrantyCode);

}
