package smart.bean;

import java.io.Serializable;

/**
 * 健康度
 */
public class HealthDegree implements Serializable {

	private static final long serialVersionUID = 8977127848981711678L;

	// 数据采集时间
	private long timestamp;
	
	private String moId;
	private String moIp;
	private String moName;
	private int degree;
	
	public HealthDegree(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getMoId() {
		return moId;
	}

	public String getMoIp() {
		return moIp;
	}

	public String getMoName() {
		return moName;
	}

	public int getDegree() {
		return degree;
	}

}
