package smart.bean;

import java.io.Serializable;

/**
 * ICMP报文监测
 */
public class ICMPDetection implements Serializable {

	private static final long serialVersionUID = 1856313059888362346L;

	// 数据采集时间
	private long timestamp;
	
	private int inMessages;
	private int outMessages;
	private int errorsIn;
	private int errorsOut;
	private int destUnreachablesIn;
	private int destUnreachablesOut;
	private int timeExceededsIn;
	private int timeExceededsOut;
	private int parmProblemsIn;
	private int parmProblemsOut;
	private int sourceQuenchesIn;
	private int redirectsIn;
	private int maskRequestsIn;
	private int maskRepliesIn;
	private int sourceQuenchesOut;
	private int redirectsOut;
	private int maskRequestsOut;
	private int maskRepliesOut;
	private int echoesIn;
	private int echoRepliesIn;
	private int echoesOut;
	private int echoRepliesOut;
	private double responseTime;
	
	public ICMPDetection(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public int getInMessages() {
		return this.inMessages;
	}

	public int getOutMessages() {
		return this.outMessages;
	}

	public int getErrorsIn() {
		return this.errorsIn;
	}

	public int getErrorsOut() {
		return this.errorsOut;
	}

	public int getDestUnreachablesIn() {
		return this.destUnreachablesIn;
	}

	public int getDestUnreachablesOut() {
		return this.destUnreachablesOut;
	}

	public int getTimeExceededsIn() {
		return this.timeExceededsIn;
	}

	public int getTimeExceededsOut() {
		return this.timeExceededsOut;
	}

	public int getParmProblemsIn() {
		return this.parmProblemsIn;
	}

	public int getParmProblemsOut() {
		return this.parmProblemsOut;
	}

	public int getSourceQuenchesIn() {
		return this.sourceQuenchesIn;
	}

	public int getRedirectsIn() {
		return this.redirectsIn;
	}

	public int getMaskRequestsIn() {
		return this.maskRequestsIn;
	}

	public int getMaskRepliesIn() {
		return this.maskRepliesIn;
	}

	public int getSourceQuenchesOut() {
		return this.sourceQuenchesOut;
	}

	public int getRedirectsOut() {
		return this.redirectsOut;
	}

	public int getMaskRequestsOut() {
		return this.maskRequestsOut;
	}

	public int getMaskRepliesOut() {
		return this.maskRepliesOut;
	}

	public int getEchoesIn() {
		return this.echoesIn;
	}

	public int getEchoRepliesIn() {
		return this.echoRepliesIn;
	}

	public int getEchoesOut() {
		return this.echoesOut;
	}

	public int getEchoRepliesOut() {
		return this.echoRepliesOut;
	}

	public double getResponseTime() {
		return responseTime;
	}

}
