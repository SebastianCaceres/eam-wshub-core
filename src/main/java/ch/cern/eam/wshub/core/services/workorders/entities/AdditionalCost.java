package ch.cern.eam.wshub.core.services.workorders.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "R5ADDITIONALCOSTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalCost {

    @Id
    @Column(name = "ADC_CODE")
    private String code;

    @Column(name = "ADC_EVENT")
    private String eventCode;

    @Column(name = "ADC_COST")
    private Double cost;

    @Column(name = "ADC_DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "ADC_DESC")
    private String description;
}
