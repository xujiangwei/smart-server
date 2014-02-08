package smart.old.core;

import smart.old.dao.InterfaceDao;
import smart.old.dao.impl.InterfaceDaoImpl;

/**
 * 接口管理器
 * 
 * @author Lianghai Li
 */
public class InterfaceManager {
	private static final InterfaceManager instance = new InterfaceManager();
	private InterfaceDao ifDao;

	public InterfaceManager() {
		this.ifDao = new InterfaceDaoImpl();
	}

	public static InterfaceManager getInstance() {
		return InterfaceManager.instance;
	}

	/**
	 * 存储接口的配置信息
	 */
	public void saveIfInfo(long if_mosn, String if_name, String if_type,
			long if_bandwidth, String if_mac, String if_isblock, int if_panel,
			String if_describe, int if_index, int if_maxdatagramlength,
			String if_alias, long if_eqptmosn) {
		synchronized (this.ifDao) {
			this.ifDao.saveIfInfo(if_mosn, if_name, if_type, if_bandwidth,
					if_mac, if_isblock, if_panel, if_describe, if_index,
					if_maxdatagramlength, if_alias, if_eqptmosn);
		}
	}
}
