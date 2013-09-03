package smart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import smart.bean.CPU;
import smart.bean.CPUPerc;
import smart.bean.Memory;
import smart.bean.MemoryDetection;
import smart.bean.NetInterface;
import smart.bean.NetInterfaceStat;
import smart.bean.NetDevice;
import smart.dao.AbstraceDao;
import smart.dao.NetDeviceDao;

/**
 * 交换机DAO实现
 */
public class NetDeviceDaoImpl extends AbstraceDao implements NetDeviceDao {

	private PreparedStatement pstmt;
	private ResultSet rs;

	public NetDeviceDaoImpl() {
		super();
	}

	/**
	 * 返回交换机ID列表
	 */
	public List<Long> getNetDeviceIdList() {
		String sql = "select id from netdevice";
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
	 * 返回指定ID的交换机
	 */
	public NetDevice getNetDeviceById(long id) {
		String sql = "select * from netdevice where id=?";
		NetDevice sw = null;
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sw = new NetDevice(rs.getLong("id"));
				sw.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw;
	}

	/**
	 * 返回交换机列表
	 */
	public List<NetDevice> getNetDevicesList() {
		String sql = "select * from netdevice";
		List<NetDevice> list = new ArrayList<NetDevice>(20);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NetDevice sw = new NetDevice(rs.getLong("id"));
				sw.setName(rs.getString("name"));
				list.add(sw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 返回指定CPU id的CPU列表
	 */
	public List<CPU> getCPUsById(long id) {
		String sql = "select * from dcpu where id=?";
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
	 * 获取指定CPU ID的CPU利用率列表
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
	 * 获取指定ID的内存
	 */
	public Memory getMemoryById(long id) {
		String sql = "select * from dmemory where id=?";
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
	 * 获取指定ID的网络接口
	 */
	public List<NetInterface> getNetInterfacesById(long id) {
		String sql = "select * from dnetinterface where id=?";
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
	 * 获取指定ID的网路接口
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
	 * 获取指定ID的网络接状态信息
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
	 * 获取指定时间戳的网络接口状态信息
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

}
