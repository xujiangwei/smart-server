package smart.entity;

import java.util.concurrent.ConcurrentHashMap;

import net.cellcloud.extras.memory.MemoryArea;

/**
 * 抽象实体。
 * 
 * @author Jiangwei Xu
 */
public abstract class AbstractEntity extends MemoryArea implements Entity {

	private static final long serialVersionUID = 1638959794358520994L;

	private String id;
	/// 子节点映射，Key：Entity ID
	private ConcurrentHashMap<String, Entity> children;
	/// 父节点
	private Entity parent;

	public AbstractEntity(String id) {
		super();
		this.id = id;
		this.children = new ConcurrentHashMap<String, Entity>();
		this.parent = null;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void addChild(Entity entity) {
		this.children.put(entity.getId(), entity);
	}

	@Override
	public void removeChild(Entity entity) {
		this.children.remove(entity);
	}

	@Override
	public Entity getChild(String id) {
		return this.children.get(id);
	}

	@Override
	public Entity getParent() {
		return this.parent;
	}

	/**
	 * 设置父对象。
	 * @param parent
	 */
	public void setParent(Entity parent) {
		this.parent = parent;
	}
}
