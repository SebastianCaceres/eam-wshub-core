package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.documents.entities.InforDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InforDocumentRepository extends JpaRepository<InforDocument, String> {

    @Query(name = InforDocument.GET_DOCUMENTS)
    List<InforDocument> findByCodeAndEntity(@Param("code") String code, @Param("entity") String entity);

}
