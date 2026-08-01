package ch.cern.eam.wshub.core;

import ch.cern.eam.wshub.core.seeder.DemoDataSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ch.cern.eam.wshub.core")
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

    @Bean
    public CommandLineRunner initDemoData(DemoDataSeeder demoDataSeeder) {
        return args -> demoDataSeeder.seedDemoData();
    }
}
