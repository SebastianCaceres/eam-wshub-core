package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.administration.entities.EAMUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EAMUserRepository extends JpaRepository<EAMUser, String> {
}
