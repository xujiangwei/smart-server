package smart.old.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.old.entity.AbstractEntity;

/**
 * 光纤接口
 */
public class OpticalInterface extends AbstractEntity {

	private static final long serialVersionUID = 8726592650379896041L;
	
	// 通频带
	private int MHz;
	// 描述
	private String description;
	// 类型
	private String type;
	
	/// 光口数据队列
	private Queue<OpticalInterfaceStat> queue;
	// 最大记录数
	private volatile int maxPercs = 100;
	
	public OpticalInterface(long id) {
		super(id);
		this.queue = new LinkedList<OpticalInterfaceStat>();
	}

	public int getMHz() {
		return MHz;
	}

	public void setMHz(int mHz) {
		MHz = mHz;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 添加光口采集数据。
	 * @param stat
	 */
	public void addOiStat(OpticalInterfaceStat stat) {
		synchronized (this.queue) {
			this.queue.add(stat);
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
	public List<OpticalInterfaceStat> getOiStats() {
		ArrayList<OpticalInterfaceStat> ret = new ArrayList<OpticalInterfaceStat>(this.queue.size());
		synchronized (this.queue) {
			ret.addAll(this.queue);
		}
		return ret;
	}
}
