package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.workorders.entities.AdditionalCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdditionalCostRepository extends JpaRepository<AdditionalCost, String> {
    List<AdditionalCost> findByEventCode(String eventCode);
}
