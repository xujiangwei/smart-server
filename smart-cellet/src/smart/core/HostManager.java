package smart.core;

import smart.dao.HostDao;
import smart.dao.impl.HostDaoImpl;

public class HostManager {
	private static final HostManager instance = new HostManager();
	private HostDao hostDao;

	private HostManager() {
		this.hostDao = new HostDaoImpl();
	}

	public static HostManager getInstance() {
		return HostManager.instance;
	}

	public void destoryHostById(long id) {
		synchronized (this.hostDao) {
			this.hostDao.destoryHostById(id);
		}
	}
}
