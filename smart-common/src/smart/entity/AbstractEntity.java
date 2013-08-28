package smart.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import net.cellcloud.extras.memory.MemoryArea;

/**
 * 抽象实体。
 * 
 * @author Jiangwei Xu
 */
public abstract class AbstractEntity extends MemoryArea implements Entity {

	private static final long serialVersionUID = 1638959794358520994L;

	private long id;
	/// 子节点映射，Key：Entity ID
	private ConcurrentHashMap<Long, Entity> children;
	/// 父节点
	private Entity parent;

	public AbstractEntity(long id) {
		super();
		this.id = id;
		this.children = new ConcurrentHashMap<Long, Entity>();
		this.parent = null;
	}

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public void addChild(Entity entity) {
		entity.setParent(this);
		this.children.put(entity.getId(), entity);
	}

	@Override
	public void removeChild(Entity entity) {
		entity.setParent(null);
		this.children.remove(entity);
	}

	@Override
	public Entity getChild(long id) {
		return this.children.get(id);
	}

	@Override
	public Entity getParent() {
		return this.parent;
	}

	@Override
	public void setParent(Entity parent) {
		this.parent = parent;
	}

	public ConcurrentHashMap<Long, Entity> getChildren() {
		return this.children;
	}

	// 返回当前节点的父辈节点集合
	public List<Entity> getElders() {
		List<Entity> elderList = new ArrayList<Entity>();
		Entity parentNode = this.getParent();
		if (parentNode == null) {
			return elderList;
		} else {
			elderList.add(parentNode);
			elderList.addAll(parentNode.getElders());
			return elderList;
		}
	}
}
