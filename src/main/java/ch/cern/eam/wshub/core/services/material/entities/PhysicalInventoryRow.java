package ch.cern.eam.wshub.core.services.material.entities;
import java.io.Serializable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "R5STOCKLINES")
@IdClass(PhysicalInventoryRowPK.class)
public class PhysicalInventoryRow implements Serializable {
    @Id
    @Column(name = "STL_TRANS")
    
    String physicalInventoryCode;

    @Id
    @Column(name = "STL_LINE")
    
    BigInteger lineNumber;

    @Column(name = "STL_PART")
    
    String part;

    @Column(name = "STL_STORE")
    
    String store;

    @Column(name = "STL_BIN")
    
    String bin;

    @Column(name = "STL_LOT")
    
    String lot;

    @Column(name = "STL_EXPQTY")
    
    BigDecimal expectedQuantity;

    @Column(name = "STL_PHYQTY")
    
    BigDecimal physicalQuantity;

    // Field missing: description

    // Field missing: error message

    public String getPhysicalInventoryCode() {
        return physicalInventoryCode;
    }

    public void setPhysicalInventoryCode(String physicalInventoryCode) {
        this.physicalInventoryCode = physicalInventoryCode;
    }

    public BigInteger getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(BigInteger line) {
        this.lineNumber = line;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public BigDecimal getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(BigDecimal expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    public BigDecimal getPhysicalQuantity() {
        return physicalQuantity;
    }

    public void setPhysicalQuantity(BigDecimal physicalQuantity) {
        this.physicalQuantity = physicalQuantity;
    }

    @Override
    public String toString() {
        return "PhysicalInventoryRow ["
            + (physicalInventoryCode != null ? "physicalInventoryCode=" + physicalInventoryCode + ", " : "")
            + (lineNumber != null ? "lineNumber=" + lineNumber + ", " : "")
            + (part != null ? "part=" + part + ", " : "")
            + (store != null ? "store=" + store + ", " : "")
            + (bin != null ? "bin=" + bin + ", " : "")
            + (lot != null ? "lot=" + lot + ", " : "")
            + (expectedQuantity != null ? "expectedQuantity=" + expectedQuantity + ", " : "")
            + (physicalQuantity != null ? "physicalQuantity=" + physicalQuantity : "")
            + "]";
    }
}
