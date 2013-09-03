package smart.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.cellcloud.util.SlidingWindowExecutor;
import net.cellcloud.util.SlidingWindowTask;
import smart.bean.CPU;
import smart.bean.CPUPerc;
import smart.bean.FileSystem;
import smart.bean.FileSystemUsage;
import smart.bean.Host;
import smart.bean.Memory;
import smart.bean.MemoryDetection;
import smart.bean.NetDevice;
import smart.bean.NetInterface;
import smart.bean.NetInterfaceStat;
import smart.bean.Progress;
import smart.bean.ProgressDetection;
import smart.dao.HostDao;
import smart.dao.NetDeviceDao;
import smart.dao.impl.HostDaoImpl;
import smart.dao.impl.NetDeviceDaoImpl;
import smart.entity.Entity;

/**
 * Bean 工厂。
 * 
 * @author Jiangwei Xu
 */
public final class BeanFactory {

	private static final BeanFactory instance = new BeanFactory();
	private SlidingWindowExecutor swe = SlidingWindowExecutor
			.newSlidingWindowThreadPool(20);
	private List<Host> list = new ArrayList<Host>(20);
	private HostDao hostDao;
	private NetDeviceDao switchDao;

	private BeanFactory() {
		this.hostDao = new HostDaoImpl();
		this.switchDao = new NetDeviceDaoImpl();
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
		swe.execute(new SlidingWindowTask(swe) {
			public void run() {
				List<Host> hList = hostDao.getHostList();
				if (hList != null && hList.size() > 0) {
					for (int i = 0; i < hList.size(); i++) {
						Host host = new Host(hList.get(i).getId());
						Memory mem = hostDao
								.getMemoryById(hList.get(i).getId());
						if (mem != null) {
							List<MemoryDetection> memList = hostDao
									.getMemoryDetecsById(mem.getId());
							if (memList != null && memList.size() > 0) {
								for (int j = 0; j < memList.size(); j++) {
									MemoryDetection memDetec = new MemoryDetection(
											memList.get(j).getTimestamp());
									mem.addMemoryDetection(memDetec);
								}
							}
							host.addChild(mem);
						}
						List<CPU> cList = hostDao.getCPUsById(hList.get(i)
								.getId());
						if (cList != null && cList.size() > 0) {
							for (int j = 0; j < cList.size(); j++) {
								CPU cpu = new CPU(cList.get(j).getId());
								List<CPUPerc> cpList = hostDao.getPercsById(cpu
										.getId());
								if (cpList != null && cpList.size() > 0) {
									for (int k = 0; k < cpList.size(); k++) {
										CPUPerc cp = new CPUPerc(cpList.get(k)
												.getTimestamp());
										cpu.addPrec(cp);
									}
								}
								if (cpu != null) {
									host.addChild(cpu);
								}
							}
						}
						List<NetInterface> nList = hostDao
								.getNetInterfacesById(hList.get(i).getId());
						if (nList != null && nList.size() > 0) {
							for (int j = 0; j < nList.size(); j++) {
								NetInterface nif = new NetInterface(nList
										.get(j).getId());
								List<NetInterfaceStat> nisList = hostDao
										.getInterfaceStatsById(nif.getId());
								if (nisList != null && nisList.size() > 0) {
									for (int k = 0; k < nisList.size(); k++) {
										NetInterfaceStat nis = new NetInterfaceStat(
												nisList.get(k).getTimestamp());
										nif.addNiStat(nis);
									}
								}
								if (nif != null) {
									host.addChild(nif);
								}
							}
						}

						List<FileSystem> fList = hostDao
								.getFileSystemsById(hList.get(i).getId());
						if (fList != null && fList.size() > 0) {
							for (int j = 0; j < fList.size(); j++) {
								FileSystem fs = new FileSystem(fList.get(j)
										.getId());
								List<FileSystemUsage> fsuList = hostDao
										.getFileSystemUsagesById(fs.getId());
								if (fsuList != null && fsuList.size() > 0) {
									for (int k = 0; k < fsuList.size(); k++) {
										FileSystemUsage fsu = new FileSystemUsage(
												fsuList.get(k).getTimestamp());
										fs.addUsage(fsu);
									}
								}
								if (fs != null) {
									host.addChild(fs);
								}
							}
						}

						List<Progress> pList = hostDao.getProgressesById(hList
								.get(i).getId());
						if (pList != null && pList.size() > 0) {
							for (int j = 0; j < pList.size(); j++) {
								Progress pro = new Progress(pList.get(j)
										.getId());
								List<ProgressDetection> pdList = hostDao
										.getProgressDetecsById(pro.getId());
								if (pdList != null && pdList.size() > 0) {
									for (int k = 0; k < pdList.size(); k++) {
										ProgressDetection pd = new ProgressDetection(
												pdList.get(k).getTimestamp());
										pro.addDetection(pd);
									}
								}
								if (pro != null) {
									host.addChild(pro);
								}
							}
						}
						list.add(host);
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

		return list;

	}

	/**
	 * 获取指定主机ID的CPU列表
	 * 
	 * @param id
	 * @return
	 */
	public List<CPU> getCPUById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getCPUsById(id);
		}
	}

	/**
	 * 获取指定ID的CPU
	 * 
	 * @param id
	 * @return
	 */
	public CPU getCPUbyId(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getCPUById(id);
		}
	}

	/**
	 * 获取指定ID的CPU利用率列表
	 * 
	 * @param id
	 * @return
	 */
	public List<CPUPerc> getPercsById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getPercsById(id);
		}
	}

