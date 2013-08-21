package smart.bean;

import java.io.Serializable;

/**
 * 流量统计。
 */
public class TrafficDetection implements Serializable {

	private static final long serialVersionUID = -5389945377904296536L;

	// 数据采集时间
	private long timestamp;
	
	private float inTraffic;
	private float outTraffic;
	private float bitsIn;
	private float bitsOut;
	
	public TrafficDetection(long timestamp){
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}

	public float getInTraffic() {
		return inTraffic;
	}

	public float getOutTraffic() {
		return outTraffic;
	}

	public float getBitsIn() {
		return bitsIn;
	}

	public float getBitsOut() {
		return bitsOut;
	}

}
