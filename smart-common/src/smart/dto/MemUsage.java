package smart.dto;

public class MemUsage {

	private int id;
	private int mem_physicsUsed;
	private int mem_physicsFree;
	private int mem_swapTotal;
	private int mem_swapUsed;
	private int mem_swapFree;
	private double mem_load;
	private long mem_collectTime;
	
	public MemUsage() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMem_physicsUsed() {
		return mem_physicsUsed;
	}

	public void setMem_physicsUsed(int mem_physicsUsed) {
		this.mem_physicsUsed = mem_physicsUsed;
	}

	public int getMem_physicsFree() {
		return mem_physicsFree;
	}

	public void setMem_physicsFree(int mem_physicsFree) {
		this.mem_physicsFree = mem_physicsFree;
	}

	public int getMem_swapTotal() {
		return mem_swapTotal;
	}

	public void setMem_swapTotal(int mem_swapTotal) {
		this.mem_swapTotal = mem_swapTotal;
	}

	public int getMem_swapUsed() {
		return mem_swapUsed;
	}

	public void setMem_swapUsed(int mem_swapUsed) {
		this.mem_swapUsed = mem_swapUsed;
	}

	public int getMem_swapFree() {
		return mem_swapFree;
	}

	public void setMem_swapFree(int mem_swapFree) {
		this.mem_swapFree = mem_swapFree;
	}

	public double getMem_load() {
		return mem_load;
	}

	public void setMem_load(double mem_load) {
		this.mem_load = mem_load;
	}

	public long getMem_collectTime() {
		return mem_collectTime;
	}

	public void setMem_collectTime(long mem_collectTime) {
		this.mem_collectTime = mem_collectTime;
	}

}
