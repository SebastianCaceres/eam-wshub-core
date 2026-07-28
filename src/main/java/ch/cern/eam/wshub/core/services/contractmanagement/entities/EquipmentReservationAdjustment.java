package ch.cern.eam.wshub.core.services.contractmanagement.entities;

import ch.cern.eam.wshub.core.annotations.GridField;
import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class EquipmentReservationAdjustment  {
    @GridField(name = "adjustmentid")
    
    private String code;
    @GridField(name = "adjustmentorg")
    
    private String orgCode;
    @GridField(name = "customerrentalcode")
    
    private String customerRentalCode;
    @GridField(name = "adjustmentcode")
    
    private String adjustmentCode;
    @GridField(name = "adjustmenttype")
    
    private String typeCode;
    @GridField(name = "adjustmentsystemtype")
    
    private String typeRCode;
    @GridField(name = "adjustmentstatus")
    
    private String statusCode;
    @GridField(name = "adjustmentsystemstatus")
    
    private String statusRCode;
    @GridField(name = "taxcode")
    
    private String taxCode;
    @GridField(name = "adjustmentdate")
    
    private Date date;
    @GridField(name = "comments")
    
    private String comments;
    @GridField(name = "quantity")
    
    private BigDecimal quantity;
    @GridField(name = "rate")
    
    private BigDecimal rate;
    @GridField(name = "totalamount")
    
    private BigDecimal totalAmount;
    @GridField(name = "taxamount")
    
    private BigDecimal taxAmount;
    @GridField(name = "invoicecode")
    
    private String invoice;
    @GridField(name = "invoiceorg")
    
    private String invoiceOrgCode;
    @GridField(name = "createdworkorder")
    
    private String createdWorkOrder;
    @GridField(name = "recordid")
    
    private BigInteger updateCount;
    
    private UserDefinedFields userDefinedFields;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getCustomerRentalCode() {
        return customerRentalCode;
    }

    public void setCustomerRentalCode(String customerRentalCode) {
        this.customerRentalCode = customerRentalCode;
    }

    public String getAdjustmentCode() {
        return adjustmentCode;
    }

    public void setAdjustmentCode(String adjustmentCode) {
        this.adjustmentCode = adjustmentCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeRCode() {
        return typeRCode;
    }

    public void setTypeRCode(String typeRCode) {
        this.typeRCode = typeRCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusRCode() {
        return statusRCode;
    }

    public void setStatusRCode(String statusRCode) {
        this.statusRCode = statusRCode;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getInvoiceOrgCode() {
        return invoiceOrgCode;
    }

    public void setInvoiceOrgCode(String invoiceOrgCode) {
        this.invoiceOrgCode = invoiceOrgCode;
    }

    public String getCreatedWorkOrder() {
        return createdWorkOrder;
    }

    public void setCreatedWorkOrder(String createdWorkOrder) {
        this.createdWorkOrder = createdWorkOrder;
    }

    public BigInteger getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(BigInteger updateCount) {
        this.updateCount = updateCount;
    }

    public UserDefinedFields getUserDefinedFields() {
        return userDefinedFields;
    }

    public void setUserDefinedFields(UserDefinedFields userDefinedFields) {
        this.userDefinedFields = userDefinedFields;
    }

    @Override
    public String toString() {
        return "EquipmentReservationAdjustment{" +
                "code='" + code + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", customerRentalCode='" + customerRentalCode + '\'' +
                ", adjustmentCode='" + adjustmentCode + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", typeRCode='" + typeRCode + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", statusRCode='" + statusRCode + '\'' +
                ", taxCode='" + taxCode + '\'' +
                ", date=" + date +
                ", comments='" + comments + '\'' +
                ", quantity=" + quantity +
                ", rate=" + rate +
                ", totalAmount=" + totalAmount +
                ", taxAmount=" + taxAmount +
                ", invoice='" + invoice + '\'' +
                ", invoiceOrgCode='" + invoiceOrgCode + '\'' +
                ", createdWorkOrder='" + createdWorkOrder + '\'' +
                ", updateCount=" + updateCount +
                ", userDefinedFields=" + userDefinedFields +
                '}';
    }
}

