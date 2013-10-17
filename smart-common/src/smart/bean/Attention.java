package smart.bean;

import smart.entity.AbstractEntity;

public class Attention extends AbstractEntity{
	
	private static final long serialVersionUID = -8919160357827986944L;
	private long moId;
	private int priority;
	private int statusId;
	private long userId;

	public Attention(long id) {
		super(id);
	}

	public long getMoId() {
		return moId;
	}

	public void setMoId(long moId) {
		this.moId = moId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
