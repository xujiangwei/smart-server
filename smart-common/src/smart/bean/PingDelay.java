package smart.bean;

import java.io.Serializable;

/**
 * Ping延迟
 */
public class PingDelay implements Serializable {

	private static final long serialVersionUID = -3239815769258061009L;

	// 数据采集时间
	private long timestamp;

	private String destination;
	private int lost;
	private int minDelay;
	private int maxDelay;
	private int averageDelay;
	
	public PingDelay(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getDestination() {
		return destination;
	}

	public int getLost() {
		return lost;
	}

	public int getMinDelay() {
		return minDelay;
	}

	public int getMaxDelay() {
		return maxDelay;
	}

	public int getAverageDelay() {
		return averageDelay;
	}

}
