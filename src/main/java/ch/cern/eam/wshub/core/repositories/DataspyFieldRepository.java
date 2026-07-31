package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.grids.entities.DataspyField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DataspyFieldRepository extends JpaRepository<DataspyField, String> {
    List<DataspyField> findByDataspy(String dataspyId);
}
