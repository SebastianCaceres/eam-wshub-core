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

    public BigInteger getTransactionLineId() { return transactionLineId; }
    public void setTransactionLineId(BigInteger transactionLineId) { this.transactionLineId = transactionLineId; }

    public String getTransactionCode() { return transactionCode; }
    public void setTransactionCode(String transactionCode) { this.transactionCode = transactionCode; }

    public String getPart() { return part; }
    public void setPart(String part) { this.part = part; }

    public String getPartDescription() { return partDescription; }
    public void setPartDescription(String partDescription) { this.partDescription = partDescription; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getBin() { return bin; }
    public void setBin(String bin) { this.bin = bin; }

    public String getLot() { return lot; }
    public void setLot(String lot) { this.lot = lot; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getPrintQuantity() { return printQuantity; }
    public void setPrintQuantity(String printQuantity) { this.printQuantity = printQuantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getAsset() { return asset; }
    public void setAsset(String asset) { this.asset = asset; }

    public String getTrackByAsset() { return trackByAsset; }
    public void setTrackByAsset(String trackByAsset) { this.trackByAsset = trackByAsset; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public UserDefinedFields getUserDefinedFields() { return userDefinedFields; }
    public void setUserDefinedFields(UserDefinedFields userDefinedFields) { this.userDefinedFields = userDefinedFields; }
}
