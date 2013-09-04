package smart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import smart.bean.User;
import smart.dao.AbstraceDao;
import smart.dao.UserDao;
import smart.util.DButil;

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
	public String getName(String token) {
		String sql = "select name from t_user where token = ?";
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
	public User getUser(String username, String password) {
		String sql = "select * from t_user where name = ? and password = ?";
		User user = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("password"));
				user.setToken(rs.getString("token"));
				user.setLastLogin(rs.getLong("lastLogin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUser(String token) {
		String sql = "select * form t_user where token = ?";
		User user = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setString(1, token);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("password"));
				user.setToken(rs.getString("token"));
				user.setLastLogin(rs.getLong("lastLogin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void saveUser(long id, String name, String passwordMD5,
			String token) {
		String sql = "insert into t_user (id, name, password, token, lastLogin) values (?,?,?,?,?)";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, passwordMD5);
			pstmt.setString(4, token);
			pstmt.setLong(5, (new Date()).getTime());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, null);
		}
	}

	@Override
	public void updateUser(String name) {
		String sql = "update t_user set lastLogin = ? where name = ?";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, (new Date()).getTime());
			pstmt.setString(2, name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, null);
		}
	}

}
