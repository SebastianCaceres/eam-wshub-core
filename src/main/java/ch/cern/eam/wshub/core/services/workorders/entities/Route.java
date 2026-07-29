package ch.cern.eam.wshub.core.services.workorders.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "R5ROUTES")
public class Route {

    @Id
    @Column(name = "ROT_CODE")
    private String code;

    @Column(name = "ROT_DESC")
    private String desc;

    @Transient
    private BigInteger revision;

    @Column(name = "ROT_CAT")
    private String equipmentClassCode;

    @Column(name = "ROT_STATUS")
    private String revisionStatusCode;

    @Transient
    private Boolean template;

    @Column(name = "ROT_APPROVEDAT")
    private Date dateApproved;

    @Column(name = "ROT_REQUESTDAT")
    private Date dateRequested;

    @Column(name = "ROT_REVISOREAS")
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
        return equipmentClassCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.equipmentClassCode = categoryCode;
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
