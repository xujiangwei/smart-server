package smart.old.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.old.entity.AbstractEntity;

/**
 * PGA
 */
public class PGA extends AbstractEntity {

	private static final long serialVersionUID = -4022495030955121108L;

	// 分配空间大小
	private long distribution;
	// 汇聚目标大小
	private long convergence;
	
	/// PGA使用队列
	private Queue<PGAUsage> queue;
	// 最大记录数
	private volatile int maxPercs = 100;
	
	public PGA(long id) {
		super(id);
		this.queue = new LinkedList<PGAUsage>();
	}

	public long getDistribution() {
		return distribution;
	}

	public void setDistribution(long distribution) {
		this.distribution = distribution;
	}

	public long getConvergence() {
		return convergence;
	}

	public void setConvergence(long convergence) {
		this.convergence = convergence;
	}

	/**
	 * 添加PGA采集数据。
	 * @param usage
	 */
	public void addUsage(PGAUsage usage) {
		synchronized (this.queue) {
			this.queue.add(usage);
		}

		if (this.queue.size() > this.maxPercs) {
			synchronized (this.queue) {
				this.queue.poll();
			}
		}
	}

	/**
	 * 返回采集的数据列表。
	 * @return
	 */
	public List<PGAUsage> getUsages() {
		ArrayList<PGAUsage> ret = new ArrayList<PGAUsage>(this.queue.size());
		synchronized (this.queue) {
			ret.addAll(this.queue);
		}
		return ret;
	}
}
