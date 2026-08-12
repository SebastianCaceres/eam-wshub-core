package ch.cern.eam.wshub.core.services.workorders.entities;



import java.math.BigDecimal;

public class MEC {
    
    private String workorderID;

    private String equipmentCode;

    private String relatedWorkorderID;

    private String locationID;

    private String costCode;

    private String safety;

    public String getWorkorderID() {
        return workorderID;
    }

    public void setWorkorderID(String workorderID) {
        this.workorderID = workorderID;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getRelatedWorkorderID() {
        return relatedWorkorderID;
    }

    public void setRelatedWorkorderID(String relatedWorkorderID) {
        this.relatedWorkorderID = relatedWorkorderID;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getCostCode() {
        return costCode;
    }

    public void setCostCode(String costCode) {
        this.costCode = costCode;
    }

    public String getSafety() {
        return safety;
    }

    public void setSafety(String safety) {
        this.safety = safety;
    }
}
