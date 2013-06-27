package smart.entity;

import java.util.concurrent.ConcurrentHashMap;

import net.cellcloud.extras.memory.MemoryArea;

/**
 * 抽象实体。
 * 
 * @author Jiangwei Xu
 */
public abstract class AbstractEntity extends MemoryArea implements Entity {

	private String id;
	/// 子节点映射，Key：Entity ID
	private ConcurrentHashMap<String, Entity> children;

	public AbstractEntity(String id) {
		super();
		this.id = id;
		this.children = new ConcurrentHashMap<String, Entity>();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void addEntity(Entity entity) {
		this.children.put(entity.getId(), entity);
	}

	@Override
	public void removeEntity(Entity entity) {
		this.children.remove(entity);
	}
}
