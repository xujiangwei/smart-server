package smart.dto;

public class FileSysUsage {

	private int id;
	private float fs_used;
	private float fs_free;
	private float fs_usage;
	private long fs_collectTime;

	public FileSysUsage() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getFs_used() {
		return fs_used;
	}

	public void setFs_used(float fs_used) {
		this.fs_used = fs_used;
	}

	public float getFs_free() {
		return fs_free;
	}

	public void setFs_free(float fs_free) {
		this.fs_free = fs_free;
	}

	public long getFs_collectTime() {
		return fs_collectTime;
	}

	public void setFs_collectTime(long fs_collectTime) {
		this.fs_collectTime = fs_collectTime;
	}

	public float getFs_usage() {
		return fs_usage;
	}

	public void setFs_usage(float fs_usage) {
		this.fs_usage = fs_usage;
	}

}
