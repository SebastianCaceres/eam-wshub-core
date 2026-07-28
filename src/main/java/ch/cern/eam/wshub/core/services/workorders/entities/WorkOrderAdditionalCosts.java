package ch.cern.eam.wshub.core.services.workorders.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class WorkOrderAdditionalCosts {

	private String costDescription;

	private BigInteger activityCode;

	private String costType;

	private Date date;

	private BigDecimal cost;

	private String workOrderNumber;

	private BigDecimal quantity;

	public String getCostDescription() {
		return costDescription;
	}
	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getWorkOrderNumber() {
		return workOrderNumber;
	}
	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}

	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public BigInteger getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(BigInteger activityCode) {
		this.activityCode = activityCode;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}
