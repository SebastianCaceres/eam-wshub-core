package ch.cern.eam.wshub.core.services.equipment.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class EquipmentReservation {
    // Main identifying fields
    
    private String code;
    
    private String organizationCode;
    
    private String description;

    // Rental Details
    
    private String rentalType;
    
    private String status;
    
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
}
