/*
 -------------------------------------------------------------------------------
 This source file is part of Mast Engine.
 -------------------------------------------------------------------------------
 */

package smart.mast.action;

/**
 * 系统内可用动作定义。
 * 
 * @author Jiangwei Xu
 */
public final class Action {

	// 服务器连接检测
	public final static String CONNECTIONCHECK = "connectionCheck";

	public final static String NODEDETECTION = "nodeDetection";
	// 登录
	public final static String LOGIN = "login";
	// 注销
	public final static String LOGOUT = "logout";

	// 当前时间
	public static final String CURRENTTIME = "requestCurrentTime";

	// 首页关注
	public static final String CANCELATTENTION = "cancelAttention";
	public static final String DELETEATTENTION = "deleteAttention";
	public static final String ADDATTENTION = "addAttention";
	public static final String ATTENTIONLIST = "requestAttentionList";

	// 消息
	public final static String MESSAGELIST = "requestMessages";
	public final static String MESSAGEDETAIL = "requestMsgDetail";
	public final static String MESSAGEDELETE = "messageDelete";
	public final static String MESSAGESEND = "messageSend";
	public final static String CONTACTS = "requestContacts";

	public final static String MESSAGEMOVE = "messageDealMove";
	public static final String MESSAGEMARK = "messageDealMark";

	public final static String MESSAGETOPINFO = "messageTopInfo";
	public final static String MESSAGETAGADD = "messageTagAdd";
	public final static String MESSAGETAGDELETE = "messageTagDelete";
	public final static String MESSAGETAGMODIFY = "messageTagModify";
	public final static String MESSAGETAGDISPLAY = "messageTagDisplay";

	public final static String MESSAGECUSTOMTAGS = "requestMessageCustomTags";
	public final static String FILEUPLOAD = "fileUpload";
	public static final String MESSAGEFILEUPLOAD = "messageFileUpload";

	// 仪表板
	public final static String HOSTTOP = "requestTopHost";
	public final static String HOSTTOPCPU = "requestTopHostCpu";
	public final static String HOSTTOPMEM = "requestTopHostMem";

	// 资源设备
	public final static String DEVICE = "requestDevice";
	public final static String DATABASE = "requestDatabase";

	public final static String EQUIPMENTCONFIG = "requestEqptConfig";
	public final static String HOSTCONFIG = "requestHostConfig";
	public final static String NETEQUIPMENTCONFIG = "requestNetEqptConfig";
	public final static String EQUIPMENTKPI = "requestEquipmentkpi";
	public final static String HOSTKPI = "requestHostKpi";
	public final static String NETEQPTKPI = "requestNetEqptKpi";

	public final static String CPUUSAGE = "requestCPUUsage";
	public final static String MEMORYUSAGE = "requestMemoryUsage";
	public final static String FILESYSTEMUSAGE = "requestFileSysUsage";
	public final static String DISKFREE="requestDiskFree";
	public final static String PINGDELAY = "requestPingDelay";
	public final static String INTERFACEFLOW = "requestIfflow";
	public final static String INTERFACEKPI = "requestIfKpi";

	public final static String EQUIPMENTBASIC = "requestEquipmentBasic";
	public final static String EQUIPMENTHEALTHSTATUS = "requestEquipmentHealthStatus";

	public final static String DELETEEQUIPMENT = "equipmentDelete";
	public final static String EQUIPMENTALARM = "requestEquipmentAlarms";
	public final static String EQUIPMENTMONITORSTATE = "eqptChangeMonitorStatus";
	public final static String SENDSNAPSHOT = "SendSnapshot";
	public final static String EQUIPMENTTOPO = "requestEquipmentTopos";

	// 告警
	public static final String ALARMLIST = "requestAlarmList";
	public static final String ALARMBUFFER = "requestAlarmBuffer";
	public static final String ALARMDETAIL = "requestAlarmDetail";
	public static final String ALARMDEAL = "alarmDeal";
	// public static final String ALARMBASEINFO = "requestAlarmBaseInfo";
	public static final String ADDALARMOPINFO = "addAlarmOpInfo";
	public static final String ADDALARMEXPERIENCE = "addAlarmExperience";
	public static final String ALARMEXPERIENCE = "requestAlarmExperience";
	public static final String ALARMOPINFO = "requestAlarmOpInfo";
	public static final String ALARMFORWARD = "alarmForward";
	public static final String ALARMCOVERAGE = "requestAlarmCoverage";
	public static final String ALARMLIFECYCLE = "requestAlarmLifeCycle";
	public static final String SUPPRESSEDALARM = "requestSuppressedAlarm";
	public static final String ALARMDISTRIBUTION = "requestAlarmDistribution";

	// 待办
	public static final String INCIDENTLIST = "requestIncidentList";
	public static final String INCIDENTDETAIL = "requestIncidentDetail";
	public static final String LISTOPERATION = "requestOperationList";
	public static final String INCIDENTPROCESS = "sendIncidentProcessResult";
	public static final String BPCATEGORYLIST = "requestBpCategory";
	public static final String COMMONUSERLIST = "requestUserList";
	public static final String BPILIST = "requestBpiList";
	public static final String IMPACTLIST = "requestImpactList";
	public static final String URGENTLIST = "requestUrgentList";
	public static final String SERVICELEVELLIST = "requestServiceLevelList";
	public static final String CLOSECODELIST = "requestCloseCodeList";

	public static final String PROBLEMLIST = "requestProblemList";
	public static final String PROBLEMDETAIL = "requestProblemDetail";
	public static final String PROBLEMPROCESS = "sendProblemProcessResult";

	public final static String INSPECTIONTASKLIST = "requestInspectionTaskList";
	public final static String INSPECTIONTASKDETAIL = "requestInspectionTaskDetail";
	public final static String INSPECTIONITEMLIST = "requestInspectionItem";
	public final static String INSPECTIONCISAVE = "requestSaveInspectionCi";
	public final static String INSPECTIONSAVE = "requestSaveInspection";

	// 资产
	public static final String CILIST = "requestCiList";
	public static final String CIDETAIL = "requestCiDetail";
	public static final String BIZSYSTEMLIST = "requestBizSystemList";

}
