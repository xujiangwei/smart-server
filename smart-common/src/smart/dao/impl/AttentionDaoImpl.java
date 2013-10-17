package smart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import smart.bean.Attention;
import smart.dao.AbstraceDao;
import smart.dao.AttentionDao;
import smart.util.DButil;

/**
 * 关注DAO实现
 */
public class AttentionDaoImpl extends AbstraceDao implements AttentionDao {

	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public AttentionDaoImpl() {
		super();
	}

	@Override
	public int getPriority(long userId){
		String sql = "select max(care_priority) as maxpri from t_care where care_userid = ?";
		int pri = 0;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getObject("maxpri") != null) {
					pri = rs.getInt("maxpri");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return pri;
	}
	
	@Override
	public void storeAttention(long userId, long moId) {
		String sql = "insert into t_care (care_moid,care_priority,care_statusid,care_userid) values (?,?,?,?)";
		int i = getPriority(userId)+1;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, moId);
			pstmt.setInt(2, i);
			pstmt.setInt(3, 1);
			pstmt.setLong(4, userId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, null);
		}
	}

	@Override
	public List<Attention> getAttention(long userId) {
		String sql = "select * from t_care where care_userid = ?";
		List<Attention> list = new ArrayList<Attention>(8);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Attention a = new Attention(rs.getLong("care_id"));
				a.setMoId(rs.getLong("care_moid"));
				a.setPriority(rs.getInt("care_priority"));
				a.setStatusId(rs.getInt("care_statusid"));
				a.setUserId(rs.getLong("care_userid"));
				list.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return list;
	}

	@Override
	public int checkMoId(long moId, long userId) {
		String sql = "select care_moid,care_statusid from t_care where care_userid = ?";
		int b = -1;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getLong("care_moid") == moId) {
					if (rs.getInt("care_statusid") == 1) {
						b = 1;
					} else {
						b = 0;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return b;
	}

//	public int getStatusId(long moId, long userId) {
//		String sql = "select care_statusid from t_care where care_moid = ? and care_userid = ?";
//		int status = -1;
//		try {
//			super.doStart();
//			pstmt = super.conn.prepareStatement(sql);
//			pstmt.setLong(1, moId);
//			pstmt.setLong(2, userId);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				status = rs.getInt("care_statusid");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return status;
//	}
	
	@Override
	public void changeStatus(long moId, long userId) {
		String sql = "update t_care set care_statusid = ? where care_moid = ? and care_userid = ?";
//		int s = getStatusId(moId, userId);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
//			if (s == 1) {
//				pstmt.setInt(1, 0);
//			} else if (s == 0) {
				pstmt.setInt(1, 1);
//			}
			pstmt.setLong(2, moId);
			pstmt.setLong(3, userId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, null);
		}
	}
	
	// 获取指定用户的已关注id
	public List<Long> getAttentionId(long userId) {
		String sql = "select care_id from t_care where care_userid = ? and care_priority > 0 order by care_priority";
		List<Long> list = new ArrayList<Long>(8);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getLong("care_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return list;
	}
	
	// 重置优先级
	public void resetPriority(long userId) {
		String sql = "update t_care set care_priority = ? where care_id = ?";
		List<Long> list = getAttentionId(userId);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				pstmt.setInt(1, i+1);
				pstmt.setLong(2, list.get(i));
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, null);
		}
	}
	
	@Override
	public void cancelAttention(JSONArray ja, long userId) {
		String sql = "update t_care set care_statusid = 0,care_priority = 0 where care_id in (?";
		if (ja.length() > 1) {
			for (int i = 0; i < ja.length()-1; i++) {
				sql = sql + ",?";
			}
		}
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql+")");
			for (int j = 0; j < ja.length(); j++) {
				pstmt.setLong(j+1, ja.getLong(j));
			}
			pstmt.executeUpdate();
			resetPriority(userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, null);
		}
	}

	@Override
	public void removeAttention(JSONArray ja, long userId) {
		String sql = "delete from t_care where care_id in (?";
		if (ja.length() > 1) {
			for (int i = 0; i < ja.length()-1; i++) {
				sql = sql + ",?";
			}
		}
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql+")");
			for (int j = 0; j < ja.length(); j++) {
				pstmt.setLong(j+1, ja.getLong(j));
			}
			pstmt.executeUpdate();
			resetPriority(userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, null);
		}
	}

}
