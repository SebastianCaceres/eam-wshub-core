package ch.cern.eam.wshub.core.services.material.entities;

import javax.persistence.*;

@Entity
@Table(name = "R5BINS")
public class Bin {

	@Column(name = "BIN_STORE")
	private String storeCode;

	@Id
	@Column(name = "BIN_CODE")
	private String binCode;

	@Column(name = "BIN_DESC")
	private String binDesc;

	@Transient
	private Boolean outOfService;

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getBinCode() {
		return binCode;
	}

	public void setBinCode(String binCode) {
		this.binCode = binCode;
	}

	public String getBinDesc() {
		return binDesc;
	}

	public void setBinDesc(String binDesc) {
		this.binDesc = binDesc;
	}

	public Boolean getOutOfService() {
		return outOfService;
	}

	public void setOutOfService(Boolean outOfService) {
		this.outOfService = outOfService;
	}
}
