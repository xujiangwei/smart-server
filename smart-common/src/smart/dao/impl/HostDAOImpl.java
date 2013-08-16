package smart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import smart.bean.CPU;
import smart.bean.FileSystem;
import smart.bean.Host;
import smart.bean.Memory;
import smart.bean.NetInterface;
import smart.bean.Progress;
import smart.dao.HostDAO;
import smart.util.DButil;

public class HostDAOImpl implements HostDAO {

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
				host = new Host(rs.getLong("id"));
				host.setName(rs.getString("name"));
				// host.setHost_systemType(rs.getString("systemType"));
				// host.setHost_Monitor(rs.getBoolean("isMonitor"));
				// host.setHost_mac(rs.getString("mac"));
				// host.setHost_ip(rs.getString("ip"));
				// host.setHost_vendor(rs.getString("vendor"));
				// host.setHost_model(rs.getString("model"));
				// host.setHost_healthDegree(rs.getDouble("healthDegree"));
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
				c = new CPU(rs.getLong("id"));
				c.setCacheSize(rs.getInt("cacheSize"));
				c.setVendor(rs.getString("vendor"));
				c.setModel(rs.getString("model"));
				c.setMhz(rs.getInt("clockSpeed"));
				c.setTotalCores(rs.getInt("totalCores"));
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
				m = new Memory(rs.getLong("id"));
				m.setPhysicsTotal(rs.getInt("physicsTotal"));
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
	public List<FileSystem> queryFileSystem(String hostid) {
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
				fs = new FileSystem(rs.getLong("id"));
				fs.setName(rs.getString("name"));
				fs.setSize(rs.getInt("size"));
				fs.setType(rs.getString("type"));
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
				ni = new NetInterface(rs.getLong("id"));
				ni.setName(rs.getString("name"));
				ni.setType(rs.getString("type"));
				ni.setDesip(rs.getString("desip"));
				ni.setIp(rs.getString("ip"));
				ni.setMac(rs.getString("mac"));
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
		List<Map<Long, Progress>> list = new ArrayList<Map<Long, Progress>>(
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
				p = new Progress(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setTotal(rs.getInt("total"));
				p.setUsername(rs.getString("user"));
				Map<Long, Progress> map = new HashMap<Long, Progress>(5);
				map.put(p.getId(), p);
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
