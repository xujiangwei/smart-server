package smart.bean.switchs;

import smart.bean.Attention;
import smart.bean.Health;
import smart.bean.Ping;
import smart.bean.Protocol;
import smart.entity.AbstractEntity;

/*
 * 交换机基本信息类
 */
public class Switch extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String switch_id;
	private String switch_name;
	private String switch_ip;
	private String switch_type;
	private String switch_vender;
	private String switch_sort;
	private long switch_createTime;
	private String switch_maintainer;
	private boolean swc_isMonitoring;
	private Attention attention;
	private Health health;
	private Ping ping;
	private Protocol protocol;

	public Switch(String id) {
		super(id);
	}

	public String getSwitch_id() {
		return switch_id;
	}

	public void setSwitch_id(String switch_id) {
		this.switch_id = switch_id;
	}

	public String getSwitch_name() {
		return switch_name;
	}

	public void setSwitch_name(String switch_name) {
		this.switch_name = switch_name;
	}

	public String getSwitch_ip() {
		return switch_ip;
	}

	public void setSwitch_ip(String switch_ip) {
		this.switch_ip = switch_ip;
	}

	public String getSwitch_type() {
		return switch_type;
	}

	public void setSwitch_type(String switch_type) {
		this.switch_type = switch_type;
	}

	public String getSwitch_vender() {
		return switch_vender;
	}

	public void setSwitch_vender(String switch_vender) {
		this.switch_vender = switch_vender;
	}

	public String getSwitch_sort() {
		return switch_sort;
	}

	public void setSwitch_sort(String switch_sort) {
		this.switch_sort = switch_sort;
	}

	public long getSwitch_createTime() {
		return switch_createTime;
	}

	public void setSwitch_createTime(long switch_createTime) {
		this.switch_createTime = switch_createTime;
	}

	public String getSwitch_maintainer() {
		return switch_maintainer;
	}

	public void setSwitch_maintainer(String switch_maintainer) {
		this.switch_maintainer = switch_maintainer;
	}

	public boolean isSwc_isMonitoring() {
		return swc_isMonitoring;
	}

	public void setSwc_isMonitoring(boolean swc_isMonitoring) {
		this.swc_isMonitoring = swc_isMonitoring;
	}

	public Attention getAttention() {
		return attention;
	}

	public void setAttention(Attention attention) {
		this.attention = attention;
	}

	public Health getHealth() {
		return health;
	}

	public void setHealth(Health health) {
		this.health = health;
	}

	public Ping getPing() {
		return ping;
	}

	public void setPing(Ping ping) {
		this.ping = ping;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

}
