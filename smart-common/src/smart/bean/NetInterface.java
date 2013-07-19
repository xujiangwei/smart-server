package smart.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import smart.entity.AbstractEntity;

/**
 * 网络接口指标信息
 */
public class NetInterface extends AbstractEntity {

	private static final long serialVersionUID = 1103861007917321020L;
	
	private List<Map<String, NetInterface>> list = new ArrayList<Map<String, NetInterface>>(10);
	private String name;
	private String type;
	private String desip;
	private String ip;
	private String mac;
	private float flow;
	private float throughput;
	private long collectTime;
	private String status;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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

	public long getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(long collectTime) {
		this.collectTime = collectTime;
	}

	public List<Map<String, NetInterface>> getList() {
		return list;
	}

	public void setList(List<Map<String, NetInterface>> list) {
		this.list = list;
	}
}
