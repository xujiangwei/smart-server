package smart.old.dao;

import java.util.List;


import smart.old.bean.CPU;
import smart.old.bean.CPUPerc;
import smart.old.bean.Memory;
import smart.old.bean.MemoryDetection;
import smart.old.bean.NetEquipment;
import smart.old.bean.NetInterface;
import smart.old.bean.NetInterfaceStat;

/**
 * 交换机DAO
 */
public interface NetEquipmentDao {

	/**
	 * 返回交换机的ID列表
	 * 
	 * @return
	 */
	public List<Long> getNetEqptIdList();

	/**
	 * 返回指定ID的交换机
	 * 
	 * @param id
	 * @return
	 */
	public NetEquipment getNetEqptById(long id);

	/**
	 * 返回交换机的列表
	 * 
	 * @return
	 */
	public List<NetEquipment> getNetEqptsList();

	/**
	 * 返回指定ID的CPU列表
	 * 
	 * @param id
	 * @return
	 */
	public List<CPU> getNetEqptCPUsById(long id);

	/**
	 * 返回指定ID的CPU
	 * 
	 * @param id
	 * @return
	 */
	public CPU getNetEqptCPUById(long id);

	/**
	 * 返回指定CPU id的CPU利用率列表
	 * 
	 * @param id
	 * @return
	 */
	public List<CPUPerc> getNetEqptPercsById(long id);

	/**
	 * 返回指定时间戳的CPU利用率
	 * 
	 * @param time
	 * @return
	 */
	public CPUPerc getNetEqptCPUPercById(long id, long timestamp);

	/**
	 * 返回指定ID的内存
	 * 
	 * @param id
	 * @return
	 */
	public Memory getNetEqptMemoryById(long id);

	/**
	 * 返回指定内存Id的监测信息列表
	 * 
	 * @param id
	 * @return
	 */
	public List<MemoryDetection> getNetEqptMemoryDetecsById(long id);

	/**
	 * 返回指定时间戳的内存监测信息
	 * 
	 * @param id
	 * @return
	 */
	public MemoryDetection getNetEqptMemoryDetecById(long id, long timestamp);

	/**
	 * 返回指定ID的网络接口列表
	 * 
	 * @param id
	 * @return
	 */

	public List<NetInterface> getNetEqptNetInterfacesById(long id);

	/**
	 * 返回指定ID的网络接口
	 * 
	 * @param id
	 * @return
	 */
	public NetInterface getNetEqptNetInterfaceById(long id);

	/**
	 * 返回指定接口 ID的网络接口采集信息列表
	 * 
	 * @param id
	 * @return
	 */
	public List<NetInterfaceStat> getNetEqptInterfaceStatsById(long id);

	/**
	 * 返回指定时间戳的网络接口监测信息
	 * 
	 * @param id
	 * @return
	 */
	public NetInterfaceStat getNetEqptInterfaceStatById(long id, long timestamp);

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

}
