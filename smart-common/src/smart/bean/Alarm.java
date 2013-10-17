package smart.bean;

import smart.entity.AbstractEntity;

/**
 * 告警详情
 */
public class Alarm extends AbstractEntity {

	private static final long serialVersionUID = -2693847734573986143L;

	private long moId;
	private long rootMoId;
	private long parentMoId;
	private String typecode;
	private String almCause;
	private String isSuppressed;
	private int severity;
	private String extraInfo;
	private String almStatus;
	private String trend;
	private long occurTime;
	private long lastTime;
	private int count;
	private String detail;
	private String originalInfo;
	private long confirmTime;
	private long confirmUserId;
	private String confirmUser;
	private String moIp;
	private String moName;
	private String causeAlias;
	private String location;
	
	public Alarm(long almId) {
		super(almId);
	}

	public long getMoId() {
		return moId;
	}

	public void setMoId(long moId) {
		this.moId = moId;
	}

	public long getRootMoId() {
		return rootMoId;
	}

	public void setRootMoId(long rootMoId) {
		this.rootMoId = rootMoId;
	}

	public long getParentMoId() {
		return parentMoId;
	}

	public void setParentMoId(long parentMoId) {
		this.parentMoId = parentMoId;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getAlmCause() {
		return almCause;
	}

	public void setAlmCause(String almCause) {
		this.almCause = almCause;
	}

	public String getIsSuppressed() {
		return isSuppressed;
	}

	public void setIsSuppressed(String isSuppressed) {
		this.isSuppressed = isSuppressed;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String getAlmStatus() {
		return almStatus;
	}

	public void setAlmStatus(String almStatus) {
		this.almStatus = almStatus;
	}

	public String getTrend() {
		return trend;
	}

	public void setTrend(String trend) {
		this.trend = trend;
	}

	public long getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(long occurTime) {
		this.occurTime = occurTime;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getOriginalInfo() {
		return originalInfo;
	}

	public void setOriginalInfo(String originalInfo) {
		this.originalInfo = originalInfo;
	}

	public long getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(long confirmTime) {
		this.confirmTime = confirmTime;
	}

	public long getConfirmUserId() {
		return confirmUserId;
	}

	public void setConfirmUserId(long confirmUserId) {
		this.confirmUserId = confirmUserId;
	}

	public String getConfirmUser() {
		return confirmUser;
	}

	public void setConfirmUser(String confirmUser) {
		this.confirmUser = confirmUser;
	}

	public String getMoIp() {
		return moIp;
	}

	public void setMoIp(String moIp) {
		this.moIp = moIp;
	}

	public String getMoName() {
		return moName;
	}

	public void setMoName(String moName) {
		this.moName = moName;
	}

	public String getCauseAlias() {
		return causeAlias;
	}

	public void setCauseAlias(String causeAlias) {
		this.causeAlias = causeAlias;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
