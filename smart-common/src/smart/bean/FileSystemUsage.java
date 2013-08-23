package smart.bean;

import java.io.Serializable;

/**
 * 文件系统检测
 */
public class FileSystemUsage implements Serializable {

	private static final long serialVersionUID = -8537691525565202580L;
	
	// 数据采集时间
	private long timestamp;
	
	private double diskQueue;
	private long diskReadBytes;
	private long diskReads;
	private double diskServiceTime;
	private long diskWriteBytes;
	private long diskWrites;
	private long files;
	private long free;
	private long freeFiles;
	private long used;
	private double usePercent;
	
	public FileSystemUsage(long timestamp){
		this.timestamp = timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setDiskQueue(double diskQueue) {
		this.diskQueue = diskQueue;
	}

	public void setDiskReadBytes(long diskReadBytes) {
		this.diskReadBytes = diskReadBytes;
	}

	public void setDiskReads(long diskReads) {
		this.diskReads = diskReads;
	}

	public void setDiskServiceTime(double diskServiceTime) {
		this.diskServiceTime = diskServiceTime;
	}

	public void setDiskWriteBytes(long diskWriteBytes) {
		this.diskWriteBytes = diskWriteBytes;
	}

	public void setDiskWrites(long diskWrites) {
		this.diskWrites = diskWrites;
	}

	public void setFiles(long files) {
		this.files = files;
	}

	public void setFree(long free) {
		this.free = free;
	}

	public void setFreeFiles(long freeFiles) {
		this.freeFiles = freeFiles;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public void setUsePercent(double usePercent) {
		this.usePercent = usePercent;
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
