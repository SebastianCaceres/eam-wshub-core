package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.workorders.entities.InforCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InforCaseRepository extends JpaRepository<InforCase, String> {
}
