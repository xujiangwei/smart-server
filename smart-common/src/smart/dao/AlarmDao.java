package smart.dao;

import smart.bean.Alarm;

/**
 * 告警DAO。
 */
public interface AlarmDao {

	/**
	 * 判断是否已有此id的告警
	 * @param moId
	 * @return
	 */
	public boolean isExist(long almId);
	
	/**
	 * 保存告警列表信息
	 * @param alarm
	 */
	public void saveAlarmList(Alarm alarm);
	
	/**
	 * 保存告警详情
	 * @param detail
	 */
	public void saveAlarmDetail(Alarm alarm);
	
	public void alarmDeal();
}
