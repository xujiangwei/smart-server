package smart.bean.host;

import java.util.List;

import smart.entity.AbstractEntity;

/**
 * CPU指标信息
 */
public class CPU extends AbstractEntity {

	private static final long serialVersionUID = -3010764141583766821L;

	private String vendor;
	private String type;
	private float clockSpeed;
	private int cacheSize;
	private double user;
	private double nice;
	private double system;
	private double IOWait;
	private double idle;
	private double cpuLoad;
	private double usage;
	private List<CPUUsage> cpuUsage;
	

	public CPU(String id) {
		super(id);
	}

	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getClockSpeed() {
		return clockSpeed;
	}
	public void setClockSpeed(float clockSpeed) {
		this.clockSpeed = clockSpeed;
	}
	public int getCacheSize() {
		return cacheSize;
	}
	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}
	public double getUser() {
		return user;
	}
	public void setUser(double user) {
		this.user = user;
	}
	public double getNice() {
		return nice;
	}
	public void setNice(double nice) {
		this.nice = nice;
	}
	public double getSystem() {
		return system;
	}
	public void setSystem(double system) {
		this.system = system;
	}
	public double getIOWait() {
		return IOWait;
	}
	public void setIOWait(double iOWait) {
		IOWait = iOWait;
	}
	public double getIdle() {
		return idle;
	}
	public void setIdle(double idle) {
		this.idle = idle;
	}
	public double getCpuLoad() {
		return cpuLoad;
	}
	public void setCpuLoad(double cpuLoad) {
		this.cpuLoad = cpuLoad;
	}
	public double getUsage() {
		return usage;
	}
	public void setUsage(double usage) {
		this.usage = usage;
	}

	public List<CPUUsage> getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(List<CPUUsage> cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	
}