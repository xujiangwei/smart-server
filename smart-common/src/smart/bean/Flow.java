package smart.bean;

import java.io.Serializable;

/**
 * 流量统计。
 */
public class Flow implements Serializable {

	private static final long serialVersionUID = -5389945377904296536L;

	// 数据采集时间
	private long timestamp;
	
	private float receive;
	private float send;
	private float receiveRate;
	private float sendRate;
	
	public Flow(long timestamp){
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}

	public float getReceive() {
		return this.receive;
	}

	public float getSend() {
		return this.send;
	}

	public float getReceiveRate() {
		return this.receiveRate;
	}

	public float getSendRate() {
		return this.sendRate;
	}

}
