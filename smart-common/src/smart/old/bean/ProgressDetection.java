package smart.old.bean;

import java.io.Serializable;

/**
 * 进程监测信息
 */
public class ProgressDetection implements Serializable {
	
	private static final long serialVersionUID = 743552990739712713L;

	// 数据采集时间
	private long timestamp;
	
	// cpu占用数
	private long cpuUsed;
	// 内存占用大小
	private long memUsed;
	private long memSize;
	private long memShare;
	private String state;
	private long cpuTime;
	
	public ProgressDetection(long timestamp){
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getCpuUsed() {
		return cpuUsed;
	}

	public void setCpuUsed(long cpuUsed) {
		this.cpuUsed = cpuUsed;
	}

	public long getMemUsed() {
		return memUsed;
	}

	public void setMemUsed(long memUsed) {
		this.memUsed = memUsed;
	}

	public long getMemSize() {
		return memSize;
	}

	public void setMemSize(long memSize) {
		this.memSize = memSize;
	}

	public long getMemShare() {
		return memShare;
	}

	public void setMemShare(long memShare) {
		this.memShare = memShare;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getCpuTime() {
		return cpuTime;
	}

	public void setCpuTime(long cpuTime) {
		this.cpuTime = cpuTime;
	}
	
}
