package ch.cern.eam.wshub.core.services.workorders.entities;

import java.math.BigDecimal;
import java.util.Date;

public class MeterReading  {

    private String UOM;
    
    private String equipmentCode;
    
    private String equipmentOrganization;
    private BigDecimal actualValue;
    private BigDecimal differenceValue;
    private Date readingDate;
    
    private String woNumber;

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String uOM) {
        UOM = uOM;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentOrganization() {
        return equipmentOrganization;
    }

    public void setEquipmentOrganization(String equipmentOrganization) {
        this.equipmentOrganization = equipmentOrganization;
    }

    public BigDecimal getActualValue() {
        return actualValue;
    }
    public void setActualValue(BigDecimal actualValue) {
        this.actualValue = actualValue;
    }

    public BigDecimal getDifferenceValue() {
        return differenceValue;
    }
    public void setDifferenceValue(BigDecimal differenceValue) {
        this.differenceValue = differenceValue;
    }

    public Date getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(Date readingDate) {
        this.readingDate = readingDate;
    }

    @Override
    public String toString() {
        return "MeterReading [UOM=" + UOM + ", equipmentCode=" + equipmentCode + ", actualValue=" + actualValue
                + ", differenceValue=" + differenceValue + ", readingDate=" + readingDate + "]";
    }

    public String getWoNumber() {
        return woNumber;
    }

    public void setWoNumber(String woNumber) {
        this.woNumber = woNumber;
    }

}
