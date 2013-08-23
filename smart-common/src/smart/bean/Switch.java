package smart.bean;

import smart.entity.AbstractEntity;

/**
 * 交换机
 */
public class Switch extends AbstractEntity {

	private static final long serialVersionUID = -4479199491497767735L;

	private String name;
	
	public Switch(long id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
