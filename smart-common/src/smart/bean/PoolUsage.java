package smart.bean;

import java.io.Serializable;

/**
 * 池使用信息
 */
public class PoolUsage implements Serializable {

	private static final long serialVersionUID = -1327241592358014676L;

	// 数据采集时间
	private long timestamp;
	
	private long used;
	private long idle;
	private double usage;
	private double SGAUsage;
	
	public PoolUsage(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getUsed() {
		return used;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public long getIdle() {
		return idle;
	}

	public void setIdle(long idle) {
		this.idle = idle;
	}

	public double getUsage() {
		return usage;
	}

	public void setUsage(double usage) {
		this.usage = usage;
	}

	public double getSGAUsage() {
		return SGAUsage;
	}

	public void setSGAUsage(double sGAUsage) {
		SGAUsage = sGAUsage;
	}

}
