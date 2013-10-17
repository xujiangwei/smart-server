package smart.core;

import org.json.JSONException;
import org.json.JSONObject;

import smart.bean.Alarm;
import smart.bean.AlarmCoverage;
import smart.dao.AlarmDao;
import smart.dao.impl.AlarmDaoImpl;

/**
 * 告警管理器
 */
public class AlarmManager {

	// 创建单例
	private static final AlarmManager instance = new AlarmManager();
	private AlarmDao alarmDao;

	private AlarmManager() {
		alarmDao = new AlarmDaoImpl();
	}

	public static AlarmManager getInstance() {
		return AlarmManager.instance;
	}

	// 判断告警表中是否已有该对象
	public boolean isExist(long almId) {
		return alarmDao.isExist(almId);
	}
	
	// 签入告警列表
	public void signInList(JSONObject jo) {
		Alarm alarm = null;
		try {
			alarm = new Alarm(jo.getLong("almId"));
			alarm.setMoId(jo.getLong("moId"));
			alarm.setRootMoId(jo.getLong("rootMoId"));
			alarm.setParentMoId(jo.getLong("parentMoId"));
			alarm.setTypecode(jo.getString("typeCode"));
			alarm.setAlmCause(jo.getString("almCause"));
			alarm.setIsSuppressed(jo.getString("isSuppressed"));
			alarm.setSeverity(jo.getInt("severity"));
			alarm.setExtraInfo(jo.getString("extraInfo"));
			alarm.setAlmStatus(jo.getString("almStatus"));
			alarm.setTrend(jo.getString("trend"));
			alarm.setOccurTime(jo.getLong("occurTime"));
			alarm.setLastTime(jo.getLong("lastTime"));
			alarm.setCount(jo.getInt("count"));
			alarm.setDetail(jo.getString("detail"));
			alarm.setOriginalInfo(jo.getString("originalInfo"));
			alarm.setConfirmTime(jo.getLong("confirmTime"));
			alarm.setConfirmUserId(jo.getLong("confirmUserId"));
			alarm.setConfirmUser(jo.getString("confirmUser"));
			alarm.setMoIp(jo.getString("moIp"));
			alarm.setMoName(jo.getString("moName"));
			alarm.setCauseAlias(jo.getString("causeAlias"));
			alarm.setLocation(jo.getString("location"));
			alarmDao.saveAlarmList(alarm);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	// 告警处理
	public void alarmDeal(long almId, String opType, String username, long userId, long dealTime) {
		if ("almConfirm".equals(opType)) {
			alarmDao.alarmConfirm(almId, username, userId, dealTime);
		} else if ("almDel".equals(opType)) {
			alarmDao.alarmDelete(almId);
		}
	}

	// 判断告警影响范围中是否已存在此受影响设备信息
	public boolean isExist(long moId, long relatedMoId){
		return alarmDao.isExist(moId, relatedMoId);
	}

	// 签入告警影响范围
	public void signInCoverage(long moId, long relatedMoId, String relatedMoIp,
			String relatedMoName, int level) {
		AlarmCoverage ac = new AlarmCoverage(relatedMoId);
		ac.setRelatedMoIp(relatedMoIp);
		ac.setRelatedMoName(relatedMoName);	
		ac.setLevel(level);
		ac.setMoId(moId);
		alarmDao.saveAlarmCoverage(ac);
	}
	
}
