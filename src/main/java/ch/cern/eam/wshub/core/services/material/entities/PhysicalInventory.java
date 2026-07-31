package ch.cern.eam.wshub.core.services.material.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "R5TRANSACTIONS")
public class PhysicalInventory  {

    @Id
    @Column(name = "TRA_CODE")
    
    private String code;

    @Column(name = "TRA_DESC")
    
    private String description;

    @Column(name = "TRA_FROMCODE")
    
    private String store;

    @Column(name = "TRA_AUTH")
    private String createdBy;

    @Column(name = "TRA_DATE")
    private Date createdDate;

    @Column(name = "TRA_PERS")
    
    private String assignedTo;

    @Column(name = "TRA_STATUS")
    
    private String status;

    
    private UserDefinedFields userDefinedFields;

    // the fields below are not stored on the database and are only used for creation
    
    private String part;

    private String partClass;

    private String stockClass;

    private String abcClass;

    private Boolean includeConsignmentItem;

    private String fromBin;

    private String toBin;

    private Date physicalInventoryDate;

    private Boolean includeChildStores;

    private Boolean includeOutOfServiceItem;

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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String approvedBy) {
        this.createdBy = approvedBy;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPhysicalInventoryDate() {
        return physicalInventoryDate;
    }

    public void setPhysicalInventoryDate(Date physicalInventoryDate) {
        this.physicalInventoryDate = physicalInventoryDate;
    }

    public UserDefinedFields getUserDefinedFields() {
        return userDefinedFields;
    }

    public void setUserDefinedFields(UserDefinedFields userDefinedFields) {
        this.userDefinedFields = userDefinedFields;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getPartClass() {
        return partClass;
    }

    public void setPartClass(String partClass) {
        this.partClass = partClass;
    }

    public String getStockClass() {
        return stockClass;
    }

    public void setStockClass(String stockClass) {
        this.stockClass = stockClass;
    }

    public String getAbcClass() {
        return abcClass;
    }

    public void setAbcClass(String abcClass) {
        this.abcClass = abcClass;
    }

    public Boolean getIncludeConsignmentItem() {
        return includeConsignmentItem;
    }

    public void setIncludeConsignmentItem(Boolean includeConsignmentItem) {
        this.includeConsignmentItem = includeConsignmentItem;
    }

    public String getFromBin() {
        return fromBin;
    }

    public void setFromBin(String fromBin) {
        this.fromBin = fromBin;
    }

    public String getToBin() {
        return toBin;
    }

    public void setToBin(String toBin) {
        this.toBin = toBin;
    }

    public Boolean getIncludeChildStores() {
        return includeChildStores;
    }

    public void setIncludeChildStores(Boolean includeChildStores) {
        this.includeChildStores = includeChildStores;
    }

    public Boolean getIncludeOutOfServiceItem() {
        return includeOutOfServiceItem;
    }

    public void setIncludeOutOfServiceItem(Boolean includeOutOfServiceItem) {
        this.includeOutOfServiceItem = includeOutOfServiceItem;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    @Override
    public String toString() {
        return "PhysicalInventory ["
            + (code != null ? "code=" + code + ", " : "")
            + (description != null ? "description=" + description + ", " : "")
            + (store != null ? "store=" + store + ", " : "")
            + (createdBy != null ? "createdBy=" + createdBy + ", " : "")
            + (createdDate != null ? "createdDate=" + createdDate + ", " : "")
            + (assignedTo != null ? "assignedTo=" + assignedTo + ", " : "")
            + (status != null ? "status=" + status + ", " : "")
            + (userDefinedFields != null ? "userDefinedFields=" + userDefinedFields + ", " : "")
            + (part != null ? "part=" + part + ", " : "")
            + (partClass != null ? "partClass=" + partClass + ", " : "")
            + (stockClass != null ? "stockClass=" + stockClass + ", " : "")
            + (abcClass != null ? "abcClass=" + abcClass + ", " : "")
            + (includeConsignmentItem != null ? "includeConsignmentItem=" + includeConsignmentItem + ", " : "")
            + (fromBin != null ? "fromBin=" + fromBin + ", " : "")
            + (toBin != null ? "toBin=" + toBin + ", " : "")
            + (physicalInventoryDate != null ? "physicalInventoryDate=" + physicalInventoryDate + ", " : "")
            + (includeChildStores != null ? "includeChildStores=" + includeChildStores + ", " : "")
            + (includeOutOfServiceItem != null ? "includeOutOfServiceItem=" + includeOutOfServiceItem : "")
            + "]";
    }
}
