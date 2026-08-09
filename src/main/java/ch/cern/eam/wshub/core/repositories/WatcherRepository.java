package ch.cern.eam.wshub.core.repositories;

import ch.cern.eam.wshub.core.services.workorders.entities.Watcher;
import ch.cern.eam.wshub.core.services.workorders.entities.WatcherId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WatcherRepository extends JpaRepository<Watcher, WatcherId> {
    List<Watcher> findByWorkOrderCode(String workOrderCode);
    void deleteByWorkOrderCodeAndPersonIn(String workOrderCode, List<String> persons);
}
