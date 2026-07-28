package ch.cern.eam.wshub.core.services.workorders.entities;

public class UserQualification {
    
    String entity;

    String userDefinedCode;

    String description;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getUserDefinedCode() {
        return userDefinedCode;
    }

    public void setUserDefinedCode(String userDefinedCode) {
        this.userDefinedCode = userDefinedCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
