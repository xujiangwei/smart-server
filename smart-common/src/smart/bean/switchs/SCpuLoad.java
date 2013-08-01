package smart.bean.switchs;

/*
 * 交换机CPU负载类
 */
public class SCpuLoad {
	private int sload_id;
	private int sload_cpuId;
	private float sload_cpuLoad;
	private long sload_collectionTime;

	public int getSload_id() {
		return sload_id;
	}

	public void setSload_id(int sload_id) {
		this.sload_id = sload_id;
	}

	public int getSload_cpuId() {
		return sload_cpuId;
	}

	public void setSload_cpuId(int sload_cpuId) {
		this.sload_cpuId = sload_cpuId;
	}

	public float getSload_cpuLoad() {
		return sload_cpuLoad;
	}

	public void setSload_cpuLoad(float sload_cpuLoad) {
		this.sload_cpuLoad = sload_cpuLoad;
	}

	public long getSload_collectionTime() {
		return sload_collectionTime;
	}

	public void setSload_collectionTime(long sload_collectionTime) {
		this.sload_collectionTime = sload_collectionTime;
	}

}
