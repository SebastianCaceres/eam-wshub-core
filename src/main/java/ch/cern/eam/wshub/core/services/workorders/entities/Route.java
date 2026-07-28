package ch.cern.eam.wshub.core.services.workorders.entities;

import java.math.BigInteger;
import java.util.Date;

public class Route {

    private String code;

    private String desc;

    private BigInteger revision;

    private String equipmentClassCode;

    private String categoryCode;

    private String revisionStatusCode;

    private Boolean template;

    private Date dateApproved;

    private Date dateRequested;

    private String revisionReason;

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

    public BigInteger getRevision() {
        return revision;
    }

    public void setRevision(BigInteger revision) {
        this.revision = revision;
    }

    public String getEquipmentClassCode() {
        return equipmentClassCode;
    }

    public void setEquipmentClassCode(String equipmentClassCode) {
        this.equipmentClassCode = equipmentClassCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getRevisionStatusCode() {
        return revisionStatusCode;
    }

    public void setRevisionStatusCode(String revisionStatusCode) {
        this.revisionStatusCode = revisionStatusCode;
    }

    public Boolean getTemplate() {
        return template;
    }

    public void setTemplate(Boolean template) {
        this.template = template;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public Date getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
    }

    public String getRevisionReason() {
        return revisionReason;
    }

    public void setRevisionReason(String revisionReason) {
        this.revisionReason = revisionReason;
    }
}
