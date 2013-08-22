package smart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import smart.bean.CPU;
import smart.bean.FileSystemDetection;
import smart.bean.Host;
import smart.bean.Memory;
import smart.bean.NetInterfaceDetection;
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
		Host host = new Host(id);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
	 * 获取指定ID的CPU
	 */
	public List<CPU> getCPUById(long id) {
		String sql = "select * from h_cpu where hostId=?";
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

				System.out.println("cpu__" + cpu.getVendor());
				list.add(cpu);
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
		String sql = "select * from h_memory where hostid=?";
		Memory memory = new Memory(id);
		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memory.setPhysicsTotal(rs.getInt("physicsTotal"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memory;
	}

	@Override
	public ProgressDetection getProgressDetectionById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileSystemDetection getFileSystemDetectionById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NetInterfaceDetection getNetInterfaceDetectionById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
