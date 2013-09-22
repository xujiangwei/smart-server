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
	public final static String MESSAGELIST = "noticem/listUnread.html?username=admin";
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
	public final static String HOST = "/monitoring-system/equipmentHosts/requestEquipmentHosts";
	public final static String NETEQUIPMENT = "/monitoring-system/netEquipment/requestNetEquipment";

	public final static String EQUIPMENTBASIC = "/monitoring-system/basic/requestEquipmentBasic";
	public final static String EQUIPMENTPERFORMANCE = "/monitoring-system/performance/requestEquipmentPerformance";
	public final static String EQUIPMENTHEALTHSTATUS = "/monitoring-system/health/requestEquipmentHealthStatus";

	public final static String DEVICEINFO = "/monitoring-system/moInfo/requestMoInfo";
	public final static String DELETEEQUIPMENT = "/monitoring-system/equipmentDel/equipmentDelete";
	public final static String EQUIPMENTALARM = "/monitoring-system/equipmentAlarms/requestEquipmentAlarms";
	public final static String EQUIPMENTMONITORSTATE = "/monitoring-system/monitorStatus/equipmentChangeMonitorStatus";
	public final static String SENDSNAPSHOT = "/monitoring-system/snapshot/equipmentSendSnapshot";
	public final static String EQUIPMENTTOPO = "/monitoring-system/equipmentTopos/requestEquipmentTopos";

	//待办
	public final static String INCIDENTLIST = "incidentm/listTodos.html?username=admin";
	public final static String INCIDENTDETAIL = "incidentm/loadById.html?username=admin";
	
	//资产
//	http://10.10.152.84:8080/fvsd-branch/sacmm/listEquipments.html?username=admin&currentIndex=1&pageSize=15
//	http://localhost:8080/fvsd-branch/sacmm/loadEquipmentById.html?username=admin&ciId=`
	public final static String CILIST = "sacmm/listEquipments.html?username=admin";
	public final static String CIDETAIL = "sacmm/loadEquipmentById.html?username=admin";
	
	private API() {
	}

}
