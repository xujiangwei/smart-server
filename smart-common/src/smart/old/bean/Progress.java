package smart.old.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.old.entity.AbstractEntity;

/**
 * 进程信息
 */
public class Progress extends AbstractEntity {

	private static final long serialVersionUID = -1440977095390940742L;

	// 进程名
	private String name;
	// 进程用户名
	private String user;
	// 进程开始时间
	private String startTime;

	/// 进程监测队列
	private Queue<ProgressDetection> percQueue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public Progress(long id) {
		super(id);
		this.percQueue = new LinkedList<ProgressDetection>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 添加进程监测数据。
	 * @param detection
	 */
	public void addDetection(ProgressDetection detection) {
		synchronized (this.percQueue) {
			this.percQueue.add(detection);
		}

		if (this.percQueue.size() > this.maxPercs) {
			synchronized (this.percQueue) {
				this.percQueue.poll();
			}
		}
	}

	/**
	 * 返回进程数据列表。
	 * @return
	 */
	public List<ProgressDetection> getDetections() {
		ArrayList<ProgressDetection> ret = new ArrayList<ProgressDetection>(this.percQueue.size());
		synchronized (this.percQueue) {
			ret.addAll(this.percQueue);
		}
		return ret;
	}
}
