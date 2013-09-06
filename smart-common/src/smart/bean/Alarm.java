package smart.bean;

import smart.entity.AbstractEntity;

/**
 * 告警详情
 */
public class Alarm extends AbstractEntity {

	private static final long serialVersionUID = -2693847734573986143L;

	private long moId;
	private String moName;
	private int severity;
	private String moIp;
	private String almCause;
	private String almStatus;
	private long lastTime;
	private String moType;
	private String location;
	private String detail;
	private long occurTime;
	private String trend;
	private int count;
	private int upgradeCount;
	private String confirmUser;
	private long confirmTime;
	private String delUser;
	private long delTime;
	
	public Alarm(long almId) {
		super(almId);
	}

	public long getMoId() {
		return moId;
	}

	public void setMoId(long moId) {
		this.moId = moId;
	}

	public String getMoName() {
		return moName;
	}

	public void setMoName(String moName) {
		this.moName = moName;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public String getMoIp() {
		return moIp;
	}

	public void setMoIp(String moIp) {
		this.moIp = moIp;
	}

	public String getAlmCause() {
		return almCause;
	}

	public void setAlmCause(String almCause) {
		this.almCause = almCause;
	}

	public String getAlmStatus() {
		return almStatus;
	}

	public void setAlmStatus(String almStatus) {
		this.almStatus = almStatus;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public String getMoType() {
		return moType;
	}

	public void setMoType(String moType) {
		this.moType = moType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public long getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(long occurTime) {
		this.occurTime = occurTime;
	}

	public String getTrend() {
		return trend;
	}

	public void setTrend(String trend) {
		this.trend = trend;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getUpgradeCount() {
		return upgradeCount;
	}

	public void setUpgradeCount(int upgradeCount) {
		this.upgradeCount = upgradeCount;
	}

	public String getConfirmUser() {
		return confirmUser;
	}

	public void setConfirmUser(String confirmUser) {
		this.confirmUser = confirmUser;
	}

	public long getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(long confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getDelUser() {
		return delUser;
	}

	public void setDelUser(String delUser) {
		this.delUser = delUser;
	}

	public long getDelTime() {
		return delTime;
	}

	public void setDelTime(long delTime) {
		this.delTime = delTime;
	}

}
