package ch.cern.eam.wshub.core.services.workorders.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;

import java.math.BigDecimal;
import java.util.Date;

public class SalesPrice {

    private String salesPriceCode;
    
    private String customerContractCode;
    
    private BigDecimal customerContractRevision;
    
    private String entityCode;
    
    private String code;
    
    private Date dateEffective;
    
    private Date dateExpired;
    
    private BigDecimal salesPrice;
    
    private String storeCode;
    
    private UserDefinedFields userDefinedFields;

    public String getCustomerContractCode() {
        return customerContractCode;
    }

    public void setCustomerContractCode(String customerContractCode) {
        this.customerContractCode = customerContractCode;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateEffective() {
        return dateEffective;
    }

    public void setDateEffective(Date dateEffective) {
        this.dateEffective = dateEffective;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getSalesPriceCode() {
        return salesPriceCode;
    }

    public void setSalesPriceCode(String salesPriceCode) {
        this.salesPriceCode = salesPriceCode;
    }

    public BigDecimal getCustomerContractRevision() {
        return customerContractRevision;
    }

    public void setCustomerContractRevision(BigDecimal customerContractRevision) {
        this.customerContractRevision = customerContractRevision;
    }

    public Date getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
    }

    public UserDefinedFields getUserDefinedFields() {
        return userDefinedFields;
    }

    public void setUserDefinedFields(UserDefinedFields userDefinedFields) {
        this.userDefinedFields = userDefinedFields;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }
}