	/**
	 * 获取指定时间戳的CPU利用率
	 * 
	 * @param id
	 * @return
	 */
	public CPUPerc getCPUPercById(long time, long timestamp) {
		synchronized (this.hostDao) {
			return this.hostDao.getCPUPercById(time, timestamp);
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
	 * 获取指定内存ID的内存监测信息列表
	 * 
	 * @param id
	 * @return
	 */
	public List<MemoryDetection> getMemoryDetecsById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getMemoryDetecsById(id);
		}
	}

	/**
	 * 获取指定时间戳的内存监测信息
	 * 
	 * @param id
	 * @return
	 */
	public MemoryDetection getMemoryDetecById(long id, long timestamp) {
		synchronized (this.hostDao) {
			return this.hostDao.getMemoryDetecById(id, timestamp);
		}
	}

	/**
	 * 获取指定主机ID的进程
	 * 
	 * @param id
	 * @return
	 */
	public List<Progress> getProgressesById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getProgressesById(id);
		}
	}

	/**
	 * 获取指定进程ID的进程监测信息列表
	 * 
	 * @param id
	 * @return
	 */
	public List<ProgressDetection> getProgressDetecsById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getProgressDetecsById(id);
		}
	}

	/**
	 * 获取指定时间戳的进程监测信息
	 * 
	 * @param id
	 * @return
	 */
	public ProgressDetection getProgressDetecById(long id, long timestamp) {
		synchronized (this.hostDao) {
			return this.hostDao.getProgressDetecById(id, timestamp);
		}
	}

	/**
	 * 获取指定主机ID的文件系统
	 * 
	 * @param id
	 * @return
	 */
	public List<FileSystem> getFileSystemsById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getFileSystemsById(id);
		}
	}

	/**
	 * 获取指定文件系统ID的文件系统
	 * 
	 * @param id
	 * @return
	 */
	public FileSystem getFileSystemById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getFileSystemById(id);
		}
	}

	/**
	 * 获取指定文件系统ID的文件系统监测信息列表
	 * 
	 * @param id
	 * @return
	 */
	public List<FileSystemUsage> getFileSystemUsagesById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getFileSystemUsagesById(id);
		}
	}

	/**
	 * 获取指定时间戳的文件系统监测信息
	 * 
	 * @param id
	 * @return
	 */
	public FileSystemUsage getFileSysUsageById(long id, long timestamp) {
		synchronized (this.hostDao) {
			return this.hostDao.getFileSysUsageById(id, timestamp);
		}
	}

	/**
	 * 获取指定主机ID的网络接口
	 * 
	 * @param id
	 * @return
	 */
	public List<NetInterface> getNetInterfacesById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getNetInterfacesById(id);
		}
	}

	/**
	 * 获取指定ID的网络接口
	 * 
	 * @param id
	 * @return
	 */
	public NetInterface getNetInterfaceById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getNetInterfaceById(id);
		}
	}

	/**
	 * 获取指定网络接口ID的网络接口监测信息列表
	 * 
	 * @param id
	 * @return
	 */
	public List<NetInterfaceStat> getInterfaceStatsById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getInterfaceStatsById(id);
		}
	}

	/**
	 * 获取指定时间戳的网络接口监测信息
	 * 
	 * @param id
	 * @return
	 */
	public NetInterfaceStat getInterfaceStatById(long id, long timestamp) {
		synchronized (this.hostDao) {
			return this.hostDao.getInterfaceStatById(id, timestamp);
		}
	}

	public List<NetDevice> getSwitchsList() {
		swe.execute(new SlidingWindowTask(swe) {
			public void run() {
				List<NetDevice> sList = switchDao.getNetDevicesList();
				if (sList != null && sList.size() > 0) {
					for (int i = 0; i < sList.size(); i++) {
						NetDevice nd = new NetDevice(sList.get(i).getId());

						Memory mem = switchDao.getMemoryById(sList.get(i)
								.getId());
						if (mem != null) {
							List<MemoryDetection> mdList = switchDao
									.getMemoryDetecsById(mem.getId());
							if (mdList != null && mdList.size() > 0) {
								for (int j = 0; j < mdList.size(); j++) {
									MemoryDetection md = new MemoryDetection(
											mdList.get(j).getTimestamp());
									mem.addMemoryDetection(md);

								}
							}

						}
						nd.addChild(mem);

						List<CPU> cList = switchDao.getCPUsById(sList.get(i)
								.getId());
						if (cList != null && cList.size() > 0) {
							for (int j = 0; j < cList.size(); j++) {
								CPU cpu = new CPU(cList.get(j).getId());

								List<CPUPerc> cuList = switchDao
										.getPercsById(cpu.getId());
								if (cuList != null && cuList.size() > 0) {
									for (int k = 0; k < cuList.size(); k++) {
										CPUPerc cp = new CPUPerc(cuList.get(k)
												.getTimestamp());
										cpu.addPrec(cp);
									}
								}
								if (cpu != null) {
									nd.addChild(cpu);
								}
							}
						}

						List<NetInterface> nList = switchDao
								.getNetInterfacesById(sList.get(i).getId());
						if (nList != null && nList.size() > 0) {
							for (int j = 0; j < nList.size(); j++) {
								NetInterface ni = new NetInterface(nList.get(j)
										.getId());

								List<NetInterfaceStat> niList = switchDao
										.getInterfaceStatsById(ni.getId());
								if (niList != null && niList.size() > 0) {
									for (int k = 0; k < niList.size(); k++) {
										NetInterfaceStat nif = new NetInterfaceStat(
												niList.get(k).getTimestamp());
										ni.addNiStat(nif);
									}
								}
								if (ni != null) {
									nd.addChild(ni);
								}
							}

						}

						// ------神奇分割线------
					}
				}

			}
		});

		return null;

	}

	public static void main(String[] args) {
		BeanFactory be = BeanFactory.getInstance();
		// be.getHostList();

		List<Host> list = be.getHostList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println("hname__" + list.get(i).getName());
				Map<Long, Entity> map = list.get(i).getChildren();
				System.out.println("**_" + map.size());
				for (long key : map.keySet()) {
					System.out.println("key= " + key + " and value= "
							+ map.get(key));
				}

			}
		}

	}
}
