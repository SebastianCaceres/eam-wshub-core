package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.material.entities.PartUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PartUsageRepository extends JpaRepository<PartUsage, String> {
    List<PartUsage> findByEventCode(String eventCode);
}
