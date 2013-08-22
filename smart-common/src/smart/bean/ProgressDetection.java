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
	// cpu占用数
	private int cpuUsed;
	// 内存占用大小
	private int memUsed;
	// 闲置状态的进程数
	private long idle;
	// 运行态的进程数
	private long running;
	// 睡眠态的进程数
	private long sleeping;
	// stop状态
	private long stopped;
	// 线程总数
	private long threads;
	// zombie状态
	private long zombie;
	
	public ProgressDetection(long timestamp){
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}

	public long getIdle() {
		return this.idle;
	}

	public long getRunning() {
		return this.running;
	}

	public long getSleeping() {
		return this.sleeping;
	}

	public long getStopped() {
		return this.stopped;
	}

	public long getThreads() {
		return this.threads;
	}

	public long getZombie() {
		return this.zombie;
	}

	public int getTotal() {
		return this.total;
	}

	public int getCpuUsed() {
		return this.cpuUsed;
	}

	public int getMemUsed() {
		return this.memUsed;
	}

}
