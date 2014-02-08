package smart.old.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.old.entity.AbstractEntity;

/**
 * UGA
 */
public class UGA extends AbstractEntity {

	private static final long serialVersionUID = 7764381241212822219L;

	private long maxSize;
	
	/// UGA使用队列
	private Queue<UGAUsage> queue;
	// 最大记录数
	private volatile int maxPercs = 100;
	
	public UGA(long id) {
		super(id);
		this.queue = new LinkedList<UGAUsage>();
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * 添加UGA采集数据。
	 * @param usage
	 */
	public void addUsage(UGAUsage usage) {
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
	public List<UGAUsage> getUsages() {
		ArrayList<UGAUsage> ret = new ArrayList<UGAUsage>(this.queue.size());
		synchronized (this.queue) {
			ret.addAll(this.queue);
		}
		return ret;
	}
}
