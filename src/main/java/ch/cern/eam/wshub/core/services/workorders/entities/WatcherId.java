package ch.cern.eam.wshub.core.services.workorders.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatcherId implements Serializable {
    private String entity = "EVNT";
    private String table = "R5EVENTS";
    private String workOrderCode;
    private String person;
}
