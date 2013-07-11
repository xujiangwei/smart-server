package smart.entity;

import java.io.Serializable;

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
	public String getId();

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
	public Entity getChild(String id);
}
