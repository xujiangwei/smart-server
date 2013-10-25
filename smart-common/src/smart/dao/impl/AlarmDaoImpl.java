package smart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import smart.bean.Alarm;
import smart.bean.AlarmCoverage;
import smart.dao.AbstraceDao;
import smart.dao.AlarmDao;
import smart.util.DButil;

/**
 * 告警DAO实现
 */
public class AlarmDaoImpl extends AbstraceDao implements AlarmDao {

	private PreparedStatement pstmt;
	private ResultSet rs;

	public AlarmDaoImpl() {
		super();
	}

	@Override
	public boolean isExist(long almId) {
		String sql = "select ta_almId from t_alarm";
		boolean b = false;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getLong("ta_almId") == almId) {
					b = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return b;
	}

	@Override
	public void saveAlarmList(Alarm alarm) {
		String sql = "insert into t_alarm values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, alarm.getId());
			pstmt.setLong(2, alarm.getMoId());
			pstmt.setLong(3, alarm.getRootMoId());
			pstmt.setLong(4, alarm.getParentMoId());
			pstmt.setString(5, alarm.getTypecode());
			pstmt.setString(6, alarm.getAlmCause());
			pstmt.setString(7, alarm.getIsSuppressed());
			pstmt.setInt(8, alarm.getSeverity());
			pstmt.setString(9, alarm.getExtraInfo());
			pstmt.setString(10, alarm.getAlmStatus());
			pstmt.setString(11, alarm.getTrend());
			pstmt.setLong(12, alarm.getOccurTime());
			pstmt.setLong(13, alarm.getLastTime());
			pstmt.setInt(14, alarm.getCount());
			pstmt.setString(15, alarm.getDetail());
			pstmt.setString(16, alarm.getOriginalInfo());
			pstmt.setLong(17, alarm.getConfirmTime());
			pstmt.setLong(18, alarm.getConfirmUserId());
			pstmt.setString(19, alarm.getConfirmUser());
			pstmt.setString(20, alarm.getMoIp());
			pstmt.setString(21, alarm.getMoName());
			pstmt.setString(22, alarm.getCauseAlias());
			pstmt.setString(23, alarm.getLocation());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}

	}

	@Override
	public void saveAlarmCoverage(AlarmCoverage coverage) {
		String sql = "insert into t_alarm_coverage(tac_related_moId, " +
				"tac_related_moName, tac_related_moIp, tac_related_level, tac_moId) " +
				"values (?,?,?,?,?)";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, coverage.getId());
			pstmt.setString(2, coverage.getRelatedMoName());
			pstmt.setString(3, coverage.getRelatedMoIp());
			pstmt.setInt(4, coverage.getLevel());
			pstmt.setLong(5, coverage.getMoId());
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}
	}

	@Override
	public boolean isExist(long moId, long relatedMoId) {
		String sql = "select tac_related_moId from t_alarm_coverage where tac_moId = ?";
		boolean b = false;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, moId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getLong("tac_related_moId") == relatedMoId) {
					b = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return b;
	}

	@Override
	public void alarmConfirm(long almId, String username, long userId, long dealTime) {
		String sql = "update t_alarm set ta_alarmstatus = ?,ta_confirm_user = ?," +
				"ta_confirm_time = ?,ta_confirm_userid = ? where ta_almId = ?";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setString(1, "确认");
			pstmt.setString(2, username);
			pstmt.setLong(3, dealTime);
			pstmt.setLong(4, userId);
			pstmt.setLong(5, almId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}
	}

	@Override
	public void alarmDelete(long almId) {
		String sql = "delete from t_alarm where ta_almId = ?";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, almId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}
	}

}
