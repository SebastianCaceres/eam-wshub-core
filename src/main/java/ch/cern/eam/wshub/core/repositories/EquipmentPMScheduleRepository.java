package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentPMSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentPMScheduleRepository extends JpaRepository<EquipmentPMSchedule, String> {

    @Query(name = EquipmentPMSchedule.FIND_PM_SCHEDULE)
    Optional<EquipmentPMSchedule> findByEquipmentCodeAndPmCode(@Param("equipmentCode") String equipmentCode, @Param("pmCode") String pmCode);

}
