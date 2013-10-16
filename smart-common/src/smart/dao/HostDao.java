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
	 * 返回指定HostID的CPU列表
	 * 
	 * @param id
	 * @return
	 */
	public List<CPU> getCPUsById(long id);

	/**
	 * 返回指定ID的CPU
	 * 
	 * @param id
	 * @return
	 */
	public CPU getCPUById(long id);

	/**
	 * 返回指定CPU id的CPU利用率列表
	 * 
	 * @param id
	 * @return
	 */
	public List<CPUPerc> getPercsById(long id);

	/**
	 * 返回指定时间戳的CPU利用率
	 * 
	 * @param time
	 * @return
	 */
	public CPUPerc getCPUPercById(long time, long timestamp);

	/**
	 * 返回指定 主机ID的内存
	 * 
	 * @param id
	 * @return
	 */
	public Memory getMemoryById(long id);

	/**
	 * 返回指定内存Id的监测信息列表
	 * 
	 * @param id
	 * @return
	 */
	public List<MemoryDetection> getMemoryDetecsById(long id);

	/**
	 * 返回指定时间戳的内存监测信息
	 * 
	 * @param id
	 * @return
	 */
	public MemoryDetection getMemoryDetecById(long id, long timestamp);

	/**
	 * 返回指定主机ID的进程列表
	 * 
	 * @param id
	 * @return
	 */
	public List<Progress> getProgressesById(long id);

	/**
	 * 返回指定ID的进程
	 * 
	 * @param id
	 * @return
	 */
	public Progress getProgressById(long id);

	/**
	 * 返回指定进程 ID的进程利用率的列表
	 * 
	 * @param id
	 * @return
	 */
	public List<ProgressDetection> getProgressDetecsById(long id);

	/**
	 * 返回指定时间戳的进程监测信息
	 * 
	 * @param id
	 * @return
	 */
	public ProgressDetection getProgressDetecById(long id, long timestamp);

	/**
	 * 返回指定主机ID的文件系统列表
	 * 
	 * @param id
	 * @return
	 */
	public List<FileSystem> getFileSystemsById(long id);

	/**
	 * 返回指定ID的文件系统
	 * 
	 * @param id
	 * @return
	 */
	public FileSystem getFileSystemById(long id);

	/**
	 * 返回指定 FileSystem ID的文件系统利用率列表
	 * 
	 * @param id
	 * @return
	 */
	public List<FileSystemUsage> getFileSystemUsagesById(long id);

	/**
	 * 返回指定时间戳的文件系统利用率
	 * 
	 * @param id
	 * @return
	 */
	public FileSystemUsage getFileSysUsageById(long id, long timestamp);

	/**
	 * 返回指定Host ID的文件系统列表
	 * 
	 * @param id
	 * @return
	 */
	public List<NetInterface> getNetInterfacesById(long id);

	/**
	 * 返回指定ID的网络接口
	 * 
	 * @param id
	 * @return
	 */
	public NetInterface getNetInterfaceById(long id);

	/**
	 * 返回指定接口 ID的网络接口采集信息列表
	 * 
	 * @param id
	 * @return
	 */
	public List<NetInterfaceStat> getInterfaceStatsById(long id);

	/**
	 * 返回指定时间戳的网络接口监测信息
	 * 
	 * @param id
	 * @return
	 */
	public NetInterfaceStat getInterfaceStatById(long id, long timestamp);

	/**
	 * 更新指定CPU ID的CPU利用率
	 * 
	 * @param id
	 */
	public void addCPUPrecsById(long cpuid, double percent, long timestamp);

	/**
	 * 更新指定内存ID的内存监测数据
	 * 
	 * @param id
	 */
	public void addMemoryDetecById(long memid, double usedPercent,
			long timestamp);

	/**
	 * 更新指定文件系统ID的文件系统利用率
	 * 
	 * @param id
	 */
	public void addFileSystemUsages(long filesysid, double usage, long timestamp);

	/**
	 * 更新指定主机的PING信息
	 * 
	 * @param hostid
	 * @param pingDelay
	 * @param timestamp
	 */
	public void addPingInfo(long hostid, double pingDelay, long timestamp);

//	public void addNetInterfaceStatus(long hostId,double )
	// public void updateProgressDetection(long id);
	// public void updateInterfaceStatsById(long id);

	/**
	 * 销毁指定ID的主机
	 * 
	 * @param id
	 */
	public void destoryHostById(long id);

}
