package smart.api;

public final class API {

	// 登录
	public final static String LOGIN = "http://10.10.152.26:8080/monitoring-system/user/login";

	// 注销
	public static final String LOGOUT = "http://10.10.152.26:8080/monitoring-system/out/logout";

	// 首页关注
	public static final String ATTENTION = "http://10.10.152.26:8080/monitoring-system/attention/reqAttention";
	public static final String CANCELATTENTION = "http://10.10.152.26:8080/monitoring-system/cancel/cancelAttention";
	public static final String DELETEATTENTION = "http://10.10.152.26:8080/monitoring-system/delAttention/deleteAttention";
	public static final String ADDATTENTION = "http://10.10.152.26:8080/monitoring-system/addAttention/addAttention";
	public static final String ATTENTIONLIST = "http://10.10.152.26:8080/monitoring-system/attentionList/reqAttentionList";

	// 告警
	public static final String ALARMDEAL = "restws/alm/external";
	public static final String ALARMDETAIL = "restws/alm/external/list/998/9980000000000000";
	public static final String ALARMFORWARD = "http://10.10.152.26:8080/monitoring-system/forward/alarmForward";
	public static final String DEALINFO = "http://10.10.152.26:8080/monitoring-system/dealInfo/addDealInfo";
	public static final String ALARMLIST = "restws/alm/external/list/998/9980000000000000/0";
	public static final String ALARMCOVERAGE = "http://10.10.152.26:8080/monitoring-system/alarmCoverage/reqAlarmCoverage";
	public static final String ALARMOPINFO = "http://10.10.152.26:8080/monitoring-system/alarmOpInfo/reqAlarmOpInfo";
	public static final String ALARMEXPERIENCE = "http://10.10.152.26:8080/monitoring-system/alarmExperience/reqAlarmExperience";
	public static final String ALARMLIFECYCLE = "http://10.10.152.26:8080/monitoring-system/alarmLifeCycle/reqAlarmLifeCycle";
	public static final String SUPPRESSEDALARM = "http://10.10.152.26:8080/monitoring-system/suppressed/reqSuppressedAlarm";
	public static final String ALARMDISTRIBUTION = "http://10.10.152.26:8080/monitoring-system/distribution/reqAlarmDistribution";

	// 消息
	public final static String MESSAGELIST = "http://10.10.152.26:8080/itsm/incidentm/listTodos.html?username=admin/fvsd-branch/noticem/listUnread.html?username=admin";
	public final static String MESSAGEDETAIL = "http://10.10.152.26:8080/monitoring-system/messageDetail/requestMessageDetail";
	public final static String MESSAGECONTACTS = "http://10.10.152.26:8080/monitoring-system/contacts/requestMessageContacts";
	public final static String MESSAGESEND = "http://10.10.152.26:8080/monitoring-system/messageSend/messageSend";
	public final static String MESSAGEDELETE = "http://10.10.152.26:8080/monitoring-system/messageDeal2/messageDealDelete";
	public final static String MESSAGEMOVE = "http://10.10.152.26:8080/monitoring-system/messageDeal3/messageDealMove";
	public final static String MESSAGEMARK = "http://10.10.152.26:8080/monitoring-system/messageDeal4/messageDealMark";
	public final static String MESSAGETOPINFO = "http://10.10.152.26:8080/monitoring-system/messageTopInfo/messageTopInfo";
	public final static String MESSAGETAGADD = "http://10.10.152.26:8080/monitoring-system/messageTag1/messageTagAdd";
	public final static String MESSAGETAGDELETE = "http://10.10.152.26:8080/monitoring-system/messageTag2/messageTagDelete";
	public final static String MESSAGETAGMODIFY = "http://10.10.152.26:8080/monitoring-system/messageTag3/messageTagModify";
	public final static String MESSAGETAGDISPLAY = "http://10.10.152.26:8080/monitoring-system/messageTag4/messageTagDisplay";

	public final static String MESSAGECUSTOMTAGS = "http://10.10.152.26:8080/monitoring-system/customTags/requestMessageCustomTags";
	public final static String MESSAGEFILEUPLOAD = "http://10.10.152.26:8080/monitoring-system/fileUpload/messageFileUpload";

	// 设备资源
	public final static String EQUIPMENTLIST = "restws/model/core/mo/list/998/9980000000000000";
	public final static String DATABASE = "restws/data/perf/mo/998";
	public final static String HOST = "/monitoring-system/equipmentHosts/requestEquipmentHosts";
	public final static String NETEQUIPMENT = "restws/data/perf/mo/998";

	public final static String CPUUSAGE="restws/data/perf/mo/998";
	public final static String MEMORYUSAGE="restws/data/perf/mo/998";
	public final static String FILESYSTEMUSAGE="restws/data/perf/mo/998";
	
	public final static String EQUIPMENTBASIC = "restws/data/perf/mo/998";
	public final static String EQUIPMENTPERFORMANCE = "http://10.10.152.26:8080/monitoring-system/performance/requestEquipmentPerformance";
	public final static String EQUIPMENTHEALTHSTATUS = "http://10.10.152.26:8080/monitoring-system/health/requestEquipmentHealthStatus";

	public final static String DELETEEQUIPMENT = "http://10.10.152.26:8080/monitoring-system/equipmentDel/equipmentDelete";
	public final static String EQUIPMENTALARM = "http://10.10.152.26:8080/monitoring-system/equipmentAlarms/requestEquipmentAlarms";
	public final static String EQUIPMENTMONITORSTATE = "http://10.10.152.26:8080/monitoring-system/monitorStatus/equipmentChangeMonitorStatus";
	public final static String SENDSNAPSHOT = "http://10.10.152.26:8080/monitoring-system/snapshot/equipmentSendSnapshot";
	public final static String EQUIPMENTTOPO = "http://10.10.152.26:8080/monitoring-system/equipmentTopos/requestEquipmentTopos";

	//待办
	public final static String INCIDENTLIST = "incidentm/listTodos.html?username=admin";
	public final static String INCIDENTDETAIL = "incidentm/loadById.html?username=admin";
	
	//资产
	public final static String CILIST = "sacmm/listEquipments.html?username=admin";
	public final static String CIDETAIL = "sacmm/loadEquipmentById.html?username=admin";
	
	private API() {
	}

}
