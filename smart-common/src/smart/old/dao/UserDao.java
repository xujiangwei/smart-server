package smart.old.dao;

import smart.old.bean.User;

/**
 * 用户DAO。
 */
public interface UserDao {

	/**
	 * 保存用户到数据库
	 * @param user
	 */
	public void saveUser(User user);
	
	/**
	 * 更新指定用户登录信息
	 * @param name
	 */
	public void updateUser(String name);
	
//	/**
//	 * 获取用户名
//	 * @param token
//	 * @return
//	 */
//	public String getName(String token);
	
	/**
	 * 获取指定用户
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUser(String username, String password);
	
//	/**
//	 * 获取指定用户
//	 * @param token
//	 * @return
//	 */
//	public User getUser(String token);
}
