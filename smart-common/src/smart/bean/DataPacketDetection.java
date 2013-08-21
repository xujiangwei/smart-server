package smart.bean;

import java.io.Serializable;

/**
 * 数据包明细。
 */
public class DataPacketDetection implements Serializable {

	private static final long serialVersionUID = 5815922969423488144L;

	// 数据采集时间
	private long timestamp;
	
	// 单播包数输入
	private float unicastIn;
	// 单播包数输出
	private float unicastOut;
	// 非单播包数的输入量
	private float nonUnicastIn;
	// 非单播包数的输出量
	private float nonUnicastOut;
	// 错包数输入
	private float errorsIn;
	// 错包数输出
	private float errorsOut;
	// 总错包数
	private float errorsTotal;
	// 丢包数输入
	private float discardsIn;
	// 丢包数输出
	private float discardsOut;
	// 总丢包数
	private float discardsTotal;
	
	public DataPacketDetection(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public float getUnicastIn() {
		return this.unicastIn;
	}

	public float getUnicastOut() {
		return this.unicastOut;
	}

	public float getNonUnicastIn() {
		return this.nonUnicastIn;
	}

	public float getNonUnicastOut() {
		return this.nonUnicastOut;
	}

	public float getErrorsIn() {
		return this.errorsIn;
	}

	public float getErrorsOut() {
		return this.errorsOut;
	}

	public float getErrorsTotal() {
		return this.errorsTotal;
	}

	public float getDiscardsIn() {
		return this.discardsIn;
	}

	public float getDiscardsOut() {
		return this.discardsOut;
	}

	public float getDiscardsTotal() {
		return this.discardsTotal;
	}

}
