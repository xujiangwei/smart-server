package smart;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.cellcloud.util.SlidingWindowExecutor;
import net.cellcloud.util.SlidingWindowTask;
import smart.bean.host.CPUUsage;
import smart.bean.host.FilesysUsage;
import smart.bean.host.MemoryUsage;
import smart.bean.host.ProgressUsage;
import smart.dao.IHostDAO;
import smart.dao.impl.HostDAOImpl;
import smart.dao.impl.HostUsageDAOImpl;
import smart.dto.CPU;
import smart.dto.CPUsage;
import smart.dto.FileSysUsage;
import smart.dto.FileSystem;
import smart.dto.Host;
import smart.dto.MemUsage;
import smart.dto.Memory;
import smart.dto.NetInterface;
import smart.dto.NetInterfaceStatus;
import smart.dto.Ping;
import smart.dto.Progress;
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

	// 获取数据库中主机信息，创建host
	public void createHost() {

		SlidingWindowExecutor swe = SlidingWindowExecutor
				.newSlidingWindowThreadPool(20);
		swe.execute(new SlidingWindowTask(swe) {
			@Override
			public void run() {
				Connection conn = DButil.getConnection();
				IHostDAO hostDAO = new HostDAOImpl(conn);
				HostUsageDAOImpl huDAO = new HostUsageDAOImpl(conn);
				List<Host> list = hostDAO.queryAll();
				for (int i = 0; i < list.size(); i++) {
					System.out.println("创建host：" + list.get(i).getHost_name());
					smart.bean.host.Host h = new smart.bean.host.Host(list.get(
							i).getHost_id());
					h.setName(list.get(i).getHost_name());
					h.setSystemType(list.get(i).getHost_systemType());
					h.setVendor(list.get(i).getHost_vendor());
					h.setMonitor(list.get(i).getHost_Monitor());
					h.setIp(list.get(i).getHost_ip());
					h.setModel(list.get(i).getHost_model());
					h.setHealthDegree(list.get(i).getHost_healthDegree());

					List<Ping> lp = hostDAO.queryPing(list.get(i).getHost_id());
					CPU c = hostDAO.queryCPU(list.get(i).getHost_id());
					Memory m = hostDAO.queryMemory(list.get(i).getHost_id());
					List<FileSystem> fs = hostDAO.queryFileSys(list.get(i)
							.getHost_id());
					List<NetInterface> lni = hostDAO.queryNI(list.get(i)
							.getHost_id());
					Progress p = hostDAO.queryPro(list.get(i).getHost_id());
					if (lp != null) {
						for (int j = 0; j < lp.size(); j++) {
							System.out.println(lp.get(j).getPing_ip() + "  "
									+ lp.get(j).getPing_delay());
						}
					}
					
					smart.bean.host.CPU bcpu = new smart.bean.host.CPU(c
							.getCpu_id());
					if (c != null) {
						System.out.println("cpu__start");
						bcpu.setVendor(c.getCpu_vendor());
						bcpu.setType(c.getCpu_type());
						bcpu.setClockSpeed(c.getCpu_clockSpeed());

						List<CPUUsage> blc = new ArrayList<CPUUsage>(20);
						CPUUsage bcu = new CPUUsage();
						List<CPUsage> lc = huDAO.queryCPUList(c.getCpu_id());
						if (lc.size() != 0) {
							for (int k = 0; k < lc.size(); k++) {
								System.out.println("cpuUsgae__start"+k);
								bcu.setId(lc.get(k).getId());
								bcu.setCpuCatchSize(lc.get(k)
										.getCpu_cacheSize());
								bcu.setCollectTime(lc.get(k)
										.getCpu_collectTime());
								bcu.setCpuLoad(lc.get(k).getCpu_load());
								bcu.setIdle(lc.get(k).getCpu_idle());
								bcu.setIOWaitl(lc.get(k).getCpu_idle());
								bcu.setNice(lc.get(k).getCpu_IOWait());
								bcu.setSystem(lc.get(k).getCpu_system());
								bcu.setUsage(lc.get(k).getCpu_usage());
								bcu.setUser(lc.get(k).getCpu_user());
								blc.add(bcu);
							}
							bcpu.setCpuUsage(blc);
							System.out.println("cpuUsage--size__"+blc.size());
						}
					}
					
					smart.bean.host.Memory bmem = new smart.bean.host.Memory(
							m.getMem_id());
					if (m != null) {
						System.out.println("mem__start");
						bmem.setCrateTime(m.getCrateTime());
						bmem.setPhysicsTotal(m.getMem_physicsTotal());

						List<MemoryUsage> blmu = new ArrayList<MemoryUsage>(20);
						MemoryUsage bmu = new MemoryUsage();

						List<MemUsage> lm = huDAO.queryMemoryList(m.getMem_id());
						if (lm.size() != 0) {
							for (int k = 0; k < lm.size(); k++) {
								System.out.println("memUsgae__start"+k);
								bmu.setId(lm.get(k).getId());
								bmu.setCollectTime(lm.get(k)
										.getMem_collectTime());
								bmu.setMemLoad(lm.get(k).getMem_load());
								bmu.setPhysicsFree(lm.get(k)
										.getMem_physicsFree());
								bmu.setPhysicsUsed(lm.get(k)
										.getMem_physicsUsed());
								bmu.setSwapFree(lm.get(k).getMem_swapFree());
								bmu.setSwapUsed(lm.get(k).getMem_swapUsed());
								bmu.setSwapTotal(lm.get(k).getMem_swapTotal());

								blmu.add(bmu);
							}
							System.out.println("menu--size__"+blmu.size());
							bmem.setMemoryUsage(blmu);
						}
					}


					if (fs != null && fs.size() > 0) {
						System.out.println("fileSystem__start");
						List<smart.bean.host.FileSystem> blfs = new ArrayList<smart.bean.host.FileSystem>(
								5);
						for (int k = 0; k < fs.size(); k++) {
							System.out.println("fileSet__start"+k);
							smart.bean.host.FileSystem bfs = new smart.bean.host.FileSystem(
									fs.get(k).getFs_id());
							bfs.setName(fs.get(k).getFs_name());
							bfs.setSize(fs.get(k).getFs_size());
							bfs.setType(fs.get(k).getFs_type());

							List<FilesysUsage> blfu = new ArrayList<FilesysUsage>(
									10);
							FilesysUsage bfu = new FilesysUsage();

							List<FileSysUsage> lfu = new ArrayList<FileSysUsage>(
									10);
							if (lfu != null && lfu.size() > 0) {
								for (int x = 0; x < lfu.size(); x++) {
									System.out.println("FSusage__start"+x);
									bfu.setId(lfu.get(x).getId());
									bfu.setFree(lfu.get(x).getFs_free());
									bfu.setUsed(lfu.get(x).getFs_used());
									bfu.setUsage(lfu.get(x).getFs_usage());
									bfu.setCollectTime(lfu.get(x)
											.getFs_collectTime());

									blfu.add(bfu);

								}
								bfs.setFilesysUsage(blfu);
								System.out.println("fsUsagesize__"+blfu.size());
							}
							blfs.add(bfs);
						}

					}
					
					if (lni != null && lni.size() > 0) {
						System.out.println("netinterface__start");
						List<smart.bean.host.NetInterface> blni = new ArrayList<smart.bean.host.NetInterface>(
								10);
						for (int k = 0; k < lni.size(); k++) {
							System.out.println("interfaceSet__start"+k);
							smart.bean.host.NetInterface bni = new smart.bean.host.NetInterface(
									lni.get(k).getNi_id());
							bni.setIp(lni.get(k).getNi_ip());
							bni.setMac(lni.get(k).getNi_mac());
							bni.setName(lni.get(k).getNi_name());
							bni.setType(lni.get(k).getNi_type());
							bni.setDesip(lni.get(k).getNi_desip());

							List<smart.bean.host.NetInterfaceStatus> blnis = new ArrayList<smart.bean.host.NetInterfaceStatus>(
									10);
							smart.bean.host.NetInterfaceStatus bnis = new smart.bean.host.NetInterfaceStatus();
							List<NetInterfaceStatus> dlnis = huDAO
									.queryNIStatusList(lni.get(k).getNi_id());
							if (dlnis != null && dlnis.size() > 0) {
								for (int x = 0; x < dlnis.size(); x++) {
									System.out.println("interfaceStatus__start"+x);
									bnis.setId(dlnis.get(x).getId());
									bnis.setFlow(dlnis.get(x).getNi_flow());
									bnis.setThroughput(dlnis.get(x)
											.getNi_throughput());
									bnis.setStatus(dlnis.get(x).getNi_status());
									bnis.setCollectTime(dlnis.get(x)
											.getNi_collectTime());

									blnis.add(bnis);
								}
								System.out.println("IFstatus__"+blnis.size());
							}
							bni.setNetInterfaceStatus(blnis);
							blni.add(bni);
						}

					}

					smart.bean.host.Progress bp=new smart.bean.host.Progress(p.getPro_id());
					if(p!=null){
						System.out.println("progress__start");
						bp.setName(p.getPro_name());
						bp.setUser(p.getPro_username());
						bp.setTotal(p.getPro_total());
						
						List<ProgressUsage> blpu=new  ArrayList<ProgressUsage>(10);
						List<smart.dto.ProgressUsage> dlpu=new ArrayList<smart.dto.ProgressUsage>(10);
						dlpu=huDAO.queryProList(p.getPro_id());
						if(dlpu!=null&&dlpu.size()>0){
							for(int y=0;y<dlpu.size();y++){
								System.out.println("progressUsgae__start"+y);
								ProgressUsage bpu=new ProgressUsage();
								bpu.setId(dlpu.get(y).getId());
								bpu.setCpuUsed(dlpu.get(y).getCpuUsed());
								bpu.setMemUsed(dlpu.get(y).getMemUsed());
								bpu.setCollectTime(dlpu.get(y).getCollectTime());
								
								blpu.add(bpu);
							}
							System.out.println("proUsgae__"+blpu.size());
						}
						bp.setProgressUsage(blpu);
						System.out.println("progress__end"+bp.getName());
					}
					
					h.addChild(bcpu);
					h.addChild(bmem);
					h.addChild(bp);
					
				}
				DButil.close(conn);
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

	public static void main(String[] args) {
		CreateEntity.getInstance().createHost();
	}
}
