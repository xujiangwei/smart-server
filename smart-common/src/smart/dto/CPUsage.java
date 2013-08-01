package smart.dto;

public class CPUsage {

	private int id;
	private int cpu_cacheSize;
	private double cpu_user;
	private double cpu_nice;
	private double cpu_system;
	private double cpu_IOWait;
	private double cpu_idle;
	private double cpu_load;
	private double cpu_usage;
	private long cpu_collectTime;
	
	public CPUsage() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCpu_cacheSize() {
		return cpu_cacheSize;
	}

	public void setCpu_cacheSize(int cpu_cacheSize) {
		this.cpu_cacheSize = cpu_cacheSize;
	}

	public double getCpu_user() {
		return cpu_user;
	}

	public void setCpu_user(double cpu_user) {
		this.cpu_user = cpu_user;
	}

	public double getCpu_nice() {
		return cpu_nice;
	}

	public void setCpu_nice(double cpu_nice) {
		this.cpu_nice = cpu_nice;
	}

	public double getCpu_system() {
		return cpu_system;
	}

	public void setCpu_system(double cpu_system) {
		this.cpu_system = cpu_system;
	}

	public double getCpu_IOWait() {
		return cpu_IOWait;
	}

	public void setCpu_IOWait(double cpu_IOWait) {
		this.cpu_IOWait = cpu_IOWait;
	}

	public double getCpu_idle() {
		return cpu_idle;
	}

	public void setCpu_idle(double cpu_idle) {
		this.cpu_idle = cpu_idle;
	}

	public double getCpu_load() {
		return cpu_load;
	}

	public void setCpu_load(double cpu_load) {
		this.cpu_load = cpu_load;
	}

	public double getCpu_usage() {
		return cpu_usage;
	}

	public void setCpu_usage(double cpu_usage) {
		this.cpu_usage = cpu_usage;
	}

	public long getCpu_collectTime() {
		return cpu_collectTime;
	}

	public void setCpu_collectTime(long cpu_collectTime) {
		this.cpu_collectTime = cpu_collectTime;
	}
	
}
