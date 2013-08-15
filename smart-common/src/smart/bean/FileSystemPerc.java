package smart.bean;

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
	
	public FileSystemPerc(long timestamp){
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}
	
	public double getUsed() {
		return this.used;
	}
	
	public double getFree() {
		return this.free;
	}
	
	public double getUsage() {
		this.usage = used/(used+free);
		return usage;
	}

}
