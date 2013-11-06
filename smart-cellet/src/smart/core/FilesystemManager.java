package smart.core;

import smart.dao.FilesystemDao;
import smart.dao.impl.FilesystemDaoImpl;

/**
 * 文件系统管理器
 * 
 * @author Administrator
 */
public class FilesystemManager {
	private static final FilesystemManager instance = new FilesystemManager();
	private FilesystemDao filesysDao;

	public FilesystemManager() {
		this.filesysDao = new FilesystemDaoImpl();
	}

	public static FilesystemManager getInstance() {
		return FilesystemManager.instance;
	}

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
			long fs_eqptmosn) {
		synchronized (this.filesysDao) {
			this.filesysDao.saveFsInfo(fs_mosn, fs_name, fs_type, fs_path,
					fs_partitionsign, fs_partitionsize, fs_eqptmosn);
		}
	}

}
