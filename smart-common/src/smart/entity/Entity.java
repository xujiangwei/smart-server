package smart.entity;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模型实体。
 * 
 * @author Jiangwei Xu
 */
public interface Entity extends Serializable {

	/**
	 * 返回 ID 。
	 * @return
	 */
	public long getId();

	/**
	 * 添加子实体。
	 * @param entity
	 */
	public void addChild(Entity entity);

	/**
	 * 删除子实体。
	 * @param entity
	 */
	public void removeChild(Entity entity);

	/**
	 * 返回子实体。
	 * @return
	 */
	public Entity getChild(long id);

	/**
	 * 返回父实体。
	 */
	public Entity getParent();

	/**
	 * 设置父实体。
	 * @param parent
	 */
	public void setParent(Entity parent);
	/**
	 * 返回全部子实体。
	 * @return
	 */
	public ConcurrentHashMap<Long, Entity> getChildren();
	
	/**
	 * 返回所有父实体集合。
	 * @return
	 */
	public List<Entity> getElders();
}
