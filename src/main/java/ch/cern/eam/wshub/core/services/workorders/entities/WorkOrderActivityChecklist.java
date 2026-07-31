package ch.cern.eam.wshub.core.services.workorders.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class WorkOrderActivityChecklist  {

    private String workOrderCode;

    private String organizationCode = "*";

    private long activityCode;

    private Long jobSequence;

    private Boolean rejectPerformedBy;

    private Boolean rejectPerformedBy2;

    private String rejectionReason;

    private String conditionOptionUserDefinedCode;

    private UserDefinedFields userDefinedFields;

    public WorkOrderActivityChecklist() {
    }

    public WorkOrderActivityChecklist(String workOrderCode, long activityCode) {
        this.workOrderCode = workOrderCode;
        this.activityCode = activityCode;
    }

    public String getWorkOrderCode() { return workOrderCode; }
    public void setWorkOrderCode(String workOrderCode) { this.workOrderCode = workOrderCode; }

    public String getOrganizationCode() { return organizationCode; }
    public void setOrganizationCode(String organizationCode) { this.organizationCode = organizationCode; }

    public long getActivityCode() { return activityCode; }
    public void setActivityCode(long activityCode) { this.activityCode = activityCode; }

    public Long getJobSequence() { return jobSequence; }
    public void setJobSequence(Long jobSequence) { this.jobSequence = jobSequence; }

    public Boolean getRejectPerformedBy() { return rejectPerformedBy; }
    public void setRejectPerformedBy(Boolean rejectPerformedBy) { this.rejectPerformedBy = rejectPerformedBy; }

    public Boolean getRejectPerformedBy2() { return rejectPerformedBy2; }
    public void setRejectPerformedBy2(Boolean rejectPerformedBy2) { this.rejectPerformedBy2 = rejectPerformedBy2; }

    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }

    public String getConditionOptionUserDefinedCode() { return conditionOptionUserDefinedCode; }
    public void setConditionOptionUserDefinedCode(String conditionOptionUserDefinedCode) { this.conditionOptionUserDefinedCode = conditionOptionUserDefinedCode; }

    public UserDefinedFields getUserDefinedFields() { return userDefinedFields; }
    public void setUserDefinedFields(UserDefinedFields userDefinedFields) { this.userDefinedFields = userDefinedFields; }
}
