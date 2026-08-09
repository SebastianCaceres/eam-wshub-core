package ch.cern.eam.wshub.core.services.equipment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "R5NCROBSERVATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NCRObservation {

    @Id
    @Column(name = "NCO_CODE")
    private String code;

    @Column(name = "NCO_NCF")
    private String nonConformityCode;

    @Column(name = "NCO_DESC")
    private String description;
}
