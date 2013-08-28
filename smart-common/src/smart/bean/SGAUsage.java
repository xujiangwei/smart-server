package smart.bean;

import java.io.Serializable;

/**
 * SGA使用信息
 */
public class SGAUsage implements Serializable {

	private static final long serialVersionUID = 2962719413302708101L;

	// 数据采集时间
	private long timestamp;
	
	private long fixedSGA;
	private long bufferCache;
	private long logBuffer;
	private double fixedSGAUsage;
	private double bufferCacheUsage;
	private double logBufferUsage;
	
	public SGAUsage(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getFixedSGA() {
		return fixedSGA;
	}

	public void setFixedSGA(long fixedSGA) {
		this.fixedSGA = fixedSGA;
	}

	public long getBufferCache() {
		return bufferCache;
	}

	public void setBufferCache(long bufferCache) {
		this.bufferCache = bufferCache;
	}

	public long getLogBuffer() {
		return logBuffer;
	}

	public void setLogBuffer(long logBuffer) {
		this.logBuffer = logBuffer;
	}

	public double getFixedSGAUsage() {
		return fixedSGAUsage;
	}

	public void setFixedSGAUsage(double fixedSGAUsage) {
		this.fixedSGAUsage = fixedSGAUsage;
	}

	public double getBufferCacheUsage() {
		return bufferCacheUsage;
	}

	public void setBufferCacheUsage(double bufferCacheUsage) {
		this.bufferCacheUsage = bufferCacheUsage;
	}

	public double getLogBufferUsage() {
		return logBufferUsage;
	}

	public void setLogBufferUsage(double logBufferUsage) {
		this.logBufferUsage = logBufferUsage;
	}

}
