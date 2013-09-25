package smart.action;

public class User {

	private long user_id;
	private String username;
	private String password;
	private String token;
	public User (long userId, String username, String password, String token){
		this.user_id = userId;
		this.username = username;
		this.password = password;
		this.token = token;
	}
	
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
