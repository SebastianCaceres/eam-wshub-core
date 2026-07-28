package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentDepreciation;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentDepreciationRepository extends JpaRepository<EquipmentDepreciation, BigDecimal> {
}
