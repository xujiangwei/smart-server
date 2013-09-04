package smart.dao;

import smart.bean.User;

/**
 * 用户DAO。
 */
public interface UserDao {

	/**
	 * 保存用户到数据库
	 * @param id
	 * @param name
	 * @param passwordMD5
	 * @param token
	 */
	public void saveUser(long id, String name, String passwordMD5, String token);
	
	public void updateUser(String name);
	
	/**
	 * 获取用户名
	 * @param token
	 * @return
	 */
	public String getName(String token);
	
	/**
	 * 获取指定用户
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUser(String username, String password);
	
	/**
	 * 获取指定用户
	 * @param token
	 * @return
	 */
	public User getUser(String token);
}
