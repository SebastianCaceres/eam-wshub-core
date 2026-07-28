package ch.cern.eam.wshub.core.services.material.entities;

import ch.cern.eam.wshub.core.annotations.GridField;
import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoPoReceiptPart  {

    @GridField(name = "receiptline")
    
    private BigInteger transactionLineId;

    @GridField(name = "receiptcode")
    
    private String transactionCode;

    @GridField(name = "partcode")
    
    private String part;

    @GridField(name = "partdesc")
    
    private String partDescription;

    @GridField(name = "receiptqty")
    
    private BigDecimal quantity;

    @GridField(name = "partuom")
    
    private String unit;

    @GridField(name = "bincode")
    
    private String bin;

    @GridField(name = "lotcode")
    
    private String lot;

    @GridField(name = "manufacturerlot")
    
    private String manufacturer;

    @GridField(name = "printqty")
    
    private String printQuantity;

    @GridField(name = "price")
    
    private BigDecimal price;

    @GridField(name = "assetid")
    
    private String asset;

    @GridField(name = "byasset")
    
    private String trackByAsset;

    private String condition;

    private UserDefinedFields userDefinedFields;

}
