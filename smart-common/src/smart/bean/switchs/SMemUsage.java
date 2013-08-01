package smart.bean.switchs;

/*
 * 交换机的内存利用率
 */
public class SMemUsage {
	private int smusage_id;
	private String smusage_memId;
	private int smusage_memSize;
	private int smusage_memUsed;
	private int smusage_memIdle;
	private float smusage_memUsage;

	public int getSmusage_id() {
		return smusage_id;
	}

	public void setSmusage_id(int smusage_id) {
		this.smusage_id = smusage_id;
	}

	public String getSmusage_memId() {
		return smusage_memId;
	}

	public void setSmusage_memId(String smusage_memId) {
		this.smusage_memId = smusage_memId;
	}

	public int getSmusage_memSize() {
		return smusage_memSize;
	}

	public void setSmusage_memSize(int smusage_memSize) {
		this.smusage_memSize = smusage_memSize;
	}

	public int getSmusage_memUsed() {
		return smusage_memUsed;
	}

	public void setSmusage_memUsed(int smusage_memUsed) {
		this.smusage_memUsed = smusage_memUsed;
	}

	public int getSmusage_memIdle() {
		return smusage_memIdle;
	}

	public void setSmusage_memIdle(int smusage_memIdle) {
		this.smusage_memIdle = smusage_memIdle;
	}

	public float getSmusage_memUsage() {
		return smusage_memUsage;
	}

	public void setSmusage_memUsage(float smusage_memUsage) {
		this.smusage_memUsage = smusage_memUsage;
	}

}
