package smart.bean;

import smart.entity.AbstractEntity;

/**
 * Web应用
 */
public class WebApplication extends AbstractEntity {

	private static final long serialVersionUID = 2383276559491958823L;

	private String serverName;
	private String applicationName;
	private String status;
	
	public WebApplication(long id) {
		super(id);
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
