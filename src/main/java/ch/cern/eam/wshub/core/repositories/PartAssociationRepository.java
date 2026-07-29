package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.material.entities.PartAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PartAssociationRepository extends JpaRepository<PartAssociation, String> {
    @Query(name = PartAssociation.GET_PART_ASSOCIATION)
    List<PartAssociation> findByPartCodeAndEquipmentCode(
        @Param("partCode") String partCode,
        @Param("equipmentCode") String equipmentCode);
}
