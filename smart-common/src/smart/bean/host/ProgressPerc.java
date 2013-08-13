package smart.bean.host;

import java.io.Serializable;

/**
 * 进程占用信息
 */
public class ProgressPerc implements Serializable {
	
	private static final long serialVersionUID = 743552990739712713L;

	// 数据采集时间
	private long timestamp;
	
	// cpu占用数
	private int cpuUsed;
	// 内存占用大小
	private int memUsed;
	
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getCpuUsed() {
		return cpuUsed;
	}

	public void setCpuUsed(int cpuUsed) {
		this.cpuUsed = cpuUsed;
	}

	public int getMemUsed() {
		return memUsed;
	}

	public void setMemUsed(int memUsed) {
		this.memUsed = memUsed;
	}

}
