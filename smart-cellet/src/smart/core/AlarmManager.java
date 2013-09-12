package smart.core;

import smart.bean.Alarm;
import smart.bean.AlarmCoverage;
import smart.dao.AlarmDao;
import smart.dao.impl.AlarmDaoImpl;

/**
 * 告警管理器
 */
public class AlarmManager {

	// 创建单例
	private static AlarmManager instance = new AlarmManager();
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
	public void signInList(long moId, long almId, String moName,
			String almCause, int severity, String moIp, long lastTime) {
		Alarm alarm = new Alarm(almId);
		alarm.setMoId(moId);
		alarm.setMoName(moName);
		alarm.setAlmCause(almCause);
		alarm.setSeverity(severity);
		alarm.setMoIp(moIp);
		alarm.setLastTime(lastTime);
		alarmDao.saveAlarmList(alarm);
	}

	// 签入告警详细
	public void signInDetail(long almId, String moType, String location,
			String detail, String almStatus, long occurTime, String trend,
			int count, int upgradeCount, String confirmUser, long confirmTime,
			String delUser, long delTime) {
		Alarm alarm = new Alarm(almId);
		alarm.setMoType(moType);
		alarm.setLocation(location);
		alarm.setDetail(detail);
		alarm.setAlmStatus(almStatus);
		alarm.setOccurTime(occurTime);
		alarm.setTrend(trend);
		alarm.setCount(count);
		alarm.setUpgradeCount(upgradeCount);
		alarm.setConfirmUser(confirmUser);
		alarm.setConfirmTime(confirmTime);
		alarm.setDelUser(delUser);
		alarm.setDelTime(delTime);
		alarmDao.saveAlarmDetail(alarm);
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
	
	// 告警处理
	public void alarmDeal(long almId, String opType, String username, long dealTime) {
		if ("almConfirm".equals(opType)) {
			alarmDao.alarmConfirm(almId, username, dealTime);
		} else if ("almDel".equals(opType)) {
			alarmDao.alarmDelete(almId);
		}
	}
}
