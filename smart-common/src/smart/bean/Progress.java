package smart.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import smart.entity.AbstractEntity;

/**
 * 进程信息
 */
public class Progress extends AbstractEntity {

	private static final long serialVersionUID = -1440977095390940742L;

	private List<Map<String, Progress>> list = new ArrayList<Map<String, Progress>>(10);
	private String name;
	private int total;
	private String user;
	private int cpuUsed;
	private int memUsed;
	private long collectTime;
	
	public Progress(String id) {
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

	public int getCpuUsed() {
		return cpuUsed;
	}

	public void setCpuUsed(int cpuUsed) {
		this.cpuUsed = cpuUsed;
	}

	public int getMemUsed() {
		return memUsed;
	}

	public void setMemUsed(int memUsed) {
		this.memUsed = memUsed;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public long getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(long collectTime) {
		this.collectTime = collectTime;
	}

	public List<Map<String, Progress>> getList() {
		return list;
	}

	public void setList(List<Map<String, Progress>> list) {
		this.list = list;
	}

}
