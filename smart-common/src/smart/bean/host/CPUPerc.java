package smart.bean.host;

import java.io.Serializable;

/**
 * CPU 利用率百分比。
 */
public final class CPUPerc implements Serializable {

	private static final long serialVersionUID = 1335146206472829631L;

	// 数据采集时间
	private long timestamp;

	private double user;
	private double sys;
	private double nice;
	private double idle;
	private double wait;
	private double combined;

	public CPUPerc(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public double getUser() {
		return this.user;
	}

	public double getSys() {
		return this.sys;
	}

	public double getNice() {
		return this.nice;
	}

	public double getIdle() {
		return this.idle;
	}

	public double getWait() {
		return this.wait;
	}

	/**
	 * Sum of User + Sys + Nice + Wait
	 * @return
	 */
	public double getCombined() {
		return this.combined;
	}
}
