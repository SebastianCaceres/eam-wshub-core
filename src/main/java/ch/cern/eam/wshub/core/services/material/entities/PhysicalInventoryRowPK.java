package ch.cern.eam.wshub.core.services.material.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class PhysicalInventoryRowPK implements Serializable {
    private String physicalInventoryCode;
    private BigInteger lineNumber;

    public PhysicalInventoryRowPK() {}

    public PhysicalInventoryRowPK(String physicalInventoryCode, BigInteger lineNumber) {
        this.physicalInventoryCode = physicalInventoryCode;
        this.lineNumber = lineNumber;
    }

    public String getPhysicalInventoryCode() {
        return physicalInventoryCode;
    }

    public void setPhysicalInventoryCode(String physicalInventoryCode) {
        this.physicalInventoryCode = physicalInventoryCode;
    }

    public BigInteger getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(BigInteger lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhysicalInventoryRowPK that = (PhysicalInventoryRowPK) o;
        return Objects.equals(physicalInventoryCode, that.physicalInventoryCode) &&
                Objects.equals(lineNumber, that.lineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(physicalInventoryCode, lineNumber);
    }
}
