package smart.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.entity.AbstractEntity;

/**
 * 路由器
 */
public class Router extends AbstractEntity {

	private static final long serialVersionUID = 1742465830060090172L;

	private String name;

	// / 健康度队列
	private Queue<HealthDegree> healthQueue;
	// / 流量统计
	private Queue<TrafficDetection> flowQueue;
	// / 数据包统计队列
	private Queue<DataPacketDetection> dataPacketQueue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public Router(long id) {
		super(id);
		this.healthQueue = new LinkedList<HealthDegree>();
		this.flowQueue = new LinkedList<TrafficDetection>();
		this.dataPacketQueue = new LinkedList<DataPacketDetection>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 添加 健康度数据。
	 * 
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
	 * 
	 * @return
	 */
	public List<HealthDegree> getHealthPercs() {
		ArrayList<HealthDegree> ret = new ArrayList<HealthDegree>(
				this.healthQueue.size());
		synchronized (this.healthQueue) {
			ret.addAll(this.healthQueue);
		}
		return ret;
	}

	/**
	 * 添加 flow流量数据。
	 * 
	 * @param
	 */
	public void addFlowPrec(TrafficDetection flow) {
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
	 * 
	 * @return
	 */
	public List<TrafficDetection> getFlowPercs() {
		ArrayList<TrafficDetection> ret = new ArrayList<TrafficDetection>(this.flowQueue.size());
		synchronized (this.flowQueue) {
			ret.addAll(this.flowQueue);
		}
		return ret;
	}

	/**
	 * 添加数据包数据。
	 * 
	 * @param
	 */
	public void addDataPacketPrec(DataPacketDetection packet) {
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
	 * 
	 * @return
	 */
	public List<DataPacketDetection> getDataPacketPercs() {
		ArrayList<DataPacketDetection> ret = new ArrayList<DataPacketDetection>(
				this.dataPacketQueue.size());
		synchronized (this.dataPacketQueue) {
			ret.addAll(this.dataPacketQueue);
		}
		return ret;
	}
}
