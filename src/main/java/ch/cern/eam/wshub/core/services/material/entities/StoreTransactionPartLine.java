package ch.cern.eam.wshub.core.services.material.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import net.datastream.schemas.mp_fields.*;
import org.openapplications.oagis_segments.AMOUNT;
import org.openapplications.oagis_segments.QUANTITY;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "EAM_STORE_TRANSFER_LINES")
public class StoreTransactionPartLine  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    private String partCode;
    
    private String repairable;
    
    private BigDecimal transactionQuantity;
    
    private BigDecimal repairQuantity;
    
    private String fromBinCode;
    
    private String toBinCode;
    
    private String lotCode;
    
    private BigDecimal price;
    
    private String assetCode;
    
    private String serialNumber;
    
    private UserDefinedFields StandardUserDefinedFields;
    
    private String partConditionTemplateConditionCode;

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(final String partCode) {
        this.partCode = partCode;
    }

    public String getRepairable() {
        return repairable;
    }

    public void setRepairable(final String repairable) {
        this.repairable = repairable;
    }

    public BigDecimal getTransactionQuantity() {
        return transactionQuantity;
    }

    public void setTransactionQuantity(final BigDecimal transactionQuantity) {
        this.transactionQuantity = transactionQuantity;
    }

    public BigDecimal getRepairQuantity() {
        return repairQuantity;
    }

    public void setRepairQuantity(final BigDecimal repairQuantity) {
        this.repairQuantity = repairQuantity;
    }

    public String getFromBinCode() {
        return fromBinCode;
    }

    public void setFromBinCode(final String fromBinCode) {
        this.fromBinCode = fromBinCode;
    }

    public String getToBinCode() {
        return toBinCode;
    }

    public void setToBinCode(final String toBinCode) {
        this.toBinCode = toBinCode;
    }

    public String getLotCode() {
        return lotCode;
    }

    public void setLotCode(final String lotCode) {
        this.lotCode = lotCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(final String assetCode) {
        this.assetCode = assetCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public UserDefinedFields getStandardUserDefinedFields() {
        return StandardUserDefinedFields;
    }

    public void setStandardUserDefinedFields(final UserDefinedFields standardUserDefinedFields) {
        StandardUserDefinedFields = standardUserDefinedFields;
    }

    public String getPartConditionTemplateConditionCode() {
        return partConditionTemplateConditionCode;
    }

    public void setPartConditionTemplateConditionCode(final String partConditionTemplateConditionCode) {
        this.partConditionTemplateConditionCode = partConditionTemplateConditionCode;
    }

    @Override
    public String toString() {
        return "StoreTransactionPartLine{" +
                "partCode='" + partCode + '\'' +
                ", repairable='" + repairable + '\'' +
                ", transactionQuantity=" + transactionQuantity +
                ", repairQuantity=" + repairQuantity +
                ", fromBinCode='" + fromBinCode + '\'' +
                ", toBinCode='" + toBinCode + '\'' +
                ", lotCode='" + lotCode + '\'' +
                ", price=" + price +
                ", assetCode='" + assetCode + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", StandardUserDefinedFields=" + StandardUserDefinedFields +
                ", partConditionTemplateConditionCode='" + partConditionTemplateConditionCode + '\'' +
                '}';
    }
}
