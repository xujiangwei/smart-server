package smart;

import java.util.List;
import java.util.Set;

import smart.bean.CPU;
import smart.bean.FileSystem;
import smart.bean.Host;
import smart.bean.Memory;
import smart.bean.NetInterface;
import smart.bean.Progress;
import smart.dao.HostDAOImpl;
import smart.dao.IHostDAO;
import smart.util.DButil;

/**
 * 创建实体对象
 * 
 */
public class CreateEntity {

	// 单例
	public static CreateEntity instance = null;

	public synchronized static CreateEntity getInstance() {
		if (instance == null)
			instance = new CreateEntity();
		return instance;
	}

	// 创建host，添加child
	public void createHost() {
		IHostDAO hostDAO = new HostDAOImpl(DButil.getConnection());
		List<Host> list = hostDAO.queryAll();
		CPU c = null;
		Memory m = null;
		FileSystem f = null;
		NetInterface ni = null;
		Progress p = null;
		for (Host h : list) {
			c = hostDAO.quersyCPU(h.getId());
			m = hostDAO.queryMemory(h.getId());
			f = hostDAO.queryFileSys(h.getId());
			ni = hostDAO.queryNI(h.getId());
			p = hostDAO.queryPro(h.getId());
			System.out.println("创建host："+h.getName());
			if (c != null) {
				h.addChild(c);
			}
			if (m != null) {
				h.addChild(m);
			}
			if (f != null && f.getL().size() != 0) {
				h.addChild(f);
			}
			if (ni != null && ni.getList().size() != 0) {
				h.addChild(ni);
			}
			if (p != null && p.getList().size() != 0) {
				for (int i = 0; i < p.getList().size(); i++) {
					Set<String> s = p.getList().get(i).keySet();
					for (String k : s) {
						System.out.println(p.getList().get(i).get(k).getName());
					}
				}
				h.addChild(p);
			}
		}
	}

	public static void main(String[] args) {
		CreateEntity.getInstance().createHost();
	}
}
