package smart.old.bean;

import smart.old.entity.AbstractEntity;

/**
 * 路由表。
 */
public class RouteTable extends AbstractEntity {

	private static final long serialVersionUID = 8232119751680150873L;

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
	private double maxPDUBytes;

	public RouteTable(long id) {
		super(id);
	}

	public String getDesIp() {
		return desIp;
	}

	public void setDesIp(String desIp) {
		this.desIp = desIp;
	}

	public String getDesSubnetMask() {
		return desSubnetMask;
	}

	public void setDesSubnetMask(String desSubnetMask) {
		this.desSubnetMask = desSubnetMask;
	}

	public String getNextHop() {
		return nextHop;
	}

	public void setNextHop(String nextHop) {
		this.nextHop = nextHop;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public double getMaxPDUBytes() {
		return maxPDUBytes;
	}

	public void setMaxPDUBytes(double maxPDUBytes) {
		this.maxPDUBytes = maxPDUBytes;
	}

}
