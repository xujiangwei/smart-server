package smart.entity;

/**
 * 模型实体。
 * 
 * @author Jiangwei Xu
 */
public interface Entity {

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
}
