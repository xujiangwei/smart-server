package smart.bean;

import java.io.Serializable;

/**
 * 文件系统检测
 */
public class FileSystemDetection implements Serializable {

	private static final long serialVersionUID = -8537691525565202580L;
	
	// 数据采集时间
	private long timestamp;
	
	// 已用大小
	private long used;
	// 剩余大小
	private long free;
	private double diskQueue;
	private long diskReadBytes;
	private long diskReads;
	private double diskServiceTime;
	private long diskWriteBytes;
	private long diskWrites;
	private long files;
	private long freeFiles;
	private double usePercent;
	
	public FileSystemDetection(long timestamp){
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
	
	public double getDiskQueue() {
		return this.diskQueue;
	}

	public long getDiskReadBytes() {
		return this.diskReadBytes;
	}

	public long getDiskReads() {
		return this.diskReads;
	}

	public double getDiskServiceTime() {
		return this.diskServiceTime;
	}

	public long getDiskWriteBytes() {
		return this.diskWriteBytes;
	}

	public long getDiskWrites() {
		return this.diskWrites;
	}

	public long getFiles() {
		return this.files;
	}

	public long getFreeFiles() {
		return this.freeFiles;
	}

	public double getUsePercent() {
		this.usePercent = used/(used+free);
		return usePercent;
	}

}
