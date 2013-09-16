package smart.dao;

import smart.bean.Alarm;
import smart.bean.AlarmCoverage;

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
	
	/**
	 * 保存告警影响范围
	 * @param coverage
	 */
	public void saveAlarmCoverage(AlarmCoverage coverage);
	
	/**
	 * 判断告警设备的影响范围中是否存在指定设备
	 * @param moId
	 * @param relatedMoId
	 * @return
	 */
	public boolean isExist(long moId, long relatedMoId);
	
	/**
	 * 告警确认操作
	 * @param almId
	 * @param username
	 * @param dealTime
	 */
	public void alarmConfirm(long almId, String username, long dealTime);
	
	/**
	 * 告警删除操作
	 * @param almId
	 */
	public void alarmDelete(long almId);
}
