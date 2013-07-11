package smart.resource;

/**
 * 拓扑图。
 * 
 * @author Jiangwei Xu
 */
public abstract class TopoMap {

	private String name;
	private String title;

	public TopoMap(String name, String title) {
		this.name = name;
		this.title = title;
	}

	public String getName() {
		return this.name;
	}

	public String getTitle() {
		return this.title;
	}
}
