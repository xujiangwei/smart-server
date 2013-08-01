package smart.bean.host;

public class CPUUsage {
	private int id;
	private double user;
	private double system;
	private double IOWaitl;
	private double idle;
	private double nice;
	private double cpuLoad;
	private int cpuCatchSize;
	private double usage;
	private long collectTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getUser() {
		return user;
	}

	public void setUser(double user) {
		this.user = user;
	}

	public double getSystem() {
		return system;
	}

	public void setSystem(double system) {
		this.system = system;
	}

	public double getIOWaitl() {
		return IOWaitl;
	}

	public void setIOWaitl(double iOWaitl) {
		IOWaitl = iOWaitl;
	}

	public double getIdle() {
		return idle;
	}

	public void setIdle(double idle) {
		this.idle = idle;
	}

	public double getNice() {
		return nice;
	}

	public void setNice(double nice) {
		this.nice = nice;
	}

	public double getCpuLoad() {
		return cpuLoad;
	}

	public void setCpuLoad(double cpuLoad) {
		this.cpuLoad = cpuLoad;
	}

	public int getCpuCatchSize() {
		return cpuCatchSize;
	}

	public void setCpuCatchSize(int cpuCatchSize) {
		this.cpuCatchSize = cpuCatchSize;
	}

	public double getUsage() {
		return usage;
	}

	public void setUsage(double usage) {
		this.usage = usage;
	}

	public long getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(long collectTime) {
		this.collectTime = collectTime;
	}

}
