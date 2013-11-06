package smart.dao.impl;

import java.sql.PreparedStatement;

import smart.dao.AbstraceDao;
import smart.dao.FilesystemDao;
import smart.util.DButil;

/**
 * 文件系统DAO实现
 * 
 * @author Administrator
 */
public class FilesystemDaoImpl extends AbstraceDao implements FilesystemDao {
	private PreparedStatement pstmt;
//	private ResultSet rs;

	public FilesystemDaoImpl() {
		super();
	}

	/**
	 * 存储文件系统配置信息
	 */
	public void saveFsInfo(long fs_mosn, String fs_name, String fs_type,
			String fs_path, String fs_partitionsign, int fs_partitionsize,
			long fs_eqptmosn) {
		String sql = "insert into t_hostfilesys values (fs_mosn,fs_name,fs_type,fs_path,fs_partitionsign,fs_partitionsize,fs_eqptmosn)";

		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, fs_mosn);
			pstmt.setString(2, fs_name);
			pstmt.setString(3, fs_type);
			pstmt.setString(4, fs_path);
			pstmt.setString(5, fs_partitionsign);
			pstmt.setInt(6, fs_partitionsize);
			pstmt.setLong(7, fs_eqptmosn);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}

	}

}
