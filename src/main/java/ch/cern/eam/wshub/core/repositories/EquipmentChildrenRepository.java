package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentChildren;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentChildrenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipmentChildrenRepository extends JpaRepository<EquipmentChildren, EquipmentChildrenId> {

    @Query(name = EquipmentChildren.GET_EQUIPMENT_CHILDREN, nativeQuery = true)
    List<EquipmentChildren> getEquipmentChildren(@Param("equipment") String equipment);

    @Query(name = EquipmentChildren.GET_EQUIPMENT_PARENTS, nativeQuery = true)
    List<EquipmentChildren> getEquipmentParents(@Param("equipment") String equipment);
}
