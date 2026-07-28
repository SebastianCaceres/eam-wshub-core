package ch.cern.eam.wshub.core.services.workorders.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TaskPlan {

	private String code;

	private String description;

	private BigInteger taskRevision;

	private Boolean performedByRequired;

	private Boolean reviewedByRequired;

	private String viewOnlyResponsibility;

	private String reviewResponsibility;

	private String performByResponsibility;

	private String performBy2Responsibility;

	private Boolean activeChecklist;

	private String tradeCode;

	private String classCode;

	private BigDecimal estimatedHours;

	private BigInteger peopleRequired;

	private String typeCode;

	private String revisionStatus;

	private Boolean outOfService;

	private String equipmentType;

	private String equipmentClass;

	private String materialList;

	private UserDefinedFields userDefinedFields;

	private String workOrderDescription;

	private String workOrderType;

	private String workOrderClass;

	private String workOrderStatus;

	private String workOrderPriority;

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

	public BigInteger getTaskRevision() {
		return taskRevision;
	}

	public void setTaskRevision(BigInteger taskRevision) {
		this.taskRevision = taskRevision;
	}

	public Boolean getPerformedByRequired() {
		return performedByRequired;
	}

	public void setPerformedByRequired(Boolean performedByRequired) {
		this.performedByRequired = performedByRequired;
	}

	public Boolean getReviewedByRequired() {
		return reviewedByRequired;
	}

	public void setReviewedByRequired(Boolean reviewedByRequired) {
		this.reviewedByRequired = reviewedByRequired;
	}

	public String getReviewResponsibility() {
		return reviewResponsibility;
	}

	public void setReviewResponsibility(String reviewResponsibility) {
		this.reviewResponsibility = reviewResponsibility;
	}

	public String getPerformByResponsibility() {
		return performByResponsibility;
	}

	public void setPerformByResponsibility(String performByResponsibility) {
		this.performByResponsibility = performByResponsibility;
	}

	public String getPerformBy2Responsibility() {
		return performBy2Responsibility;
	}

	public void setPerformBy2Responsibility(String performBy2Responsibility) {
		this.performBy2Responsibility = performBy2Responsibility;
	}

	public Boolean getActiveChecklist() {
		return activeChecklist;
	}

	public void setActiveChecklist(Boolean activeChecklist) {
		this.activeChecklist = activeChecklist;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public BigDecimal getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(BigDecimal estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public BigInteger getPeopleRequired() {
		return peopleRequired;
	}

	public void setPeopleRequired(BigInteger peopleRequired) {
		this.peopleRequired = peopleRequired;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getRevisionStatus() {
		return revisionStatus;
	}

	public void setRevisionStatus(String revisionStatus) {
		this.revisionStatus = revisionStatus;
	}

	public Boolean getOutOfService() {
		return outOfService;
	}

	public void setOutOfService(Boolean outOfService) {
		this.outOfService = outOfService;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getEquipmentClass() {
		return equipmentClass;
	}

	public void setEquipmentClass(String equipmentClass) {
		this.equipmentClass = equipmentClass;
	}

	public String getMaterialList() {
		return materialList;
	}

	public void setMaterialList(String materialList) {
		this.materialList = materialList;
	}

	public UserDefinedFields getUserDefinedFields() {
		return userDefinedFields;
	}

	public void setUserDefinedFields(UserDefinedFields userDefinedFields) {
		this.userDefinedFields = userDefinedFields;
	}

	public String getViewOnlyResponsibility() {
		return viewOnlyResponsibility;
	}

	public void setViewOnlyResponsibility(String viewOnlyResponsibility) {
		this.viewOnlyResponsibility = viewOnlyResponsibility;
	}

	public String getWorkOrderDescription() {
		return workOrderDescription;
	}

	public void setWorkOrderDescription(String workOrderDescription) {
		this.workOrderDescription = workOrderDescription;
	}

	public String getWorkOrderType() {
		return workOrderType;
	}

	public void setWorkOrderType(String workOrderType) {
		this.workOrderType = workOrderType;
	}

	public String getWorkOrderClass() {
		return workOrderClass;
	}

	public void setWorkOrderClass(String workOrderClass) {
		this.workOrderClass = workOrderClass;
	}

	public String getWorkOrderStatus() {
		return workOrderStatus;
	}

	public void setWorkOrderStatus(String workOrderStatus) {
		this.workOrderStatus = workOrderStatus;
	}

	public String getWorkOrderPriority() {
		return workOrderPriority;
	}

	public void setWorkOrderPriority(String workOrderPriority) {
		this.workOrderPriority = workOrderPriority;
	}

	@Override
	public String toString() {
		return "TaskPlan{" +
				"activeChecklist=" + activeChecklist +
				", classCode='" + classCode + '\'' +
				", code='" + code + '\'' +
				", description='" + description + '\'' +
				", equipmentClass='" + equipmentClass + '\'' +
				", equipmentType='" + equipmentType + '\'' +
				", estimatedHours=" + estimatedHours +
				", materialList='" + materialList + '\'' +
				", outOfService=" + outOfService +
				", peopleRequired=" + peopleRequired +
				", performBy2Responsibility='" + performBy2Responsibility + '\'' +
				", performByResponsibility='" + performByResponsibility + '\'' +
				", performedByRequired=" + performedByRequired +
				", reviewedByRequired=" + reviewedByRequired +
				", reviewResponsibility='" + reviewResponsibility + '\'' +
				", revisionStatus='" + revisionStatus + '\'' +
				", taskRevision=" + taskRevision +
				", tradeCode='" + tradeCode + '\'' +
				", typeCode='" + typeCode + '\'' +
				", userDefinedFields=" + userDefinedFields +
				", viewOnlyResponsibility='" + viewOnlyResponsibility + '\'' +
				", workOrderClass='" + workOrderClass + '\'' +
				", workOrderDescription='" + workOrderDescription + '\'' +
				", workOrderPriority='" + workOrderPriority + '\'' +
				", workOrderStatus='" + workOrderStatus + '\'' +
				", workOrderType='" + workOrderType + '\'' +
				'}';
	}
  
}
