package ch.cern.eam.wshub.core.services.material.entities;

public class Bin  {

	private String storeCode;

	private String binCode;

	private String binDesc;

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
