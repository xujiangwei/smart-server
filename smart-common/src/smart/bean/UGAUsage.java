package smart.bean;

import java.io.Serializable;

/**
 * UGA使用信息
 */
public class UGAUsage implements Serializable {

	private static final long serialVersionUID = 1938701618022660860L;

	// 数据采集时间
	private long timestamp;
	
	private long used;
	private double usage;
	
	public UGAUsage(long timestamp) {
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

	public double getUsage() {
		return usage;
	}

	public void setUsage(double usage) {
		this.usage = usage;
	}

}
