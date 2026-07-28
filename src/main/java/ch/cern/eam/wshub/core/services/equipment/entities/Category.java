package ch.cern.eam.wshub.core.services.equipment.entities;

import ch.cern.eam.wshub.core.services.entities.CustomField;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "R5OBJECTS")
public class Category  {

    @Id
    @Column(name = "OBJ_CODE")
    
    private String code;

    @Column(name = "OBJ_DESC")
    
    private String description;

    @Transient
    
    private String classCode;

    @Transient
    
    private String classDesc;

    @Transient
    
    private String manufacturerCode;

    @Transient
    
    private String manufacturerDesc;

    @Transient
    @Column(name = "OBJ_SCHEMATIC")
    
    private String schematic;

    @Transient
    
    private CustomField[] customFields;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public String getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public String getManufacturerDesc() {
        return manufacturerDesc;
    }

    public void setManufacturerDesc(String manufacturerDesc) {
        this.manufacturerDesc = manufacturerDesc;
    }

    public String getSchematic() {
        return schematic;
    }

    public void setSchematic(String schematic) {
        this.schematic = schematic;
    }

    public CustomField[] getCustomFields() {
        return customFields;
    }

    public void setCustomFields(CustomField[] customFields) {
        this.customFields = customFields;
    }

    @Override
    public String toString() {
        return "Category{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", classCode='" + classCode + '\'' +
                ", classDesc='" + classDesc + '\'' +
                ", manufacturerCode='" + manufacturerCode + '\'' +
                ", manufacturerDesc='" + manufacturerDesc + '\'' +
                ", schematic='" + schematic + '\'' +
                ", customFields=" + Arrays.toString(customFields) +
                '}';
    }
}

