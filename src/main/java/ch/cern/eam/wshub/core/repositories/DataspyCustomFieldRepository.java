package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.grids.customfields.DataspyCustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DataspyCustomFieldRepository extends JpaRepository<DataspyCustomField, String> {
    @Query(name = DataspyCustomField.GET_CUSTOM_FIELDS_FOR_DATASPY, nativeQuery = true)
    List<DataspyCustomField> findByDataspyID(@Param("dataspyID") String dataspyID);
}
