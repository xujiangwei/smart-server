package smart.bean;

import java.io.Serializable;

/**
 * 文件系统检测
 */
public class FileSystemDetection implements Serializable {

	private static final long serialVersionUID = -8537691525565202580L;
	
	// 数据采集时间
	private long timestamp;
	
	// 文件系统名
	private String name;
	// 文件系统大小
	private float size;
	// 文件系统类型
	private String type;
	// 已用大小
	private double used;
	// 剩余大小
	private double free;
	// 文件系统利用率
	private double usage;
	
	public FileSystemDetection(long timestamp){
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}
	
	public String getName() {
		return name;
	}

	public float getSize() {
		return size;
	}

	public String getType() {
		return type;
	}

	public double getUsed() {
		return this.used;
	}
	
	public double getFree() {
		return this.free;
	}
	
	public double getUsage() {
		this.usage = used/(used+free);
		return usage;
	}

}
