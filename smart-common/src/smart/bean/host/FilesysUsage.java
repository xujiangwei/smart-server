package smart.bean.host;
public class FilesysUsage {
	private int id;
	private float used;
	private float free;
	private float usage;
	private long collectTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getUsed() {
		return used;
	}

	public void setUsed(float used) {
		this.used = used;
	}

	public float getFree() {
		return free;
	}

	public void setFree(float free) {
		this.free = free;
	}


	public long getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(long collectTime) {
		this.collectTime = collectTime;
	}

	public float getUsage() {
		return usage;
	}

	public void setUsage(float usage) {
		this.usage = usage;
	}

}
