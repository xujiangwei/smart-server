package smart.bean;

import java.util.ArrayList;
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
	// 总进程数
	private int total;
	// 进程用户名
	private String username;

	/// 进程利用率
	private Queue<ProgressPerc> percQueue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public Progress(long id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 添加进程使用数据。
	 * @param perc
	 */
	public void addPrec(ProgressPerc perc) {
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
	 * 返回进程使用列表。
	 * @return
	 */
	public List<ProgressPerc> getPercs() {
		ArrayList<ProgressPerc> ret = new ArrayList<ProgressPerc>(this.percQueue.size());
		synchronized (this.percQueue) {
			ret.addAll(this.percQueue);
		}
		return ret;
	}
}
