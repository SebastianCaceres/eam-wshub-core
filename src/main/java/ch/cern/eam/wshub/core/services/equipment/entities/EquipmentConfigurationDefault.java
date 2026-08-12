package ch.cern.eam.wshub.core.services.equipment.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class EquipmentConfigurationDefault implements Serializable {

    private BigDecimal revisionNum;
    private String equipmentConfigStatusCode;
    private String equipmentStatusCode;
    private String equipmentTypeCode;
    private Boolean autoNumber;
    private String organizationCode;

    public Boolean getAutoNumber() {
        return autoNumber;
    }

    public void setAutoNumber(Boolean autoNumber) {
        this.autoNumber = autoNumber;
    }

    public BigDecimal getRevisionNum() {
        return revisionNum;
    }

    public void setRevisionNum(BigDecimal revisionNum) {
        this.revisionNum = revisionNum;
    }

    public String getEquipmentConfigStatusCode() {
        return equipmentConfigStatusCode;
    }

    public void setEquipmentConfigStatusCode(String equipmentConfigStatusCode) {
        this.equipmentConfigStatusCode = equipmentConfigStatusCode;
    }

    public String getEquipmentStatusCode() {
        return equipmentStatusCode;
    }

    public void setEquipmentStatusCode(String equipmentStatusCode) {
        this.equipmentStatusCode = equipmentStatusCode;
    }

    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }
}
