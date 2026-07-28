package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.grids.entities.GridMetadataRequestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GridMetadataRequestResultRepository extends JpaRepository<GridMetadataRequestResult, String> {
}
