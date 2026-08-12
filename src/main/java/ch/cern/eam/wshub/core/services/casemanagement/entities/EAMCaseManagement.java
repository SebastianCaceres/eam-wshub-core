package ch.cern.eam.wshub.core.services.casemanagement.entities;

import ch.cern.eam.wshub.core.services.entities.CustomField;
import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public class EAMCaseManagement  {
	
	protected String caseCode;
	
	protected String caseDescription;
	
	protected String equipmentCode;
	
	protected String caseType;
	
	protected String departmentCode;
	
	protected String statusCode;
	
	protected String systemStatusCode;
	
	protected String reviewedByDesign;
	
	protected Date dateReviewed;
	
	protected String createdBy;
	
	protected Date createdDate;
	
	protected String updatedby;
	
	protected Date dateUpdated;

	//CASE DETAILS
	
	protected String classCode;
	
	protected String locationCode;
	
	protected String serviceProblemCode;
	
	protected String area;
	
	protected String workaddress;
	
	protected String casePriorityCode;
	
	protected String casePriorityEntity;
	
	protected String costCode;
	
	protected Date eventStartDate;
	
	protected Date eventEndDate;
	
	protected Boolean regulatory;
	
	protected Boolean followUpRequired;
	
	protected Boolean isHazardousMaterial;
	
	protected BigDecimal estimatedTotalCost;
	
	protected BigDecimal totalCost;
	
	protected String currencyCode;
	
	protected Boolean costRefreshRequired;
	
	protected String caseParentCode;
	
	protected String workOrderCode;
	
	protected String permitToWorkCode;
	
	protected String shiftCode;
	
	protected String projectCode;
	
	protected String campaignCode;
	// --- //

	// Liner Reference Details
	
	protected String linearRefUom;
//	
//	protected LINEARREFERENCEEVENT_Type linearreferenceevent;
	
	protected String inspectionDirectionCode;
	
	protected String flowCode;
	
	protected BigDecimal startingAt;
	
	protected BigDecimal equipmentLength;
	// --- //

	// Tracking Details
	
	protected String requestedBy;
	
	protected Date dateRequested;
	
	protected String personResponsible;
	
	protected String email;
	
	protected String preparedBy;
	
	protected String preparedByEmail;
	
	protected String assignedTo;
	
	protected String assignedToEmail;
	
	protected Date scheduledStartDate;
	
	protected Date scheduledEndDate;
	
	protected Date requestedStart;
	
	protected Date requestedEnd;
	
	protected Date startDate;
	
	protected Date completedDate;
	
	protected String contactRecordCode;
	
	protected String contactRecordStatus;
	
	protected String sourceType;
	// --- //

	// Follow-Up Details
	
	protected String woDescription;
	
	protected String standardWo;
	
	protected String workOrderType;
	
	protected String workorderClassCode;
	
	protected String workorderStatus;
	
	protected String woPriority;
	
	protected String taskCode;
	
	protected String casetaskJobPlan;
	
	protected String tradeCode;
	
	protected BigDecimal estimatedHours;
	
	protected BigInteger persons;
	
	protected String followupWorkOrder;
	// --- //

	// Root Cause Details
	
	private BigDecimal downtimehours;
	
	private BigDecimal downtimecostvalue;
	
	private BigDecimal totalcost;
	
	private BigDecimal lostproductivityhours;
	
	private String problemdescription;
	
	private String whathappened;
	
	private String why1;
	
	private String why2;
	
	private String why3;
	
	private String why4;
	
	private String why5;
	
	private String solution;
	// --- //

	@JsonIgnore
	
	private CustomField[] customFields;

	private Map<String, String> customFieldMap;

	private UserDefinedFields userDefinedFields;

	private BigInteger updateCount;

	protected String hasDepartmentSecurity;
	
	protected String isEnhancedPlanningTask;
	
	protected String isCaseHaveTasks;

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getCaseDescription() {
		return caseDescription;
	}

	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getSystemStatusCode() {
		return systemStatusCode;
	}

	public void setSystemStatusCode(String systemStatusCode) {
		this.systemStatusCode = systemStatusCode;
	}

	public String getReviewedByDesign() {
		return reviewedByDesign;
	}

	public void setReviewedByDesign(String reviewedByDesign) {
		this.reviewedByDesign = reviewedByDesign;
	}

	public Date getDateReviewed() {
		return dateReviewed;
	}

	public void setDateReviewed(Date dateReviewed) {
		this.dateReviewed = dateReviewed;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getServiceProblemCode() {
		return serviceProblemCode;
	}

	public void setServiceProblemCode(String serviceProblemCode) {
		this.serviceProblemCode = serviceProblemCode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getWorkaddress() {
		return workaddress;
	}

	public void setWorkaddress(String workaddress) {
		this.workaddress = workaddress;
	}

	public String getCasePriorityCode() {
		return casePriorityCode;
	}

	public void setCasePriorityCode(String casePriorityCode) {
		this.casePriorityCode = casePriorityCode;
	}

	public String getCasePriorityEntity() {
		return casePriorityEntity;
	}

	public void setCasePriorityEntity(String casePriorityEntity) {
		this.casePriorityEntity = casePriorityEntity;
	}

	public String getCostCode() {
		return costCode;
	}

	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	public Date getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public Date getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public Boolean getRegulatory() {
		return regulatory;
	}

	public void setRegulatory(Boolean regulatory) {
		this.regulatory = regulatory;
	}

	public Boolean getFollowUpRequired() {
		return followUpRequired;
	}

	public void setFollowUpRequired(Boolean followUpRequired) {
		this.followUpRequired = followUpRequired;
	}

	public Boolean getHazardousMaterial() {
		return isHazardousMaterial;
	}

	public void setHazardousMaterial(Boolean hazardousMaterial) {
		isHazardousMaterial = hazardousMaterial;
	}

	public BigDecimal getEstimatedTotalCost() {
		return estimatedTotalCost;
	}

	public void setEstimatedTotalCost(BigDecimal estimatedTotalCost) {
		this.estimatedTotalCost = estimatedTotalCost;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Boolean getCostRefreshRequired() {
		return costRefreshRequired;
	}

	public void setCostRefreshRequired(Boolean costRefreshRequired) {
		this.costRefreshRequired = costRefreshRequired;
	}

	public String getCaseParentCode() {
		return caseParentCode;
	}

	public void setCaseParentCode(String caseParentCode) {
		this.caseParentCode = caseParentCode;
	}

	public String getWorkOrderCode() {
		return workOrderCode;
	}

	public void setWorkOrderCode(String workOrderCode) {
		this.workOrderCode = workOrderCode;
	}

	public String getPermitToWorkCode() {
		return permitToWorkCode;
	}

	public void setPermitToWorkCode(String permitToWorkCode) {
		this.permitToWorkCode = permitToWorkCode;
	}

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	public String getLinearRefUom() {
		return linearRefUom;
	}

	public void setLinearRefUom(String linearRefUom) {
		this.linearRefUom = linearRefUom;
	}

	public String getInspectionDirectionCode() {
		return inspectionDirectionCode;
	}

	public void setInspectionDirectionCode(String inspectionDirectionCode) {
		this.inspectionDirectionCode = inspectionDirectionCode;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public BigDecimal getStartingAt() {
		return startingAt;
	}

	public void setStartingAt(BigDecimal startingAt) {
		this.startingAt = startingAt;
	}

	public BigDecimal getEquipmentLength() {
		return equipmentLength;
	}

	public void setEquipmentLength(BigDecimal equipmentLength) {
		this.equipmentLength = equipmentLength;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public Date getDateRequested() {
		return dateRequested;
	}

	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}

	public String getPersonResponsible() {
		return personResponsible;
	}

	public void setPersonResponsible(String personResponsible) {
		this.personResponsible = personResponsible;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}

	public String getPreparedByEmail() {
		return preparedByEmail;
	}

	public void setPreparedByEmail(String preparedByEmail) {
		this.preparedByEmail = preparedByEmail;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getAssignedToEmail() {
		return assignedToEmail;
	}

	public void setAssignedToEmail(String assignedToEmail) {
		this.assignedToEmail = assignedToEmail;
	}

	public Date getScheduledStartDate() {
		return scheduledStartDate;
	}

	public void setScheduledStartDate(Date scheduledStartDate) {
		this.scheduledStartDate = scheduledStartDate;
	}

	public Date getScheduledEndDate() {
		return scheduledEndDate;
	}

	public void setScheduledEndDate(Date scheduledEndDate) {
		this.scheduledEndDate = scheduledEndDate;
	}

	public Date getRequestedStart() {
		return requestedStart;
	}

	public void setRequestedStart(Date requestedStart) {
		this.requestedStart = requestedStart;
	}

	public Date getRequestedEnd() {
		return requestedEnd;
	}

	public void setRequestedEnd(Date requestedEnd) {
		this.requestedEnd = requestedEnd;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public String getContactRecordCode() {
		return contactRecordCode;
	}

	public void setContactRecordCode(String contactRecordCode) {
		this.contactRecordCode = contactRecordCode;
	}

	public String getContactRecordStatus() {
		return contactRecordStatus;
	}

	public void setContactRecordStatus(String contactRecordStatus) {
		this.contactRecordStatus = contactRecordStatus;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getWoDescription() {
		return woDescription;
	}

	public void setWoDescription(String woDescription) {
		this.woDescription = woDescription;
	}

	public String getStandardWo() {
		return standardWo;
	}

	public void setStandardWo(String standardWo) {
		this.standardWo = standardWo;
	}

	public String getWorkOrderType() {
		return workOrderType;
	}

	public void setWorkOrderType(String workOrderType) {
		this.workOrderType = workOrderType;
	}

	public String getWorkorderClassCode() {
		return workorderClassCode;
	}

	public void setWorkorderClassCode(String workorderClassCode) {
		this.workorderClassCode = workorderClassCode;
	}

	public String getWorkorderStatus() {
		return workorderStatus;
	}

	public void setWorkorderStatus(String workorderStatus) {
		this.workorderStatus = workorderStatus;
	}

	public String getWoPriority() {
		return woPriority;
	}

	public void setWoPriority(String woPriority) {
		this.woPriority = woPriority;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getCasetaskJobPlan() {
		return casetaskJobPlan;
	}

	public void setCasetaskJobPlan(String casetaskJobPlan) {
		this.casetaskJobPlan = casetaskJobPlan;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public BigDecimal getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(BigDecimal estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public BigInteger getPersons() {
		return persons;
	}

	public void setPersons(BigInteger persons) {
		this.persons = persons;
	}

	public String getFollowupWorkOrder() {
		return followupWorkOrder;
	}

	public void setFollowupWorkOrder(String followupWorkOrder) {
		this.followupWorkOrder = followupWorkOrder;
	}

	public CustomField[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomField[] customFields) {
		this.customFields = customFields;
	}

	public Map<String, String> getCustomFieldMap() {
		return customFieldMap;
	}

	public void setCustomFieldMap(Map<String, String> customFieldMap) {
		this.customFieldMap = customFieldMap;
	}

	public UserDefinedFields getUserDefinedFields() {
		return userDefinedFields;
	}

	public void setUserDefinedFields(UserDefinedFields userDefinedFields) {
		this.userDefinedFields = userDefinedFields;
	}

	public BigInteger getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(BigInteger updateCount) {
		this.updateCount = updateCount;
	}

	public String getHasDepartmentSecurity() {
		return hasDepartmentSecurity;
	}

	public void setHasDepartmentSecurity(String hasDepartmentSecurity) {
		this.hasDepartmentSecurity = hasDepartmentSecurity;
	}

	public String getIsEnhancedPlanningTask() {
		return isEnhancedPlanningTask;
	}

	public void setIsEnhancedPlanningTask(String isEnhancedPlanningTask) {
		this.isEnhancedPlanningTask = isEnhancedPlanningTask;
	}

	public String getIsCaseHaveTasks() {
		return isCaseHaveTasks;
	}

	public void setIsCaseHaveTasks(String isCaseHaveTasks) {
		this.isCaseHaveTasks = isCaseHaveTasks;
	}

	public BigDecimal getDowntimehours() {
		return downtimehours;
	}

	public void setDowntimehours(BigDecimal downtimehours) {
		this.downtimehours = downtimehours;
	}

	public BigDecimal getDowntimecostvalue() {
		return downtimecostvalue;
	}

	public void setDowntimecostvalue(BigDecimal downtimecostvalue) {
		this.downtimecostvalue = downtimecostvalue;
	}

	public BigDecimal getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(BigDecimal totalcost) {
		this.totalcost = totalcost;
	}

	public BigDecimal getLostproductivityhours() {
		return lostproductivityhours;
	}

	public void setLostproductivityhours(BigDecimal lostproductivityhours) {
		this.lostproductivityhours = lostproductivityhours;
	}

	public String getProblemdescription() {
		return problemdescription;
	}

	public void setProblemdescription(String problemdescription) {
		this.problemdescription = problemdescription;
	}

	public String getWhathappened() {
		return whathappened;
	}

	public void setWhathappened(String whathappened) {
		this.whathappened = whathappened;
	}

	public String getWhy1() {
		return why1;
	}

	public void setWhy1(String why1) {
		this.why1 = why1;
	}

	public String getWhy2() {
		return why2;
	}

	public void setWhy2(String why2) {
		this.why2 = why2;
	}

	public String getWhy3() {
		return why3;
	}

	public void setWhy3(String why3) {
		this.why3 = why3;
	}

	public String getWhy4() {
		return why4;
	}

	public void setWhy4(String why4) {
		this.why4 = why4;
	}

	public String getWhy5() {
		return why5;
	}

	public void setWhy5(String why5) {
		this.why5 = why5;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	@Override
	public String toString() {
		return "EAMCaseManagement{" +
				"caseCode='" + caseCode + '\'' +
				", caseDescription='" + caseDescription + '\'' +
				", equipmentCode='" + equipmentCode + '\'' +
				", caseType='" + caseType + '\'' +
				", departmentCode='" + departmentCode + '\'' +
				", statusCode='" + statusCode + '\'' +
				", systemStatusCode='" + systemStatusCode + '\'' +
				", reviewedByDesign='" + reviewedByDesign + '\'' +
				", dateReviewed=" + dateReviewed +
				", createdBy='" + createdBy + '\'' +
				", createdDate=" + createdDate +
				", updatedby='" + updatedby + '\'' +
				", dateUpdated=" + dateUpdated +
				", classCode='" + classCode + '\'' +
				", locationCode='" + locationCode + '\'' +
				", serviceProblemCode='" + serviceProblemCode + '\'' +
				", area='" + area + '\'' +
				", workaddress='" + workaddress + '\'' +
				", casePriorityCode='" + casePriorityCode + '\'' +
				", casePriorityEntity='" + casePriorityEntity + '\'' +
				", costCode='" + costCode + '\'' +
				", eventStartDate=" + eventStartDate +
				", eventEndDate=" + eventEndDate +
				", regulatory='" + regulatory + '\'' +
				", followUpRequired=" + followUpRequired +
				", isHazardousMaterial=" + isHazardousMaterial +
				", estimatedTotalCost=" + estimatedTotalCost +
				", totalCost=" + totalCost +
				", currencyCode='" + currencyCode + '\'' +
				", costRefreshRequired='" + costRefreshRequired + '\'' +
				", caseParentCode='" + caseParentCode + '\'' +
				", workOrderCode='" + workOrderCode + '\'' +
				", permitToWorkCode='" + permitToWorkCode + '\'' +
				", shiftCode='" + shiftCode + '\'' +
				", projectCode='" + projectCode + '\'' +
				", campaignCode='" + campaignCode + '\'' +
				", linearRefUom='" + linearRefUom + '\'' +
				", inspectionDirectionCode='" + inspectionDirectionCode + '\'' +
				", flowCode='" + flowCode + '\'' +
				", startingAt=" + startingAt +
				", equipmentLength=" + equipmentLength +
				", requestedBy='" + requestedBy + '\'' +
				", dateRequested=" + dateRequested +
				", personResponsible='" + personResponsible + '\'' +
				", email='" + email + '\'' +
				", preparedBy='" + preparedBy + '\'' +
				", preparedByEmail='" + preparedByEmail + '\'' +
				", assignedTo='" + assignedTo + '\'' +
				", assignedToEmail='" + assignedToEmail + '\'' +
				", scheduledStartDate=" + scheduledStartDate +
				", scheduledEndDate=" + scheduledEndDate +
				", requestedStart=" + requestedStart +
				", requestedEnd=" + requestedEnd +
				", startDate=" + startDate +
				", completedDate=" + completedDate +
				", contactRecordCode='" + contactRecordCode + '\'' +
				", contactRecordStatus='" + contactRecordStatus + '\'' +
				", sourceType='" + sourceType + '\'' +
				", woDescription='" + woDescription + '\'' +
				", standardWo='" + standardWo + '\'' +
				", workOrderType='" + workOrderType + '\'' +
				", workorderClassCode='" + workorderClassCode + '\'' +
				", workorderStatus='" + workorderStatus + '\'' +
				", woPriority='" + woPriority + '\'' +
				", taskCode='" + taskCode + '\'' +
				", casetaskJobPlan='" + casetaskJobPlan + '\'' +
				", tradeCode='" + tradeCode + '\'' +
				", estimatedHours=" + estimatedHours +
				", persons=" + persons +
				", followupWorkOrder='" + followupWorkOrder + '\'' +
				", customFieldMap=" + customFieldMap +
				", userDefinedFields=" + userDefinedFields +
				", updateCount=" + updateCount +
				", hasDepartmentSecurity='" + hasDepartmentSecurity + '\'' +
				", isEnhancedPlanningTask='" + isEnhancedPlanningTask + '\'' +
				", isCaseHaveTasks='" + isCaseHaveTasks + '\'' +
				'}';
	}
}
