package ch.cern.eam.wshub.core.services.workorders.entities;

import java.util.Date;
import java.util.List;

public class WorkOrderActivityChecklistDefaultResult  {
    
    private String performer1Name;

    private String performer2Name;

    private String reviewerName;

    private String performer1Qualification;

    private String performer2Qualification;

    private String  reviewerQualification;

    private String rejectionReason;

    private String rejectPerformedBy;

    private String rejectPerformedBy2;

    List<UserQualification> userQualifications;

    Date timePerf1;

    Date timePerf2;

    Date timeRev1;

    public WorkOrderActivityChecklistDefaultResult(){
        rejectPerformedBy = "false";
        rejectPerformedBy2 = "false";
    }

    public String getPerformer1Name() {
        return performer1Name;
    }

    public void setPerformer1Name(String performer1Name) {
        this.performer1Name = performer1Name;
    }

    public String getPerformer2Name() {
        return performer2Name;
    }

    public void setPerformer2Name(String performer2Name) {
        this.performer2Name = performer2Name;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getPerformer1Qualification() {
        return performer1Qualification;
    }

    public void setPerformer1Qualification(String performer1Qualification) {
        this.performer1Qualification = performer1Qualification;
    }

    public String getPerformer2Qualification() {
        return performer2Qualification;
    }

    public void setPerformer2Qualification(String performer2Qualification) {
        this.performer2Qualification = performer2Qualification;
    }

    public String getReviewerQualification() {
        return reviewerQualification;
    }

    public void setReviewerQualification(String reviewerQualification) {
        this.reviewerQualification = reviewerQualification;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getRejectPerformedBy() {
        return rejectPerformedBy;
    }

    public void setRejectPerformedBy(String rejectPerformedBy) {
        this.rejectPerformedBy = rejectPerformedBy;
    }

    public String getRejectPerformedBy2() {
        return rejectPerformedBy2;
    }

    public void setRejectPerformedBy2(String rejectPerformedBy2) {
        this.rejectPerformedBy2 = rejectPerformedBy2;
    }

    public Date getTimePerf1() {
        return timePerf1;
    }

    public void setTimePerf1(Date timePerf1) {
        this.timePerf1 = timePerf1;
    }

    public Date getTimePerf2() {
        return timePerf2;
    }

    public void setTimePerf2(Date timePerf2) {
        this.timePerf2 = timePerf2;
    }

    public Date getTimeRev1() {
        return timeRev1;
    }

    public void setTimeRev1(Date timeRev1) {
        this.timeRev1 = timeRev1;
    }

    public List<UserQualification> getUserQualifications() {
        return userQualifications;
    }

    public void setUserQualifications(List<UserQualification> userQualifications) {
        this.userQualifications = userQualifications;
    }

}
