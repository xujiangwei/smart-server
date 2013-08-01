package smart.bean.host;

public class MemoryUsage {
	private int id;
	private int physicsUsed;
	private int physicsFree;
	private int swapTotal;
	private int swapUsed;
	private int swapFree;
	private double memLoad;
	private long collectTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPhysicsUsed() {
		return physicsUsed;
	}

	public void setPhysicsUsed(int physicsUsed) {
		this.physicsUsed = physicsUsed;
	}

	public int getPhysicsFree() {
		return physicsFree;
	}

	public void setPhysicsFree(int physicsFree) {
		this.physicsFree = physicsFree;
	}

	public int getSwapTotal() {
		return swapTotal;
	}

	public void setSwapTotal(int swapTotal) {
		this.swapTotal = swapTotal;
	}

	public int getSwapUsed() {
		return swapUsed;
	}

	public void setSwapUsed(int swapUsed) {
		this.swapUsed = swapUsed;
	}

	public int getSwapFree() {
		return swapFree;
	}

	public void setSwapFree(int swapFree) {
		this.swapFree = swapFree;
	}

	public double getMemLoad() {
		return memLoad;
	}

	public void setMemLoad(double memLoad) {
		this.memLoad = memLoad;
	}

	public long getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(long collectTime) {
		this.collectTime = collectTime;
	}

}
