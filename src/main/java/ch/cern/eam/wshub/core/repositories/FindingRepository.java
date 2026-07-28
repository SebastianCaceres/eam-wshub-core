package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.workorders.entities.Finding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FindingRepository extends JpaRepository<Finding, String> {
}
