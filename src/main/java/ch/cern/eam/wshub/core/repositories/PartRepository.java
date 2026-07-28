package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.material.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, String> {
}
