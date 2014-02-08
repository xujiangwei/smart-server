package smart.old.dao;

/**
 * 磁盘DAO
 * 
 * @author Administrator
 */
public interface DiskDao {

	/**
	 * 存储磁盘配置信息
	 * 
	 * @param disk_mosn
	 * @param disk_name
	 * @param disk_type
	 * @param disk_path
	 * @param disk_partitionsign
	 * @param disk_partitionsize
	 * @param disk_eqptmosn
	 */
	public void saveDiskInfo(long disk_mosn, String disk_name,
			String disk_type, String disk_path, String disk_partitionsign,
			int disk_partitionsize, long disk_eqptmosn);
}
