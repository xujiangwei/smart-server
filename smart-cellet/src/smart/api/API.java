package smart.api;

public final class API {

	// 登录
	public final static String LOGIN = "/monitoring-system/user/login";

	// 注销
	public static final String LOGOUT = "/monitoring-system/out/logout";

	// 首页关注
	public static final String ATTENTION = "/monitoring-system/attention/reqAttention";
	public static final String CANCELATTENTION = "/monitoring-system/cancel/cancelAttention";
	public static final String DELETEATTENTION = "/monitoring-system/delAttention/deleteAttention";
	public static final String ADDATTENTION = "/monitoring-system/addAttention/addAttention";
	public static final String ATTENTIONLIST = "/monitoring-system/attentionList/reqAttentionList";

	// 告警
	public static final String ALARMDEAL = "/monitoring-system/deal/alarmDeal";
	public static final String ALARMDETAIL = "/monitoring-system/baseInfo/reqAlarmBaseInfo";
	public static final String ALARMFORWARD = "/monitoring-system/forward/alarmForward";
	public static final String DEALINFO = "/monitoring-system/dealInfo/addDealInfo";
	public static final String ALARMLIST = "/monitoring-system/alarmList/reqAlarmList";
	public static final String ALARMCOVERAGE = "/monitoring-system/alarmCoverage/reqAlarmCoverage";
	public static final String ALARMOPINFO = "/monitoring-system/alarmOpInfo/reqAlarmOpInfo";
	public static final String ALARMEXPERIENCE = "/monitoring-system/alarmExperience/reqAlarmExperience";
	public static final String ALARMLIFECYCLE = "/monitoring-system/alarmLifeCycle/reqAlarmLifeCycle";
	public static final String SUPPRESSEDALARM = "/monitoring-system/suppressed/reqSuppressedAlarm";
	public static final String ALARMDISTRIBUTION = "/monitoring-system/distribution/reqAlarmDistribution";

	// 消息
	public final static String MESSAGELIST = "/monitoring-system/messages/requestMessages";
	public final static String MESSAGEDETAIL = "/monitoring-system/messageDetail/requestMessageDetail";
	public final static String MESSAGECONTACTS = "/monitoring-system/contacts/requestMessageContacts";
	public final static String MESSAGESEND = "/monitoring-system/messageSend/messageSend";
	public final static String MESSAGEDELETE = "/monitoring-system/messageDeal2/messageDealDelete";
	public final static String MESSAGEMOVE = "/monitoring-system/messageDeal3/messageDealMove";
	public final static String MESSAGEMARK = "/monitoring-system/messageDeal4/messageDealMark";
	public final static String MESSAGETOPINFO = "/monitoring-system/messageTopInfo/messageTopInfo";
	public final static String MESSAGETAGADD = "/monitoring-system/messageTag1/messageTagAdd";
	public final static String MESSAGETAGDELETE = "/monitoring-system/messageTag2/messageTagDelete";
	public final static String MESSAGETAGMODIFY = "/monitoring-system/messageTag3/messageTagModify";
	public final static String MESSAGETAGDISPLAY = "/monitoring-system/messageTag4/messageTagDisplay";

	public final static String MESSAGECUSTOMTAGS = "/monitoring-system/customTags/requestMessageCustomTags";
	public final static String MESSAGEFILEUPLOAD = "/monitoring-system/fileUpload/messageFileUpload";

	// 设备资源
	public final static String HOST = "/monitoring-system/moHosts/requestMoHosts";
	public final static String SWITCH = "/monitoring-system/moSwitchs/requestMoSwitchs";
	public final static String ROUTER = "/monitoring-system/moRouters/requestMoRouters";

	public final static String DEVICEBASIC = "/monitoring-system/basic/requestMoBasic";
	public final static String DEVICEPERFORMANCE = "/monitoring-system/performance/requestPerformance";
	public final static String DEVICEHEALTHSTATUS = "/monitoring-system/health/requestMoHealthStatus";

	public final static String DEVICEINFO = "/monitoring-system/moInfo/requestMoInfo";
	public final static String DELETEDEVICE = "/monitoring-system/moDel/moDelete";
	public final static String DEVICEALARM = "/monitoring-system/moAlarms/requestMoAlarms";
	public final static String DEVICEMONITORSTATE = "/monitoring-system/monitorStatus/moChangeMonitorStatus";
	public final static String SENDSNAPSHOT = "/monitoring-system/snapshot/moSendSnapshot";
	public final static String DEVICETOPO = "/monitoring-system/moTopos/requestMoTopos";


	private API() {
	}

}
