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

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getPhysicsUsed() {
		return physicsUsed;
	}

	public void setPhysicsUsed(int physicsUsed) {
		this.physicsUsed = physicsUsed;
	}

	public int getPhysicsFree() {
		return physicsFree;
	}

	public void setPhysicsFree(int physicsFree) {
		this.physicsFree = physicsFree;
	}

	public int getSwapTotal() {
		return swapTotal;
	}

	public void setSwapTotal(int swapTotal) {
		this.swapTotal = swapTotal;
	}

	public int getSwapUsed() {
		return swapUsed;
	}

	public void setSwapUsed(int swapUsed) {
		this.swapUsed = swapUsed;
	}

	public int getSwapFree() {
		return swapFree;
	}

	public void setSwapFree(int swapFree) {
		this.swapFree = swapFree;
	}

	public double getCombined() {
		this.combined = (physicsUsed+swapUsed)/(physicsUsed+physicsFree+swapTotal);
		return combined;
	}

}
