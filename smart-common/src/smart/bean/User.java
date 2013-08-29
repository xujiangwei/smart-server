package smart.bean;

import smart.entity.AbstractEntity;

/**
 * 用户实体。
 * 
 * @author Jiangwei Xu
 *
 */
public class User extends AbstractEntity {

	private static final long serialVersionUID = -846448829015174354L;

	private String name;
	private String pwd;
	private String token;
	private String ip;
	private int port;
	
	public User(long id) {
		super(id);
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
