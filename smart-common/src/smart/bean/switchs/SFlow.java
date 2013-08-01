package smart.bean.switchs;

/*
 * 交换机流接口量类
 */
public class SFlow {
	private int sflow_id;
	private String sflow_sifId;
	private float sflow_receive;
	private float sflow_send;
	private float sflow_total;
	private float sflowrate_receive;
	private float sflowrate_send;
	private float sflowrate_total;
	private long sflow_collectTime;

	public int getSflow_id() {
		return sflow_id;
	}

	public void setSflow_id(int sflow_id) {
		this.sflow_id = sflow_id;
	}

	public String getSflow_sifId() {
		return sflow_sifId;
	}

	public void setSflow_sifId(String sflow_sifId) {
		this.sflow_sifId = sflow_sifId;
	}

	public float getSflow_receive() {
		return sflow_receive;
	}

	public void setSflow_receive(float sflow_receive) {
		this.sflow_receive = sflow_receive;
	}

	public float getSflow_send() {
		return sflow_send;
	}

	public void setSflow_send(float sflow_send) {
		this.sflow_send = sflow_send;
	}

	public float getSflow_total() {
		return sflow_total;
	}

	public void setSflow_total(float sflow_total) {
		this.sflow_total = sflow_total;
	}

	public float getSflowrate_receive() {
		return sflowrate_receive;
	}

	public void setSflowrate_receive(float sflowrate_receive) {
		this.sflowrate_receive = sflowrate_receive;
	}

	public float getSflowrate_send() {
		return sflowrate_send;
	}

	public void setSflowrate_send(float sflowrate_send) {
		this.sflowrate_send = sflowrate_send;
	}

	public float getSflowrate_total() {
		return sflowrate_total;
	}

	public void setSflowrate_total(float sflowrate_total) {
		this.sflowrate_total = sflowrate_total;
	}

	public long getSflow_collectTime() {
		return sflow_collectTime;
	}

	public void setSflow_collectTime(long sflow_collectTime) {
		this.sflow_collectTime = sflow_collectTime;
	}

}
