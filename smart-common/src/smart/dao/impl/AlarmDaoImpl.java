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
		}  finally {
			DButil.close(pstmt, rs);
		}
		return b;
	}

	@Override
	public void saveAlarmList(Alarm alarm) {
		String sql = "insert into t_alarm (ta_moId, ta_almId, ta_moName, ta_almCause, ta_severity, ta_moIp, ta_lastTime) values (?,?,?,?,?,?,?)";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, alarm.getMoId());
			pstmt.setLong(2, alarm.getId());
			pstmt.setString(3, alarm.getMoName());
			pstmt.setString(4, alarm.getAlmCause());
			pstmt.setInt(5, alarm.getSeverity());
			pstmt.setString(6, alarm.getMoIp());
			pstmt.setLong(7, alarm.getLastTime());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, null);
		}

	}

	@Override
	public void saveAlarmDetail(Alarm alarm) {
		String sql = "update t_alarm set ta_motype = ?,ta_location = ?,ta_detail = ?," +
				"ta_almStatus = ?,ta_occurtime = ?,ta_trend = ?,ta_count = ?,ta_upgradeCount = ?," +
				"ta_confirmUser = ?,ta_confirmTime = ?,ta_delUser = ?,ta_delTime = ? where ta_almId = ?";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setString(1, alarm.getMoType());
			pstmt.setString(2, alarm.getLocation());
			pstmt.setString(3, alarm.getDetail());
			pstmt.setString(4, alarm.getAlmStatus());
			pstmt.setLong(5, alarm.getOccurTime());
			pstmt.setString(6, alarm.getTrend());
			pstmt.setInt(7, alarm.getCount());
			pstmt.setInt(8, alarm.getUpgradeCount());
			pstmt.setString(9, alarm.getConfirmUser());
			pstmt.setLong(10, alarm.getConfirmTime());
			pstmt.setString(11, alarm.getDelUser());
			pstmt.setLong(12, alarm.getDelTime());
			pstmt.setLong(13, alarm.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, null);
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
			DButil.close(pstmt, null);
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
			DButil.close(pstmt, rs);
		}
		return b;
	}

	@Override
	public void alarmConfirm(long almId, String username, long dealTime) {
		String sql = "update t_alarm set ta_almStatus = ?,ta_confirmUser = ?," +
				"ta_confirmTime=? where ta_almId = ?";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setString(1, "确认");
			pstmt.setString(2, username);
			pstmt.setLong(3, dealTime);
			pstmt.setLong(4, almId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, null);
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
			DButil.close(pstmt, null);
		}
	}

}
