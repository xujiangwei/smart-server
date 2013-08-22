package smart.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.entity.AbstractEntity;

/**
 * 进程信息
 */
public class Progress extends AbstractEntity {

	private static final long serialVersionUID = -1440977095390940742L;

	// 进程名
	private String name;
	// 进程用户名
	private String username;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 添加进程监测数据。
	 * @param perc
	 */
	public void addPrec(ProgressDetection perc) {
		synchronized (this.percQueue) {
			this.percQueue.add(perc);
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
	public List<ProgressDetection> getPercs() {
		ArrayList<ProgressDetection> ret = new ArrayList<ProgressDetection>(this.percQueue.size());
		synchronized (this.percQueue) {
			ret.addAll(this.percQueue);
		}
		return ret;
	}
}
