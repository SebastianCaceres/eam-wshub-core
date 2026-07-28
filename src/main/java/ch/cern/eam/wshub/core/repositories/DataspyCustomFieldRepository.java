package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.grids.customfields.DataspyCustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataspyCustomFieldRepository extends JpaRepository<DataspyCustomField, String> {
}
