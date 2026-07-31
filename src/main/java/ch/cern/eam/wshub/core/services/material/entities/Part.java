package ch.cern.eam.wshub.core.services.material.entities;

import ch.cern.eam.wshub.core.services.entities.CustomField;
import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;
import ch.cern.eam.wshub.core.services.userdefinedscreens.UserDefinedListHelpable;
import ch.cern.eam.wshub.core.services.userdefinedscreens.entities.UDLValue;
import ch.cern.eam.wshub.core.services.userdefinedscreens.entities.xmlhashmap.XmlHashMapAdapter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "R5PARTS")
public class Part implements UserDefinedListHelpable {

	/**
	 * 
	 */

	@Id
	@Column(name = "PAR_CODE")
	
	private String code;

	@Transient
	private String newCode;

	@Column(name = "PAR_DESC")
	
	private String description;

	@Column(name = "PAR_ORG")
	
	private String organization;

	@Column(name = "PAR_UOM")
	
	private String UOM;
	@Transient
	
	private String UOMDesc;

	@Column(name = "PAR_CLASS")
	
	private String classCode;
	@Transient
	
	private String classDesc;

	@Column(name = "PAR_CATEGORY")
	private String categoryCode;
	@Transient
	private String categoryDesc;
	@Column(name = "PAR_COMMODITY")
	private String commodityCode;
	@Transient
	private String commodityDesc;
	@Transient
	
	private String trackingMethod;
	@Transient
	
	private String priceType;
	@Column(name = "PAR_BASEPRICE")
	private BigDecimal basePrice;
	@Transient
	private BigDecimal averagePrice;
	@Transient
	private BigDecimal standardPrice;
	@Column(name = "PAR_LASTPRICE")
	private BigDecimal lastPrice;
	@Transient
	
	private Boolean trackByAsset;
	@Transient
	
	private Boolean trackAsKit;
	@Transient
	
	private Boolean trackCores;
	@Transient
	
	private Boolean outOfService;
	@Transient
	
	private Boolean trackByLot;
	@Transient
	
	private Boolean preventReorders;
	@Column(name = "PAR_BUYER")
	private String buyerCode;
	@Column(name = "PAR_PREFSUP")
	private String preferredSupplier;
	@Transient
	
	private String longDescription;

	@Transient
	
	private CustomField[] customFields;
	@Transient
	
	private UserDefinedFields userDefinedFields;

	@Transient
	private String copyFrom;

	@Transient
	private HashMap<String, ArrayList<UDLValue>> userDefinedList;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUOM() {
		return UOM;
	}

	public void setUOM(String uOM) {
		UOM = uOM;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	@JsonProperty("customField")

	public CustomField[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomField[] customFields) {
		this.customFields = customFields;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getTrackingMethod() {
		return trackingMethod;
	}

	public void setTrackingMethod(String trackingMethod) {
		this.trackingMethod = trackingMethod;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public BigDecimal getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Boolean getTrackByAsset() {
		return trackByAsset;
	}

	public void setTrackByAsset(Boolean trackByAsset) {
		this.trackByAsset = trackByAsset;
	}

	public Boolean getTrackAsKit() {
		return trackAsKit;
	}

	public void setTrackAsKit(Boolean trackAsKit) {
		this.trackAsKit = trackAsKit;
	}

	public Boolean getTrackCores() {
		return trackCores;
	}

	public void setTrackCores(Boolean trackCores) {
		this.trackCores = trackCores;
	}

	public Boolean getOutOfService() {
		return outOfService;
	}

	public void setOutOfService(Boolean outOfService) {
		this.outOfService = outOfService;
	}

	public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getPreferredSupplier() {
		return preferredSupplier;
	}

	public void setPreferredSupplier(String preferredSupplier) {
		this.preferredSupplier = preferredSupplier;
	}

	public UserDefinedFields getUserDefinedFields() {
		return userDefinedFields;
	}

	public void setUserDefinedFields(UserDefinedFields userDefinedFields) {
		this.userDefinedFields = userDefinedFields;
	}

	public String getNewCode() {
		return newCode;
	}

	public void setNewCode(String newCode) {
		this.newCode = newCode;
	}

	public String getUOMDesc() {
		return UOMDesc;
	}

	public void setUOMDesc(String uOMDesc) {
		UOMDesc = uOMDesc;
	}

	public String getClassDesc() {
		return classDesc;
	}

	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getCommodityDesc() {
		return commodityDesc;
	}

	public void setCommodityDesc(String commodityDesc) {
		this.commodityDesc = commodityDesc;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Boolean getTrackByLot() {
		return trackByLot;
	}

	public void setTrackByLot(Boolean trackByLot) {
		this.trackByLot = trackByLot;
	}

	public Boolean getPreventReorders() {
		return preventReorders;
	}

	public void setPreventReorders(Boolean preventReorders) {
		this.preventReorders = preventReorders;
	}

	@Override
	public String getCopyFrom() {
		return copyFrom;
	}

	public void setCopyFrom(String copyFrom) {
		this.copyFrom = copyFrom;
	}

	@Override
	public HashMap<String, ArrayList<UDLValue>> getUserDefinedList() {
		return userDefinedList;
	}

	@Override
	public void setUserDefinedList(HashMap<String, ArrayList<UDLValue>> userDefinedList) {
		this.userDefinedList = userDefinedList;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Part{" +
				"code='" + code + '\'' +
				", newCode='" + newCode + '\'' +
				", description='" + description + '\'' +
				", UOM='" + UOM + '\'' +
				", UOMDesc='" + UOMDesc + '\'' +
				", classCode='" + classCode + '\'' +
				", classDesc='" + classDesc + '\'' +
				", categoryCode='" + categoryCode + '\'' +
				", categoryDesc='" + categoryDesc + '\'' +
				", commodityCode='" + commodityCode + '\'' +
				", commodityDesc='" + commodityDesc + '\'' +
				", trackingMethod='" + trackingMethod + '\'' +
				", priceType='" + priceType + '\'' +
				", basePrice='" + basePrice + '\'' +
				", averagePrice='" + averagePrice + '\'' +
				", standardPrice='" + standardPrice + '\'' +
				", lastPrice='" + lastPrice + '\'' +
				", trackByAsset='" + trackByAsset + '\'' +
				", trackAsKit='" + trackAsKit + '\'' +
				", trackCores='" + trackCores + '\'' +
				", outOfService='" + outOfService + '\'' +
				", trackByLot='" + trackByLot + '\'' +
				", preventReorders='" + preventReorders + '\'' +
				", buyerCode='" + buyerCode + '\'' +
				", preferredSupplier='" + preferredSupplier + '\'' +
				", longDescription='" + longDescription + '\'' +
				", customFields=" + Arrays.toString(customFields) +
				", userDefinedFields=" + userDefinedFields +
				", copyFrom='" + copyFrom + '\'' +
				", userDefinedList='" + userDefinedList + '\'' +
				'}';
	}
}
