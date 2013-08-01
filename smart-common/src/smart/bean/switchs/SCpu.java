package smart.bean.switchs;

import smart.entity.AbstractEntity;

/*
 * 交换机CPU类
 */
public class SCpu extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	private int scpu_id;
	private String scpu_name;
	private String scpu_alias;
	private String scpu_type;
	private String scpu_vender;
	private float scpu_clockSpeed;
	private String scpu_switchId;
	private SCpuLoad sCpuLoad;
	private SCpuUsage sCpuUsage;

	public SCpu(String id) {
		super(id);
	}

	public int getScpu_id() {
		return scpu_id;
	}

	public void setScpu_id(int scpu_id) {
		this.scpu_id = scpu_id;
	}

	public String getScpu_name() {
		return scpu_name;
	}

	public void setScpu_name(String scpu_name) {
		this.scpu_name = scpu_name;
	}

	public String getScpu_alias() {
		return scpu_alias;
	}

	public void setScpu_alias(String scpu_alias) {
		this.scpu_alias = scpu_alias;
	}

	public String getScpu_type() {
		return scpu_type;
	}

	public void setScpu_type(String scpu_type) {
		this.scpu_type = scpu_type;
	}

	public String getScpu_vender() {
		return scpu_vender;
	}

	public void setScpu_vender(String scpu_vender) {
		this.scpu_vender = scpu_vender;
	}

	public float getScpu_clockSpeed() {
		return scpu_clockSpeed;
	}

	public void setScpu_clockSpeed(float scpu_clockSpeed) {
		this.scpu_clockSpeed = scpu_clockSpeed;
	}

	public String getScpu_switchId() {
		return scpu_switchId;
	}

	public void setScpu_switchId(String scpu_switchId) {
		this.scpu_switchId = scpu_switchId;
	}

	public SCpuLoad getsCpuLoad() {
		return sCpuLoad;
	}

	public void setsCpuLoad(SCpuLoad sCpuLoad) {
		this.sCpuLoad = sCpuLoad;
	}

	public SCpuUsage getsCpuUsage() {
		return sCpuUsage;
	}

	public void setsCpuUsage(SCpuUsage sCpuUsage) {
		this.sCpuUsage = sCpuUsage;
	}

}
