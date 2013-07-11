package smart.core;

import smart.auth.User;

/**
 * 用户管理器。
 * 
 * @author Jiangwei Xu
 *
 */
public final class UserManager {

	private static final UserManager instance = new UserManager();

	private UserManager() {
	}

	public static UserManager getInstance() {
		return UserManager.instance;
	}

	public User signIn(String name, String passwordMD5) {
		return null;
	}
}
