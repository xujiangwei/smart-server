package smart.dao;

/**
 * 文件系统DAO
 * 
 * @author Administrator
 */
public interface FilesystemDao {

	/**
	 * 存储文件系统的配置信息
	 * 
	 * @param fs_mosn
	 * @param fs_name
	 * @param fs_type
	 * @param fs_hangnode
	 * @param fs_fsname
	 * @param fs_fstype
	 * @param fs_nodenum
	 * @param fs_capacity
	 * @param fs_eqptmosn
	 */

	public void saveFsInfo(long fs_mosn, String fs_name, String fs_type,
			String fs_hangnode, String fs_fsname, String fs_fstype,
			int fs_nodenum, int fs_capacity, long fs_eqptmosn);
}
