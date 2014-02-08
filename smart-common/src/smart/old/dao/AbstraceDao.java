package smart.old.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import smart.old.util.DButil;
import smart.old.util.component.AbstractLifeCycle;

/**
 * 抽象 DAO 。管理数据库连接和缓存。
 */
public abstract class AbstraceDao extends AbstractLifeCycle {
	protected Statement stmt;
	protected ResultSet rs;
	protected Connection conn;

	public AbstraceDao() {
	}

	@Override
	protected void doStart() throws Exception {
		conn = DButil.getInstance().getConnection();
	}

	@Override
	protected void doStop() throws Exception {
		DButil.getInstance().close(stmt, rs);
	}
}
