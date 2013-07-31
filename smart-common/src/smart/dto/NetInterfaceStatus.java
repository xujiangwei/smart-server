package smart.dto;

public class NetInterfaceStatus {

	private int id;
	private float ni_flow;
	private float ni_throughput;
	private long ni_collectTime;
	private String ni_status;
	
	public NetInterfaceStatus() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getNi_flow() {
		return ni_flow;
	}

	public void setNi_flow(float ni_flow) {
		this.ni_flow = ni_flow;
	}

	public float getNi_throughput() {
		return ni_throughput;
	}

	public void setNi_throughput(float ni_throughput) {
		this.ni_throughput = ni_throughput;
	}

	public long getNi_collectTime() {
		return ni_collectTime;
	}

	public void setNi_collectTime(long ni_collectTime) {
		this.ni_collectTime = ni_collectTime;
	}

	public String getNi_status() {
		return ni_status;
	}

	public void setNi_status(String ni_status) {
		this.ni_status = ni_status;
	}
	
}
