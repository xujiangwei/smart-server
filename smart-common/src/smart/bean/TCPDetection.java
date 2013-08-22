package smart.bean;

import java.io.Serializable;

/**
 * TCP连接监测
 */
public class TCPDetection implements Serializable {

	private static final long serialVersionUID = -4510164684727726167L;

	// 数据采集时间
	private long timestamp;
	
	private long activeOpens;
	private long attemptFails;
	private long currEstab;
	private long estabResets;
	private long inErrs;
	private long inSegs;
	private long outRsts;
	private long outSegs;
	private long passiveOpens;
	private long retransSegs;
	
	public TCPDetection(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public long getActiveOpens() {
		return this.activeOpens;
	}

	public long getAttemptFails() {
		return this.attemptFails;
	}

	public long getCurrEstab() {
		return this.currEstab;
	}

	public long getEstabResets() {
		return this.estabResets;
	}

	public long getInErrs() {
		return this.inErrs;
	}

	public long getInSegs() {
		return this.inSegs;
	}

	public long getOutRsts() {
		return this.outRsts;
	}

	public long getOutSegs() {
		return this.outSegs;
	}

	public long getPassiveOpens() {
		return this.passiveOpens;
	}

	public long getRetransSegs() {
		return this.retransSegs;
	}
	
}
