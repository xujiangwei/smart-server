package smart.bean;

import java.io.Serializable;

/**
 * Memory监测。
 */
public class MemoryDetection implements Serializable {
	
	private static final long serialVersionUID = 320344498893843397L;
	
	// 内存数据采集时间
	private long timestamp;
	
	private int used;
	private int IOBufferRam;
	private int fileSystemCache;
	private int availRealMem;
	private int totalSawp;
	private int totalRealMem;
	
	public MemoryDetection(long timestamp){
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public int getUsed() {
		return used;
	}

	public int getIOBufferRam() {
		return IOBufferRam;
	}

	public int getFileSystemCache() {
		return fileSystemCache;
	}

	public int getAvailRealMem() {
		return availRealMem;
	}

	public int getTotalSawp() {
		return totalSawp;
	}

	public int getTotalRealMem() {
		return totalRealMem;
	}

}
