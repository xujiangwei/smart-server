package smart.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.entity.AbstractEntity;

/**
 * Web服务器
 */
public class WebServer extends AbstractEntity {

	private static final long serialVersionUID = 3674856846919710491L;

	private String name;
	/// Web应用队列
	private Queue<WebApplication> percQueue;
	// 最大记录数
	private volatile int maxPercs = 100;
	
	public WebServer(long id) {
		super(id);
		this.percQueue = new LinkedList<WebApplication>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 添加web应用数据。
	 * @param perc
	 */
	public void addPrec(WebApplication perc) {
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
	 * 返回web应用数据列表。
	 * @return
	 */
	public List<WebApplication> getPercs() {
		ArrayList<WebApplication> ret = new ArrayList<WebApplication>(this.percQueue.size());
		synchronized (this.percQueue) {
			ret.addAll(this.percQueue);
		}
		return ret;
	}
}
