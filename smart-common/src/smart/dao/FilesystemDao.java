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
	 * @param fs_path
	 * @param fs_partitionsign
	 * @param fs_partitionsize
	 * @param fs_eqptmosn
	 */
	public void saveFsInfo(long fs_mosn, String fs_name, String fs_type,
			String fs_path, String fs_partitionsign, int fs_partitionsize,
			long fs_eqptmosn);
}
