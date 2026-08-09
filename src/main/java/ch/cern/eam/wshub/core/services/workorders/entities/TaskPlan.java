package ch.cern.eam.wshub.core.services.workorders.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "R5TASKPLANS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskPlan {

    @Id
    @Column(name = "TKP_CODE")
    private String code;

    @Column(name = "TKP_DESC")
    private String description;

    @Column(name = "TKP_DURATION")
    private Double duration;

    @Transient
    private BigInteger taskRevision;

    @Transient
    private String revisionStatus;

    @Transient
    private String viewOnlyResponsibility;

    @Transient
    private Boolean performedByRequired;

    @Transient
    private Boolean reviewedByRequired;

    @Transient
    private UserDefinedFields userDefinedFields;
}
