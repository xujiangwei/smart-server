package smart.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import smart.entity.AbstractEntity;

/**
 * 文件系统
 */
public class FileSystem extends AbstractEntity {

	private static final long serialVersionUID = -8415710933972002573L;

	// 文件系统名
	private String devName;
	// directory name
	private String dirName;
	// 文件系统大小
	private float size;
	// 类型
	private int type;
	// 类型名
	private  String typeName;
	// 文件系统类型
	private String sysTypeName;
	
	/// 文件系统监测数据队列
	private Queue<FileSystemDetection> queue;
	// 最大记录数
	private volatile int maxPercs = 100;

	public FileSystem(long id) {
		super(id);
		this.queue = new LinkedList<FileSystemDetection>();
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSysTypeName() {
		return sysTypeName;
	}

	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}

	/**
	 * 添加文件系统监测数据。
	 * @param fsDetection
	 */
	public void addFSDetection(FileSystemDetection fsDetection) {
		synchronized (this.queue) {
			this.queue.add(fsDetection);
		}

		if (this.queue.size() > this.maxPercs) {
			synchronized (this.queue) {
				this.queue.poll();
			}
		}
	}

	/**
	 * 返回文件系统监测数据列表。
	 * @return
	 */
	public List<FileSystemDetection> getFSDetection() {
		ArrayList<FileSystemDetection> ret = new ArrayList<FileSystemDetection>(this.queue.size());
		synchronized (this.queue) {
			ret.addAll(this.queue);
		}
		return ret;
	}
}
