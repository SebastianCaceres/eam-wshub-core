package ch.cern.eam.wshub.core.services.material.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoPoReceipt  {

    private String code;

    private String description;

    private String organization;

    private String status;

    private String supplier;

    private String store;

    private String referenceNumber;

    private Date createdDate;

    private UserDefinedFields userDefinedFields;

    private List<NoPoReceiptPart> parts;

}
