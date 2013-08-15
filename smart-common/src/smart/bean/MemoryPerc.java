package smart.bean;

import java.io.Serializable;

/**
 * Memory 利用率百分比。
 */
public class MemoryPerc implements Serializable {
	
	private static final long serialVersionUID = 320344498893843397L;
	
	// 内存数据采集时间
	private long timestamp;
	
	private int physicsUsed;
	private int physicsFree;
	private int swapTotal;
	private int swapUsed;
	private int swapFree;
	private double combined;
	
	public MemoryPerc(long timestamp){
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public int getPhysicsUsed() {
		return this.physicsUsed;
	}

	public int getPhysicsFree() {
		return this.physicsFree;
	}

	public int getSwapTotal() {
		return this.swapTotal;
	}

	public int getSwapUsed() {
		return this.swapUsed;
	}

	public int getSwapFree() {
		return this.swapFree;
	}

	public double getCombined() {
		this.combined = (physicsUsed+swapUsed)/(physicsUsed+physicsFree+swapTotal);
		return combined;
	}

}
