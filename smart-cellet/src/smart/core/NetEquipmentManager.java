package smart.core;

import smart.dao.NetEquipmentDao;
import smart.dao.impl.NetEquipmentDaoImpl;

/**
 * 网络设备管理器
 * 
 * @author Administrator
 */
public class NetEquipmentManager {
	private static final NetEquipmentManager instance = new NetEquipmentManager();
	private NetEquipmentDao netEquipmentDao;

	public NetEquipmentManager() {
		this.netEquipmentDao = new NetEquipmentDaoImpl();
	}

	public static NetEquipmentManager getInstance() {
		return NetEquipmentManager.instance;

	}

	/**
	 * 更新指定CPU ID的CPU利用率
	 * 
	 * @param cpuid
	 * @param percent
	 * @param timestamp
	 */
	public void addCPUPrecsById(long cpuid, double percent, long timestamp) {
		synchronized (this.netEquipmentDao) {
			this.netEquipmentDao.addCPUPrecsById(cpuid, percent, timestamp);
		}
	}

	/**
	 * 更新指定内存ID的内存监测数据
	 * 
	 * @param memid
	 * @param usedPercent
	 * @param timestamp
	 */
	public void addMemoryDetecById(long memid, double usedPercent,
			long timestamp) {
		synchronized (this.netEquipmentDao) {
			this.netEquipmentDao.addMemoryDetecById(memid, usedPercent,
					timestamp);
		}
	}

}
