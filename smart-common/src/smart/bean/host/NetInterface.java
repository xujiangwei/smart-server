package smart.bean.host;

import java.util.List;

import smart.entity.AbstractEntity;

/**
 * 网络接口指标信息
 */
public class NetInterface extends AbstractEntity {

	private static final long serialVersionUID = 1103861007917321020L;

	private String name;
	private String type;
	private String desip;
	private String ip;
	private String mac;
	private List<NetInterfaceStatus> netInterfaceStatus;

	public NetInterface(String id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesip() {
		return desip;
	}

	public void setDesip(String desip) {
		this.desip = desip;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public List<NetInterfaceStatus> getNetInterfaceStatus() {
		return netInterfaceStatus;
	}

	public void setNetInterfaceStatus(
			List<NetInterfaceStatus> netInterfaceStatus) {
		this.netInterfaceStatus = netInterfaceStatus;
	}

}
