package smart.dao;

import smart.util.component.AbstractLifeCycle;

/**
 * 抽象 DAO 。管理数据库连接和缓存。
 */
public abstract class AbstraceDao extends AbstractLifeCycle {

	public AbstraceDao() {
		
	}

	@Override
	protected void doStart() throws Exception {
	}

	@Override
	protected void doStop() throws Exception {
	}
}
