package smart.bean;

import java.io.Serializable;

/**
 * Web应用信息
 */
public class WebApplication implements Serializable {

	private static final long serialVersionUID = 2383276559491958823L;

	// 数据采集时间
	private long timestamp;
	
	private String serverName;
	private String applicationName;
	private String status;
	
	public WebApplication(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getServerName() {
		return serverName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public String getStatus() {
		return status;
	}

}
