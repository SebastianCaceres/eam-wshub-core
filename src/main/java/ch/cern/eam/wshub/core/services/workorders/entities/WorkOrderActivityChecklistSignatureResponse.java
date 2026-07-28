package ch.cern.eam.wshub.core.services.workorders.entities;

import java.util.Date;

public class WorkOrderActivityChecklistSignatureResponse  {
    private String signer;

    private Date timeStamp;

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
