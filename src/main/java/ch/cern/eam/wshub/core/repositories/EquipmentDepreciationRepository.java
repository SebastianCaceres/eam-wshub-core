package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentDepreciation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EquipmentDepreciationRepository extends JpaRepository<EquipmentDepreciation, BigDecimal> {
    @Query(name = EquipmentDepreciation.GETDEPRECIATION)
    List<EquipmentDepreciation> findByEquipmentCode(@Param("equipmentCode") String equipmentCode);
}
