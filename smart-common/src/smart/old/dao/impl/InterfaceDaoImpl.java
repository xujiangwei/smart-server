package smart.old.dao.impl;

import java.sql.PreparedStatement;

import smart.old.bean.NetInterface;
import smart.old.dao.AbstraceDao;
import smart.old.dao.InterfaceDao;
import smart.old.util.DButil;

/**
 * 接口DAO实现类
 * 
 * @author Administrator
 */
public class InterfaceDaoImpl extends AbstraceDao implements InterfaceDao {
	private PreparedStatement pstmt;
//	private ResultSet rs;

	public InterfaceDaoImpl() {
		super();
	}

	@Override
	public void saveIfInfo(NetInterface nif) {

	}

	/**
	 * 存储接口的配置信息
	 */
	public void saveIfInfo(long if_mosn, String if_name, String if_type,
			long if_bandwidth, String if_mac, String if_isblock, int if_panel,
			String if_describe, int if_index, int if_maxdatagramlength,
			String if_alias, long if_eqptmosn) {
		String sql = "insert into t_interface (if_mosn,if_name,if_type,if_bandwidth,if_mac,if_isblock,if_panel,if_describe,if_index,if_maxdatagramlength,if_alias,if_eqptmosn) values(?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, if_mosn);
			pstmt.setString(2, if_name);
			pstmt.setString(3, if_type);
			pstmt.setLong(4, if_bandwidth);
			pstmt.setString(5, if_mac);
			pstmt.setString(6, if_isblock);
			pstmt.setInt(7, if_panel);
			pstmt.setString(8, if_describe);
			pstmt.setInt(9, if_index);
			pstmt.setInt(10, if_maxdatagramlength);
			pstmt.setString(11, if_alias);
			pstmt.setLong(12, if_eqptmosn);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}

	}

}
