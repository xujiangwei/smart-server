package smart.bean;

import smart.entity.AbstractEntity;

/**
 * Web服务器
 */
public class WebServer extends AbstractEntity {

	private static final long serialVersionUID = 3674856846919710491L;

	private String name;
	
	public WebServer(long id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
