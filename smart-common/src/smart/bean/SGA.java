package smart.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.entity.AbstractEntity;

/**
 * SGA
 */
public class SGA extends AbstractEntity {

	private static final long serialVersionUID = -6589963996107924819L;

	private long maxSize;
	private long realSize;
	
	/// SGA使用队列
	private Queue<SGAUsage> queue;
	// 最大记录数
	private volatile int maxPercs = 100;
	
	public SGA(long id) {
		super(id);
		this.queue = new LinkedList<SGAUsage>();
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public long getRealSize() {
		return realSize;
	}

	public void setRealSize(long realSize) {
		this.realSize = realSize;
	}

	/**
	 * 添加SGA采集数据。
	 * @param usage
	 */
	public void addUsage(SGAUsage usage) {
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
	public List<SGAUsage> getUsages() {
		ArrayList<SGAUsage> ret = new ArrayList<SGAUsage>(this.queue.size());
		synchronized (this.queue) {
			ret.addAll(this.queue);
		}
		return ret;
	}
}
