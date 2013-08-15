package smart.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.entity.AbstractEntity;

/**
 * CPU指标信息
 */
public class CPU extends AbstractEntity {

	private static final long serialVersionUID = -3010764141583766821L;

	// 厂商
	private String vendor;
	// 模块名
	private String model;
	// 时钟频率，单位：MHz
	private int clockSpeed;
	// 缓存大小
	private long cacheSize;
	// 总计核心数
	private int totalCores;

	/// CPU 利用率（百分比）
	private Queue<CPUPerc> percQueue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public CPU(long id) {
		super(id);
		this.percQueue = new LinkedList<CPUPerc>();
	}

	public String getVendor() {
		return this.vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return this.model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	public int getMhz() {
		return this.clockSpeed;
	}
	public void setMhz(int mhz) {
		this.clockSpeed = mhz;
	}

	public long getCacheSize() {
		return this.cacheSize;
	}
	public void setCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
	}

	public int getTotalCores() {
		return this.totalCores;
	}
	public void setTotalCores(int totalCores) {
		this.totalCores = totalCores;
	}

	/**
	 * 添加 CPU 利用百分比数据。
	 * @param perc
	 */
	public void addPrec(CPUPerc perc) {
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
	public List<CPUPerc> getPercs() {
		ArrayList<CPUPerc> ret = new ArrayList<CPUPerc>(this.percQueue.size());
		synchronized (this.percQueue) {
			ret.addAll(this.percQueue);
		}
		return ret;
	}
}
