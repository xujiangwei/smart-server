package smart.bean;

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
	
	public ProgressPerc(long timestamp){
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public int getCpuUsed() {
		return cpuUsed;
	}

	public int getMemUsed() {
		return memUsed;
	}

}
