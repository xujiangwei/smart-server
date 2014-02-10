package smart.entity;

/**
 * 
 * @author Jiangwei Xu
 *
 */
public abstract class AbstractEntity implements Entity {

	private String name;

	public AbstractEntity(String name) {
		super();
		this.setName(name);
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
