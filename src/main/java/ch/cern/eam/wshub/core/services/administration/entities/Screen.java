package ch.cern.eam.wshub.core.services.administration.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "R5SCREENS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Screen {

    @Id
    @Column(name = "SCR_CODE")
    private String code;

    @Column(name = "SCR_DESC")
    private String description;
}
