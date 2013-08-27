package smart.dao;

import java.util.List;

import smart.bean.CPU;
import smart.bean.CPUPerc;
import smart.bean.FileSystem;
import smart.bean.FileSystemUsage;
import smart.bean.Host;
import smart.bean.Memory;
import smart.bean.MemoryDetection;
import smart.bean.NetInterface;
import smart.bean.NetInterfaceStat;
import smart.bean.Progress;
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
	 * 返回指定时刻的CPU利用率
	 * 
	 * @param time
	 * @return
	 */
	public CPUPerc getCPUPercById(long time);

	/**
	 * 返回指定CPU id的CPU利用率列表
	 * 
	 * @param id
	 * @return
	 */
	public List<CPUPerc> getPercsById(long id);

	/**
	 * 返回指定ID的内存
	 * 
	 * @param id
	 * @return
	 */
	public Memory getMemoryById(long id);

	/**
	 * 返回指定Memory Id 的MemoryDetection列表
	 * 
	 * @param id
	 * @return
	 */
	public List<MemoryDetection> getMemoryDetecsById(long id);

	/**
	 * 返回指定Host ID的进程列表
	 * 
	 * @param id
	 * @return
	 */
	 public List<Progress> getProgressById(long id);
	 
	 /**
	  * 返回指定Progress ID的进程利用率的列表
	  * @param id
	  * @return
	  */
	 public List<ProgressDetection> getProgressDetecById(long id);

	/**
	 * 返回指定Host ID的文件系统列表
	 * 
	 * @param id
	 * @return
	 */
	public List<FileSystem> getFileSystemById(long id);

	/**
	 * 返回指定 FileSystem ID的文件系统利用率列表
	 * @param id
	 * @return
	 */
	public List<FileSystemUsage> getFileSystemUsagesById(long id);
	/**
	 * 返回指定Host ID的文件系统列表
	 * 
	 * @param id
	 * @return
	 */
	public List<NetInterface> getNetInterfaceById(long id);
	
	/**
	 * 返回指定接口 ID的网络接口采集信息列表
	 * @param id
	 * @return
	 */
	public List<NetInterfaceStat> getInterfaceStats(long id);
}
