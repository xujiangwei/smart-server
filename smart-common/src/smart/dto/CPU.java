package smart.dto;

public class CPU {

	private String cpu_id;
	private String cpu_vendor;
	private String cpu_type;
	private float cpu_clockSpeed;
	
	public CPU() {
	}

	public String getCpu_id() {
		return cpu_id;
	}

	public void setCpu_id(String cpu_id) {
		this.cpu_id = cpu_id;
	}

	public String getCpu_vendor() {
		return cpu_vendor;
	}

	public void setCpu_vendor(String cpu_vendor) {
		this.cpu_vendor = cpu_vendor;
	}

	public String getCpu_type() {
		return cpu_type;
	}

	public void setCpu_type(String cpu_type) {
		this.cpu_type = cpu_type;
	}

	public float getCpu_clockSpeed() {
		return cpu_clockSpeed;
	}

	public void setCpu_clockSpeed(float cpu_clockSpeed) {
		this.cpu_clockSpeed = cpu_clockSpeed;
	}

}
