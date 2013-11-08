package smart.dao.impl;

import java.sql.PreparedStatement;

import smart.dao.AbstraceDao;
import smart.dao.DiskDao;
import smart.util.DButil;

public class DiskDaoImpl extends AbstraceDao implements DiskDao {
	private PreparedStatement pstmt;
//	private ResultSet rs;

	/**
	 * 存储磁盘配置信息
	 */
	public void saveDiskInfo(long disk_mosn, String disk_name,
			String disk_type, String disk_path, String disk_partitionsign,
			int disk_partitionsize, long disk_eqptmosn) {

		String sql = "insert into t_hostdisk (disk_mosn,disk_name,disk_type,disk_path,disk_partitionsign,disk_partitionsize,disk_eqptmosn) values (?,?,?,?,?,?,?) ";

		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, disk_mosn);
			pstmt.setString(2, disk_name);
			pstmt.setString(3, disk_type);
			pstmt.setString(4, disk_path);
			pstmt.setString(5, disk_partitionsign);
			pstmt.setInt(6, disk_partitionsize);
			pstmt.setLong(7, disk_eqptmosn);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}
	}
}
