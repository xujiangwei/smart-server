package smart.core;

import smart.dao.HostCPUDao;
import smart.dao.impl.HostCPUDaoImpl;

/**
 * 主机CPU管理器
 * 
 * @author Lianghai Li
 */
public class HostCPUManager {
	private static final HostCPUManager instance = new HostCPUManager();
	private HostCPUDao hostCPUDao;

	public HostCPUManager() {
		this.hostCPUDao = new HostCPUDaoImpl();
	}

	public static HostCPUManager getInstance() {
		return HostCPUManager.instance;
	}

	/**
	 * 存储主机CPU的配置信息
	 * 
	 * @param hcpu_mosn
	 * @param hcpu_name
	 * @param hcpu_type
	 * @param hcpu_catch
	 * @param hcpu_sign
	 * @param hcpu_mhz
	 * @param hcpu_model
	 * @param hcpu_vender
	 * @param hcpu_bits
	 * @param hcpu_eqptmosn
	 */
	public void saveHostCPUInfo(long hcpu_mosn, String hcpu_name,
			String hcpu_type, String hcpu_catch, String hcpu_sign,
			double hcpu_mhz, String hcpu_model, String hcpu_vender,
			long hcpu_eqptmosn) {
		synchronized (this.hostCPUDao) {
			this.hostCPUDao.saveHostCPUInfo(hcpu_mosn, hcpu_name, hcpu_type,
					hcpu_catch, hcpu_sign, hcpu_mhz, hcpu_model, hcpu_vender,
					hcpu_eqptmosn);
		}
	}
}
