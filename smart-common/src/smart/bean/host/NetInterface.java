package smart.bean.host;

import java.util.ArrayList;
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
	// 目标地址ip
	private String desip;
	// 源ip
	private String ip;
	// 源mac地址
	private String mac;

	/// 网络接口利用率
	private Queue<NetInterfacePerc> percQueue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public NetInterface(long id) {
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

	/**
	 * 添加网络接口使用数据。
	 * @param perc
	 */
	public void addPrec(NetInterfacePerc perc) {
		synchronized (this.percQueue) {
			this.percQueue.add(perc);
		}

		if (this.percQueue.size() > this.maxPercs) {
			synchronized (this.percQueue) {
				this.percQueue.poll();
			}
		}
	}

	/**
	 * 返回网络接口使用列表。
	 * @return
	 */
	public List<NetInterfacePerc> getPercs() {
		ArrayList<NetInterfacePerc> ret = new ArrayList<NetInterfacePerc>(this.percQueue.size());
		synchronized (this.percQueue) {
			ret.addAll(this.percQueue);
		}
		return ret;
	}
}
