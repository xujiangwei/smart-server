package smart.bean;

import java.io.Serializable;

/**
 * 进程监测信息
 */
public class ProgressDetection implements Serializable {
	
	private static final long serialVersionUID = 743552990739712713L;

	// 数据采集时间
	private long timestamp;
	
	// 当前总进程数
	private int total;
	// 进程名
	private String name;
	// 进程用户名
	private String username;
	// cpu占用数
	private int cpuUsed;
	// 内存占用大小
	private int memUsed;
	
	public ProgressDetection(long timestamp){
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}

	public int getTotal() {
		return this.total;
	}

	public String getName() {
		return this.name;
	}

	public String getUsername() {
		return this.username;
	}

	public int getCpuUsed() {
		return this.cpuUsed;
	}

	public int getMemUsed() {
		return this.memUsed;
	}

}
