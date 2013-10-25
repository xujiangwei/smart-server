
package smart.api.host;public class ServiceDeskHostConfig implements HostConfig {	private  String host="http://10.10.152.20:9080";		private String projectName="itsm";		@Override	public String getHost() {		return host;	}	@Override	public String getProjectName() {	return  projectName;	}	@Override	public String getAPIHost() {		StringBuilder sb=new StringBuilder(host).append("/").append(projectName);		return sb.toString();	}}

