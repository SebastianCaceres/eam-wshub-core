package ch.cern.eam.wshub.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
    "ch.cern.eam.wshub.core.services.workorders.entities",
    "ch.cern.eam.wshub.core.services.material.entities",
    "ch.cern.eam.wshub.core.services.equipment.entities",
    "ch.cern.eam.wshub.core.services.administration.entities",
    "ch.cern.eam.wshub.core.services.documents.entities",
    "ch.cern.eam.wshub.core.services.comments.entities",
    "ch.cern.eam.wshub.core.services.entities",
    "ch.cern.eam.wshub.core.services.grids.entities",
    "ch.cern.eam.wshub.core.services.grids.customfields"
})
@EnableJpaRepositories(basePackages = "ch.cern.eam.wshub.core.repositories")
public class TestApplication {
}
