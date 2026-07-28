package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.documents.entities.InforDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InforDocumentRepository extends JpaRepository<InforDocument, String> {
}
