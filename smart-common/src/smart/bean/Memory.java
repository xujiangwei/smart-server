package smart.bean;

import smart.entity.AbstractEntity;

/**
 * 内存指标信息
 */
public class Memory extends AbstractEntity {

	private static final long serialVersionUID = 2383064429098508774L;

	private int physicsTotal;
	private int physicsUsed;
	private int physicsFree;
	private int swapTotal;
	private int swapUsed;
	private int swapFree;
	private double memLoad;
	private long collectTime;
	
	public Memory(String id) {
		super(id);
	}

	public int getPhysicsTotal() {
		return physicsTotal;
	}

	public void setPhysicsTotal(int physicsTotal) {
		this.physicsTotal = physicsTotal;
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
