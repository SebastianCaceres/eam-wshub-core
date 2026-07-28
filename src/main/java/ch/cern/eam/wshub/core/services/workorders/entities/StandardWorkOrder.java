package ch.cern.eam.wshub.core.services.workorders.entities;

import ch.cern.eam.wshub.core.services.entities.CustomField;
import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;

import java.math.BigInteger;

public class StandardWorkOrder {

    private String code;
    
    private String desc;

    private String typeCode;
    
    private String typeDesc;

    private String workOrderTypeCode;
    
    private String workOrderTypeDesc;

    private BigInteger duration;

    private String classCode;
    
    private String classDesc;

    private String woClassCode;
    
    private String woClassDesc;

    private String equipmentClassCode;
    
    private String equipmentCassDesc;

    private String categoryCode;
    
    private String categoryDesc;

    private String priorityCode;
    
    private String priorityDesc;

    private Boolean outOfService = false;

    private CustomField[] customFields;

    private UserDefinedFields userDefinedFields;

    private String problemCode;

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

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getWorkOrderTypeCode() {
        return workOrderTypeCode;
    }

    public void setWorkOrderTypeCode(String workOrderTypeCode) {
        this.workOrderTypeCode = workOrderTypeCode;
    }

    public String getWorkOrderTypeDesc() {
        return workOrderTypeDesc;
    }

    public void setWorkOrderTypeDesc(String workOrderTypeDesc) {
        this.workOrderTypeDesc = workOrderTypeDesc;
    }

    public BigInteger getDuration() {
        return duration;
    }

    public void setDuration(BigInteger duration) {
        this.duration = duration;
    }

    public String getWoClassCode() {
        return woClassCode;
    }

    public void setWoClassCode(String woClassCode) {
        this.woClassCode = woClassCode;
    }

    public String getWoClassDesc() {
        return woClassDesc;
    }

    public void setWoClassDesc(String woClassDesc) {
        this.woClassDesc = woClassDesc;
    }

    public String getEquipmentClassCode() {
        return equipmentClassCode;
    }

    public void setEquipmentClassCode(String equipmentClassCode) {
        this.equipmentClassCode = equipmentClassCode;
    }

    public String getEquipmentCassDesc() {
        return equipmentCassDesc;
    }

    public void setEquipmentCassDesc(String equipmentCassDesc) {
        this.equipmentCassDesc = equipmentCassDesc;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getPriorityCode() {
        return priorityCode;
    }

    public void setPriorityCode(String priorityCode) {
        this.priorityCode = priorityCode;
    }

    public String getPriorityDesc() {
        return priorityDesc;
    }

    public void setPriorityDesc(String priorityDesc) {
        this.priorityDesc = priorityDesc;
    }

    public Boolean getOutOfService() {
        return outOfService;
    }

    public void setOutOfService(Boolean outOfService) {
        this.outOfService = outOfService;
    }

    public CustomField[] getCustomFields() {
        return customFields;
    }

    public void setCustomFields(CustomField[] customFields) {
        this.customFields = customFields;
    }

    public UserDefinedFields getUserDefinedFields() {
        return userDefinedFields;
    }

    public void setUserDefinedFields(UserDefinedFields userDefinedFields) {
        this.userDefinedFields = userDefinedFields;
    }

    public String getProblemCode() {
        return problemCode;
    }

    public void setProblemCode(String problemCode) {
        this.problemCode = problemCode;
    }

    @Override
    public String toString() {
        return "StandardWorkOrder{" +
                "categoryCode='" + categoryCode + '\'' +
                ", categoryDesc='" + categoryDesc + '\'' +
                ", classCode='" + classCode + '\'' +
                ", classDesc='" + classDesc + '\'' +
                ", code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", duration=" + duration +
                ", equipmentCassDesc='" + equipmentCassDesc + '\'' +
                ", equipmentClassCode='" + equipmentClassCode + '\'' +
                ", outOfService=" + outOfService +
                ", priorityCode='" + priorityCode + '\'' +
                ", priorityDesc='" + priorityDesc + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                ", woClassCode='" + woClassCode + '\'' +
                ", woClassDesc='" + woClassDesc + '\'' +
                ", workOrderTypeCode='" + workOrderTypeCode + '\'' +
                ", workOrderTypeDesc='" + workOrderTypeDesc + '\'' +
                '}';
    }
}
