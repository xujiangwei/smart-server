package smart.old.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import smart.old.entity.AbstractEntity;

/**
 * 池
 */
public class Pool extends AbstractEntity {

	private static final long serialVersionUID = -1597462873321682385L;

	// 名称
	private String name;
	// 系统默认大小
	private long defaultSize;
	// 实际大小
	private long realSize;
	
	/// 池采集信息队列
	private Queue<PoolUsage> queue;
	// 最大记录数
	private volatile int maxPercs = 100;
	
	public Pool(long id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDefaultSize() {
		return defaultSize;
	}

	public void setDefaultSize(long defaultSize) {
		this.defaultSize = defaultSize;
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
	public void addUsage(PoolUsage usage) {
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
	public List<PoolUsage> getUsages() {
		ArrayList<PoolUsage> ret = new ArrayList<PoolUsage>(this.queue.size());
		synchronized (this.queue) {
			ret.addAll(this.queue);
		}
		return ret;
	}
}
