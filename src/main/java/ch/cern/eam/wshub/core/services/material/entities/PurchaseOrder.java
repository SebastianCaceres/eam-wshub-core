package ch.cern.eam.wshub.core.services.material.entities;

import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;

public class PurchaseOrder  {

	private String purchaseOrderId;
	private String statusCode;

	private UserDefinedFields userDefinedFields;
	
	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public UserDefinedFields getUserDefinedFields() {
		return userDefinedFields;
	}
	
	public void setUserDefinedFields(UserDefinedFields userDefinedFields) {
		this.userDefinedFields = userDefinedFields;
	}

	public String getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
}
