package smart.bean;

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

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setUser(double user) {
		this.user = user;
	}

	public void setSys(double sys) {
		this.sys = sys;
	}

	public void setNice(double nice) {
		this.nice = nice;
	}

	public void setIdle(double idle) {
		this.idle = idle;
	}

	public void setWait(double wait) {
		this.wait = wait;
	}

	public void setCombined(double combined) {
		this.combined = combined;
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
		this.combined = user + sys + nice + wait;
		return combined;
	}
}
