package ch.cern.eam.wshub.core.services.workorders.entities;

import ch.cern.eam.wshub.core.annotations.GridField;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Entity
@NamedNativeQuery(name = "FINDACT", query = "select * from R5ACTIVITIES LEFT OUTER JOIN R5TASKS ON R5ACTIVITIES.ACT_TASK = R5TASKS.TSK_CODE WHERE ACT_EVENT = :workOrder ORDER BY ACT_ACT", resultClass = Activity.class)
@Table(name = "R5ACTIVITIES")
public class Activity  {

	@Id
	@Column(name = "ACT_ACT")
	@GridField(name="activity")
	
	private BigInteger activityCode;

	@Column(name = "ACT_NOTE")
	@GridField(name="activitynote")
	
	private String activityNote;

	@Column(name = "ACT_EVENT")
	@GridField(name="workordernum")
	
	private String workOrderNumber;

	@Column(name = "ACT_PERSONS")
	@GridField(name="personsreq")
	
	private BigInteger peopleRequired;

	@Column(name = "ACT_EST")
	@GridField(name="esthrs")
	
	private BigDecimal estimatedHours;

	@Column(name = "ACT_REM")
	@GridField(name="hrsremain")
	
	private BigDecimal hoursRemaining;

	@Column(name = "ACT_START")
	@GridField(name="actstartdate")
	
	private Date startDate;

	@Transient
	@GridField(name="actenddate")
	
	private Date endDate;

	@Column(name = "ACT_MATLIST")
	@GridField(name="matlcode")
	
	private String materialList;

	@Column(name = "ACT_TASK")
	@GridField(name="task")
	
	private String taskCode;

	@Column(name = "TSK_DESC")
	
	private String taskDesc;

	@Column(name = "TSK_REVISION")
	@GridField(name = "taskrev")
	private String taskRev;

	@Column(name = "ACT_TRADE")
	@GridField(name="trade")
	
	private String tradeCode;

	@Column(name = "ACT_QTY")
	@GridField(name="taskqty")
	
	private BigDecimal taskQty;

	@Column(name = "ACT_PERCOMPLETE")
	@GridField(name="percentcomplete")
	
	private String percentCompleted;

	@Transient
	private WorkOrderActivityChecklistItem[] checklists;

	@Transient
	private Map<String, WorkOrderActivityChecklistSignatureResult> signatures;

	@Transient
	private Boolean forceActivityExpansion;

	public String getWorkOrderNumber() {
		return workOrderNumber;
	}

	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}

	public BigInteger getPeopleRequired() {
		return peopleRequired;
	}

	public void setPeopleRequired(BigInteger peopleRequired) {
		this.peopleRequired = peopleRequired;
	}

	public BigDecimal getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(BigDecimal estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMaterialList() {
		return materialList;
	}

	public void setMaterialList(String materialList) {
		this.materialList = materialList;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public BigDecimal getHoursRemaining() {
		return hoursRemaining;
	}

	public void setHoursRemaining(BigDecimal hoursRemaining) {
		this.hoursRemaining = hoursRemaining;
	}

	public BigInteger getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(BigInteger activityCode) {
		this.activityCode = activityCode;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTaskRev() {
		return taskRev;
	}

	public void setTaskRev(String taskRev) {
		this.taskRev = taskRev;
	}

	public WorkOrderActivityChecklistItem[] getChecklists() {
		return checklists;
	}

	public void setChecklists(WorkOrderActivityChecklistItem[] checklists) {
		this.checklists = checklists;
	}

	public BigDecimal getTaskQty() {
		return taskQty;
	}

	public void setTaskQty(BigDecimal taskQty) {
		this.taskQty = taskQty;
	}

	public String getActivityNote() {return activityNote;}

	public void setActivityNote(String activityNote) {this.activityNote = activityNote; }

	public Map<String, WorkOrderActivityChecklistSignatureResult> getSignatures() {
		return signatures;
	}

	public void setSignatures(Map<String, WorkOrderActivityChecklistSignatureResult> signatures) {
		this.signatures = signatures;
	}

	public Boolean getForceActivityExpansion() {
		return forceActivityExpansion;
	}

	public void setForceActivityExpansion(Boolean forceActivityExpansion) {
		this.forceActivityExpansion = forceActivityExpansion;
	}

	public String getPercentCompleted() {
		return percentCompleted;
	}

	public void setPercentCompleted(String percentCompleted) {
		this.percentCompleted = percentCompleted;
	}

	@Override
	public String toString() {
		return "Activity{" +
				"activityCode='" + activityCode + '\'' +
				", activityNote='" + activityNote + '\'' +
				", workOrderNumber='" + workOrderNumber + '\'' +
				", peopleRequired='" + peopleRequired + '\'' +
				", estimatedHours='" + estimatedHours + '\'' +
				", hoursRemaining='" + hoursRemaining + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", materialList='" + materialList + '\'' +
				", taskCode='" + taskCode + '\'' +
				", taskDesc='" + taskDesc + '\'' +
				", taskRev='" + taskRev + '\'' +
				", tradeCode='" + tradeCode + '\'' +
				", taskQty='" + taskQty + '\'' +
				", checklists=" + Arrays.toString(checklists) +
				"signatures=" + signatures +
				'}';
	}

}
