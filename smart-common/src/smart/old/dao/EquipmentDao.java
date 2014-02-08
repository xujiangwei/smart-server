package smart.old.dao;

import smart.old.bean.Equipment;

/**
 * 设备DAO
 */
public interface EquipmentDao {

	/**
	 * 保存设备信息
	 * @param e
	 */
	public void saveEquipment(Equipment e);
	
	/**
	 * 查看是否存在已知id的设备
	 * @param equipmentId
	 * @return
	 */
	public boolean isExist(long equipmentId);
}
