package smart.dao.impl;

import java.util.List;

import smart.bean.Host;
import smart.dao.AbstraceDao;
import smart.dao.HostDao;

/**
 * 主机 DAO 实现。
 */
public class HostDaoImpl extends AbstraceDao implements HostDao {

	public HostDaoImpl() {
		super();
	}

	public List<Long> getHostIdList() {
		// TODO
		return null;
	}

	public Host getHostById(long id) {
		// TODO
		// 从 super 获取数据库连接
		// 查询数据
		// 关闭连接
		return null;
	}
}
