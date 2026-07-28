package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.documents.entities.InforDocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InforDocEntityRepository extends JpaRepository<InforDocEntity, String> {
}
