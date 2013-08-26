package smart.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.entity.AbstractEntity;

/**
 * 内存指标信息
 */
public class Memory extends AbstractEntity {

	private static final long serialVersionUID = 2383064429098508774L;

	// 总的系统内存
	private long total;
	// 物理内存总大小
	private long physicsTotal;
	
	/// Memory监测队列
	private Queue<MemoryDetection> memQueue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public Memory(long id) {
		super(id);
		this.memQueue = new LinkedList<MemoryDetection>();
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPhysicsTotal() {
		return physicsTotal;
	}

	public void setPhysicsTotal(long physicsTotal) {
		this.physicsTotal = physicsTotal;
	}

	/**
	 * 添加 Memory监测数据。
	 * @param memDetection
	 */
	public void addMemoryDetection(MemoryDetection memDetection) {
		synchronized (this.memQueue) {
			this.memQueue.add(memDetection);
		}

		if (this.memQueue.size() > this.maxPercs) {
			synchronized (this.memQueue) {
				this.memQueue.poll();
			}
		}
	}

	/**
	 * 返回Memory监测列表。
	 * @return
	 */
	public List<MemoryDetection> getMemoryDetections() {
		ArrayList<MemoryDetection> ret = new ArrayList<MemoryDetection>(this.memQueue.size());
		synchronized (this.memQueue) {
			ret.addAll(this.memQueue);
		}
		return ret;
	}
	
}
