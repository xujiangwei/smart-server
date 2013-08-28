package smart.bean;

import smart.entity.AbstractEntity;

/**
 * 数据库
 */
public class DateBase extends AbstractEntity {

	private static final long serialVersionUID = -4244320262523085173L;

	private String name;
	
	public DateBase(long id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
