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
	private long lastLogin;
	
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

	public long getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}

}
