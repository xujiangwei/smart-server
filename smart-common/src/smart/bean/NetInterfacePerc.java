package smart.bean;

import java.io.Serializable;

/**
 * 网络接口使用信息。
 */
public class NetInterfacePerc implements Serializable {
	
	private static final long serialVersionUID = 4162739398121108684L;

	// 数据采集时间
	private long timestamp;
	
	// 网络接口流量
	private float flow;
	// 网络接口吞吐量
	private float throughput;
	// 接口状态
	private String status;

	public NetInterfacePerc(long timestamp){
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public String getStatus() {
		return status;
	}

	public float getFlow() {
		return flow;
	}

	public float getThroughput() {
		return throughput;
	}

}
