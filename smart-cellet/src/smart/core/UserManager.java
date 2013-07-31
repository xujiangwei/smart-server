package smart.core;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import smart.auth.User;

/**
 * 用户管理器。
 * 
 * @author Jiangwei Xu
 * 
 */
public final class UserManager {

	// 创建单例
	private static final UserManager instance = new UserManager();

	private UserManager() {
	}

	public static UserManager getInstance() {
		return UserManager.instance;
	}

	// 记录当前在线人数
	private static ConcurrentHashMap<Integer, User> userMap = new ConcurrentHashMap<Integer, User>(10);
	private static int count = 0;

	public User signIn(String name, String passwordMD5) {
		return null;
	}

	// 创建用户
	public void userCreated(User user) {
		userMap.put(user.getPort(), user);

		count++;
		System.out.println("loginUser: "
				+ UserManager.userMap.get(user.getPort()).getName());
		System.out.println("在线用户: " + UserManager.count + "个");
		getUsers();
		System.out.println();
	}

	// 根据ip删除用户
	public void userDestoryed(String token, String ip) {
		count--;
		Set<Integer> set = userMap.keySet();
		Integer[] keySet = set.toArray(new Integer[0]);
		for (int i : keySet) {
			if ((token.equals(userMap.get(i).getToken()) && (ip.equals(userMap
					.get(i).getIp())))) {
				System.out.println("logoutUser: "
						+ UserManager.userMap.get(i).getName());
				System.out.println("在线用户: " + UserManager.count + "个");
				userMap.remove(i);
			}
		}
		getUsers();
		System.out.println();
	}

	// 判断静态map里是否已有该对象
	public boolean isExist(String username, String password, String ip) {
		Set<Integer> set = userMap.keySet();
		boolean flag = false;
		for (Integer i : set) {
			if ((username.equals(userMap.get(i).getName()))
					&& (password.equals(userMap.get(i).getPwd()) && (ip
							.equals(userMap.get(i).getIp())))) {
				flag = true;
				return flag;
			}
		}
		return flag;
	}

	// 根据用户token获取用户名
	public String getUsername(String token) {
		Set<Integer> set = userMap.keySet();
		String username = null;
		for (int i : set) {
			if (token.equals(userMap.get(i).getToken())) {
				username = userMap.get(i).getName();
			}
		}
		return username;
	}

	// 根据用户token和ip获取用户名
	public String getName(String token, String ip) {
		Set<Integer> set = userMap.keySet();
		String name = null;
		for (int i : set) {
			if (token.equals(userMap.get(i).getToken())
					&& ip.equals(userMap.get(i).getIp())) {
				name = userMap.get(i).getName();
			}
		}
		return name;
	}

	// 查看所有在线用户
	public void getUsers() {
		Set<Integer> set = userMap.keySet();
		System.out.println("currentUsers: ");
		System.out
				.println("username      token                               ip           port");
		for (int i : set) {
			System.out.println(UserManager.userMap.get(i).getName() + "      "
					+ UserManager.userMap.get(i).getToken() + "      "
					+ UserManager.userMap.get(i).getIp() + "      "
					+ UserManager.userMap.get(i).getPort());
		}
	}
}
