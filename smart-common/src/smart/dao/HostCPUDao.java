package smart.dao;

import java.util.List;

import smart.bean.CPU;
import smart.bean.CPUPerc;

/**
 * CPU DAO 。
 */
public interface HostCPUDao {

	/**
	 * 返回指定设备ID的CPU列表
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
	 * 更新指定CPU ID的CPU利用率
	 * 
	 * @param id
	 */
	public void addCPUPrecsById(long cpuid, double percent, long timestamp);

	/**
	 * 存储主机CPU的配置信息
	 * 
	 * @param hcpu_mosn
	 * @param hcpu_name
	 * @param hcpu_type
	 * @param hcpu_catch
	 * @param hcpu_sign
	 * @param hcpu_mhz
	 * @param hcpu_model
	 * @param hcpu_vender
	 * @param hcpu_bits
	 * @param hcpu_eqptmosn
	 */
	public void saveHostCPUInfo(long hcpu_mosn, String hcpu_name,
			String hcpu_type, long hcpu_catch, String hcpu_sign,
			double hcpu_mhz, String hcpu_model, String hcpu_vender,
			long hcpu_bits, long hcpu_eqptmosn);
}
