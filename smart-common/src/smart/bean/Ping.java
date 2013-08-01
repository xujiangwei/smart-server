package smart.bean;

public class Ping {
	private int p_id;
	private String p_ip;
	private int p_lost;
	private int p_delay;
	private long p_collectTime;

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getP_ip() {
		return p_ip;
	}

	public void setP_ip(String p_ip) {
		this.p_ip = p_ip;
	}

	public int getP_lost() {
		return p_lost;
	}

	public void setP_lost(int p_lost) {
		this.p_lost = p_lost;
	}

	public int getP_delay() {
		return p_delay;
	}

	public void setP_delay(int p_delay) {
		this.p_delay = p_delay;
	}

	public long getP_collectTime() {
		return p_collectTime;
	}

	public void setP_collectTime(long p_collectTime) {
		this.p_collectTime = p_collectTime;
	}

}
