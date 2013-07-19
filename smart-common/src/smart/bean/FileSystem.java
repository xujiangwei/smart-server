package smart.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import smart.entity.AbstractEntity;

/**
 * 文件系统信息
 */
public class FileSystem extends AbstractEntity {

	private static final long serialVersionUID = -8415710933972002573L;

	private List<Map<String, FileSystem>> l = new ArrayList<Map<String, FileSystem>>(5);
	private String name;
	private float size;
	private String type;
	private float used;
	private float free;
	private double usage;
	private long collectTime;
	
	public FileSystem(String id) {
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
	public float getUsed() {
		return used;
	}
	public void setUsed(float used) {
		this.used = used;
	}
	public float getFree() {
		return free;
	}
	public void setFree(float free) {
		this.free = free;
	}
	public double getUsage() {
		return usage;
	}
	public void setUsage(double usage) {
		this.usage = usage;
	}

	public long getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(long collectTime) {
		this.collectTime = collectTime;
	}

	public List<Map<String, FileSystem>> getL() {
		return l;
	}

	public void setL(List<Map<String, FileSystem>> l) {
		this.l = l;
	}
}
