package smart.bean;

import java.io.Serializable;

/**
 * 数据包明细。
 */
public class DataPacket implements Serializable {

	private static final long serialVersionUID = 5815922969423488144L;

	// 数据采集时间
	private long timestamp;
	
	// 单播包数输入
	private float inUnicast;
	// 单播包数输出
	private float outUnicast;
	// 非单播包数的输入量
	private float nonInUnicast;
	// 非单播包数的输出量
	private float nonOutUnicast;
	// 错包数输入
	private float inWrong;
	// 错包数输出
	private float outWrong;
	// 总错包数
	private float wrongTotal;
	// 丢包数输入
	private float inLost;
	// 丢包数输出
	private float outLost;
	// 总丢包数
	private float lostTotal;
	
	public DataPacket(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public float getInUnicast() {
		return this.inUnicast;
	}

	public float getOutUnicast() {
		return this.outUnicast;
	}

	public float getNonInUnicast() {
		return this.nonInUnicast;
	}

	public float getNonOutUnicast() {
		return this.nonOutUnicast;
	}

	public float getInWrong() {
		return this.inWrong;
	}

	public float getOutWrong() {
		return this.outWrong;
	}

	/**
	 * Sum of inWrong + outWrong
	 * @return
	 */
	public float getWrongTotal() {
		return this.wrongTotal;
	}

	public float getInLost() {
		return this.inLost;
	}

	public float getOutLost() {
		return this.outLost;
	}

	/**
	 * Sum of inLost + outLost
	 * @return
	 */
	public float getLostTotal() {
		return this.lostTotal;
	}

}
