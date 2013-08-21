package smart.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.entity.AbstractEntity;

/**
 * 主机
 */
public class Host extends AbstractEntity {

	private static final long serialVersionUID = 4733691548460944139L;

	// 主机名
	private String name;
	
	/// 进程监测队列
	private Queue<ProgressDetection> proQueue;
	/// 文件系统监测队列
	private Queue<FileSystemDetection> fileSystemQueue;
	/// 网络接口监测队列
	private Queue<NetInterfaceDetection> niQueue;
	/// 健康度队列
	private Queue<HealthDegree> healthQueue;
	/// Ping延迟队列
	private Queue<PingDelay> pingQueue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public Host(long id) {
		super(id);
		this.proQueue = new LinkedList<ProgressDetection>();
		this.healthQueue = new LinkedList<HealthDegree>();
		this.pingQueue = new LinkedList<PingDelay>();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 添加进程使用数据。
	 * @param perc
	 */
	public void addProPrec(ProgressDetection perc) {
		synchronized (this.proQueue) {
			this.proQueue.add(perc);
		}

		if (this.proQueue.size() > this.maxPercs) {
			synchronized (this.proQueue) {
				this.proQueue.poll();
			}
		}
	}

	/**
	 * 返回进程数据列表。
	 * @return
	 */
	public List<ProgressDetection> getProPercs() {
		ArrayList<ProgressDetection> ret = new ArrayList<ProgressDetection>(this.proQueue.size());
		synchronized (this.proQueue) {
			ret.addAll(this.proQueue);
		}
		return ret;
	}
	
	/**
	 * 添加文件系统监测数据。
	 * @param perc
	 */
	public void addFileSystemPrec(FileSystemDetection perc) {
		synchronized (this.fileSystemQueue) {
			this.fileSystemQueue.add(perc);
		}

		if (this.fileSystemQueue.size() > this.maxPercs) {
			synchronized (this.fileSystemQueue) {
				this.fileSystemQueue.poll();
			}
		}
	}

	/**
	 * 返回文件系统监测数据列表。
	 * @return
	 */
	public List<FileSystemDetection> getFileSystemPercs() {
		ArrayList<FileSystemDetection> ret = new ArrayList<FileSystemDetection>(this.fileSystemQueue.size());
		synchronized (this.fileSystemQueue) {
			ret.addAll(this.fileSystemQueue);
		}
		return ret;
	}
	
	/**
	 * 添加网络接口监测数据。
	 * @param perc
	 */
	public void addNiPrec(NetInterfaceDetection niDetection) {
		synchronized (this.niQueue) {
			this.niQueue.add(niDetection);
		}

		if (this.niQueue.size() > this.maxPercs) {
			synchronized (this.niQueue) {
				this.niQueue.poll();
			}
		}
	}

	/**
	 * 返回网络接口数据列表。
	 * @return
	 */
	public List<NetInterfaceDetection> getNiPercs() {
		ArrayList<NetInterfaceDetection> ret = new ArrayList<NetInterfaceDetection>(this.niQueue.size());
		synchronized (this.niQueue) {
			ret.addAll(this.niQueue);
		}
		return ret;
	}
	
	/**
	 * 添加 健康度数据。
	 * @param 
	 */
	public void addHealthPrec(HealthDegree health) {
		synchronized (this.healthQueue) {
			this.healthQueue.add(health);
		}

		if (this.healthQueue.size() > this.maxPercs) {
			synchronized (this.healthQueue) {
				this.healthQueue.poll();
			}
		}
	}

	/**
	 * 返回健康度数据列表。
	 * @return
	 */
	public List<HealthDegree> getHealthPercs() {
		ArrayList<HealthDegree> ret = new ArrayList<HealthDegree>(this.healthQueue.size());
		synchronized (this.healthQueue) {
			ret.addAll(this.healthQueue);
		}
		return ret;
	}
	
	/**
	 * 添加ping延迟数据。
	 * @param 
	 */
	public void addPingPrec(PingDelay delay) {
		synchronized (this.pingQueue) {
			this.pingQueue.add(delay);
		}

		if (this.pingQueue.size() > this.maxPercs) {
			synchronized (this.pingQueue) {
				this.pingQueue.poll();
			}
		}
	}

	/**
	 * 返回ping延迟数据列表。
	 * @return
	 */
	public List<PingDelay> getPingPercs() {
		ArrayList<PingDelay> ret = new ArrayList<PingDelay>(this.pingQueue.size());
		synchronized (this.pingQueue) {
			ret.addAll(this.pingQueue);
		}
		return ret;
	}
}
