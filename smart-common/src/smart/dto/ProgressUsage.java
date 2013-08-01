package smart.dto;

public class ProgressUsage {

	private int id;
	private int cpuUsed;
	private int memUsed;
	private long collectTime;
	
	public ProgressUsage() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCpuUsed() {
		return cpuUsed;
	}

	public void setCpuUsed(int cpuUsed) {
		this.cpuUsed = cpuUsed;
	}

	public int getMemUsed() {
		return memUsed;
	}

	public void setMemUsed(int memUsed) {
		this.memUsed = memUsed;
	}

	public long getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(long collectTime) {
		this.collectTime = collectTime;
	}

}
