package smart.bean;

import java.io.Serializable;

/**
 * TCP连接监测
 */
public class TCPDetection implements Serializable {

	private static final long serialVersionUID = -4510164684727726167L;

	// 数据采集时间
	private long timestamp;
	
	private int inOpenConnections;
	private int outOpenConnections;
	private int currentConnections;
	private int failedConnections;;
	private int establishedResets;
	private int resetsSent;
	private int inSegments;
	private int outSegments;
	private int erroredSegments;
	private int retransmissions;
	
	public TCPDetection(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public int getInOpenConnections() {
		return this.inOpenConnections;
	}

	public int getOutOpenConnections() {
		return this.outOpenConnections;
	}

	public int getCurrentConnections() {
		return this.currentConnections;
	}

	public int getFailedConnections() {
		return this.failedConnections;
	}

	public int getEstablishedResets() {
		return this.establishedResets;
	}

	public int getResetsSent() {
		return this.resetsSent;
	}

	public int getInSegments() {
		return this.inSegments;
	}

	public int getOutSegments() {
		return this.outSegments;
	}

	public int getErroredSegments() {
		return this.erroredSegments;
	}

	public int getRetransmissions() {
		return this.retransmissions;
	}
	
}
