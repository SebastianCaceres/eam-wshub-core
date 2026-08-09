package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.equipment.entities.NCRObservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NCRObservationRepository extends JpaRepository<NCRObservation, String> {
    List<NCRObservation> findByNonConformityCode(String nonConformityCode);
}
