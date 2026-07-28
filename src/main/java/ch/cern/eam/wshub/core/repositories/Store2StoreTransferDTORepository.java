package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.material.entities.Store2StoreTransferDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Store2StoreTransferDTORepository extends JpaRepository<Store2StoreTransferDTO, Long> {
}
