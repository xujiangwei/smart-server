package smart.entity;

import java.util.List;

/**
 * 用于描述核心数据结构的实体对象。
 * 
 * @author Jiangwei Xu
 *
 */
public interface Entity {

	/**
	 * 返回实体名称。
	 * @return
	 */
	public String getName();

	/**
	 * 添加子实体。
	 * @param entity
	 */
	public void addChild(Entity entity);

	/**
	 * 移除子实体。
	 * @param entity
	 */
	public void removeChild(Entity entity);

	/**
	 * 返回指定的子实体。
	 * 
	 * @param name
	 * @return
	 */
	public Entity getChild(String name);

	/**
	 * 检索所有子实体。
	 * 
	 * @param name
	 * @return
	 */
	public List<Entity> searchChildren(String name);
}
