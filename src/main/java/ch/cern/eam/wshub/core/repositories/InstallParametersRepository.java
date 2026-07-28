package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.entities.InstallParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallParametersRepository extends JpaRepository<InstallParameters, String> {
}
