package smart.dto;

public class Host {

	private String host_id;
	private String host_systemType;
	private String host_name;
	private boolean host_Monitor;
	private String host_ip;
	private String host_mac;
	private String host_vendor;
	private String host_model;
	private double host_healthDegree;
	
	public Host() {
	}

	public String getHost_id() {
		return host_id;
	}

	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}

	public String getHost_systemType() {
		return host_systemType;
	}

	public void setHost_systemType(String host_systemType) {
		this.host_systemType = host_systemType;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getHost_ip() {
		return host_ip;
	}

	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}

	public String getHost_mac() {
		return host_mac;
	}

	public void setHost_mac(String host_mac) {
		this.host_mac = host_mac;
	}

	public String getHost_vendor() {
		return host_vendor;
	}

	public void setHost_vendor(String host_vendor) {
		this.host_vendor = host_vendor;
	}

	public String getHost_model() {
		return host_model;
	}

	public void setHost_model(String host_model) {
		this.host_model = host_model;
	}

	public double getHost_healthDegree() {
		return host_healthDegree;
	}

	public void setHost_healthDegree(double host_healthDegree) {
		this.host_healthDegree = host_healthDegree;
	}

	public boolean getHost_Monitor() {
		return host_Monitor;
	}

	public void setHost_Monitor(boolean host_Monitor) {
		this.host_Monitor = host_Monitor;
	}

}
