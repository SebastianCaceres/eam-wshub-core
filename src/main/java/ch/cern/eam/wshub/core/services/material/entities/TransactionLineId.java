package ch.cern.eam.wshub.core.services.material.entities;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.math.BigInteger;

@Getter
@Setter
@ToString
@Embeddable
public class TransactionLineId implements Serializable  {

    private BigInteger transactionLineId;

    private String transactionCode;

    public TransactionLineId(String transactionCode, BigInteger transactionLineId) {
        this.transactionCode = transactionCode;
        this.transactionLineId = transactionLineId;
    }

    public TransactionLineId() {

    }

    public BigInteger getTransactionLineId() { return transactionLineId; }
    public void setTransactionLineId(BigInteger transactionLineId) { this.transactionLineId = transactionLineId; }

    public String getTransactionCode() { return transactionCode; }
    public void setTransactionCode(String transactionCode) { this.transactionCode = transactionCode; }
}
