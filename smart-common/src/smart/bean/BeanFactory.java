package smart.bean;

import java.util.List;

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
	public Host getHost(long id) {

		synchronized (this.hostDao) {
		}
		return this.hostDao.getHostById(id);
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
		List<Host> hList = null;
		synchronized (this.hostDao) {
			// 新建滑窗执行器
			// SlidingWindowExecutor swe = SlidingWindowExecutor
			// .newSlidingWindowThreadPool(20);
			// swe.execute(new SlidingWindowTask(swe) {
			// public void run() {
			hList = hostDao.getHostList();
			if (hList != null && hList.size() > 0) {
				for (int i = 0; i < hList.size(); i++) {
					Host host = new Host(hList.get(i).getId());
					host.setName(hList.get(i).getName());

					Memory mem = hostDao.getMemoryById(hList.get(i).getId());
					mem.setPhysicsTotal(mem.getPhysicsTotal());
					if (mem != null) {
						List<MemoryDetection> memList = hostDao
								.getMemoryDetecsById(mem.getId());
						if (memList != null && memList.size() > 0) {
							for (int j = 0; j < memList.size(); j++) {
								MemoryDetection memDetec = new MemoryDetection(
										memList.get(j).getUsed());
								memDetec.setActualFree(memList.get(j)
										.getActualFree());
								memDetec.setActualUsed(memList.get(j)
										.getActualUsed());
								memDetec.setFree(memList.get(j).getFree());
								memDetec.setFreePercent(memList.get(j)
										.getFreePercent());
								memDetec.setRam(memList.get(j).getRam());
								memDetec.setUsed(memList.get(j).getUsed());
								memDetec.setUsedPercent(memList.get(j)
										.getUsedPercent());
								mem.addMemoryDetection(memDetec);
							}
						}
						host.addChild(mem);
					}

					List<CPU> cList = hostDao.getCPUById(hList.get(i).getId());
					if (cList != null && cList.size() > 0) {
						for (int j = 0; j < cList.size(); j++) {
							CPU cpu = new CPU(cList.get(j).getId());
							cpu.setCacheSize(cList.get(j).getCacheSize());
							cpu.setMhz(cList.get(j).getMhz());
							cpu.setModel(cList.get(j).getModel());
							cpu.setTotalCores(cList.get(j).getTotalCores());
							cpu.setVendor(cList.get(j).getVendor());

							List<CPUPerc> cpList = hostDao.getPercsById(cpu
									.getId());
							if (cpList != null && cpList.size() > 0) {
								for (int k = 0; k < cpList.size(); k++) {
									CPUPerc cp = new CPUPerc(cpList.get(k)
											.getTimestamp());

									cp.setIdle(cpList.get(k).getIdle());
									cp.setNice(cpList.get(k).getNice());
									cp.setSys(cpList.get(k).getSys());
									cp.setUser(cpList.get(k).getUser());
									cp.setWait(cpList.get(k).getWait());
									// cp.setCombined(cpList.get(k).getCombined());
									cpu.addPrec(cp);
								}
							}
							if (cpu != null) {
								host.addChild(cpu);
							}  
						}
					}

					List<NetInterface> nList = hostDao
							.getNetInterfaceById(hList.get(i).getId());
					if (nList != null && nList.size() > 0) {
						for (int j = 0; j < nList.size(); j++) {
							NetInterface nif = new NetInterface(nList.get(j)
									.getId());
							nif.setAddress(nList.get(j).getAddress());
							nif.setBroadcast(nList.get(j).getBroadcast());
							nif.setDescription(nList.get(j).getDescription());
							nif.setDestination(nList.get(j).getDestination());
							nif.setGateway(nList.get(j).getGateway());
							nif.setMac(nList.get(j).getMac());
							nif.setMetric(nList.get(j).getMetric());
							nif.setMtu(nList.get(j).getMtu());
							nif.setName(nList.get(j).getName());
							nif.setNetmask(nList.get(j).getNetmask());
							nif.setType(nList.get(j).getType());

							List<NetInterfaceStat> nisList = hostDao
									.getInterfaceStats(nif.getId());
							if (nisList != null && nisList.size() > 0) {
								for (int k = 0; k < nisList.size(); k++) {
									NetInterfaceStat nis = new NetInterfaceStat(
											nisList.get(k).getTimestamp());
									nis.setRxBytes(nisList.get(k).getRxBytes());
									nis.setRxDropped(nisList.get(k)
											.getRxDropped());
									nis.setRxErrors(nisList.get(k)
											.getRxErrors());
									nis.setRxFrame(nisList.get(k).getRxFrame());
									nis.setRxOverruns(nisList.get(k)
											.getRxOverruns());
									nis.setRxPackets(nisList.get(k)
											.getRxPackets());
									nis.setSpeed(nisList.get(k).getSpeed());
									nis.setStatus(nisList.get(k).getStatus());
									nis.setThroughput(nisList.get(k)
											.getThroughput());
									nis.setTraffic(nisList.get(k).getTraffic());
									nis.setTxBytes(nisList.get(k).getTxBytes());
									nis.setTxCarrier(nisList.get(k)
											.getTxCarrier());
									nis.setTxCollisions(nisList.get(k)
											.getTxCollisions());
									nis.setTxDropped(nisList.get(k)
											.getTxDropped());
									nis.setTxErrors(nisList.get(k)
											.getTxErrors());
									nis.setTxOverruns(nisList.get(k)
											.getTxOverruns());
									nis.setTxPackets(nisList.get(k)
											.getTxPackets());
									nis.setRxBytes(nisList.get(k).getRxBytes());
									nis.setRxBytes(nisList.get(k).getRxBytes());

									nif.addNiPrec(nis);
								}
							}
							if (nif != null) {
								host.addChild(nif);
							}
						}
					}

					List<FileSystem> fList = hostDao.getFileSystemById(hList
							.get(i).getId());
					if (fList != null && fList.size() > 0) {
						for (int j = 0; j < fList.size(); j++) {
							FileSystem fs = new FileSystem(fList.get(j).getId());
							fs.setDevName(fList.get(j).getDevName());
							fs.setDirName(fList.get(j).getDirName());
							fs.setFlags(fList.get(j).getFlags());
							fs.setOptions(fList.get(j).getOptions());
							fs.setSize(fList.get(j).getSize());
							fs.setSysTypeName(fList.get(j).getSysTypeName());
							fs.setType(fList.get(j).getType());
							fs.setTypeName(fList.get(j).getTypeName());

							List<FileSystemUsage> fsuList = hostDao
									.getFileSystemUsagesById(fs.getId());
							if (fsuList != null && fsuList.size() > 0) {
								for (int k = 0; k < fsuList.size(); k++) {
									FileSystemUsage fsu = new FileSystemUsage(
											fsuList.get(k).getTimestamp());
									fsu.setDiskQueue(fsuList.get(k)
											.getDiskQueue());
									fsu.setDiskReadBytes(fsuList.get(k)
											.getDiskReads());
									fsu.setDiskReads(fsuList.get(k)
											.getDiskReads());
									fsu.setDiskServiceTime(fsuList.get(k)
											.getDiskServiceTime());
									fsu.setDiskWriteBytes(fsuList.get(k)
											.getDiskReadBytes());
									fsu.setDiskWrites(fsuList.get(k)
											.getDiskWrites());
									fsu.setFiles(fsuList.get(k).getFiles());
									fsu.setFree(fsuList.get(k).getFreeFiles());
									fsu.setFreeFiles(fsuList.get(k)
											.getFreeFiles());
									fsu.setUsed(fsuList.get(k).getUsed());
									fsu.setUsePercent(fsuList.get(k)
											.getUsePercent());

									fs.addFSDetection(fsu);
								}
							}
							if (fs != null) {
								host.addChild(fs);
							} 
						}

					}
				}
			}
			// }
			// });
			// 等待子线程全部结束
			// try {
			// Thread.sleep(500);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			//
			// // 关闭所有子线程，不关的话主线程会一直阻塞
			// swe.shutdown();
			// try {
			// // 等待线程终止
			// if (!swe.awaitTermination(60, TimeUnit.SECONDS)) {
			// swe.shutdownNow();
			// if (!swe.awaitTermination(60, TimeUnit.SECONDS))
			// System.err.println("Pool did not terminate");
			// }
			// } catch (InterruptedException ie) {
			// swe.shutdownNow();
			// Thread.currentThread().interrupt();
			// }
		}

		return hList;

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
	// public Progress getProgressById(long id) {
	// synchronized (this.hostDao) {
	// return this.hostDao.getProgressById(id);
	// }
	// }

	/**
	 * 获取指定主机ID的文件系统
	 * 
	 * @param id
	 * @return
	 */
	public List<FileSystem> getFileSystemById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getFileSystemById(id);
		}
	}

	/**
	 * 获取指定主机ID的网络接口
	 * 
	 * @param id
	 * @return
	 */
	public List<NetInterface> getNetInterfaceById(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getNetInterfaceById(id);
		}
	}

	public static void main(String[] args) {
		BeanFactory be = new BeanFactory();
		 be.getHostList();


	}
}
