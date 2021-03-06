package smart.old.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import smart.old.bean.CPU;
import smart.old.bean.CPUPerc;
import smart.old.bean.FileSystem;
import smart.old.bean.FileSystemUsage;
import smart.old.bean.Host;
import smart.old.bean.Memory;
import smart.old.bean.MemoryDetection;
import smart.old.bean.NetInterface;
import smart.old.bean.NetInterfaceStat;
import smart.old.bean.Progress;
import smart.old.bean.ProgressDetection;
import smart.old.dao.AbstraceDao;
import smart.old.dao.HostDao;
import smart.old.util.DButil;

/**
 * 主机 DAO 实现。
 */
public class HostDaoImpl extends AbstraceDao implements HostDao {

	private PreparedStatement pstmt;
	private ResultSet rs;

	public HostDaoImpl() {
		super();
	}

	/**
	 * 获取主机ID列表
	 */
	public List<Long> getHostIdList() {
		String sql = "select te_id from t_equipment where te_moType="+"主机";
		List<Long> list = new ArrayList<Long>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getLong("te_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}

		return list;
	}

	/**
	 * 获取指定ID的主机
	 */
	public Host getHostById(long id) {
		// 从 super 获取数据库连接
		// 查询数据
		// 关闭连接
		String sql = "select * from t_equipment where te_id=?";
		Host host = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				host = new Host(rs.getLong("te_id"));
				host.setName(rs.getString("te_moName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}

		return host;
	}

	/**
	 * 获取主机列表
	 */
	public List<Host> getHostList() {
		String sql = "select * from  t_equipment where te_moType="+"主机";
		List<Host> list = new ArrayList<Host>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Host host = new Host(rs.getLong("te_id"));
				host.setName(rs.getString("te_moName"));
				list.add(host);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return list;

	}

	/**
	 * 获取指定主机ID的CPU列表
	 */
	public List<CPU> getCPUsById(long id) {
		String sql = "select * from t_cpu where cpu_eqptid =?";
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
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}

		return list;
	}

	/**
	 * 获取指定CPU ID的CPU
	 */
	public CPU getCPUById(long id) {
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
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return cpu;
	}

	/**
	 * 获取指定CPU ID的CPU利用率列表
	 */
	public List<CPUPerc> getPercsById(long id) {
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
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return list;
	}

	/**
	 * 获取指定CPU ID和时间戳的CPU利用率
	 */
	public CPUPerc getCPUPercById(long id, long timestamp) {
		String sql = "select * from t_cpu_prec where prec_cpuid=? and prec_timestamp=?";
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
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return cp;
	}

	/**
	 * 获取指定主机ID的内存
	 */
	public Memory getMemoryById(long id) {
		String sql = "select * from t_memory where mem_eqptid=?";
		Memory memory = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memory = new Memory(rs.getLong("mem_id"));
				memory.setPhysicsTotal(rs.getInt("mem_physicsTotal"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return memory;
	}

	/**
	 * 获取指定内存Id 的内存监测信息列表
	 */
	public List<MemoryDetection> getMemoryDetecsById(long id) {
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
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return list;
	}

	/**
	 * 获取指定内存ID和时间戳的内存监测信息
	 */
	public MemoryDetection getMemoryDetecById(long id, long timestamp) {
		String sql = "select * from t_mem_usage where memusage_memid=? and memusage_timestamp=?";
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
				memDetec.setFree(rs.getLong("memusage_free"));
				memDetec.setFreePercent(rs.getDouble("memusage_freePercent"));
				memDetec.setRam(rs.getLong("memusage_ram"));
				memDetec.setUsed(rs.getLong("memusage_used"));
				memDetec.setUsedPercent(rs.getDouble("memusage_usedPercent"));
				memDetec.setTimestamp(rs.getLong("memusage_timestamp"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return memDetec;
	}

	/**
	 * 获取指定主机ID的文件系统列表
	 */
	public List<FileSystem> getFileSystemsById(long id) {
		String sql = "select * from t_filesystem where filesys_eqptid =?";
		List<FileSystem> list = new ArrayList<FileSystem>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FileSystem fileSys = new FileSystem(rs.getLong("filesys_id"));
				fileSys.setDevName(rs.getString("filesys_devName"));
				fileSys.setDirName(rs.getString("filesys_dirName"));
				fileSys.setFlags(rs.getLong("filesys_flags"));
				fileSys.setOptions(rs.getString("filesys_options"));
				fileSys.setSize(rs.getFloat("filesys_size"));
				fileSys.setSysTypeName(rs.getString("filesys_sysTypeName"));
				fileSys.setType(rs.getInt("filesys_type"));
				fileSys.setTypeName(rs.getString("filesys_typeName"));
				list.add(fileSys);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return list;

	}

	/**
	 * 获取指定文件系统ID的文件系统
	 */
	public FileSystem getFileSystemById(long id) {
		String sql = "select * from t_filesystem where filesys_id=?";
		FileSystem fs = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fs = new FileSystem(rs.getLong("filesys_id"));
				fs.setDevName(rs.getString("filesys_devName"));
				fs.setDirName(rs.getString("filesys_dirName"));
				fs.setFlags(rs.getLong("filesys_flags"));
				fs.setOptions(rs.getString("filesys_options"));
				fs.setSize(rs.getFloat("filesys_size"));
				fs.setSysTypeName(rs.getString("filesys_sysTypeName"));
				fs.setType(rs.getInt("filesys_type"));
				fs.setTypeName(rs.getString("filesys_typeName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return fs;
	}

	/**
	 * 获取指定文件系统ID的文件系统利用率列表
	 */
	public List<FileSystemUsage> getFileSystemUsagesById(long id) {
		String sql = "select * from t_filesys_usage where filesysusage_filesysid=?";
		List<FileSystemUsage> list = new ArrayList<FileSystemUsage>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FileSystemUsage fsu = new FileSystemUsage(
						rs.getLong("filesysusage_timestamp"));
				fsu.setDiskQueue(rs.getDouble("filesysusage_diskQueue"));
				fsu.setDiskReadBytes(rs.getLong("filesysusage_diskReadBytes"));
				fsu.setDiskReads(rs.getLong("filesysusage_diskReads"));
				fsu.setDiskServiceTime(rs
						.getLong("filesysusage_diskServiceTime"));
				fsu.setDiskWriteBytes(rs.getLong("filesysusage_diskWriteBytes"));
				fsu.setDiskWrites(rs.getLong("filesysusage_diskWrites"));
				fsu.setFiles(rs.getLong("filesysusage_files"));
				fsu.setFree(rs.getLong("filesysusage_free"));
				fsu.setFreeFiles(rs.getLong("filesysusage_freeFiles"));
				fsu.setTimestamp(rs.getLong("filesysusage_timestamp"));
				fsu.setUsed(rs.getLong("filesysusage_used"));
				fsu.setUsePercent(rs.getDouble("filesysusage_usePercent"));

				list.add(fsu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return list;
	}

	/**
	 * 获取指定时间戳的文件系统利用率
	 */
	public FileSystemUsage getFileSysUsageById(long id, long timestamp) {
		String sql = "select * from t_filesys_usage where filesysusage_filesysid=? and filesysusage_timestamp=?";
		FileSystemUsage fsu = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.setLong(2, timestamp);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fsu = new FileSystemUsage(rs.getLong("filesysusage_timestamp"));
				fsu.setDiskQueue(rs.getDouble("filesysusage_diskQueue"));
				fsu.setDiskReadBytes(rs.getLong("filesysusage_diskReadBytes"));
				fsu.setDiskReads(rs.getLong("filesysusage_diskReads"));
				fsu.setDiskServiceTime(rs
						.getLong("filesysusage_diskServiceTime"));
				fsu.setDiskWriteBytes(rs.getLong("filesysusage_diskWriteBytes"));
				fsu.setDiskWrites(rs.getLong("filesysusage_diskWrites"));
				fsu.setFiles(rs.getLong("filesysusage_files"));
				fsu.setFree(rs.getLong("filesysusage_free"));
				fsu.setFreeFiles(rs.getLong("filesysusage_freeFiles"));
				fsu.setTimestamp(rs.getLong("filesysusage_timestamp"));
				fsu.setUsed(rs.getLong("filesysusage_used"));
				fsu.setUsePercent(rs.getDouble("filesysusage_usePercent"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return fsu;
	}

	/**
	 * 获取指定主机ID的网络接口列表
	 */
	public List<NetInterface> getNetInterfacesById(long id) {
		String sql = "select * from t_netinterface where netif_eqptid =?";
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
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return list;

	}

	/**
	 * 获取指定网络接口ID的网络接口
	 */
	public NetInterface getNetInterfaceById(long id) {
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
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return nif;

	}

	/**
	 * 获取指定接口 ID的网络接口采集信息列表
	 */
	public List<NetInterfaceStat> getInterfaceStatsById(long id) {
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
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return list;
	}

	/**
	 * 获取指定网络接口 ID和时间戳的网络接口状态
	 */
	public NetInterfaceStat getInterfaceStatById(long id, long timestamp) {
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
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return nis;
	}

	/**
	 * 获取指定主机ID的进程
	 */
	public List<Progress> getProgressesById(long id) {
		String sql = "select * from t_progress where prog_eqptid=?";
		List<Progress> list = new ArrayList<Progress>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Progress pro = new Progress(rs.getLong("prog_id"));
				pro.setName(rs.getString("prog_name"));
				pro.setStartTime(rs.getString("prog_startTime"));
				pro.setUser(rs.getString("prog_user"));

				list.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return list;
	}

	/**
	 * 获取指定进程ID的进程
	 */
	public Progress getProgressById(long id) {
		String sql = "select * from t_progress where prog_id=?";
		Progress pro = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pro = new Progress(rs.getLong("prog_id"));
				pro.setName(rs.getString("prog_name"));
				pro.setStartTime(rs.getString("prog_startTime"));
				pro.setUser(rs.getString("prog_user"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return pro;
	}

	/**
	 * 获取指定ID的进程监测信息
	 */
	public List<ProgressDetection> getProgressDetecsById(long id) {
		String sql = "select * from t_prog_usage where progusage_progid=?";
		List<ProgressDetection> list = new ArrayList<ProgressDetection>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProgressDetection pd = new ProgressDetection(
						rs.getLong("progusage_timestamp"));
				pd.setCpuTime(rs.getLong("progusage_cpuTime"));
				pd.setCpuUsed(rs.getLong("progusage_cpuUsed"));
				pd.setMemShare(rs.getLong("progusage_memShare"));
				pd.setMemSize(rs.getLong("progusage_memSize"));
				pd.setMemUsed(rs.getLong("progusage_memUsed"));
				pd.setState(rs.getString("progusage_state"));
				pd.setTimestamp(rs.getLong("progusage_timestamp"));

				list.add(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return list;
	}

	/**
	 * 获取指定时间戳的进程监测信息
	 */
	public ProgressDetection getProgressDetecById(long id, long timestamp) {
		String sql = "select * from t_prog_usage where progusage_progid=? and progusage_timestamp=?";
		ProgressDetection prod = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.setLong(2, timestamp);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prod = new ProgressDetection(rs.getLong("progusage_timestamp"));
				prod.setCpuTime(rs.getLong("progusage_cpuTime"));
				prod.setCpuUsed(rs.getLong("progusage_cpuUsed"));
				prod.setMemShare(rs.getLong("progusage_memShare"));
				prod.setMemSize(rs.getLong("progusage_memSize"));
				prod.setMemUsed(rs.getLong("progusage_memUsed"));
				prod.setState(rs.getString("progusage_state"));
				prod.setTimestamp(rs.getLong("progusage_timestamp"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, rs);
		}
		return prod;
	}

	/**
	 * 销毁指定ID的主机
	 */
	public void destoryHostById(long id) {
		String sql = "delete from t_host where host_id=?";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}

	}

	/**
	 * 更新指定CPU ID的CPU利用率
	 */
	public void addCPUPrecsById(long cpuid, double percent, long timestamp) {
		String sql = "insert into t_cpu_prec (prec_cpuid,prec_combined,prec_timestamp)values (?,?,?)";
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
			DButil.getInstance().close(pstmt, null);
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
			DButil.getInstance().close(pstmt, null);
		}
	}

	/**
	 * 更新指定文件系统ID的文件系统利用率
	 */
	public void addFileSystemUsages(long filesysid, double usage, long timestamp) {
		String sql = "insert into t_filesys_usage (filesysusage_filesysid,filesysusage_usePercent,filesysusage_timestamp) values (?,?,?)";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, filesysid);
			pstmt.setDouble(2, usage);
			pstmt.setLong(3, timestamp);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}

	}

	/**
	 * 更新指定主机的PING信息
	 */
	public void addPingInfo(long hostid, double pingDelay, long timestamp) {
		String sql = "insert into t_pinginfo (pinginfo_hostid,pinginfo_delay,pinginfo_timestamp) values (?,?,?)";
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, hostid);
			pstmt.setDouble(2, pingDelay);
			pstmt.setLong(3, timestamp);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}
	}

}
