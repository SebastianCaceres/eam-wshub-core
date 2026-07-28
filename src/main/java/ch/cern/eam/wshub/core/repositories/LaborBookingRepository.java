package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.workorders.entities.LaborBooking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LaborBookingRepository extends JpaRepository<LaborBooking, String> {
    @Query(name = LaborBooking.GETBOOKEDLABOR)
    List<LaborBooking> findByWorkOrder(@Param("workOrder") String workOrder);
}
