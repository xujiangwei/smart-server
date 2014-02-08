package smart.old.core;

import smart.old.dao.HostDao;
import smart.old.dao.impl.HostDaoImpl;

/**
 * 主机管理器
 * 
 * @author Lianghai Li
 */
public class HostManager {
	private static final HostManager instance = new HostManager();
	private HostDao hostDao;

	private HostManager() {
		this.hostDao = new HostDaoImpl();
	}

	public static HostManager getInstance() {
		return HostManager.instance;
	}

	/**
	 * 更新CPU利用率
	 * 
	 * @param prec_cpuid
	 * @param prec_combined
	 * @param prec_timestamp
	 */
	public void addCPUPrecsById(long cpuid, double percent, long timestamp) {
		synchronized (this.hostDao) {
			this.hostDao.addCPUPrecsById(cpuid, percent, timestamp);
		}
	}

	/**
	 * 更新内存利用率
	 * 
	 * @param memid
	 * @param usedPercent
	 * @param timestamp
	 */
	public void addMemoryDetecById(long memid, double usedPercent,
			long timestamp) {
		synchronized (this.hostDao) {
			this.hostDao.addMemoryDetecById(memid, usedPercent, timestamp);
		}
	}

	/**
	 * 更新文件系统利用率
	 * 
	 * @param filesysid
	 * @param usage
	 * @param timestamp
	 */
	public void addFileSystemUsages(long filesysid, double usage, long timestamp) {
		synchronized (this.hostDao) {
			this.hostDao.addFileSystemUsages(filesysid, usage, timestamp);
		}
	}

	/**
	 * 更新指定主机的PING信息
	 * 
	 * @param hostid
	 * @param pingDelay
	 * @param timestamp
	 */
	public void addPingInfo(long hostid, double pingDelay, long timestamp) {
		synchronized (this.hostDao) {
			this.hostDao.addPingInfo(hostid, pingDelay, timestamp);
		}
	}

	/**
	 * 销毁主机
	 * 
	 * @param id
	 */
	public void destoryHostById(long id) {
		synchronized (this.hostDao) {
			this.hostDao.destoryHostById(id);
		}
	}
}
