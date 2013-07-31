package smart.bean.switchs;

import smart.entity.AbstractEntity;

/*
 * 交换机内存类
 */
public class SMemory extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String smem_id;
	private String smem_switchId;
	private String smem_name;
	private String smem_type;
	private int smem_size;
	private SMemUsage sMemUsage;

	public SMemory(String id) {
		super(id);
	}

	public String getSmem_id() {
		return smem_id;
	}

	public void setSmem_id(String smem_id) {
		this.smem_id = smem_id;
	}

	public String getSmem_switchId() {
		return smem_switchId;
	}

	public void setSmem_switchId(String smem_switchId) {
		this.smem_switchId = smem_switchId;
	}

	public String getSmem_name() {
		return smem_name;
	}

	public void setSmem_name(String smem_name) {
		this.smem_name = smem_name;
	}

	public String getSmem_type() {
		return smem_type;
	}

	public void setSmem_type(String smem_type) {
		this.smem_type = smem_type;
	}

	public int getSmem_size() {
		return smem_size;
	}

	public void setSmem_size(int smem_size) {
		this.smem_size = smem_size;
	}

	public SMemUsage getsMemUsage() {
		return sMemUsage;
	}

	public void setsMemUsage(SMemUsage sMemUsage) {
		this.sMemUsage = sMemUsage;
	}

}
