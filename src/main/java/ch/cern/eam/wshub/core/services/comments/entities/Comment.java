package ch.cern.eam.wshub.core.services.comments.entities;

import javax.persistence.*;

@Entity
@Table(name = "R5COMMENTS")
public class Comment  {

	@Id
	@Column(name = "CMT_CODE")
	private String pk;

	@Column(name = "CMT_TEXT")
	private String text;

	@Column(name = "CMT_LINE")
	private String lineNumber;

	@Transient
	private String updateCount;

	@Column(name = "CMT_CREATEUSER")
	private String creationUserCode;

	@Transient
	private String creationUserDesc;

	@Transient
	private String updateUserCode;

	@Transient
	private String updateUserDesc;

	@Transient
	private String creationDate;

	@Transient
	private String updateDate;

	@Column(name = "CMT_TYPE")
	private String typeCode;

	@Column(name = "CMT_RKEY")
	private String entityKeyCode;

	@Column(name = "CMT_RENTITY")
	private String entityCode;

	@Transient
	private Boolean print;

	@Transient
	private String organization;

	public String getPk() {
		return pk != null ? pk : (this.getEntityKeyCode() + "C" + this.getLineNumber());
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	private boolean updated;
	
	private boolean created;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getUpdateCount() {
		return updateCount;
	}
	public void setUpdateCount(String updateCount) {
		this.updateCount = updateCount;
	}
	public String getCreationUserCode() {
		return creationUserCode;
	}
	public void setCreationUserCode(String creationUserCode) {
		this.creationUserCode = creationUserCode;
	}
	public String getCreationUserDesc() {
		return creationUserDesc;
	}
	public void setCreationUserDesc(String creationUserDesc) {
		this.creationUserDesc = creationUserDesc;
	}
	public String getUpdateUserCode() {
		return updateUserCode;
	}
	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}
	public String getUpdateUserDesc() {
		return updateUserDesc;
	}
	public void setUpdateUserDesc(String updateUserDesc) {
		this.updateUserDesc = updateUserDesc;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getEntityKeyCode() {
		return entityKeyCode;
	}
	public void setEntityKeyCode(String entityKeyCode) {
		this.entityKeyCode = entityKeyCode;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Comment ["
				+ (text != null ? "text=" + text + ", " : "")
				+ (lineNumber != null ? "lineNumber=" + lineNumber + ", " : "")
				+ (updateCount != null ? "updateCount=" + updateCount + ", "
						: "")
				+ (creationUserCode != null ? "creationUserCode="
						+ creationUserCode + ", " : "")
				+ (creationUserDesc != null ? "creationUserDesc="
						+ creationUserDesc + ", " : "")
				+ (updateUserCode != null ? "updateUserCode=" + updateUserCode
						+ ", " : "")
				+ (updateUserDesc != null ? "updateUserDesc=" + updateUserDesc
						+ ", " : "")
				+ (creationDate != null ? "creationDate=" + creationDate + ", "
						: "")
				+ (updateDate != null ? "updateDate=" + updateDate + ", " : "")
				+ (typeCode != null ? "typeCode=" + typeCode + ", " : "")
				+ (entityKeyCode != null ? "entityKeyCode=" + entityKeyCode
						+ ", " : "")
				+ (entityCode != null ? "entityCode=" + entityCode : "") + "]";
	}
	public boolean isUpdated() {
		return updated;
	}
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	public boolean isCreated() {
		return created;
	}
	public void setCreated(boolean created) {
		this.created = created;
	}

	public Boolean getPrint() {
		return print;
	}

	public void setPrint(Boolean print) {
		this.print = print;
	}
}
