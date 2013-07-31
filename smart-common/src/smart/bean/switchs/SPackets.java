package smart.bean.switchs;

/*
 * 交换机的数据包明细类
 */
public class SPackets {
	private int sp_id;
	private int sp_inUnicastPackets;
	private int sp_outUnicastPackets;
	private int sp_noninUnicastPackets;
	private int sp_nonoutUnicastPackets;
	private int sp_inWrongPackets;
	private int sp_outWrongPackets;
	private int sp_inLostPackets;
	private int sp_outLostPackets;
	private int sp_lostTotalPackets;
	private int sp_wrongTotalPackets;
	private int sp_totalPackets;
	private int sp_lostPacketsRate;
	private int sp_wrongPacketsRate;
	private String sp_ifId;

	public int getSp_id() {
		return sp_id;
	}

	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}

	public int getSp_inUnicastPackets() {
		return sp_inUnicastPackets;
	}

	public void setSp_inUnicastPackets(int sp_inUnicastPackets) {
		this.sp_inUnicastPackets = sp_inUnicastPackets;
	}

	public int getSp_outUnicastPackets() {
		return sp_outUnicastPackets;
	}

	public void setSp_outUnicastPackets(int sp_outUnicastPackets) {
		this.sp_outUnicastPackets = sp_outUnicastPackets;
	}

	public int getSp_noninUnicastPackets() {
		return sp_noninUnicastPackets;
	}

	public void setSp_noninUnicastPackets(int sp_noninUnicastPackets) {
		this.sp_noninUnicastPackets = sp_noninUnicastPackets;
	}

	public int getSp_nonoutUnicastPackets() {
		return sp_nonoutUnicastPackets;
	}

	public void setSp_nonoutUnicastPackets(int sp_nonoutUnicastPackets) {
		this.sp_nonoutUnicastPackets = sp_nonoutUnicastPackets;
	}

	public int getSp_inWrongPackets() {
		return sp_inWrongPackets;
	}

	public void setSp_inWrongPackets(int sp_inWrongPackets) {
		this.sp_inWrongPackets = sp_inWrongPackets;
	}

	public int getSp_outWrongPackets() {
		return sp_outWrongPackets;
	}

	public void setSp_outWrongPackets(int sp_outWrongPackets) {
		this.sp_outWrongPackets = sp_outWrongPackets;
	}

	public int getSp_inLostPackets() {
		return sp_inLostPackets;
	}

	public void setSp_inLostPackets(int sp_inLostPackets) {
		this.sp_inLostPackets = sp_inLostPackets;
	}

	public int getSp_outLostPackets() {
		return sp_outLostPackets;
	}

	public void setSp_outLostPackets(int sp_outLostPackets) {
		this.sp_outLostPackets = sp_outLostPackets;
	}

	public int getSp_lostTotalPackets() {
		return sp_lostTotalPackets;
	}

	public void setSp_lostTotalPackets(int sp_lostTotalPackets) {
		this.sp_lostTotalPackets = sp_lostTotalPackets;
	}

	public int getSp_wrongTotalPackets() {
		return sp_wrongTotalPackets;
	}

	public void setSp_wrongTotalPackets(int sp_wrongTotalPackets) {
		this.sp_wrongTotalPackets = sp_wrongTotalPackets;
	}

	public int getSp_totalPackets() {
		return sp_totalPackets;
	}

	public void setSp_totalPackets(int sp_totalPackets) {
		this.sp_totalPackets = sp_totalPackets;
	}

	public int getSp_lostPacketsRate() {
		return sp_lostPacketsRate;
	}

	public void setSp_lostPacketsRate(int sp_lostPacketsRate) {
		this.sp_lostPacketsRate = sp_lostPacketsRate;
	}

	public int getSp_wrongPacketsRate() {
		return sp_wrongPacketsRate;
	}

	public void setSp_wrongPacketsRate(int sp_wrongPacketsRate) {
		this.sp_wrongPacketsRate = sp_wrongPacketsRate;
	}

	public String getSp_ifId() {
		return sp_ifId;
	}

	public void setSp_ifId(String sp_ifId) {
		this.sp_ifId = sp_ifId;
	}

}
