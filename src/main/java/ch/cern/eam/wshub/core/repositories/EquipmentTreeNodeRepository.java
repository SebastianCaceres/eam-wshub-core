package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentTreeNode;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentTreeNodeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipmentTreeNodeRepository extends JpaRepository<EquipmentTreeNode, EquipmentTreeNodeId> {

    @Query(name = EquipmentTreeNode.GET_TREE, nativeQuery = true)
    List<EquipmentTreeNode> getTree(@Param("equipment") String equipment);
}
