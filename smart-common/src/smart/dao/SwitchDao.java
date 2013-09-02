package smart.dao;

import java.util.List;

import smart.bean.CPU;
import smart.bean.CPUPerc;
import smart.bean.Memory;
import smart.bean.MemoryDetection;
import smart.bean.NetInterface;
import smart.bean.NetInterfaceStat;
import smart.bean.Switch;

/**
 * 交换机DAO
 */
public interface SwitchDao {

	/**
	 * 返回交换机的ID列表
	 * 
	 * @return
	 */
	public List<Long> getSwitchIdList();

	/**
	 * 返回指定ID的交换机
	 * 
	 * @param id
	 * @return
	 */
	public Switch getSwitchById(long id);

	/**
	 * 返回交换机的列表
	 * 
	 * @return
	 */
	public List<Switch> getSwitchsList();

	/**
	 * 返回指定ID的CPU列表
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
	public CPUPerc getCPUPercById(long id, long timestamp);

	/**
	 * 返回指定ID的内存
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
	 * 返回指定ID的网络接口列表
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

}
