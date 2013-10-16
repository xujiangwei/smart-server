package smart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import smart.bean.CPU;
import smart.bean.CPUPerc;
import smart.bean.Memory;
import smart.bean.MemoryDetection;
import smart.bean.NetEquipment;
import smart.bean.NetInterface;
import smart.bean.NetInterfaceStat;
import smart.dao.AbstraceDao;
import smart.dao.NetEquipmentDao;
import smart.util.DButil;

/**
 * 交换机DAO实现
 */
public class NetEquipmentDaoImpl extends AbstraceDao implements NetEquipmentDao {

	private PreparedStatement pstmt;
	private ResultSet rs;

	public NetEquipmentDaoImpl() {
		super();
	}

	/**
	 * 返回网络设备ID列表
	 */
	public List<Long> getNetEqptIdList() {
		String sql = "select neteqpt_id from t_netequipment";
		List<Long> list = new ArrayList<Long>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getLong("neteqpt_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 返回指定ID的网络设备
	 */
	public NetEquipment getNetEqptById(long id) {
		String sql = "select * from t_netequipment where neteqpt_id=?";
		NetEquipment netEqpt = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				netEqpt = new NetEquipment(rs.getLong("neteqpt_id"));
				netEqpt.setName(rs.getString("neteqpt_name"));
				netEqpt.setName(rs.getString("neteqpt_type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return netEqpt;
	}

	/**
	 * 返回网络设备列表
	 */
	public List<NetEquipment> getNetEqptsList() {
		String sql = "select * from t_netequipment";
		List<NetEquipment> list = new ArrayList<NetEquipment>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NetEquipment netEqpt = new NetEquipment(
						rs.getLong("neteqpt_id"));
				netEqpt.setName(rs.getString("neteqpt_name"));
				netEqpt.setName(rs.getString("neteqpt_type"));
				list.add(netEqpt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 返回指定Id的CPU列表
	 */
	public List<CPU> getNetEqptCPUsById(long id) {
		String sql = "select * from t_cpu where cpu__neteqptid=?";
		List<CPU> list = new ArrayList<CPU>(3);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CPU cpu = new CPU(rs.getLong("cpu_id"));
				cpu.setTotalCores(rs.getInt("cpu_totalCores"));
				cpu.setMhz(rs.getInt("cpu_mhz"));
				cpu.setCacheSize(rs.getLong("cpu_cacheSize"));
				cpu.setModel(rs.getString("cpu_model"));
				cpu.setVendor(rs.getString("cpu_vendor"));
				list.add(cpu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 获取指定ID的CPU
	 */
	public CPU getNetEqptCPUById(long id) {
		String sql = "select * from t_cpu where cpu_id=?";
		CPU cpu = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cpu = new CPU(rs.getLong("cpu_id"));
				cpu.setCacheSize(rs.getLong("cpu_cacheSize"));
				cpu.setMhz(rs.getInt("cpu_mhz"));
				cpu.setModel(rs.getString("cpu_model"));
				cpu.setTotalCores(rs.getInt("cpu_totalCores"));
				cpu.setVendor(rs.getString("cpu_vendor"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cpu;
	}

	/**
	 * 获取指定CPU ID的CPU利用率列表
	 */
	public List<CPUPerc> getNetEqptPercsById(long id) {
		String sql = "select * from t_cpu_prec where prec_cpuid=?";
		List<CPUPerc> list = new ArrayList<CPUPerc>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CPUPerc cp = new CPUPerc(rs.getLong("prec_timestamp"));
				cp.setIdle(rs.getDouble("prec_idle"));
				cp.setNice(rs.getDouble("prec_nice"));
				cp.setSys(rs.getDouble("prec_sys"));
				cp.setUser(rs.getDouble("prec_user"));
				cp.setWait(rs.getDouble("prec_wait"));
				cp.setCombined(rs.getDouble("prec_combined"));
				cp.setTimestamp(rs.getLong("prec_timestamp"));

				list.add(cp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定时间戳的CPU利用率
	 */
	public CPUPerc getNetEqptCPUPercById(long id, long timestamp) {
		String sql = "select * from dcpuprec where prec_cpuid=? and prec_timestamp=?";
		CPUPerc cp = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.setLong(2, timestamp);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cp = new CPUPerc(rs.getLong("prec_timestamp"));
				cp.setIdle(rs.getDouble("prec_idle"));
				cp.setNice(rs.getDouble("prec_nice"));
				cp.setSys(rs.getDouble("prec_sys"));
				cp.setUser(rs.getDouble("prec_user"));
				cp.setWait(rs.getDouble("prec_wait"));
				cp.setCombined(rs.getDouble("prec_combined"));
				cp.setTimestamp(rs.getLong("prec_timestamp"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cp;
	}

	/**
	 * 获取指定ID的内存
	 */
	public Memory getNetEqptMemoryById(long id) {
		String sql = "select * from t_memory where mem_neteqptid=?";
		Memory memory = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memory = new Memory(rs.getLong("mem_Id"));
				memory.setPhysicsTotal(rs.getInt("mem_physicsTotal"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memory;
	}

	/**
	 * 获取指定内存Id 的内存监测信息列表
	 */
	public List<MemoryDetection> getNetEqptMemoryDetecsById(long id) {
		String sql = "select * from t_mem_usage where memusage_memid=?";
		List<MemoryDetection> list = new ArrayList<MemoryDetection>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemoryDetection memDetc = new MemoryDetection(
						rs.getLong("memusage_timestamp"));
				memDetc.setActualFree(rs.getLong("memusage_actualFree"));
				memDetc.setActualUsed(rs.getLong("memusage_actualUsed"));
				memDetc.setFree(rs.getLong("memusage_free"));
				memDetc.setFreePercent(rs.getDouble("memusage_freePercent"));
				memDetc.setRam(rs.getLong("memusage_ram"));
				memDetc.setUsed(rs.getLong("memusage_used"));
				memDetc.setUsedPercent(rs.getDouble("memusage_usedPercent"));
				memDetc.setTimestamp(rs.getLong("memusage_timestamp"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定时间戳的内存监测信息
	 */
	public MemoryDetection getNetEqptMemoryDetecById(long id, long timestamp) {
		String sql = "select * from dmemoryusage where memusage_memid=? and memusage_timestamp=?";
		MemoryDetection memDetec = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.setLong(2, timestamp);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memDetec = new MemoryDetection(rs.getLong("memusage_timestamp"));
				memDetec.setActualFree(rs.getLong("memusage_actualFree"));
				memDetec.setActualUsed(rs.getLong("memusage_actualUsed"));
				memDetec.setFree(rs.getLong("free"));
				memDetec.setFreePercent(rs.getDouble("memusage_freePercent"));
				memDetec.setRam(rs.getLong("memusage_ram"));
				memDetec.setUsed(rs.getLong("memusage_used"));
				memDetec.setUsedPercent(rs.getDouble("memusage_usedPercent"));
				memDetec.setTimestamp(rs.getLong("memusage_timestamp"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memDetec;
	}

	/**
	 * 获取指定ID的网络接口
	 */
	public List<NetInterface> getNetEqptNetInterfacesById(long id) {
		String sql = "select * from t_netinterface where netif_neteqptid=?";
		List<NetInterface> list = new ArrayList<NetInterface>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NetInterface nif = new NetInterface(rs.getLong("netif_id"));
				nif.setAddress(rs.getString("netif_address"));
				nif.setBroadcast(rs.getString("netif_broadcast"));
				nif.setDescription(rs.getString("netif_description"));
				nif.setDestination(rs.getString("netif_destination"));
				nif.setGateway(rs.getString("netif_gateway"));
				nif.setMac(rs.getString("netif_mac"));
				nif.setMetric(rs.getLong("netif_metric"));
				nif.setMtu(rs.getLong("netif_mtu"));
				nif.setName(rs.getString("netif_name"));
				nif.setNetmask(rs.getString("netif_netmask"));
				nif.setType(rs.getString("netif_type"));

				list.add(nif);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * 获取指定ID的网路接口
	 */
	public NetInterface getNetEqptNetInterfaceById(long id) {
		String sql = "select * from t_netinterface where netif_id=?";
		NetInterface nif = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				nif = new NetInterface(rs.getLong("netif_id"));
				nif.setAddress(rs.getString("netif_address"));
				nif.setBroadcast(rs.getString("netif_broadcast"));
				nif.setDescription(rs.getString("netif_description"));
				nif.setDestination(rs.getString("netif_destination"));
				nif.setGateway(rs.getString("netif_gateway"));
				nif.setMac(rs.getString("netif_mac"));
				nif.setMetric(rs.getLong("netif_metric"));
				nif.setMtu(rs.getLong("netif_mtu"));
				nif.setName(rs.getString("netif_name"));
				nif.setNetmask(rs.getString("netif_netmask"));
				nif.setType(rs.getString("netif_type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nif;

	}

	/**
	 * 获取指定ID的网络接状态信息
	 */
	public List<NetInterfaceStat> getNetEqptInterfaceStatsById(long id) {
		String sql = "select * from t_netif_status where netifstat_netifid=?";
		List<NetInterfaceStat> list = new ArrayList<NetInterfaceStat>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NetInterfaceStat nis = new NetInterfaceStat(
						rs.getLong("netifstat_timestamp"));
				nis.setRxBytes(rs.getLong("netifstat_rxBytes"));
				nis.setRxDropped(rs.getLong("netifstat_rxDropped"));
				nis.setRxErrors(rs.getLong("netifstat_rxErrors"));
				nis.setRxFrame(rs.getLong("netifstat_rxFrame"));
				nis.setRxOverruns(rs.getLong("netifstat_rxOverruns"));
				nis.setRxPackets(rs.getLong("netifstat_rxPackets"));
				nis.setSpeed(rs.getLong("netifstat_speed"));
				nis.setStatus(rs.getString("netifstat_status"));
				nis.setThroughput(rs.getFloat("netifstat_throughput"));
				nis.setTimestamp(rs.getLong("netifstat_timestamp"));
				nis.setTraffic(rs.getFloat("netifstat_traffic"));
				nis.setTxBytes(rs.getLong("netifstat_txBytes"));
				nis.setTxCarrier(rs.getLong("netifstat_txCarrier"));
				nis.setTxCollisions(rs.getLong("netifstat_txCollisions"));
				nis.setTxDropped(rs.getLong("netifstat_txDropped"));
				nis.setTxErrors(rs.getLong("netifstat_txErrors"));
				nis.setTxOverruns(rs.getLong("netifstat_txOverruns"));
				nis.setTxPackets(rs.getLong("netifstat_txPackets"));

				list.add(nis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定时间戳的网络接口状态信息
	 */
	public NetInterfaceStat getNetEqptInterfaceStatById(long id, long timestamp) {
		String sql = "select * from t_netif_status where netifstat_netifid=? and netifstat_timestamp=?";
		NetInterfaceStat nis = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.setLong(2, timestamp);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				nis = new NetInterfaceStat(rs.getLong("netifstat_timestamp"));
				nis.setRxBytes(rs.getLong("netifstat_rxBytes"));
				nis.setRxDropped(rs.getLong("netifstat_rxDropped"));
				nis.setRxErrors(rs.getLong("netifstat_rxErrors"));
				nis.setRxFrame(rs.getLong("netifstat_rxFrame"));
				nis.setRxOverruns(rs.getLong("netifstat_rxOverruns"));
				nis.setRxPackets(rs.getLong("netifstat_rxPackets"));
				nis.setSpeed(rs.getLong("netifstat_speed"));
				nis.setStatus(rs.getString("netifstat_status"));
				nis.setThroughput(rs.getFloat("netifstat_throughput"));
				nis.setTimestamp(rs.getLong("netifstat_timestamp"));
				nis.setTraffic(rs.getFloat("netifstat_traffic"));
				nis.setTxBytes(rs.getLong("netifstat_txBytes"));
				nis.setTxCarrier(rs.getLong("netifstat_txCarrier"));
				nis.setTxCollisions(rs.getLong("netifstat_txCollisions"));
				nis.setTxDropped(rs.getLong("netifstat_txDropped"));
				nis.setTxErrors(rs.getLong("netifstat_txErrors"));
				nis.setTxOverruns(rs.getLong("netifstat_txOverruns"));
				nis.setTxPackets(rs.getLong("netifstat_txPackets"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nis;
	}

	/**
	 * 更新指定CPU ID的CPU利用率
	 */
	public void addCPUPrecsById(long cpuid, double percent, long timestamp) {
		String sql = "insert into t_cpu_prec (prec_cpuid,prec_combined,prec_timestamp) values (?,?,?)";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, cpuid);
			pstmt.setDouble(2, percent);
			pstmt.setLong(3, timestamp);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, null);
		}

	}

	/**
	 * 更新指定内存ID的内存监测数据
	 */
	public void addMemoryDetecById(long memid, double usedPercent,
			long timestamp) {
		String sql = "insert into t_mem_usage (memusage_memid,memusage_usedPercent,memusage_timestamp) values (?,?,?)";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, memid);
			pstmt.setDouble(2, usedPercent);
			pstmt.setLong(3, timestamp);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, null);
		}

	}

}
