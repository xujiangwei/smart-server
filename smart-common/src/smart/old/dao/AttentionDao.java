package smart.old.dao;

import java.util.List;

import org.json.JSONArray;

import smart.old.bean.Attention;

/**
 * 关注DAO
 */
public interface AttentionDao {

	/**
	 * 获取指定用户的关注中最高优先级
	 * @param userId
	 * @return
	 */
	public int getPriority(long userId);
	
	/**
	 * 添加关注
	 * @param userId
	 * @param moId
	 */
	public void storeAttention(long userId, long moId);
	
	/**
	 * 获取关注列表
	 * @param userId
	 * @return
	 */
	public List<Attention> getAttention(long userId);
	
	/**
	 * 通过用户userId检查是否已有此设备的关注
	 * @param moId
	 * @param userId
	 * @return
	 */
	public int checkMoId(long moId, long userId);
	
	/**
	 * 根据关注与否改变状态
	 * @param moId
	 * @param userId
	 */
	public void changeStatus(long moId, long userId);
	
	/**
	 * 取消关注
	 * @param ja
	 * @param userId
	 */
	public void cancelAttention(JSONArray ja, long userId);
	
	/**
	 * 移除对应关注Id下的关注
	 * @param ja
	 * @param userId
	 */
	public void removeAttention(JSONArray ja, long userId);
}
