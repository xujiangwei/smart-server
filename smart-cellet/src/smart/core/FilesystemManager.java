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
	 * @param fs_hangnode
	 * @param fs_fsname
	 * @param fs_fstype
	 * @param fs_nodenum
	 * @param fs_capacity
	 * @param fs_eqptmosn
	 */
	public void saveFsInfo(long fs_mosn, String fs_name, String fs_type,
			String fs_hangnode, String fs_fsname, String fs_fstype,
			int fs_nodenum, int fs_capacity, long fs_eqptmosn) {
		synchronized (this.filesysDao) {
			this.filesysDao.saveFsInfo(fs_mosn, fs_name, fs_type, fs_hangnode,
					fs_fsname, fs_fstype, fs_nodenum, fs_capacity, fs_eqptmosn);
		}
	}

}
