package smart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import smart.bean.Alarm;
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
	public void alarmDeal() {

	}

}
