package ch.cern.eam.wshub.core.services.workorders.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WorkOrderActivityChecklist  {

    @NonNull
    
    private String workOrderCode;

    private String organizationCode = "*";

    @NonNull
    
    private long activityCode;

    private Long jobSequence;

    private Boolean rejectPerformedBy;

    private Boolean rejectPerformedBy2;

    private String rejectionReason;

    private String conditionOptionUserDefinedCode;

    private UserDefinedFields userDefinedFields;
}
