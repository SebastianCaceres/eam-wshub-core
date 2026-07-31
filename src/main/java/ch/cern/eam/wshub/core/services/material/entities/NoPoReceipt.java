package ch.cern.eam.wshub.core.services.material.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "R5TRANSACTIONS")
public class NoPoReceipt  {

    @Id
    @Column(name = "TRA_CODE")
    private String code;

    @Column(name = "TRA_DESC")
    private String description;

    @Column(name = "TRA_ORG")
    private String organization;

    @Column(name = "TRA_STATUS")
    private String status;

    @Column(name = "TRA_SUPPLIER")
    private String supplier;

    @Column(name = "TRA_STORE")
    private String store;

    @Transient
    private String referenceNumber;

    @Column(name = "TRA_DATE")
    private Date createdDate;

    @Transient
    private UserDefinedFields userDefinedFields;

    @Transient
    private List<NoPoReceiptPart> parts;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOrganization() { return organization; }
    public void setOrganization(String organization) { this.organization = organization; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }

    public String getStore() { return store; }
    public void setStore(String store) { this.store = store; }

    public String getReferenceNumber() { return referenceNumber; }
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public UserDefinedFields getUserDefinedFields() { return userDefinedFields; }
    public void setUserDefinedFields(UserDefinedFields userDefinedFields) { this.userDefinedFields = userDefinedFields; }

    public List<NoPoReceiptPart> getParts() { return parts; }
    public void setParts(List<NoPoReceiptPart> parts) { this.parts = parts; }
}
