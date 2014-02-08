package smart.old.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import smart.old.bean.User;
import smart.old.dao.AbstraceDao;
import smart.old.dao.UserDao;
import smart.old.util.DButil;

/**
 * 用户 DAO 实现。
 */
public class UserDaoImpl extends AbstraceDao implements UserDao {

	private PreparedStatement pstmt;
	private ResultSet rs;

	public UserDaoImpl() {
		super();
	}

//	@Override
//	public String getName(String token) {
//		String sql = "select user_name from t_user where user_token = ?";
//		String name = null;
//		try {
//			super.doStart();
//			pstmt = super.conn.prepareStatement(sql);
//			pstmt.setString(1, token);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				name = rs.getString("user_name");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return name;
//	}

	@Override
	public User getUser(String username, String password) {
		String sql = "select * from t_user where user_name = ? and user_password = ?";
		User user = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getLong("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPwd(rs.getString("user_password"));
				user.setLastLogin(rs.getLong("user_lastLogin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return user;
	}

//	@Override
//	public User getUser(String token) {
//		String sql = "select * form t_user where user_token = ?";
//		User user = null;
//		try {
//			super.doStart();
//			pstmt = super.conn.prepareStatement(sql);
//			pstmt.setString(1, token);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				user = new User(rs.getLong("user_id"));
//				user.setName(rs.getString("user_name"));
//				user.setPwd(rs.getString("user_password"));
//				user.setToken(rs.getString("user_token"));
//				user.setLastLogin(rs.getLong("user_lastLogin"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return user;
//	}

	@Override
	public void saveUser(User user) {
		String sql = "insert into t_user (user_id, user_name, user_password, user_lastLogin) values (?,?,?,?)";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPwd());
			pstmt.setLong(4, user.getLastLogin());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}
	}

	@Override
	public void updateUser(String name) {
		String sql = "update t_user set user_lastLogin = ? where user_name = ?";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, (new Date()).getTime());
			pstmt.setString(2, name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}
	}

}
