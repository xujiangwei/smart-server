package smart.bean;

import java.io.Serializable;

/**
 * SNMP监测
 */
public class SNMPDetection implements Serializable {

	private static final long serialVersionUID = -456948882826247819L;

	// 数据采集时间
	private long timestamp;
	
	private double context;
	private int numberOfProcesses;
	private int totalMemory;
	private int interrupts;
	private double systemUptime;
	private double loadAverage;
	
	public SNMPDetection(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public double getContext() {
		return this.context;
	}

	public int getNumberOfProcesses() {
		return this.numberOfProcesses;
	}

	public int getTotalMemory() {
		return this.totalMemory;
	}

	public int getInterrupts() {
		return this.interrupts;
	}

	public double getSystemUptime() {
		return this.systemUptime;
	}

	public double getLoadAverage() {
		return this.loadAverage;
	}

}
