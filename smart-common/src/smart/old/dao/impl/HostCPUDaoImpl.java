package smart.old.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import smart.old.bean.CPU;
import smart.old.bean.CPUPerc;
import smart.old.dao.AbstraceDao;
import smart.old.dao.HostCPUDao;
import smart.old.util.DButil;

/**
 * CPU DAO 实现。
 */
public class HostCPUDaoImpl extends AbstraceDao implements HostCPUDao {
	private PreparedStatement pstmt;
	private ResultSet rs;

	public HostCPUDaoImpl() {
		super();
	}

	/**
	 * 获取指定设备ID的CPU列表
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
	 * 存储主机CPU的配置信息
	 */
	public void saveHostCPUInfo(long hcpu_mosn, String hcpu_name,
			String hcpu_type, String hcpu_catch, String hcpu_sign,
			double hcpu_mhz, String hcpu_model, String hcpu_vender,
			long hcpu_eqptmosn) {

		String sql = "insert into t_hostcpu (hcpu_mosn,hcpu_name,hcpu_type,hcpu_catch,hcpu_sign,hcpu_mhz,hcpu_model,hcpu_vender,hcpu_eqptmosn) values  (?,?,?,?,?,?,?,?,?)";

		try {
			super.doStart();
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setLong(1, hcpu_mosn);
			pstmt.setString(2, hcpu_name);
			pstmt.setString(3, hcpu_type);
			pstmt.setString(4, hcpu_catch);
			pstmt.setString(5, hcpu_sign);
			pstmt.setDouble(6, hcpu_mhz);
			pstmt.setString(7, hcpu_model);
			pstmt.setString(8, hcpu_vender);
			// pstmt.setLong(9, hcpu_bits);
			pstmt.setLong(9, hcpu_eqptmosn);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.getInstance().close(pstmt, null);
		}

	}
}
