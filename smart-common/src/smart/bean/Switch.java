package smart.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.entity.AbstractEntity;

/**
 * 交换机
 */
public class Switch extends AbstractEntity {

	private static final long serialVersionUID = -4479199491497767735L;

	private String name;
	
	/// 健康度队列
	private Queue<HealthDegree> healthQueue;
	/// Ping延迟队列
	private Queue<PingDelay> pingQueue;
	/// 流量统计
	private Queue<Flow> flowQueue;
	/// 数据包统计队列
	private Queue<DataPacket> dataPacketQueue;
	/// 路由表队列
	private Queue<RouteTable> tableQueue;
	// 最大记录数
	private volatile int maxPercs = 100;
	
	public Switch(long id) {
		super(id);
		this.healthQueue = new LinkedList<HealthDegree>();
		this.pingQueue = new LinkedList<PingDelay>();
		this.flowQueue = new LinkedList<Flow>();
		this.dataPacketQueue = new LinkedList<DataPacket>();
		this.tableQueue = new LinkedList<RouteTable>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 添加 健康度数据。
	 * @param 
	 */
	public void addHealthPrec(HealthDegree health) {
		synchronized (this.healthQueue) {
			this.healthQueue.add(health);
		}

		if (this.healthQueue.size() > this.maxPercs) {
			synchronized (this.healthQueue) {
				this.healthQueue.poll();
			}
		}
	}

	/**
	 * 返回健康度数据列表。
	 * @return
	 */
	public List<HealthDegree> getHealthPercs() {
		ArrayList<HealthDegree> ret = new ArrayList<HealthDegree>(this.healthQueue.size());
		synchronized (this.healthQueue) {
			ret.addAll(this.healthQueue);
		}
		return ret;
	}
	
	/**
	 * 添加ping延迟数据。
	 * @param 
	 */
	public void addPingPrec(PingDelay delay) {
		synchronized (this.pingQueue) {
			this.pingQueue.add(delay);
		}

		if (this.pingQueue.size() > this.maxPercs) {
			synchronized (this.pingQueue) {
				this.pingQueue.poll();
			}
		}
	}

	/**
	 * 返回ping延迟数据列表。
	 * @return
	 */
	public List<PingDelay> getPingPercs() {
		ArrayList<PingDelay> ret = new ArrayList<PingDelay>(this.pingQueue.size());
		synchronized (this.pingQueue) {
			ret.addAll(this.pingQueue);
		}
		return ret;
	}
	
	/**
	 * 添加 flow流量数据。
	 * @param 
	 */
	public void addFlowPrec(Flow flow) {
		synchronized (this.flowQueue) {
			this.flowQueue.add(flow);
		}

		if (this.flowQueue.size() > this.maxPercs) {
			synchronized (this.flowQueue) {
				this.flowQueue.poll();
			}
		}
	}

	/**
	 * 返回flow流量数据列表。
	 * @return
	 */
	public List<Flow> getFlowPercs() {
		ArrayList<Flow> ret = new ArrayList<Flow>(this.flowQueue.size());
		synchronized (this.flowQueue) {
			ret.addAll(this.flowQueue);
		}
		return ret;
	}
	
	/**
	 * 添加数据包数据。
	 * @param 
	 */
	public void addDataPacketPrec(DataPacket packet) {
		synchronized (this.dataPacketQueue) {
			this.dataPacketQueue.add(packet);
		}

		if (this.dataPacketQueue.size() > this.maxPercs) {
			synchronized (this.dataPacketQueue) {
				this.dataPacketQueue.poll();
			}
		}
	}

	/**
	 * 返回数据包数据列表。
	 * @return
	 */
	public List<DataPacket> getDataPacketPercs() {
		ArrayList<DataPacket> ret = new ArrayList<DataPacket>(this.dataPacketQueue.size());
		synchronized (this.dataPacketQueue) {
			ret.addAll(this.dataPacketQueue);
		}
		return ret;
	}
	
	/**
	 * 添加路由表数据。
	 * @param 
	 */
	public void addRouteTablePrec(RouteTable table) {
		synchronized (this.tableQueue) {
			this.tableQueue.add(table);
		}

		if (this.tableQueue.size() > this.maxPercs) {
			synchronized (this.tableQueue) {
				this.tableQueue.poll();
			}
		}
	}

	/**
	 * 返回路由表列表。
	 * @return
	 */
	public List<RouteTable> getRouteTablePercs() {
		ArrayList<RouteTable> ret = new ArrayList<RouteTable>(this.tableQueue.size());
		synchronized (this.tableQueue) {
			ret.addAll(this.tableQueue);
		}
		return ret;
	}
}
