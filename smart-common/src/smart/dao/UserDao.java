package smart.dao;

import java.util.List;

import smart.bean.User;

/**
 * 用户DAO。
 */
public interface UserDao {

	/**
	 * 获取所有在线用户
	 * @return
	 */
	public List<User> getUSers();
	
	/**
	 * 获取用户名
	 * @param token
	 * @param ip
	 * @return
	 */
	public String getName(String token, String ip);
	
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
	 * @param ip
	 * @return
	 */
	public User getUser(String username, String password, String ip);
	
	/**
	 * 获取指定用户
	 * @param token
	 * @param ip
	 * @return
	 */
	public User getUser(String token, String ip);
}
