package smart.dao;

import smart.bean.NetInterface;

/**
 * 接口DAO
 * 
 * @author Administrator
 */
public interface InterfaceDao {
	public void saveIfInfo(NetInterface nif);

	/**
	 * 存储接口配置信息
	 * 
	 * @param if_mosn
	 * @param if_name
	 * @param if_type
	 * @param if_bandwidth
	 * @param if_mac
	 * @param if_isblock
	 * @param if_panel
	 * @param if_describe
	 * @param if_index
	 * @param if_maxdatagramlength
	 * @param if_alias
	 * @param if_eqptmosn
	 */
	public void saveIfInfo(long if_mosn, String if_name, String if_type,
			long if_bandwidth, String if_mac, String if_isblock, int if_panel,
			String if_describe, int if_index, int if_maxdatagramlength,
			String if_alias, long if_eqptmosn);
}
