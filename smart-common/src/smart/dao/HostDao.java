package smart.dao;

import java.util.List;

import smart.bean.CPU;
import smart.bean.FileSystemDetection;
import smart.bean.Host;
import smart.bean.Memory;
import smart.bean.NetInterfaceDetection;
import smart.bean.ProgressDetection;

/**
 * 主机 DAO 。
 */
public interface HostDao {

	/**
	 * 返回主机 ID 列表。
	 * 
	 * @return
	 */
	public List<Long> getHostIdList();

	/**
	 * 返回指定 ID 的主机。
	 * 
	 * @param id
	 * @return
	 */
	public Host getHostById(long id);

	/**
	 * 返回Host列表
	 * 
	 * @return
	 */
	public List<Host> getHostList();

	/**
	 * 返回指定HostID的CPU
	 * 
	 * @param id
	 * @return
	 */
	public List<CPU> getCPUById(long id);

	/**
	 * 返回指定ID的进程
	 * 
	 * @param id
	 * @return
	 */
	public ProgressDetection getProgressDetectionById(long id);

	/**
	 * 返回指定ID的内存
	 * 
	 * @param id
	 * @return
	 */
	public Memory getMemoryById(long id);

	/**
	 * 返回指定ID的文件系统
	 * 
	 * @param id
	 * @return
	 */
	public FileSystemDetection getFileSystemDetectionById(long id);

	/**
	 * 返回指定ID的文件系统
	 * 
	 * @param id
	 * @return
	 */
	public NetInterfaceDetection getNetInterfaceDetectionById(long id);
}
