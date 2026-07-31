package ch.cern.eam.wshub.core.services.equipment.entities;

import ch.cern.eam.wshub.core.services.entities.CustomField;
import ch.cern.eam.wshub.core.services.entities.UserDefinedFields;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Arrays;

@Entity
@Table(name = "R5OBJECTS")
public class Location  {

	@Id
	@Column(name = "OBJ_CODE")
	
	private String code;

	@Column(name = "OBJ_DESC")
	
	private String description;

	@Transient
	
	private CustomField[] customFields;

	@Transient
	
	private String departmentCode;

	@Transient
	
	private String departmentDesc;

	@Transient
	
	private String classCode;
	@Transient
	
	private String classDesc;

	@Transient
	
	private Boolean safety;

	@Transient
	
	private Boolean outOfService;

	@Transient
	
	private String costCode;

	@Transient
	
	private UserDefinedFields userDefinedFields;

	@Transient
	private String hierarchyLocationCode;

	@Transient
	private String hierarchyLocationDesc;

	@Transient
	private String copyFrom;

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

	public CustomField[] getCustomFields() {
		return customFields;
	}
	public void setCustomFields(CustomField[] customFields) {
		this.customFields = customFields;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassDesc() {
		return classDesc;
	}
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}
	public Boolean getSafety() {
		return safety;
	}
	public void setSafety(Boolean safety) {
		this.safety = safety;
	}
	public Boolean getOutOfService() {
		return outOfService;
	}
	public void setOutOfService(Boolean outOfService) {
		this.outOfService = outOfService;
	}
	public String getCostCode() {
		return costCode;
	}
	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}
	public UserDefinedFields getUserDefinedFields() {
		return userDefinedFields;
	}
	public void setUserDefinedFields(UserDefinedFields userDefinedFields) {
		this.userDefinedFields = userDefinedFields;
	}

	public String getHierarchyLocationCode() {
		return hierarchyLocationCode;
	}

	public void setHierarchyLocationCode(String hierarchyLocationCode) {
		this.hierarchyLocationCode = hierarchyLocationCode;
	}

	public String getHierarchyLocationDesc() {
		return hierarchyLocationDesc;
	}

	public void setHierarchyLocationDesc(String hierarchyLocationDesc) {
		this.hierarchyLocationDesc = hierarchyLocationDesc;
	}

	public String getCopyFrom() {
		return copyFrom;
	}

	public void setCopyFrom(String copyFrom) {
		this.copyFrom = copyFrom;
	}

	@Override
	public String toString() {
		return "Location ["
				+ (code != null ? "code=" + code + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (customFields != null ? "customFields="
					+ Arrays.toString(customFields) + ", " : "")
				+ (departmentCode != null ? "departmentCode=" + departmentCode + ", " : "")
				+ (departmentDesc != null ? "departmentDesc=" + departmentDesc + ", " : "")
				+ (classCode != null ? "classCode=" + classCode + ", " : "")
				+ (classDesc != null ? "classDesc=" + classDesc + ", " : "")
				+ (safety != null ? "safety=" + safety + ", " : "")
				+ (outOfService != null ? "outOfService=" + outOfService + ", " : "")
				+ (costCode != null ? "costCode=" + costCode + ", " : "")
				+ (userDefinedFields != null ? "userDefinedFields=" + userDefinedFields + ", " : "")
				+ (copyFrom != null ? "copyFrom=" + copyFrom : "") + "]";
	}
}
