package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.material.entities.StoreTransactionPartLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreTransactionPartLineRepository extends JpaRepository<StoreTransactionPartLine, Long> {
}
