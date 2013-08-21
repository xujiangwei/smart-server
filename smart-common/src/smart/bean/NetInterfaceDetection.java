package smart.bean;

import java.io.Serializable;

/**
 * 网络接口使用信息。
 */
public class NetInterfaceDetection implements Serializable {
	
	private static final long serialVersionUID = 4162739398121108684L;

	// 数据采集时间
	private long timestamp;
	
	// 网络接口名
	private String name;
	// 网络接口类型
	private String type;
	// 目标地址ip
	private String desip;
	// 源ip
	private String ip;
	// 源mac地址
	private String mac;
	// 网络接口流量
	private float traffic;
	// 网络接口吞吐量
	private float throughput;
	// 接口状态
	private String status;

	public NetInterfaceDetection(long timestamp){
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getDesip() {
		return desip;
	}

	public String getIp() {
		return ip;
	}

	public String getMac() {
		return mac;
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

}
