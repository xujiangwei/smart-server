package smart.bean.host;

import java.util.List;

import smart.entity.AbstractEntity;

/**
 * 进程信息
 */
public class Progress extends AbstractEntity {

	private static final long serialVersionUID = -1440977095390940742L;

	private String name;
	private int total;
	private String user;
	private List<ProgressUsage> progressUsage;

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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<ProgressUsage> getProgressUsage() {
		return progressUsage;
	}

	public void setProgressUsage(List<ProgressUsage> progressUsage) {
		this.progressUsage = progressUsage;
	}

}
