package smart.bean;

import smart.entity.AbstractEntity;

/**
 * snmp协议
 */
public class SnmpProtocol extends AbstractEntity {

	private static final long serialVersionUID = -1883607569206991687L;

	// 端口
	private int port;
	// 版本
	private String version;
	// 超时时间
	private int timeout;
	// 描述
	private String description;
	// 运行时长
	private long runningTime;
	// 系统ID
	private long systemID;
	// 系统名
	private String systemName;
	// 系统描述
	private String systemDescription;
	// 联系方式
	private String contactInformation;
	
	public SnmpProtocol(long id) {
		super(id);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(long runningTime) {
		this.runningTime = runningTime;
	}

	public long getSystemID() {
		return systemID;
	}

	public void setSystemID(long systemID) {
		this.systemID = systemID;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemDescription() {
		return systemDescription;
	}

	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

}
