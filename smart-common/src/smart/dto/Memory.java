package smart.dto;

public class Memory {

	private String mem_id;
	private int mem_physicsTotal;
	private long crateTime;
	
	public Memory() {
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getMem_physicsTotal() {
		return mem_physicsTotal;
	}

	public void setMem_physicsTotal(int mem_physicsTotal) {
		this.mem_physicsTotal = mem_physicsTotal;
	}

	public long getCrateTime() {
		return crateTime;
	}

	public void setCrateTime(long crateTime) {
		this.crateTime = crateTime;
	}

}
