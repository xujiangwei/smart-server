package smart.bean;

import smart.entity.AbstractEntity;

/**
 * nwtelnet协议
 */
public class NwtelnetProtocol extends AbstractEntity {

	private static final long serialVersionUID = 5035100726474431942L;

	// 端口
	private int port;
	// 登录提示符
	private String loginPrompt;
	// 用户名
	private String username;
	// 密码提示符
	private String passwordPrompt;
	// 密码
	private String password;
	// 设备名称
	private String moName;
	
	public NwtelnetProtocol(long id) {
		super(id);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getLoginPrompt() {
		return loginPrompt;
	}

	public void setLoginPrompt(String loginPrompt) {
		this.loginPrompt = loginPrompt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordPrompt() {
		return passwordPrompt;
	}

	public void setPasswordPrompt(String passwordPrompt) {
		this.passwordPrompt = passwordPrompt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMoName() {
		return moName;
	}

	public void setMoName(String moName) {
		this.moName = moName;
	}

}
