package smart.old.core;

import smart.old.dao.DiskDao;
import smart.old.dao.impl.DiskDaoImpl;

/**
 * 磁盘管理器
 * 
 * @author Lianghai Li
 */
public class DiskManager {
	private static final DiskManager instance = new DiskManager();
	private DiskDao diskDao;

	public DiskManager() {
		this.diskDao = new DiskDaoImpl();
	}

	public static DiskManager getInstance() {
		return DiskManager.instance;
	}

	/**
	 * 存储磁盘配置信息
	 * 
	 * @param disk_mosn
	 * @param disk_name
	 * @param disk_type
	 * @param disk_path
	 * @param disk_partitionsign
	 * @param disk_partitionsize
	 * @param disk_eqptmosn
	 */
	public void saveDiskInfo(long disk_mosn, String disk_name,
			String disk_type, String disk_path, String disk_partitionsign,
			int disk_partitionsize, long disk_eqptmosn) {
		synchronized (this.diskDao) {
			this.diskDao.saveDiskInfo(disk_mosn, disk_name, disk_type,
					disk_path, disk_partitionsign, disk_partitionsize,
					disk_eqptmosn);

		}
	}
}
