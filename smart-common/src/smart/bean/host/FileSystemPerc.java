package smart.bean.host;

import java.io.Serializable;

/**
 * 文件系统利用率百分比
 */
public class FileSystemPerc implements Serializable {

	private static final long serialVersionUID = -8537691525565202580L;
	
	// 数据采集时间
	private long timestamp;
	
	private double used;
	private double free;
	private double usage;
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public double getUsed() {
		return used;
	}
	public void setUsed(double used) {
		this.used = used;
	}
	public double getFree() {
		return free;
	}
	public void setFree(double free) {
		this.free = free;
	}
	
	public double getUsage() {
		this.usage = used/(used+free);
		return usage;
	}

}
