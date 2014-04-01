package smart.entity;

/**
 * 主机实体。
 * 
 * @author Jiangwei Xu
 *
 */
public class Host extends AbstractEntity {

	private String description;

	public Host(String name) {
		super(name);
	}

	public Host(String name, String description) {
		super(name);
		this.setDescription(description);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
