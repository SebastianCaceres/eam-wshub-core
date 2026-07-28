package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.grids.entities.GridDataspy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GridDataspyRepository extends JpaRepository<GridDataspy, String> {
}
