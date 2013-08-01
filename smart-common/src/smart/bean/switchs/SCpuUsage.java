package smart.bean.switchs;

/*
 * 交换机CPU利用率类
 */
public class SCpuUsage {
	private int scu_id;
	private String scu_cpuId;
	private float scu_cpuIdle;
	private float scu_cpuUsed;
	private float scu_cpuUsage;
	private long scu_collectTime;

	public int getScu_id() {
		return scu_id;
	}

	public void setScu_id(int scu_id) {
		this.scu_id = scu_id;
	}

	public String getScu_cpuId() {
		return scu_cpuId;
	}

	public void setScu_cpuId(String scu_cpuId) {
		this.scu_cpuId = scu_cpuId;
	}

	public float getScu_cpuIdle() {
		return scu_cpuIdle;
	}

	public void setScu_cpuIdle(float scu_cpuIdle) {
		this.scu_cpuIdle = scu_cpuIdle;
	}

	public float getScu_cpuUsed() {
		return scu_cpuUsed;
	}

	public void setScu_cpuUsed(float scu_cpuUsed) {
		this.scu_cpuUsed = scu_cpuUsed;
	}

	public float getScu_cpuUsage() {
		return scu_cpuUsage;
	}

	public void setScu_cpuUsage(float scu_cpuUsage) {
		this.scu_cpuUsage = scu_cpuUsage;
	}

	public long getScu_collectTime() {
		return scu_collectTime;
	}

	public void setScu_collectTime(long scu_collectTime) {
		this.scu_collectTime = scu_collectTime;
	}

}
