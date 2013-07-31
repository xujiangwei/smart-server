package smart.dto;

public class Ping {

	private int ping_id;
	private String ping_ip;
	private int ping_lost;
	private int ping_delay;
	private long collectTime;
	
	public Ping() {
	}

	public int getPing_id() {
		return ping_id;
	}

	public void setPing_id(int ping_id) {
		this.ping_id = ping_id;
	}

	public String getPing_ip() {
		return ping_ip;
	}

	public void setPing_ip(String ping_ip) {
		this.ping_ip = ping_ip;
	}

	public int getPing_lost() {
		return ping_lost;
	}

	public void setPing_lost(int ping_lost) {
		this.ping_lost = ping_lost;
	}

	public int getPing_delay() {
		return ping_delay;
	}

	public void setPing_delay(int ping_delay) {
		this.ping_delay = ping_delay;
	}

	public long getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(long collectTime) {
		this.collectTime = collectTime;
	}
	
}
