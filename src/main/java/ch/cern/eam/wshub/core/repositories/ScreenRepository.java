package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.administration.entities.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, String> {
}
