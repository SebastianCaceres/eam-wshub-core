package ch.cern.eam.wshub.core.services.equipment.entities;

import ch.cern.eam.wshub.core.services.entities.CustomField;
import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

public class EquipmentConfigurationEntity  {

    @Transient
    
    private String equipmentConfigCode;
    @Transient
    
    private String equipmentConfigDesc;

    @Transient
    
    private String organizationCode;

    @Transient
    
    private String organizationDesc;

    @Transient
    
    private String equipmentConfigStatusCode;

    @Transient
    
    private String equipmentConfigStatusDesc;

    @Transient
    
    private String configurationDepartmentCode;

    @Transient
    
    private String configurationDepartmentDesc;

    @Transient
    
    private BigDecimal revisionNum;

    @Transient
    
    private String equipmentTypeCode;

    @Transient
    
    private String equipmentTypeDesc;

    @Transient
    
    private CustomField[] customFields;

    // Configuration Details
    @Transient
    
    private String configurationClassCode;

    @Transient
    
    private String configurationClassDesc;

    @Transient
    
    private String configurationCategoryCode;

    @Transient
    
    private String configurationCategoryDesc;

    @Transient
    
    private Date dateCreated;

    @Transient
    
    private Date dateUpdated;

    @Transient
    
    private String createdBy;

    @Transient
    
    private String updatedBy;

    @Transient
    
    private String classOrganizationCode;

    @Transient
    
    private String getClassOrganizationDesc;

    @Transient
    
    private String costCode;

    @Transient
    
    private String costCodeDesc;

    @Transient
    
    private BigDecimal equipmentValue;

    // Equipment Generation Details
    @Transient
    
    private String equipmentPrefix;

    @Transient
    
    private String equipmentSuffix;

    @Transient
    
    private Boolean createAsSpecific;

    @Transient
    
    private String equipmentStatusCode;

    @Transient
    
    private String equipmentStatusDesc;

    @Transient
    
    private BigInteger equipmentSequenceLength;

    @Transient
    
    private Boolean autoNumber;

    @Transient
    
    private String sampleCode;

    @Transient
    
    private String commissioningWONum;

    @Transient
    
    private String commissioningWODesc;

    // Tracking Details
    @Transient
    
    private String manufacturerCode;

    @Transient
    
    private String model;

    @Transient
    
    private String modelRevision;

    @Transient
    
    private String partCode;

    @Transient
    
    private UserDefinedFields userDefinedFields;

    public String getEquipmentConfigCode() {
        return equipmentConfigCode;
    }

    public void setEquipmentConfigCode(String equipmentConfigCode) {
        this.equipmentConfigCode = equipmentConfigCode;
    }

    public String getEquipmentConfigDesc() {
        return equipmentConfigDesc;
    }

    public void setEquipmentConfigDesc(String equipmentConfigDesc) {
        this.equipmentConfigDesc = equipmentConfigDesc;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationDesc() {
        return organizationDesc;
    }

    public void setOrganizationDesc(String organizationDesc) {
        this.organizationDesc = organizationDesc;
    }

    public String getEquipmentConfigStatusCode() {
        return equipmentConfigStatusCode;
    }

    public void setEquipmentConfigStatusCode(String equipmentConfigStatusCode) {
        this.equipmentConfigStatusCode = equipmentConfigStatusCode;
    }

    public String getEquipmentConfigStatusDesc() {
        return equipmentConfigStatusDesc;
    }

    public void setEquipmentConfigStatusDesc(String equipmentConfigStatusDesc) {
        this.equipmentConfigStatusDesc = equipmentConfigStatusDesc;
    }

    public String getConfigurationDepartmentCode() {
        return configurationDepartmentCode;
    }

    public void setConfigurationDepartmentCode(String configurationDepartmentCode) {
        this.configurationDepartmentCode = configurationDepartmentCode;
    }

    public String getConfigurationDepartmentDesc() {
        return configurationDepartmentDesc;
    }

    public void setConfigurationDepartmentDesc(String configurationDepartmentDesc) {
        this.configurationDepartmentDesc = configurationDepartmentDesc;
    }

    public BigDecimal getRevisionNum() {
        return revisionNum;
    }

    public void setRevisionNum(BigDecimal revisionNum) {
        this.revisionNum = revisionNum;
    }

    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
    }

    public String getEquipmentTypeDesc() {
        return equipmentTypeDesc;
    }

    public void setEquipmentTypeDesc(String equipmentTypeDesc) {
        this.equipmentTypeDesc = equipmentTypeDesc;
    }

    public CustomField[] getCustomFields() {
        return customFields;
    }

    public void setCustomFields(CustomField[] customFields) {
        this.customFields = customFields;
    }

    public String getConfigurationClassCode() {
        return configurationClassCode;
    }

    public void setConfigurationClassCode(String configurationClassCode) {
        this.configurationClassCode = configurationClassCode;
    }

    public String getConfigurationClassDesc() {
        return configurationClassDesc;
    }

    public void setConfigurationClassDesc(String configurationClassDesc) {
        this.configurationClassDesc = configurationClassDesc;
    }

    public String getConfigurationCategoryCode() {
        return configurationCategoryCode;
    }

    public void setConfigurationCategoryCode(String configurationCategoryCode) {
        this.configurationCategoryCode = configurationCategoryCode;
    }

    public String getConfigurationCategoryDesc() {
        return configurationCategoryDesc;
    }

