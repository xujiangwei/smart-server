package smart.dao;

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
import smart.util.DButil;

public class HostDAOImpl implements IHostDAO {

	private Connection conn;    // 初始化连接
	
	public HostDAOImpl(Connection conn) {
		this.conn=conn;
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
			while(rs.next()){
				String id = rs.getString("id");
				host = new Host(id);
				host.setName(rs.getString("name"));
				host.setSystemType(rs.getString("systemType"));
				host.setMonitor(rs.getBoolean("isMonitor"));
				host.setIp(rs.getString("ip"));
				host.setVendor(rs.getString("vendor"));
				host.setModel(rs.getString("model"));
				host.setHealthDegree(rs.getDouble("healthDegree"));
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
	 * @param hostid
	 * @return cpu
	 */
	public CPU quersyCPU(String hostid){
		CPU c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_cpu where hostid =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hostid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				c = new CPU(rs.getString("id"));
				c.setVendor(rs.getString("vendor"));
				c.setType(rs.getString("type"));
				c.setClockSpeed(rs.getFloat("clockSpeed"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}	
	
	/**
	 * 获取对应主机的内存
	 * @param hostid
	 * @return Memory
	 */
	public Memory queryMemory(String hostid){
		Memory m = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_memory where hostid =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hostid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				m = new Memory(rs.getString("id"));
				m.setPhysicsTotal(rs.getInt("physicsTotal"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	/**
	 * 获取对应主机的文件系统
	 * @param hostid
	 * @return FileSystem
	 */
	public FileSystem queryFileSys(String hostid){
		List<Map<String, FileSystem>> list = new ArrayList<Map<String, FileSystem>>(5);
		FileSystem fs = new FileSystem("0010");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_filesys where hostid =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hostid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fs = new FileSystem(rs.getString("id"));
				fs.setName(rs.getString("name"));
				fs.setSize(rs.getInt("size"));
				fs.setType(rs.getString("type"));
				Map<String, FileSystem> map = new HashMap<String, FileSystem>(5);
				map.put(rs.getString("id"), fs);
				list.add(map);
				fs.setL(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fs;
	}
	
	/**
	 * 获取对应主机的网络接口
	 * @param hostid
	 * @return NetInterface
	 */
	public NetInterface queryNI(String hostid){
		List<Map<String, NetInterface>> list = new ArrayList<Map<String, NetInterface>>(5);
		NetInterface ni = new NetInterface("0000");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_netinterface where hostid =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hostid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ni = new NetInterface(rs.getString("id"));
				ni.setName(rs.getString("name"));
				ni.setType(rs.getString("type"));
				ni.setDesip(rs.getString("desip"));
				ni.setIp(rs.getString("ip"));
				ni.setMac(rs.getString("mac"));
				Map<String, NetInterface> map = new HashMap<String, NetInterface>(5);
				map.put(rs.getString("id"), ni);
				list.add(map);
				ni.setList(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ni;
	}
	
	/**
	 * 获取对应主机的进程
	 * @param hostid
	 * @return Progress
	 */
	public Progress queryPro(String hostid){
		List<Map<String, Progress>> list = new ArrayList<Map<String, Progress>>(50);
		Progress p = new Progress("1100");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_progress where hostid =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hostid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				p = new Progress(id);
				p.setName(rs.getString("name"));
				p.setTotal(rs.getInt("total"));
				p.setUser(rs.getString("user"));
				Map<String, Progress> map = new HashMap<String, Progress>(5);
				map.put(id, p);
				list.add(map);
				p.setList(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
}
