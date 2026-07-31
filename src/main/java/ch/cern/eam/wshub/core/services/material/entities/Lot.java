package ch.cern.eam.wshub.core.services.material.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
@Getter
@Setter
@ToString
@Entity
@Table(name = "R5STOCKLOTS")
public class Lot {

    @Id
    @Column(name = "LOT_CODE")
    private String code;

    @Column(name = "LOT_DESC")
    private String desc;

    @Column(name = "LOT_CLASS")
    private String classCode;

    @Column(name = "LOT_EXDATE")
    private Date expirationDate;

    @Column(name = "LOT_MFGLO")
    private String manufacturerLot;

    @Transient
    private UserDefinedFields userDefinedFields;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getManufacturerLot() {
        return manufacturerLot;
    }

    public void setManufacturerLot(String manufacturerLot) {
        this.manufacturerLot = manufacturerLot;
    }

    public UserDefinedFields getUserDefinedFields() {
        return userDefinedFields;
    }

    public void setUserDefinedFields(UserDefinedFields userDefinedFields) {
        this.userDefinedFields = userDefinedFields;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "classCode='" + classCode + '\'' +
                ", code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", expirationDate=" + expirationDate +
                ", manufacturerLot='" + manufacturerLot + '\'' +
                '}';
    }
}