    public void setConfigurationCategoryDesc(String configurationCategoryDesc) {
        this.configurationCategoryDesc = configurationCategoryDesc;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getClassOrganizationCode() {
        return classOrganizationCode;
    }

    public void setClassOrganizationCode(String classOrganizationCode) {
        this.classOrganizationCode = classOrganizationCode;
    }

    public String getGetClassOrganizationDesc() {
        return getClassOrganizationDesc;
    }

    public void setGetClassOrganizationDesc(String getClassOrganizationDesc) {
        this.getClassOrganizationDesc = getClassOrganizationDesc;
    }

    public String getCostCode() {
        return costCode;
    }

    public void setCostCode(String costCode) {
        this.costCode = costCode;
    }

    public String getCostCodeDesc() {
        return costCodeDesc;
    }

    public void setCostCodeDesc(String costCodeDesc) {
        this.costCodeDesc = costCodeDesc;
    }

    public BigDecimal getEquipmentValue() {
        return equipmentValue;
    }

    public void setEquipmentValue(BigDecimal equipmentValue) {
        this.equipmentValue = equipmentValue;
    }

    public String getEquipmentPrefix() {
        return equipmentPrefix;
    }

    public void setEquipmentPrefix(String equipmentPrefix) {
        this.equipmentPrefix = equipmentPrefix;
    }

    public String getEquipmentSuffix() {
        return equipmentSuffix;
    }

    public void setEquipmentSuffix(String equipmentSuffix) {
        this.equipmentSuffix = equipmentSuffix;
    }

    public Boolean getCreateAsSpecific() {
        return createAsSpecific;
    }

    public void setCreateAsSpecific(Boolean createAsSpecific) {
        this.createAsSpecific = createAsSpecific;
    }

    public String getEquipmentStatusCode() {
        return equipmentStatusCode;
    }

    public void setEquipmentStatusCode(String equipmentStatusCode) {
        this.equipmentStatusCode = equipmentStatusCode;
    }

    public String getEquipmentStatusDesc() {
        return equipmentStatusDesc;
    }

    public void setEquipmentStatusDesc(String equipmentStatusDesc) {
        this.equipmentStatusDesc = equipmentStatusDesc;
    }

    public BigInteger getEquipmentSequenceLength() {
        return equipmentSequenceLength;
    }

    public void setEquipmentSequenceLength(BigInteger equipmentSequenceLength) {
        this.equipmentSequenceLength = equipmentSequenceLength;
    }

    public Boolean getAutoNumber() {
        return autoNumber;
    }

    public void setAutoNumber(Boolean autoNumber) {
        this.autoNumber = autoNumber;
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public String getCommissioningWONum() {
        return commissioningWONum;
    }

    public void setCommissioningWONum(String commissioningWONum) {
        this.commissioningWONum = commissioningWONum;
    }

    public String getCommissioningWODesc() {
        return commissioningWODesc;
    }

    public void setCommissioningWODesc(String commissioningWODesc) {
        this.commissioningWODesc = commissioningWODesc;
    }

    public String getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelRevision() {
        return modelRevision;
    }

    public void setModelRevision(String modelRevision) {
        this.modelRevision = modelRevision;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public UserDefinedFields getUserDefinedFields() {
        return userDefinedFields;
    }

    public void setUserDefinedFields(UserDefinedFields userDefinedFields) {
        this.userDefinedFields = userDefinedFields;
    }

    @Override
    public String toString() {
        return "EquipmentConfigurationEntity{" +
                "equipmentConfigCode='" + equipmentConfigCode + '\'' +
                ", equipmentConfigDesc='" + equipmentConfigDesc + '\'' +
                ", organizationCode='" + organizationCode + '\'' +
                ", organizationDesc='" + organizationDesc + '\'' +
                ", equipmentConfigStatusCode='" + equipmentConfigStatusCode + '\'' +
                ", equipmentConfigStatusDesc='" + equipmentConfigStatusDesc + '\'' +
                ", configurationDepartmentCode='" + configurationDepartmentCode + '\'' +
                ", configurationDepartmentDesc='" + configurationDepartmentDesc + '\'' +
                ", revisionNum=" + revisionNum +
                ", equipmentTypeCode='" + equipmentTypeCode + '\'' +
                ", equipmentTypeDesc='" + equipmentTypeDesc + '\'' +
                ", customFields=" + Arrays.toString(customFields) +
                ", configurationClassCode='" + configurationClassCode + '\'' +
                ", configurationClassDesc='" + configurationClassDesc + '\'' +
                ", configurationCategoryCode='" + configurationCategoryCode + '\'' +
                ", configurationCategoryDesc='" + configurationCategoryDesc + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", classOrganizationCode='" + classOrganizationCode + '\'' +
                ", getClassOrganizationDesc='" + getClassOrganizationDesc + '\'' +
                ", costCode='" + costCode + '\'' +
                ", costCodeDesc='" + costCodeDesc + '\'' +
                ", equipmentValue=" + equipmentValue +
                ", equipmentPrefix='" + equipmentPrefix + '\'' +
                ", equipmentSuffix='" + equipmentSuffix + '\'' +
                ", createAsSpecific=" + createAsSpecific +
                ", equipmentStatusCode='" + equipmentStatusCode + '\'' +
                ", equipmentStatusDesc='" + equipmentStatusDesc + '\'' +
                ", equipmentSequenceLength=" + equipmentSequenceLength +
                ", autoNumber=" + autoNumber +
                ", sampleCode='" + sampleCode + '\'' +
                ", commissioningWONum='" + commissioningWONum + '\'' +
                ", commissioningWODesc='" + commissioningWODesc + '\'' +
                ", manufacturerCode='" + manufacturerCode + '\'' +
                ", model='" + model + '\'' +
                ", modelRevision='" + modelRevision + '\'' +
                ", partCode='" + partCode + '\'' +
                ", userDefinedFields=" + userDefinedFields +
                '}';
    }
}
