package smart.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserManager {

	private static Map<Long, User> m = new HashMap<Long, User>();
	
	static {
		User user1 = new User(9980000000000000l,"admin1","e10adc3949ba59abbe56e057f20f883e","nTAMYtNoZRjqkKJyYtNPlTnPrxFNMs");
		User user2 = new User(111l,"admin","21232f297a57a5a743894a0e4a801fc3","RBFhYgQaqdkMRFinABeeCwlEiylYYi");
		User user3 = new User(131l,"dhcc","e10adc3949ba59abbe56e057f20f883e","VPvAZTiTpGGqGUREKeNbDEdDtZXvCi");
		User user4 = new User(124l,"test","098f6bcd4621d373cade4e832627b4f6","OkQtDSOZtQIcwgRTdVhsfhHWXaWhvC");
		m.put(user1.getUser_id(), user1);
		m.put(user2.getUser_id(), user2);
		m.put(user3.getUser_id(), user3);
		m.put(user4.getUser_id(), user4);
	}
	
	// 返回登录的状态
	public int getStatus(String username, String password){
		Set<Long> keyset = m.keySet();
		int status = 0;
		for (Long i : keyset) {
			if (username.equals(m.get(i).getUsername())) {
				if (password.equals(m.get(i).getPassword())) {
					status = 300;
					break;
				} else {
					status = 102;
					break;
				}
			} else {
				status =101;
				continue;
			}
		}
		return status;
	}
	
	// 返回登录的用户信息
	public User getUser(String username, String password){
		Set<Long> keyset = m.keySet();
		User user = null;
		for (Long i : keyset) {
			if (username.equals(m.get(i).getUsername())) {
				if (password.equals(m.get(i).getPassword())) {
					user = m.get(i);
					break;
				} else {
					break;
				}
			} else {
				continue;
			}
		}
		return user;
	}
	
	// 根据token获取用户id
	public Long getUserId(String token){
		Set<Long> keyset = m.keySet();
		long id = 0;
		for (Long i : keyset) {
			if (token.equals(m.get(i).getToken())) {
				id = m.get(i).getUser_id();
			}
		}
		return id;
	}
}
