package smart.bean;

import java.io.Serializable;

/**
 * 网络接口采集信息。
 */
public class NetInterfaceDetection implements Serializable {
	
	private static final long serialVersionUID = 4162739398121108684L;

	// 数据采集时间
	private long timestamp;
	
	// 网络接口流量
	private float traffic;
	// 网络接口吞吐量
	private float throughput;
	// 接口状态
	private String status;
	// 接收
	private long rxBytes;
	private long rxDropped;
	private long rxErrors;
	private long rxFrame;
	private long rxOverruns;
	private long rxPackets;
	private long speed;
	// 传送
	private long txBytes;
	private long txCarrier;
	private long txCollisions;
	private long txDropped;
	private long txErrors;
	private long txOverruns;
	private long txPackets;

	public NetInterfaceDetection(long timestamp){
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}

	public String getStatus() {
		return this.status;
	}

	public float getTraffic() {
		return this.traffic;
	}

	public float getThroughput() {
		return this.throughput;
	}

	public long getRxBytes() {
		return this.rxBytes;
	}

	public long getRxDropped() {
		return this.rxDropped;
	}

	public long getRxErrors() {
		return this.rxErrors;
	}

	public long getRxFrame() {
		return this.rxFrame;
	}

	public long getRxOverruns() {
		return this.rxOverruns;
	}

	public long getRxPackets() {
		return this.rxPackets;
	}

	public long getSpeed() {
		return this.speed;
	}

	public long getTxBytes() {
		return this.txBytes;
	}

	public long getTxCarrier() {
		return this.txCarrier;
	}

	public long getTxCollisions() {
		return this.txCollisions;
	}

	public long getTxDropped() {
		return this.txDropped;
	}

	public long getTxErrors() {
		return this.txErrors;
	}

	public long getTxOverruns() {
		return this.txOverruns;
	}

	public long getTxPackets() {
		return this.txPackets;
	}

}
