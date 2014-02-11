package smart.old.api.host;

/**
 * 告警的主机配置信息
 * 
 * @author Administrator
 *
 */
public class AlarmHostConfig implements HostConfig {

	private String host = "http://10.10.152.20:7981";
	
	public AlarmHostConfig() {
	}

	@Override
	public String getHost() {
		return this.host;
	}

	@Override
	public String getProjectName() {
		return null;
	}

	@Override
	public String getAPIHost() {
		StringBuilder sb = new StringBuilder(host).append("//");
		return sb.toString();
	}

}
