package smart.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import smart.entity.AbstractEntity;

/**
 * 文件系统指标信息
 */
public class FileSystem extends AbstractEntity {

	private static final long serialVersionUID = -8415710933972002573L;

	// 文件系统名
	private String name;
	// 文件系统大小
	private float size;
	// 文件系统类型
	private String type;
	
	/// 文件系统利用率（百分比）
	private Queue<FileSystemPerc> percQueue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public FileSystem(long id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 添加文件系统利用百分比数据。
	 * @param perc
	 */
	public void addPrec(FileSystemPerc perc) {
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
	 * 返回利用率百分比列表。
	 * @return
	 */
	public List<FileSystemPerc> getPercs() {
		ArrayList<FileSystemPerc> ret = new ArrayList<FileSystemPerc>(this.percQueue.size());
		synchronized (this.percQueue) {
			ret.addAll(this.percQueue);
		}
		return ret;
	}
}
