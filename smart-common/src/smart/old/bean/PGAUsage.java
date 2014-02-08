package smart.old.bean;

import java.io.Serializable;

/**
 * PGA使用情况
 */
public class PGAUsage implements Serializable {

	private static final long serialVersionUID = -228483066382716980L;

	// 数据采集时间
	private long timestamp;
	
	private long used;
	private long idle;
	private long usage;
	private long bufferHitRate;
	
	public PGAUsage(long timestamp) {
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

	public long getUsage() {
		return usage;
	}

	public void setUsage(long usage) {
		this.usage = usage;
	}

	public long getBufferHitRate() {
		return bufferHitRate;
	}

	public void setBufferHitRate(long bufferHitRate) {
		this.bufferHitRate = bufferHitRate;
	}

}
