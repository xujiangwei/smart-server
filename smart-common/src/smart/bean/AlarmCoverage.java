package smart.bean;

import smart.entity.AbstractEntity;

/**
 * 告警影响范围
 */
public class AlarmCoverage extends AbstractEntity {

	private static final long serialVersionUID = -4757090295694739794L;

	private String relatedMoName;
	private String relatedMoIp;
	private int level;
	private long moId;
	
	public AlarmCoverage(long id) {
		super(id);
	}

	public String getRelatedMoName() {
		return relatedMoName;
	}

	public void setRelatedMoName(String relatedMoName) {
		this.relatedMoName = relatedMoName;
	}

	public String getRelatedMoIp() {
		return relatedMoIp;
	}

	public void setRelatedMoIp(String relatedMoIp) {
		this.relatedMoIp = relatedMoIp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getMoId() {
		return moId;
	}

	public void setMoId(long moId) {
		this.moId = moId;
	}

}
