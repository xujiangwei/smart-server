package smart.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.entity.AbstractEntity;

/**
 * 网络接口指标信息
 */
public class NetInterface extends AbstractEntity {

	private static final long serialVersionUID = 1103861007917321020L;

	// 网络接口名
	private String name;
	// 网络接口类型
	private String type;
	// 广播
	private String broadcast;
	// 源ip
	private String address;
	// 描述
	private String description;
	// 源mac地址
	private String mac;
	// 网关
	private String gateway;
	// 子网掩码
	private String netmask;
	// 目标地址ip
	private String destination;
	// 到达目的ip的跳数
	private long metric;
	// 最大传输单元
	private long mtu;

	/// 网络接口监测队列
	private Queue<NetInterfaceStat> queue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public NetInterface(long id) {
		super(id);
		this.queue = new LinkedList<NetInterfaceStat>();
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

	public String getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public long getMetric() {
		return metric;
	}

	public void setMetric(long metric) {
		this.metric = metric;
	}

	public long getMtu() {
		return mtu;
	}

	public void setMtu(long mtu) {
		this.mtu = mtu;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	/**
	 * 添加网络接口监测数据。
	 * @param niDetection
	 */
	public void addNiPrec(NetInterfaceStat niDetection) {
		synchronized (this.queue) {
			this.queue.add(niDetection);
		}

		if (this.queue.size() > this.maxPercs) {
			synchronized (this.queue) {
				this.queue.poll();
			}
		}
	}

	/**
	 * 返回网络接口数据列表。
	 * @return
	 */
	public List<NetInterfaceStat> getNiPercs() {
		ArrayList<NetInterfaceStat> ret = new ArrayList<NetInterfaceStat>(this.queue.size());
		synchronized (this.queue) {
			ret.addAll(this.queue);
		}
		return ret;
	}
	
}
