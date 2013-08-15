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

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getFlow() {
		return flow;
	}

	public void setFlow(float flow) {
		this.flow = flow;
	}

	public float getThroughput() {
		return throughput;
	}

	public void setThroughput(float throughput) {
		this.throughput = throughput;
	}

}
