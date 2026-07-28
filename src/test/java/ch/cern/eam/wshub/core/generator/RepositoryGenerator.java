package ch.cern.eam.wshub.core.generator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class RepositoryGenerator {

    static class EntityMetadata {
        String entityPackage;
        String entityClass;
        String pkType;

        EntityMetadata(String entityPackage, String entityClass, String pkType) {
            this.entityPackage = entityPackage;
            this.entityClass = entityClass;
            this.pkType = pkType;
        }
    }

    @Test
    public void generateRepositories() throws Exception {
        List<EntityMetadata> entities = new ArrayList<>();
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.workorders.entities", "Activity", "BigInteger"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.equipment.entities", "Category", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.grids.customfields", "DataspyCustomField", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.grids.entities", "DataspyField", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.administration.entities", "EAMUser", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.workorders.entities", "Employee", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.equipment.entities", "Equipment", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.equipment.entities", "EquipmentDepreciation", "BigDecimal"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.equipment.entities", "EquipmentPMSchedule", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.equipment.entities", "EquipmentWarranty", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.workorders.entities", "Finding", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.grids.entities", "GridDataspy", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.grids.entities", "GridField", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.grids.entities", "GridMetadataRequestResult", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.workorders.entities", "InforCase", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.workorders.entities", "InforCaseTask", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.documents.entities", "InforDocEntity", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.documents.entities", "InforDocument", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.entities", "InstallParameters", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.workorders.entities", "LaborBooking", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.material.entities", "Part", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.material.entities", "PartAssociation", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.material.entities", "PhysicalInventory", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.material.entities", "PhysicalInventoryRow", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.workorders.entities", "RouteEquipment", "String"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.material.entities", "Store2StoreTransferDTO", "Long"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.material.entities", "StoreTransactionPartLine", "Long"));
        entities.add(new EntityMetadata("ch.cern.eam.wshub.core.services.workorders.entities", "WorkOrder", "String"));

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        Template template = ve.getTemplate("repository.vm");

        File outputDir = new File("src/main/java/ch/cern/eam/wshub/core/repositories");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        for (EntityMetadata entity : entities) {
            VelocityContext context = new VelocityContext();
            context.put("entityPackage", entity.entityPackage);
            context.put("entityClass", entity.entityClass);
            context.put("pkType", entity.pkType);

            File repoFile = new File(outputDir, entity.entityClass + "Repository.java");
            try (Writer writer = new FileWriter(repoFile)) {
                template.merge(context, writer);
            }
            System.out.println("Generated repository for " + entity.entityClass);
        }
    }
}
