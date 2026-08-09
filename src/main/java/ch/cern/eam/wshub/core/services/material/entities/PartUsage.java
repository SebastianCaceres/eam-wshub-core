package ch.cern.eam.wshub.core.services.material.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "R5PARTUSAGES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartUsage {

    @Id
    @Column(name = "PRU_CODE")
    private String code;

    @Column(name = "PRU_EVENT")
    private String eventCode;

    @Column(name = "PRU_PART")
    private String partCode;

    @Column(name = "PRU_QTY")
    private Double quantity;
}
