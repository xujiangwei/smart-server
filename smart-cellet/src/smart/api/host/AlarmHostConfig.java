package smart.api.host;

public class AlarmHostConfig implements HostConfig {

	private String host = "http://10.10.152.18:7981";
	
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
