package smart.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.entity.AbstractEntity;

/**
 * 主机
 */
public class Host extends AbstractEntity {

	private static final long serialVersionUID = 4733691548460944139L;

	// 主机名
	private String name;
	
	/// 健康度队列
	private Queue<HealthDegree> healthQueue;
	/// Ping延迟队列
	private Queue<PingDelay> pingQueue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public Host(long id) {
		super(id);
		this.healthQueue = new LinkedList<HealthDegree>();
		this.pingQueue = new LinkedList<PingDelay>();
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
}
