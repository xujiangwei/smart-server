package smart.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象层实体。
 * 
 * @author Jiangwei Xu
 *
 */
public abstract class AbstractEntity implements Entity {

	private String name;
	private String displayName;

	private HashMap<String, Entity> children;

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

	@Override
	public void addChild(Entity entity) {
		if (null == this.children) {
			this.children = new HashMap<String, Entity>(2);
		}

		synchronized (this.children) {
			this.children.put(entity.getName(), entity);
		}
	}

	@Override
	public void removeChild(Entity entity) {
		if (null == this.children) {
			return;
		}

		synchronized (this.children) {
			this.children.remove(entity.getName());
		}
	}

	@Override
	public Entity getChild(String name) {
		if (null == this.children) {
			return null;
		}

		synchronized (this.children) {
			return this.children.get(name);
		}
	}

	@Override
	public List<Entity> searchChildren(String name) {
		List<Entity> list = new ArrayList<Entity>(2);
		for (Map.Entry<String, Entity> entry : this.children.entrySet()) {
			String n = entry.getKey();
			Entity e = entry.getValue();

			// 判断当前子实体
			if (n.equals(name)) {
				list.add(e);
			}

			List<Entity> el = e.searchChildren(name);
			if (null != el && !el.isEmpty()) {
				list.addAll(el);
			}
		}
		return list;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String value) {
		this.displayName = value;
	}
}
