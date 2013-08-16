package smart.dao;

import java.util.List;

import smart.bean.Host;

/**
 * 主机 DAO 。
 */
public interface HostDao {

	/**
	 * 返回主机 ID 列表。
	 * @return
	 */
	public List<Long> getHostIdList();

	/**
	 * 返回指定 ID 的主机。
	 * @param id
	 * @return
	 */
	public Host getHostById(long id);
}
