package smart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
import smart.dao.AbstraceDao;
import smart.dao.HostDao;

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
		String sql = "select id from host";
		List<Long> list = new ArrayList<Long>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getLong("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		String sql = "select * from host where id=?";
		Host host = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				host=new Host(rs.getLong("id"));
				host.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return host;
	}

	/**
	 * 获取主机列表
	 */
	public List<Host> getHostList() {
		String sql = "select * from  host";
		List<Host> list = new ArrayList<Host>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Host host = new Host(rs.getLong("id"));
				host.setName(rs.getString("name"));
				list.add(host);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * 获取指定ID的CPU列表
	 */
	public List<CPU> getCPUsById(long id) {
		String sql = "select * from dcpu where hostid=?";
		List<CPU> list = new ArrayList<CPU>(3);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CPU cpu = new CPU(rs.getLong("id"));
				cpu.setTotalCores(rs.getInt("totalCores"));
				cpu.setMhz(rs.getInt("mhz"));
				cpu.setCacheSize(rs.getLong("cacheSize"));
				cpu.setModel(rs.getString("model"));
				cpu.setVendor(rs.getString("vendor"));

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
	public CPU getCPUById(long id) {
		String sql = "select * from dcpu where id=?";
		CPU cpu = new CPU(id);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cpu.setCacheSize(rs.getLong("cacheSize"));
				cpu.setMhz(rs.getInt("mhz"));
				cpu.setModel(rs.getString("model"));
				cpu.setTotalCores(rs.getInt("totalCores"));
				cpu.setVendor(rs.getString("vendor"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cpu;
	}

	/**
	 * 获取指定时间戳的CPU利用率
	 */
	public CPUPerc getCPUPercById(long id, long timestamp) {
		String sql = "select * from dcpuprec where cpuid=? and timestamp=?";
		CPUPerc cp = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.setLong(2, timestamp);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cp = new CPUPerc(rs.getLong("timestamp"));
				cp.setIdle(rs.getDouble("idle"));
				cp.setNice(rs.getDouble("nice"));
				cp.setSys(rs.getDouble("sys"));
				cp.setUser(rs.getDouble("user"));
				cp.setWait(rs.getDouble("wait"));
				cp.setCombined(rs.getDouble("combined"));
				cp.setTimestamp(rs.getLong("timestamp"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cp;
	}

	/**
	 * 返回指定CPU id的CPU利用率列表
	 */
	public List<CPUPerc> getPercsById(long id) {
		String sql = "select * from dcpuprec where cpuid=?";
		List<CPUPerc> list = new ArrayList<CPUPerc>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CPUPerc cp = new CPUPerc(rs.getLong("id"));
				cp.setIdle(rs.getDouble("idle"));
				cp.setNice(rs.getDouble("nice"));
				cp.setSys(rs.getDouble("sys"));
				cp.setUser(rs.getDouble("user"));
				cp.setWait(rs.getDouble("wait"));
				cp.setCombined(rs.getDouble("combined"));
				cp.setTimestamp(rs.getLong("timestamp"));

				list.add(cp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定主机ID的内存
	 */
	public Memory getMemoryById(long id) {
		String sql = "select * from dmemory where hostid=?";
		Memory memory = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memory = new Memory(rs.getLong("id"));
				memory.setPhysicsTotal(rs.getInt("physicsTotal"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memory;
	}

	/**
	 * 获取指定内存Id 的内存监测信息列表
	 */
	public List<MemoryDetection> getMemoryDetecsById(long id) {
		String sql = "select * from dmemoryusage where memid=?";
		List<MemoryDetection> list = new ArrayList<MemoryDetection>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemoryDetection memDetc = new MemoryDetection(rs.getLong("id"));
				memDetc.setActualFree(rs.getLong("actualFree"));
				memDetc.setActualUsed(rs.getLong("actualUsed"));
				memDetc.setFree(rs.getLong("free"));
				memDetc.setFreePercent(rs.getDouble("freePercent"));
				memDetc.setRam(rs.getLong("ram"));
				memDetc.setUsed(rs.getLong("used"));
				memDetc.setUsedPercent(rs.getDouble("usedPercent"));
				memDetc.setTimestamp(rs.getLong("timestamp"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定时间戳的内存监测信息
	 */
	public MemoryDetection getMemoryDetecById(long id, long timestamp) {
		String sql = "select * from dmemoryusage where memid=? and timestamp=?";
		MemoryDetection memDetec = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.setLong(2, timestamp);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memDetec = new MemoryDetection(rs.getLong("timestamp"));
				memDetec.setActualFree(rs.getLong("actualFree"));
				memDetec.setActualUsed(rs.getLong("actualUsed"));
				memDetec.setFree(rs.getLong("free"));
				memDetec.setFreePercent(rs.getDouble("freePercent"));
				memDetec.setRam(rs.getLong("ram"));
				memDetec.setUsed(rs.getLong("used"));
				memDetec.setUsedPercent(rs.getDouble("usedPercent"));
				memDetec.setTimestamp(rs.getLong("timestamp"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memDetec;
	}

	/**
	 * 获取指定主机ID的文件系统列表
	 */
	public List<FileSystem> getFileSystemsById(long id) {
		String sql = "select * from dfilesys where hostid=?";
		List<FileSystem> list = new ArrayList<FileSystem>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FileSystem fileSys = new FileSystem(rs.getLong("id"));
				fileSys.setDevName(rs.getString("devName"));
				fileSys.setDirName(rs.getString("dirName"));
				fileSys.setFlags(rs.getLong("flags"));
				fileSys.setOptions(rs.getString("options"));
				fileSys.setSize(rs.getFloat("size"));
				fileSys.setSysTypeName(rs.getString("sysTypeName"));
				fileSys.setType(rs.getInt("type"));
				fileSys.setTypeName(rs.getString("typeName"));
				list.add(fileSys);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * 获取指定ID的文件系统
	 */
	public FileSystem getFileSystemById(long id) {
		String sql = "select * from dfilesys where id=?";
		FileSystem fs = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fs = new FileSystem(rs.getLong("id"));
				fs.setDevName(rs.getString("devName"));
				fs.setDirName(rs.getString("dirName"));
				fs.setFlags(rs.getLong("flags"));
				fs.setOptions(rs.getString("options"));
				fs.setSize(rs.getFloat("size"));
				fs.setSysTypeName(rs.getString("sysTypeName"));
				fs.setType(rs.getInt("type"));
				fs.setTypeName(rs.getString("typeName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fs;
	}

	/**
	 * 获取指定文件系统ID的文件系统利用率列表
	 */
	public List<FileSystemUsage> getFileSystemUsagesById(long id) {
		String sql = "select * from dfilesysusage where filesysid=?";
		List<FileSystemUsage> list = new ArrayList<FileSystemUsage>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FileSystemUsage fsu = new FileSystemUsage(rs.getLong("id"));
				fsu.setDiskQueue(rs.getDouble("diskQueue"));
				fsu.setDiskReadBytes(rs.getLong("diskReadBytes"));
				fsu.setDiskReads(rs.getLong("diskReads"));
				fsu.setDiskServiceTime(rs.getLong("diskServiceTime"));
				fsu.setDiskWriteBytes(rs.getLong("diskWriteBytes"));
				fsu.setDiskWrites(rs.getLong("diskWrites"));
				fsu.setFiles(rs.getLong("files"));
				fsu.setFree(rs.getLong("free"));
				fsu.setFreeFiles(rs.getLong("freeFiles"));
				fsu.setTimestamp(rs.getLong("timestamp"));
				fsu.setUsed(rs.getLong("used"));
				fsu.setUsePercent(rs.getDouble("usePercent"));

				list.add(fsu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定时间戳的文件系统利用率
	 */
	public FileSystemUsage getFileSysUsageById(long id, long timestamp) {
		String sql = "select * from dfilesysusage where filesysid=? and timestamp=?";
		FileSystemUsage fsu = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.setLong(2, timestamp);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fsu = new FileSystemUsage(rs.getLong("timestamp"));
				fsu.setDiskQueue(rs.getDouble("diskQueue"));
				fsu.setDiskReadBytes(rs.getLong("diskReadBytes"));
				fsu.setDiskReads(rs.getLong("diskReads"));
				fsu.setDiskServiceTime(rs.getLong("diskServiceTime"));
				fsu.setDiskWriteBytes(rs.getLong("diskWriteBytes"));
				fsu.setDiskWrites(rs.getLong("diskWrites"));
				fsu.setFiles(rs.getLong("files"));
				fsu.setFree(rs.getLong("free"));
				fsu.setFreeFiles(rs.getLong("freeFiles"));
				fsu.setTimestamp(rs.getLong("timestamp"));
				fsu.setUsed(rs.getLong("used"));
				fsu.setUsePercent(rs.getDouble("usePercent"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fsu;
	}

	/**
	 * 获取指定ID的网络接口列表
	 */
	public List<NetInterface> getNetInterfacesById(long id) {
		String sql = "select * from dnetinterface where hostid=?";
		List<NetInterface> list = new ArrayList<NetInterface>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NetInterface nif = new NetInterface(rs.getLong("id"));
				nif.setAddress(rs.getString("address"));
				nif.setBroadcast(rs.getString("broadcast"));
				nif.setDescription(rs.getString("description"));
				nif.setDestination(rs.getString("destination"));
				nif.setGateway(rs.getString("gateway"));
				nif.setMac(rs.getString("mac"));
				nif.setMetric(rs.getLong("metric"));
				nif.setMtu(rs.getLong("mtu"));
				nif.setName(rs.getString("name"));
				nif.setNetmask(rs.getString("netmask"));
				nif.setType(rs.getString("type"));

				list.add(nif);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * 获取指定ID的网络接口
	 */
	public NetInterface getNetInterfaceById(long id) {
		String sql = "select * from dnetinterface where id=?";
		NetInterface nif = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				nif = new NetInterface(rs.getLong("id"));
				nif.setAddress(rs.getString("address"));
				nif.setBroadcast(rs.getString("broadcast"));
				nif.setDescription(rs.getString("description"));
				nif.setDestination(rs.getString("destination"));
				nif.setGateway(rs.getString("gateway"));
				nif.setMac(rs.getString("mac"));
				nif.setMetric(rs.getLong("metric"));
				nif.setMtu(rs.getLong("mtu"));
				nif.setName(rs.getString("name"));
				nif.setNetmask(rs.getString("netmask"));
				nif.setType(rs.getString("type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nif;

	}

	/**
	 * 获取指定接口 ID的网络接口采集信息列表
	 */
	public List<NetInterfaceStat> getInterfaceStatsById(long id) {
		String sql = "select * from dnistatus where niid=?";
		List<NetInterfaceStat> list = new ArrayList<NetInterfaceStat>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NetInterfaceStat nis = new NetInterfaceStat(rs.getLong("id"));
				nis.setRxBytes(rs.getLong("rxBytes"));
				nis.setRxDropped(rs.getLong("rxDropped"));
				nis.setRxErrors(rs.getLong("rxErrors"));
				nis.setRxFrame(rs.getLong("rxFrame"));
				nis.setRxOverruns(rs.getLong("rxOverruns"));
				nis.setRxPackets(rs.getLong("rxPackets"));
				nis.setSpeed(rs.getLong("speed"));
				nis.setStatus(rs.getString("status"));
				nis.setThroughput(rs.getFloat("throughput"));
				nis.setTimestamp(rs.getLong("timestamp"));
				nis.setTraffic(rs.getFloat("traffic"));
				nis.setTxBytes(rs.getLong("txBytes"));
				nis.setTxCarrier(rs.getLong("txCarrier"));
				nis.setTxCollisions(rs.getLong("txCollisions"));
				nis.setTxDropped(rs.getLong("txDropped"));
				nis.setTxErrors(rs.getLong("txErrors"));
				nis.setTxOverruns(rs.getLong("txOverruns"));
				nis.setTxPackets(rs.getLong("txPackets"));

				list.add(nis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定时间戳的网络接口状态
	 */
	public NetInterfaceStat getInterfaceStatById(long id, long timestamp) {
		String sql = "select * from dnistatus where niid=? and timestamp=?";
		NetInterfaceStat nis = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.setLong(2, timestamp);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				nis = new NetInterfaceStat(rs.getLong("timestamp"));
				nis.setRxBytes(rs.getLong("rxBytes"));
				nis.setRxDropped(rs.getLong("rxDropped"));
				nis.setRxErrors(rs.getLong("rxErrors"));
				nis.setRxFrame(rs.getLong("rxFrame"));
				nis.setRxOverruns(rs.getLong("rxOverruns"));
				nis.setRxPackets(rs.getLong("rxPackets"));
				nis.setSpeed(rs.getLong("speed"));
				nis.setStatus(rs.getString("status"));
				nis.setThroughput(rs.getFloat("throughput"));
				nis.setTimestamp(rs.getLong("timestamp"));
				nis.setTraffic(rs.getFloat("traffic"));
				nis.setTxBytes(rs.getLong("txBytes"));
				nis.setTxCarrier(rs.getLong("txCarrier"));
				nis.setTxCollisions(rs.getLong("txCollisions"));
				nis.setTxDropped(rs.getLong("txDropped"));
				nis.setTxErrors(rs.getLong("txErrors"));
				nis.setTxOverruns(rs.getLong("txOverruns"));
				nis.setTxPackets(rs.getLong("txPackets"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nis;
	}

	/**
	 * 获取指定ID的进程
	 */
	public List<Progress> getProgressesById(long id) {
		String sql = "select * from dprogress where hostid=?";
		List<Progress> list = new ArrayList<Progress>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Progress pro = new Progress(rs.getLong("id"));
				pro.setName(rs.getString("name"));
				pro.setStartTime(rs.getString("startTime"));
				pro.setUser(rs.getString("user"));

				list.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定ID的进程
	 */
	public Progress getProgressById(long id) {
		String sql = "select * from dprogress where id=?";
		Progress pro = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pro = new Progress(rs.getLong("id"));
				pro.setName(rs.getString("name"));
				pro.setStartTime(rs.getString("startTime"));
				pro.setUser(rs.getString("user"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pro;
	}

	/**
	 * 获取指定ID的进程监测信息
	 */
	public List<ProgressDetection> getProgressDetecsById(long id) {
		String sql = "select * from dprousage where progressid=?";
		List<ProgressDetection> list = new ArrayList<ProgressDetection>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProgressDetection pd = new ProgressDetection(
						rs.getLong("timestamp"));
				pd.setCpuTime(rs.getLong("cpuTime"));
				pd.setCpuUsed(rs.getLong("cpuUsed"));
				pd.setMemShare(rs.getLong("memShare"));
				pd.setMemSize(rs.getLong("memSize"));
				pd.setMemUsed(rs.getLong("memUsed"));
				pd.setState(rs.getString("state"));
				pd.setTimestamp(rs.getLong("timestamp"));

				list.add(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定时间戳的进程监测信息
	 */
	public ProgressDetection getProgressDetecById(long id, long timestamp) {
		String sql = "select * from dprousage where progressid=? and timestamp=?";
		ProgressDetection prod = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.setLong(2, timestamp);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prod = new ProgressDetection(rs.getLong("timestamp"));
				prod.setCpuTime(rs.getLong("cpuTime"));
				prod.setCpuUsed(rs.getLong("cpuUsed"));
				prod.setMemShare(rs.getLong("memShare"));
				prod.setMemSize(rs.getLong("memSize"));
				prod.setMemUsed(rs.getLong("memUsed"));
				prod.setState(rs.getString("state"));
				prod.setTimestamp(rs.getLong("timestamp"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prod;
	}

}
