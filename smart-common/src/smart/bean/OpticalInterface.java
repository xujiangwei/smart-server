package smart.bean;

import smart.entity.AbstractEntity;

/**
 * 光纤接口
 */
public class OpticalInterface extends AbstractEntity {

	private static final long serialVersionUID = 8726592650379896041L;
	
	// 通频带
	private int MHz;
	// 描述
	private String description;
	// 类型
	private String type;
	
	public OpticalInterface(long id) {
		super(id);
	}

	public int getMHz() {
		return MHz;
	}

	public void setMHz(int mHz) {
		MHz = mHz;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
