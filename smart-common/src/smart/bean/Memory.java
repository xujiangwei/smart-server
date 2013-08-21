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

	// 物理内存总大小
	private int physicsTotal;
	
	/// Memory监测队列
	private Queue<MemoryDetection> memQueue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public Memory(long id) {
		super(id);
		this.memQueue = new LinkedList<MemoryDetection>();
	}

	public int getPhysicsTotal() {
		return physicsTotal;
	}

	public void setPhysicsTotal(int physicsTotal) {
		this.physicsTotal = physicsTotal;
	}

	/**
	 * 添加 Memory监测数据。
	 * @param perc
	 */
	public void addPrec(MemoryDetection perc) {
		synchronized (this.memQueue) {
			this.memQueue.add(perc);
		}

		if (this.memQueue.size() > this.maxPercs) {
			synchronized (this.memQueue) {
				this.memQueue.poll();
			}
		}
	}

	/**
	 * 返回内存监测列表。
	 * @return
	 */
	public List<MemoryDetection> getPercs() {
		ArrayList<MemoryDetection> ret = new ArrayList<MemoryDetection>(this.memQueue.size());
		synchronized (this.memQueue) {
			ret.addAll(this.memQueue);
		}
		return ret;
	}
}
