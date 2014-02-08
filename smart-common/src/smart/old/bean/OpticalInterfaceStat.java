package smart.old.bean;

import java.io.Serializable;

/**
 * 光口采集信息
 */
public class OpticalInterfaceStat implements Serializable {

	private static final long serialVersionUID = 8507676787287295498L;

	// 数据采集时间
	private long timestamp;
	
	private long BitsIn;
	private long BitsOut;
	private double InTraffic;
	private double OutTraffic;
	
	public OpticalInterfaceStat(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getBitsIn() {
		return BitsIn;
	}

	public void setBitsIn(long bitsIn) {
		BitsIn = bitsIn;
	}

	public long getBitsOut() {
		return BitsOut;
	}

	public void setBitsOut(long bitsOut) {
		BitsOut = bitsOut;
	}

	public double getInTraffic() {
		return InTraffic;
	}

	public void setInTraffic(double inTraffic) {
		InTraffic = inTraffic;
	}

	public double getOutTraffic() {
		return OutTraffic;
	}

	public void setOutTraffic(double outTraffic) {
		OutTraffic = outTraffic;
	}

}
