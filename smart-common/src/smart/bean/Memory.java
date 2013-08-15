package smart.bean;

import java.util.ArrayList;
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
	
	/// Memory利用率（百分比）
	private Queue<MemoryPerc> percQueue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public Memory(long id) {
		super(id);
	}

	public int getPhysicsTotal() {
		return physicsTotal;
	}

	public void setPhysicsTotal(int physicsTotal) {
		this.physicsTotal = physicsTotal;
	}

	/**
	 * 添加 Memory利用百分比数据。
	 * @param perc
	 */
	public void addPrec(MemoryPerc perc) {
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
	 * 返回利用率百分比列表。
	 * @return
	 */
	public List<MemoryPerc> getPercs() {
		ArrayList<MemoryPerc> ret = new ArrayList<MemoryPerc>(this.percQueue.size());
		synchronized (this.percQueue) {
			ret.addAll(this.percQueue);
		}
		return ret;
	}
}
