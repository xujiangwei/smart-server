package smart.bean.host;

import java.util.concurrent.ConcurrentHashMap;

import smart.entity.AbstractEntity;

/**
 * CPU指标信息
 */
public class CPU extends AbstractEntity {

	private static final long serialVersionUID = -3010764141583766821L;

	private String vendor;
	private String model;
	private int clockSpeed;
	private long cacheSize;
	private int totalCores;

	/// CPU 利用率（百分比），Key: 数据时间戳
	private ConcurrentHashMap<Long, CPUPerc> percs;

	public CPU(String id) {
		super(id);
		this.percs = new ConcurrentHashMap<Long, CPUPerc>(2);
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
	public void getCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
	}

	public int getTotalCores() {
		return this.totalCores;
	}
	public void setTotalCores(int totalCores) {
		this.totalCores = totalCores;
	}

	public void addPrec(long timestamp, CPUPerc perc) {
		this.percs.put(timestamp, perc);
	}
}
