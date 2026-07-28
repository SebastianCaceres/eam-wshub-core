package ch.cern.eam.wshub.core.services.entities;

public class Credentials  {

	public Credentials() {
		super();
	}

	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.requestSource = "UNKNOWN";
		this.language = "EN";
	}

	private String username;
	private String password;
	private String language;
	// not mandatory, only for statistics
	private String requestSource;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRequestSource() {
		return requestSource;
	}

	public void setRequestSource(String requestSource) {
		this.requestSource = requestSource;
	}

	public String getLanguage() {
		if (language == null)
			language = "EN";
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "Credentials{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", language='" + language + '\'' +
				", requestSource='" + requestSource + '\'' +
				'}';
	}
}
