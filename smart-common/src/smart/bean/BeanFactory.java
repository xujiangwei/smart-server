package smart.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.cellcloud.util.SlidingWindowExecutor;
import net.cellcloud.util.SlidingWindowTask;
import smart.dao.HostDao;
import smart.dao.impl.HostDaoImpl;

/**
 * Bean 工厂。
 * 
 * @author Jiangwei Xu
 */
public final class BeanFactory {

	private static final BeanFactory instance = new BeanFactory();

	private HostDao hostDao;

	private BeanFactory() {
		this.hostDao = new HostDaoImpl();
	}

	public static BeanFactory getInstance() {
		return BeanFactory.instance;
	}

	/**
	 * 创建主机实体。
	 * 
	 * @param id
	 * @return
	 */

	public void createHost(long id) {
		synchronized (this.hostDao) {
			// 新建滑窗执行器
			SlidingWindowExecutor swe = SlidingWindowExecutor
					.newSlidingWindowThreadPool(20);
			swe.execute(new SlidingWindowTask(swe) {

				public void run() {
					BeanFactory be = new BeanFactory();
					List<Host> hlist = new ArrayList<Host>(10);
					hlist = be.getHostList();
					for (int i = 0; i < hlist.size(); i++) {
						System.out.println("创建host__" + hlist.get(i).getId());
						Host host = new Host(hlist.get(i).getId());
						host.setName(hlist.get(i).getName());

						Memory mem = be.getMemoryById(hlist.get(i).getId());
						mem.setPhysicsTotal(mem.getPhysicsTotal());
						host.addChild(mem);

						List<CPU> clist = be.getCPUById(hlist.get(i).getId());
						System.out.println("clist__" + clist.size());
						for (int j = 0; j < clist.size(); j++) {
							CPU cpu = new CPU(clist.get(j).getId());
							cpu.setCacheSize(clist.get(j).getCacheSize());
							cpu.setMhz(clist.get(j).getMhz());
							cpu.setModel(clist.get(j).getModel());
							cpu.setTotalCores(clist.get(j).getTotalCores());
							cpu.setVendor(clist.get(j).getVendor());

							host.addChild(cpu);
						}
					}

				}
			});
			// 等待子线程全部结束
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 关闭所有子线程，不关的话主线程会一直阻塞
			swe.shutdown();
			try {
				// 等待线程终止
				if (!swe.awaitTermination(60, TimeUnit.SECONDS)) {
					swe.shutdownNow();
					if (!swe.awaitTermination(60, TimeUnit.SECONDS))
						System.err.println("Pool did not terminate");
				}
			} catch (InterruptedException ie) {
				swe.shutdownNow();
				Thread.currentThread().interrupt();
			}
		}

	}

	/**
	 * 创建主机实体。
	 * 
	 * @param id
	 * @return
	 */
	// 新建滑窗执行器

	public Host getHost(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getHostById(id);
		}
	}

	/**
	 * 获取主机ID列表
	 * 
	 * @return
	 */
	public List<Long> getHostIdList() {
		synchronized (this.hostDao) {
			return this.hostDao.getHostIdList();
		}

	}

	/**
	 * 获取主机列表
	 * 
	 * @return
	 */
	public List<Host> getHostList() {
		synchronized (this.hostDao) {
			return this.hostDao.getHostList();
		}
	}

	/**
	 * 获取指定主机ID的CPU列表
	 * 
	 * @param id
	 * @return
	 */
	public List<CPU> getCPUById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getCPUById(id);
		}
	}

	/**
	 * 获取指定主机ID的内存
	 * 
	 * @param id
	 * @return
	 */
	public Memory getMemoryById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getMemoryById(id);
		}
	}

	/**
	 * 获取指定主机ID的进程
	 * 
	 * @param id
	 * @return
	 */
	public ProgressDetection getProgressDetectionById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getProgressDetectionById(id);
		}
	}

	/**
	 * 获取指定主机ID的文件系统
	 * 
	 * @param id
	 * @return
	 */
	public FileSystemDetection getFileSystemDetectionById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getFileSystemDetectionById(id);
		}
	}

	/**
	 * 获取指定主机ID的网络接口
	 * 
	 * @param id
	 * @return
	 */
	public NetInterfaceDetection getNetInterfaceDetectionById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getNetInterfaceDetectionById(id);
		}
	}

	public static void main(String[] args) {
		BeanFactory be = new BeanFactory();
		be.createHost(112);
	}
}
