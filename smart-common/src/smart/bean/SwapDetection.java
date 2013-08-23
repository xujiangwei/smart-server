package smart.bean;

import java.io.Serializable;

/**
 * system swap
 */
public class SwapDetection implements Serializable {

	private static final long serialVersionUID = -8997989867413593603L;

	// swap数据采集时间
	private long timestamp;
	
	private long free;
	private long pageIn;
	private long pageOut;
	private long total;
	private long used;
	
	public SwapDetection(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setFree(long free) {
		this.free = free;
	}

	public void setPageIn(long pageIn) {
		this.pageIn = pageIn;
	}

	public void setPageOut(long pageOut) {
		this.pageOut = pageOut;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public long getFree() {
		return this.free;
	}

	public long getPageIn() {
		return this.pageIn;
	}

	public long getPageOut() {
		return this.pageOut;
	}

	public long getTotal() {
		return this.total;
	}

	public long getUsed() {
		return this.used;
	}

}
