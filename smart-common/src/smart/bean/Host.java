package smart.bean;

import smart.entity.AbstractEntity;

/**
 * 主机
 */
public class Host extends AbstractEntity {

	private static final long serialVersionUID = 4733691548460944139L;

	// 主机名
	private String name;
	
	public Host(long id) {
		super(id);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
