package smart.bean.switchs;

/*
 * 交换机的网络接口类
 */
public class SNetInterface {
	private String sif_id;
	private String sif_ip;
	private String sif_alias;
	private String sif_type;
	private String sif_physicalAddress;
	private String sif_description;
	private int sif_bandwidth;
	private int sif_maxDatagramLength;
	private boolean sif_runtimeStatus;
	private boolean sif_manageStatus;
	private boolean sif_status;
	private String sif_switchId;
	private SFlow sFlow;
	private SPackets sPackets;

	public String getSif_id() {
		return sif_id;
	}

	public void setSif_id(String sif_id) {
		this.sif_id = sif_id;
	}

	public String getSif_ip() {
		return sif_ip;
	}

	public void setSif_ip(String sif_ip) {
		this.sif_ip = sif_ip;
	}

	public String getSif_alias() {
		return sif_alias;
	}

	public void setSif_alias(String sif_alias) {
		this.sif_alias = sif_alias;
	}

	public String getSif_type() {
		return sif_type;
	}

	public void setSif_type(String sif_type) {
		this.sif_type = sif_type;
	}

	public String getSif_physicalAddress() {
		return sif_physicalAddress;
	}

	public void setSif_physicalAddress(String sif_physicalAddress) {
		this.sif_physicalAddress = sif_physicalAddress;
	}

	public String getSif_description() {
		return sif_description;
	}

	public void setSif_description(String sif_description) {
		this.sif_description = sif_description;
	}

	public int getSif_bandwidth() {
		return sif_bandwidth;
	}

	public void setSif_bandwidth(int sif_bandwidth) {
		this.sif_bandwidth = sif_bandwidth;
	}

	public int getSif_maxDatagramLength() {
		return sif_maxDatagramLength;
	}

	public void setSif_maxDatagramLength(int sif_maxDatagramLength) {
		this.sif_maxDatagramLength = sif_maxDatagramLength;
	}

	public boolean isSif_runtimeStatus() {
		return sif_runtimeStatus;
	}

	public void setSif_runtimeStatus(boolean sif_runtimeStatus) {
		this.sif_runtimeStatus = sif_runtimeStatus;
	}

	public boolean isSif_manageStatus() {
		return sif_manageStatus;
	}

	public void setSif_manageStatus(boolean sif_manageStatus) {
		this.sif_manageStatus = sif_manageStatus;
	}

	public boolean isSif_status() {
		return sif_status;
	}

	public void setSif_status(boolean sif_status) {
		this.sif_status = sif_status;
	}

	public String getSif_switchId() {
		return sif_switchId;
	}

	public void setSif_switchId(String sif_switchId) {
		this.sif_switchId = sif_switchId;
	}

	public SFlow getsFlow() {
		return sFlow;
	}

	public void setsFlow(SFlow sFlow) {
		this.sFlow = sFlow;
	}

	public SPackets getsPackets() {
		return sPackets;
	}

	public void setsPackets(SPackets sPackets) {
		this.sPackets = sPackets;
	}

}
