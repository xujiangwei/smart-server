package smart.bean.host;

import smart.entity.AbstractEntity;

/**
 * 主机
 */
public class Host extends AbstractEntity {

	private static final long serialVersionUID = 4733691548460944139L;

	private String name;
	private String systemType;
	private boolean isMonitor;
	private String ip;
	private String mac;
	private String vendor;
	private String model;
	private double healthDegree;

	public Host(String id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMonitor() {
		return isMonitor;
	}

	public void setMonitor(boolean isMonitor) {
		this.isMonitor = isMonitor;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getHealthDegree() {
		return healthDegree;
	}

	public void setHealthDegree(double healthDegree) {
		this.healthDegree = healthDegree;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
