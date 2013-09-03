package smart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import smart.bean.User;
import smart.dao.AbstraceDao;
import smart.dao.UserDao;

/**
 * 用户 DAO 实现。
 */
public class UserDaoImpl extends AbstraceDao implements UserDao {

	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDaoImpl() {
		super();
	}

	@Override
	public List<User> getUSers() {
		String sql = "select * from onlineUser";
		List<User> list = new ArrayList<User>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("password"));
				user.setIp(rs.getString("ip"));
				user.setToken(rs.getString("token"));
				user.setPort(rs.getInt("port"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getName(String token, String ip) {
		String sql = "select name from onlineUser where token = ? and ip = ?";
		String name = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setString(1, token);
			pstmt.setString(2, ip);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	@Override
	public String getName(String token) {
		String sql = "select name from onlineUser where token = ?";
		String name = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setString(1, token);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	@Override
	public User getUser(String username, String password, String ip) {
		String sql = "select * form onlineUser where username = ? and password = ? and ip = ?";
		User user = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, ip);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("password"));
				user.setToken(rs.getString("token"));
				user.setIp(rs.getString("ip"));
				user.setPort(rs.getInt("port"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUser(String token, String ip) {
		String sql = "select * form onlineUser where token = ? and ip = ?";
		User user = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setString(1, token);
			pstmt.setString(2, ip);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("password"));
				user.setToken(rs.getString("token"));
				user.setIp(rs.getString("ip"));
				user.setPort(rs.getInt("port"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
