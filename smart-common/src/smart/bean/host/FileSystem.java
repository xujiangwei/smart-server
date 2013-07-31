package smart.bean.host;

import java.util.List;

import smart.entity.AbstractEntity;

/**
 * 文件系统信息
 */
public class FileSystem extends AbstractEntity {

	private static final long serialVersionUID = -8415710933972002573L;

	private String name;
	private float size;
	private String type;
	private List<FilesysUsage> filesysUsage;

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

	public List<FilesysUsage> getFilesysUsage() {
		return filesysUsage;
	}

	public void setFilesysUsage(List<FilesysUsage> filesysUsage) {
		this.filesysUsage = filesysUsage;
	}

}
