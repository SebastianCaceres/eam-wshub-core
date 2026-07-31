package ch.cern.eam.wshub.core.services.equipment.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "R5BOOKINGS")
public class EquipmentReservation {
    // Main identifying fields
    @Id
    @Column(name = "BOO_CODE")
    private String code;

    @Column(name = "BOO_ORG")
    private String organizationCode;

    @Column(name = "BOO_DESC")
    private String description;

    // Rental Details
    @Column(name = "BOO_TYPE")
    private String rentalType;

    @Column(name = "BOO_STATUS")
    private String status;

    @Column(name = "BOO_OBJECT")
    private String equipmentCode;
    
    private String locationCode;
    
    private String classCode;
    
    private String reference;
    
    private String vehicleType;
    
    private String issueTo;
    
    private String costCode;
    
    private String rentalTemplate;
    
    private String customer;

    private Date completedDate;
    
    private String createdBy;
    
    private Date createdDate;

    // Issue Details
    
    private Date estimatedIssueDate;
    
    private Date issuedDate;
    
    private String issueLocation;
    
    private String issuedBy;

    // Return Details
    
    private Date returnDate;
    
    private Date estimatedReturnDate;
    
    private String returnLocation;

    // Invoicing Details
    
    private BigDecimal invoicedAmount;
    
    private BigDecimal calculatedDays;
    
    private BigDecimal calculatedHours;
    
    private BigDecimal correctedDays;
    
    private BigDecimal correctedHours;
    
    private BigDecimal adjustments;
    
    private BigDecimal netAmount;
    
    private BigDecimal grossAmount;
    
    private BigDecimal taxAmount;
    
    private BigDecimal issueFuelLevel;
    
    private BigDecimal issueReading;
    
    private String returnedTo;
    
    private BigDecimal returnFuelLevel;
    
    private BigDecimal returnReading;
    
    private String uom;
    @Transient
    
    private UserDefinedFields userDefinedFields;

    public Date getCompletedDate() {
        return completedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getEstimatedIssueDate() {
        return estimatedIssueDate;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Date getEstimatedReturnDate() {
        return estimatedReturnDate;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getOrganizationCode() { return organizationCode; }
    public void setOrganizationCode(String organizationCode) { this.organizationCode = organizationCode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRentalType() { return rentalType; }
    public void setRentalType(String rentalType) { this.rentalType = rentalType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getEquipmentCode() { return equipmentCode; }
    public void setEquipmentCode(String equipmentCode) { this.equipmentCode = equipmentCode; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
}
