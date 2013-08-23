package smart.bean;

import smart.entity.AbstractEntity;

/**
 * 路由器
 */
public class Router extends AbstractEntity {

	private static final long serialVersionUID = 1742465830060090172L;

	private String name;

	public Router(long id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
