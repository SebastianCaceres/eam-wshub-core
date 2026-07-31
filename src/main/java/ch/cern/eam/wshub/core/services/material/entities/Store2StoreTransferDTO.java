package ch.cern.eam.wshub.core.services.material.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EAM_STORE_TRANSFERS")
public class Store2StoreTransferDTO  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String description;
    
    private String fromStoreCode;
    
    private String toStoreCode;
    
    private String adviceNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRANSFER_ID")
    @Transient
    private List<StoreTransactionPartLine> partLines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getFromStoreCode() {
        return fromStoreCode;
    }

    public void setFromStoreCode(final String fromStoreCode) {
        this.fromStoreCode = fromStoreCode;
    }

    public String getToStoreCode() {
        return toStoreCode;
    }

    public void setToStoreCode(final String toStoreCode) {
        this.toStoreCode = toStoreCode;
    }

    public String getAdviceNumber() {
        return adviceNumber;
    }

    public void setAdviceNumber(final String adviceNumber) {
        this.adviceNumber = adviceNumber;
    }

    public List<StoreTransactionPartLine> getPartLines() {
        return partLines;
    }

    public void setPartLines(final List<StoreTransactionPartLine> partLines) {
        this.partLines = partLines;
    }

    @Override
    public String toString() {
        return "Store2StoreTransferDTO{" +
                "desccription='" + description + '\'' +
                ", fromStoreCode='" + fromStoreCode + '\'' +
                ", toStoreCode='" + toStoreCode + '\'' +
                ", adviceNumber='" + adviceNumber + '\'' +
                ", partLines=" + partLines +
                '}';
    }
}
