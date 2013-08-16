package smart.bean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.cellcloud.util.SlidingWindowExecutor;
import net.cellcloud.util.SlidingWindowTask;
import smart.dao.Dao;
import smart.dao.impl.DaoImpl;

/**
 * Bean 工厂。
 * 
 * @author Jiangwei Xu
 */
public final class BeanFactory {

	private Dao dao;
	private static final BeanFactory instance = new BeanFactory();

	SlidingWindowExecutor swe = SlidingWindowExecutor
			.newSlidingWindowThreadPool(20);

	private BeanFactory() {
	}

	public void testDaoImpl() {
		dao = new DaoImpl();
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
	public Host createHost(long id) {

		swe.execute(new SlidingWindowTask(swe) {
			@Override
			public void run() {

				List<Host> list = dao.findAll(Host.class);
				for (int i = 0; i < list.size(); i++) {
					System.out.println("创建host：" + list.get(i).getName());
					Host host = new Host(list.get(i).getId());
					host.setName(list.get(i).getName());
					
					
					CPU cpu= dao.findById(CPU.class, host.getId());
					Memory  memory=dao.findById(Memory.class, host.getId());
					FileSystem fileSystem=dao.findById(FileSystem.class, host.getId());
					Progress progress=dao.findById(Progress.class, host.getId());
					NetInterface netInterface=dao.findById(NetInterface.class, host.getId());
					
					if(cpu!=null){
						System.out.println("cpu__start");
						cpu.setParent(host);
					}
					
					if(memory!=null){
						System.out.println("memory__start");
						memory.setParent(host);
					}
					
					if(fileSystem!=null){
						System.out.println("fileSystem__start");
						fileSystem.setParent(host);
					}
					
					if(progress!=null){
						System.out.println("progress__start");
						progress.setParent(host);
					}
					
					if(netInterface!=null){
						System.out.println("netInterface__start");
						netInterface.setParent(host);
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

		return new Host(id);
	}
	
	
	public Host create(){
		Host h1=dao.findById(Host.class, 112);
		return h1;
	}
	
	
	public static void main(String[] args) {
		BeanFactory b=new BeanFactory();
		b.create();
	}
}
