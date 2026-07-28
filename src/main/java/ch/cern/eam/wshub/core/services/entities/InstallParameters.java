package ch.cern.eam.wshub.core.services.entities;

import javax.persistence.*;
import javax.persistence.Entity;
@Entity
@NamedNativeQueries({
	@NamedNativeQuery(name=InstallParameters.GETINSTALLPARAMS,
		query="select * from r5install"
	)
})
@Table(name="r5install")
public class InstallParameters  {

	public static final String GETINSTALLPARAMS = "InstallParameters.GETINSTALLPARAMS";

	@Id
	@Column(name="ins_code")
	private String code;
	
	@Column(name="ins_desc")
	private String value;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
