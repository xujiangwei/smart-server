package smart.old.core;

import java.util.Date;

import smart.old.bean.User;
import smart.old.dao.UserDao;
import smart.old.dao.impl.UserDaoImpl;

/**
 * 用户管理器。
 * 
 * @author Jiangwei Xu
 * 
 */
public final class UserManager {

	// 创建单例
	private static final UserManager instance = new UserManager();
	private UserDao userDao;

	private UserManager() {
		userDao = new UserDaoImpl();
	}

	public static UserManager getInstance() {
		return UserManager.instance;
	}

	// 签入
	public void signIn(long id, String name, String passwordMD5) {
		User user = new User(id);
		user.setName(name);
		user.setPwd(passwordMD5);
		user.setLastLogin((new Date()).getTime());
		userDao.saveUser(user);
	}

	// 更新登录信息
	public void update(String name){
		userDao.updateUser(name);
	}
	
	// 判断数据库中是否已有该对象
	public boolean isExist(String username, String password) {
		boolean b = false;
		User user = userDao.getUser(username, password);
		if (user != null) {
			b = true;
		}
		return b;
	}

}
