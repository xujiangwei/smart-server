package smart.bean;

import java.io.Serializable;

/**
 * 路由表。
 */
public class RouteTable implements Serializable {

	private static final long serialVersionUID = 8232119751680150873L;

	// 数据采集时间
	private long timestamp;
	
	// 路由目标地址
	private String desIp;
	// 目标地址子网掩码
	private String desSubnetMask;
	// 路由的下一跳地址
	private String nextHop;
	// 路由类型
	private String type;
	// 路由协议
	private String protocol;
	// PDU最大字节数
	private double maxPUDBytes;

	public RouteTable(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public String getDesIp() {
		return this.desIp;
	}

	public String getDesSubnetMask() {
		return this.desSubnetMask;
	}

	public String getNextHop() {
		return this.nextHop;
	}

	public String getType() {
		return this.type;
	}

	public String getProtocol() {
		return this.protocol;
	}

	public double getMaxPUDBytes() {
		return this.maxPUDBytes;
	}

}
