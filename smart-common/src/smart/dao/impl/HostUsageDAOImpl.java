package smart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import smart.dao.IHostUsageDAO;
import smart.dto.CPUsage;
import smart.dto.FileSysUsage;
import smart.dto.MemUsage;
import smart.dto.NetInterfaceStatus;
import smart.dto.ProgressUsage;
import smart.util.DButil;

/**
 * 主机动态指标信息
 * @author Administrator
 *
 */
public class HostUsageDAOImpl implements IHostUsageDAO {

	private Connection conn;
	
	public HostUsageDAOImpl(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 查询cpu动态信息记录
	 * @param cpuid
	 * @return
	 */
	public List<CPUsage> queryCPUList(String cpuid){
		List<CPUsage> l = new ArrayList<CPUsage>(10);
		CPUsage cs = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_cpusage where cpuid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cpuid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cs = new CPUsage();
				cs.setId(rs.getInt("id"));
				cs.setCpu_cacheSize(rs.getInt("cpuCacheSize"));
				cs.setCpu_user(rs.getDouble("user"));
				cs.setCpu_nice(rs.getDouble("nice"));
				cs.setCpu_system(rs.getDouble("system"));
				cs.setCpu_IOWait(rs.getDouble("IOWait"));
				cs.setCpu_idle(rs.getDouble("idle"));
				cs.setCpu_load(rs.getDouble("cpuLoad"));
				cs.setCpu_usage(rs.getDouble("usage"));
				cs.setCpu_collectTime(rs.getLong("collectTime"));
				l.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return l;
	}
	
	/**
	 * 查询文件系统的监控记录
	 * @param fileSysid
	 * @return
	 */
	public List<FileSysUsage> queryFileSysList(String fileSysid){
		List<FileSysUsage> lf = new ArrayList<FileSysUsage>(5);
		FileSysUsage fs = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_filesysusage where filesysid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fileSysid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fs = new FileSysUsage();
				fs.setId(rs.getInt("id"));
				fs.setFs_used(rs.getFloat("used"));
				fs.setFs_free(rs.getFloat("free"));
				fs.setFs_usage(rs.getFloat("usage"));
				fs.setFs_collectTime(rs.getLong("collectTime"));
				lf.add(fs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return lf;
	}
	
	/**
	 * 查询内存使用记录
	 * @param memid
	 * @return
	 */
	public List<MemUsage> queryMemoryList(String memid){
		List<MemUsage> lm = new ArrayList<MemUsage>(10);
		MemUsage m = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_memoryusage where memid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				m = new MemUsage();
				m.setId(rs.getInt("id"));
				m.setMem_physicsUsed(rs.getInt("physicsUsed"));
				m.setMem_physicsFree(rs.getInt("physicsFree"));
				m.setMem_swapTotal(rs.getInt("swapTotal"));
				m.setMem_swapUsed(rs.getInt("swapUsed"));
				m.setMem_swapFree(rs.getInt("swapFree"));
				m.setMem_load(rs.getDouble("memLoad"));
				m.setMem_collectTime(rs.getLong("collectTime"));
				lm.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return lm;
	}
	
	/**
	 * 查询主机网络接口记录
	 * @param niId
	 * @return
	 */
	public List<NetInterfaceStatus> queryNIStatusList(String niId){
		List<NetInterfaceStatus> ln = new ArrayList<NetInterfaceStatus>(10);
		NetInterfaceStatus nis = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_nistatus where niId = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, niId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				nis = new NetInterfaceStatus();
				nis.setId(rs.getInt("id"));
				nis.setNi_status(rs.getString("status"));
				nis.setNi_flow(rs.getFloat("flow"));
				nis.setNi_throughput(rs.getFloat("throughput"));
				nis.setNi_collectTime(rs.getLong("collectTime"));
				ln.add(nis);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(pstmt, rs);
		}
		return ln;
	}
	
	/**
	 * 查询主机进程记录
	 * @param progressid
	 * @return
	 */
	public List<ProgressUsage> queryProList(String progressid){
		List<ProgressUsage> l = new ArrayList<ProgressUsage>(10);
		ProgressUsage pu = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from h_prousage where progressid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, progressid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pu = new ProgressUsage();
				pu.setId(rs.getInt("id"));
				pu.setCpuUsed(rs.getInt("cpuUsed"));
				pu.setMemUsed(rs.getInt("memUsed"));
				pu.setCollectTime(rs.getLong("collectTime"));
				l.add(pu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
}
