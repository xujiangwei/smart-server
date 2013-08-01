package smart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import smart.dao.IHostDAO;
import smart.dto.CPU;
import smart.dto.FileSystem;
import smart.dto.Host;
import smart.dto.Memory;
import smart.dto.NetInterface;
import smart.dto.Ping;
import smart.dto.Progress;
import smart.util.DButil;

public class HostDAOImpl implements IHostDAO {

	private Connection conn; // 初始化连接

	public HostDAOImpl(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 获取所有主机
	 */
	@Override
	public List<Host> queryAll() {
		List<Host> list = new ArrayList<Host>(10);
		Host host = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from host";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				host = new Host();
				host.setHost_id(rs.getString("id"));
				host.setHost_name(rs.getString("name"));
				host.setHost_systemType(rs.getString("systemType"));
				host.setHost_Monitor(rs.getBoolean("isMonitor"));
				host.setHost_mac(rs.getString("mac"));
				host.setHost_ip(rs.getString("ip"));
				host.setHost_vendor(rs.getString("vendor"));
				host.setHost_model(rs.getString("model"));
				host.setHost_healthDegree(rs.getDouble("healthDegree"));
				list.add(host);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return list;
	}

	/**
	 * 获取主机的ping记录
	 */
	public List<Ping> queryPing(String hostid) {
		Ping ping = null;
		List<Ping> list = new ArrayList<Ping>(5);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_ping where hostid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hostid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ping = new Ping();
				ping.setPing_id(rs.getInt("id"));
				ping.setPing_ip(rs.getString("ip"));
				ping.setPing_lost(rs.getInt("lost"));
				ping.setPing_delay(rs.getInt("delay"));
				ping.setCollectTime(rs.getLong("collectTime"));
				list.add(ping);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return list;
	}

	/**
	 * 获取对应主机的cpu
	 * 
	 * @param hostid
	 * @return cpu
	 */
	public CPU queryCPU(String hostid) {
		CPU c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_cpu where hostid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hostid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				c = new CPU();
				c.setCpu_id(rs.getString("id"));
				c.setCpu_vendor(rs.getString("vendor"));
				c.setCpu_type(rs.getString("type"));
				c.setCpu_clockSpeed(rs.getFloat("clockSpeed"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return c;
	}

	/**
	 * 获取对应主机的内存
	 * 
	 * @param hostid
	 * @return Memory
	 */
	public Memory queryMemory(String hostid) {
		Memory m = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_memory where hostid =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hostid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m = new Memory();
				m.setMem_id(rs.getString("id"));
				m.setMem_physicsTotal(rs.getInt("physicsTotal"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return m;
	}

	/**
	 * 获取对应主机的文件系统
	 * 
	 * @param hostid
	 * @return FileSystem
	 */
	public List<FileSystem> queryFileSys(String hostid) {
		List<FileSystem> list = new ArrayList<FileSystem>(5);
		FileSystem fs = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_filesys where hostid =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hostid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fs = new FileSystem();
				fs.setFs_id(rs.getString("id"));
				fs.setFs_name(rs.getString("name"));
				fs.setFs_size(rs.getInt("size"));
				fs.setFs_type(rs.getString("type"));
				list.add(fs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return list;
	}

	/**
	 * 获取对应主机的网络接口
	 * 
	 * @param hostid
	 * @return NetInterface
	 */
	public List<NetInterface> queryNI(String hostid) {
		List<NetInterface> list = new ArrayList<NetInterface>(5);
		NetInterface ni = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_netinterface where hostid =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hostid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ni = new NetInterface();
				ni.setNi_id(rs.getString("id"));
				ni.setNi_name(rs.getString("name"));
				ni.setNi_type(rs.getString("type"));
				ni.setNi_desip(rs.getString("desip"));
				ni.setNi_ip(rs.getString("ip"));
				ni.setNi_mac(rs.getString("mac"));
				list.add(ni);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return list;
	}

	/**
	 * 获取对应主机的进程
	 * 
	 * @param hostid
	 * @return Progress
	 */
	public Progress queryPro(String hostid) {
		List<Map<String, Progress>> list = new ArrayList<Map<String, Progress>>(
				50);
		Progress p = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_progress where hostid =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hostid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				p = new Progress();
				p.setPro_id(rs.getString("id"));
				p.setPro_name(rs.getString("name"));
				p.setPro_total(rs.getInt("total"));
				p.setPro_username(rs.getString("user"));
				Map<String, Progress> map = new HashMap<String, Progress>(5);
				map.put(p.getPro_id(), p);
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return p;
	}

}
