package smart.bean.host;

import java.util.List;

import smart.entity.AbstractEntity;

/**
 * 内存指标信息
 */
public class Memory extends AbstractEntity {

	private static final long serialVersionUID = 2383064429098508774L;

	private int physicsTotal;
	private long crateTime;
	private List<MemoryUsage> memoryUsage;

	public Memory(long id) {
		super(id);
	}

	public int getPhysicsTotal() {
		return physicsTotal;
	}

	public void setPhysicsTotal(int physicsTotal) {
		this.physicsTotal = physicsTotal;
	}

	public long getCrateTime() {
		return crateTime;
	}

	public void setCrateTime(long crateTime) {
		this.crateTime = crateTime;
	}

	public List<MemoryUsage> getMemoryUsage() {
		return memoryUsage;
	}

	public void setMemoryUsage(List<MemoryUsage> memoryUsage) {
		this.memoryUsage = memoryUsage;
	}

}
