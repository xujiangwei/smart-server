package smart.bean;

import java.io.Serializable;

/**
 * Memory监测。
 */
public class MemoryDetection implements Serializable {
	
	private static final long serialVersionUID = 320344498893843397L;
	
	// 内存数据采集时间
	private long timestamp;
	
	private long actualFree;
	private long actualUsed;
	private long free;
	private double freePercent;
	private long ram;
	private long used;
	private double usedPercent;
	
	public MemoryDetection(long timestamp){
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public long getActualFree() {
		return this.actualFree;
	}

	public long getActualUsed() {
		return this.actualUsed;
	}

	public long getFree() {
		return this.free;
	}

	public double getFreePercent() {
		return this.freePercent;
	}

	public long getRam() {
		return this.ram;
	}

	public long getUsed() {
		return this.used;
	}

	public double getUsedPercent() {
		return this.usedPercent;
	}

}
