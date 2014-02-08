package smart.old.dao.impl;

import java.sql.PreparedStatement;

import smart.old.dao.AbstraceDao;
import smart.old.dao.FilesystemDao;
import smart.old.util.DButil;

/**
 * 文件系统DAO实现
 * 
 * @author Administrator
 */
public class FilesystemDaoImpl extends AbstraceDao implements FilesystemDao {
	private PreparedStatement pstmt;

	// private ResultSet rs;

	public FilesystemDaoImpl() {
		super();
	}

	/**
	 * 存储文件系统配置信息
	 */
	public void saveFsInfo(long fs_mosn, String fs_name, String fs_type,
			String fs_hangnode, String fs_fsname, String fs_fstype,
			int fs_nodenum, int fs_capacity, long fs_eqptmosn) {

		String sql = "insert into t_hostfilesystem (fs_mosn,fs_name,fs_type,fs_hangnode,fs_fsname,fs_fstype,fs_nodenum,fs_capacity,fs_eqptmosn) values (?,?,?,?,?,?,?,?,?)";

		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, fs_mosn);
			pstmt.setString(2, fs_name);
			pstmt.setString(3, fs_type);
			pstmt.setString(4, fs_hangnode);
			pstmt.setString(5, fs_fsname);
			pstmt.setString(6, fs_fstype);
			pstmt.setLong(7, fs_nodenum);
			pstmt.setLong(8, fs_capacity);
			pstmt.setLong(9, fs_eqptmosn);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}

	}

}
