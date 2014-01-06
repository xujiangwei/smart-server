package smart.api;

public final class API {

	// 登录
	public final static String LOGIN = "userm/login.html";

	// 注销
	public static final String LOGOUT = "userm/logout.html";

	// 告警
	public static final String ALARMLIST = "restws/alm/external/list/998/9980000000000000/0?pagesize=";
	// public static final String ALARMLIST =
	// "restws/alm/external/list/101/2013071712351482/0?pagesize=";
	// public static final String ALARMLIST = "alarm/list?dmsn=101&pagesize=";
	public static final String ALARMDEAL = "restws/alm/external";
	public static final String ALARMDETAIL = "restws/alm/external/list/998/9980000000000000";
	// public static final String ALARMDETAIL =
	// "restws/alm/external/list/101/2013071712351482";
	public static final String ALARMOPINFO = "restws/alm/external/deal";
	public static final String ALARMEXPERIENCE = "restws/alm/external/exp";
	public static final String ALARMBUFFER = "alarm/buff";
	public static final String ALARMLEVEL = "restws/alm/stat/severity/998/9980000000000000";

	public static final String ALARMFORWARD = "http://10.10.152.20:8081/monitoring-system/forward/alarmForward";
	public static final String DEALINFO = "http://10.10.152.20:8081/monitoring-system/dealInfo/addDealInfo";
	public static final String ALARMCOVERAGE = "http://10.10.152.20:8081/monitoring-system/alarmCoverage/reqAlarmCoverage";
	public static final String ALARMLIFECYCLE = "http://10.10.152.20:8081/monitoring-system/alarmLifeCycle/reqAlarmLifeCycle";
	public static final String SUPPRESSEDALARM = "http://10.10.152.20:8081/monitoring-system/suppressed/reqSuppressedAlarm";
	public static final String ALARMDISTRIBUTION = "http://10.10.152.20:8081/monitoring-system/distribution/reqAlarmDistribution";

	// 消息
	public final static String CONTACTS = "userm/getUserList.html";
	public final static String MESSAGELIST = "noticem/listUnread.html";
	public final static String MESSAGEDETAIL = "noticem/forView.html";
	public final static String MESSAGEDELETE = "noticem/deleteAll.html";
	public final static String MESSAGESEND = "noticem/send.html";

	public final static String MESSAGEMOVE = "http://10.10.152.20:8081/monitoring-system/messageDeal3/messageDealMove";
	public final static String MESSAGEMARK = "http://10.10.152.20:8081/monitoring-system/messageDeal4/messageDealMark";
	public final static String MESSAGETOPINFO = "http://10.10.152.20:8081/monitoring-system/messageTopInfo/messageTopInfo";
	public final static String MESSAGETAGADD = "http://10.10.152.20:8081/monitoring-system/messageTag1/messageTagAdd";
	public final static String MESSAGETAGDELETE = "http://10.10.152.20:8081/monitoring-system/messageTag2/messageTagDelete";
	public final static String MESSAGETAGMODIFY = "http://10.10.152.20:8081/monitoring-system/messageTag3/messageTagModify";
	public final static String MESSAGETAGDISPLAY = "http://10.10.152.20:8081/monitoring-system/messageTag4/messageTagDisplay";
	public final static String MESSAGECUSTOMTAGS = "http://10.10.152.20:8081/monitoring-system/customTags/requestMessageCustomTags";
	public final static String MESSAGEFILEUPLOAD = "http://10.10.152.20:8081/monitoring-system/fileUpload/messageFileUpload";

	// 报表数据
	public final static String HOSTTOPCPU = "typ/allinfo/creatAmChart.action?atomId=atom_hoAvgCPURatio_amcolumn&top=10&time=1&mockUserName=admin&mockDMSN=998";
	public final static String HOSTTOPMEM = "typ/allinfo/creatAmChart.action?atomId=atom_hoAvgMemRatio_amcolumn&top=10&time=1&mockUserName=admin&mockDMSN=998";

	// 设备资源
	public final static String EQUIPMENTLIST = "restws/model/core/mo/list/998/9980000000000000";
	// public final static String EQUIPMENTLIST =
	// "restws/model/core/mo/list/101/2013071712351482";
	public final static String DATABASE = "restws/data/perf/mo/998";
	public final static String EQUIPMENTCONFIG = "restws/data/cfg/mo/998";
	public final static String HOSTCONFIG = "restws/data/cfg/mo/998";
	public final static String NETEQUIPMENTCONFIG = "restws/data/cfg/mo/998";
	public final static String EQUIPMENTBASIC = "restws/data/perf/mo/998";
	public final static String EQUIPMENTALARMS = "restws/alm/stat/severity/998/9980000000000000?MOSN=";

	public final static String CPU = "restws/data/perf/mo/998";
	public final static String MEMORY = "restws/data/perf/mo/998";
	public final static String FILESYSTEM = "restws/data/perf/mo/998";
	public final static String FILESYSTEMKPI = "restws/data/perf/mo/998";
	public final static String DISKKPI="restws/data/perf/mo/998";
	public final static String PING = "restws/data/perf/type/998/FPING/fAvgRestTime";
	public final static String INTERFACEINFLOW = "restws/data/perf/mo/998";
	public final static String INTERFACEOUTFLOW = "restws/data/perf/mo/998";
	public final static String INTERFACEKPI = "restws/data/perf/mo/998";
	public final static String BOARD = "restws/data/perf/mo/998";

	public final static String EQUIPMENTHEALTHSTATUS = "http://10.10.152.20:8081/monitoring-system/health/requestEquipmentHealthStatus";
	public final static String DELETEEQUIPMENT = "http://10.10.152.20:8081/monitoring-system/equipmentDel/equipmentDelete";
	public final static String EQUIPMENTMONITORSTATE = "http://10.10.152.20:8081/monitoring-system/monitorStatus/equipmentChangeMonitorStatus";
	public final static String SENDSNAPSHOT = "http://10.10.152.20:8081/monitoring-system/snapshot/equipmentSendSnapshot";
	public final static String EQUIPMENTTOPO = "http://10.10.152.20:8081/monitoring-system/equipmentTopos/requestEquipmentTopos";

	/*************************** 以下为服务台产品相关配置 ***********************************/
	public final static String COMMONUSERLIST = "userm/getUserList.html";
	public final static String SERVICEDESKLOGIN = "userm/login.html";

	// 待办
	public final static String INCIDENTLIST = "incidentm/listTodos.html";
	public final static String INCIDENTDETAIL = "incidentm/loadById.html";
	public final static String LISTOPERATION = "incidentm/listIncidentOperation.html";
	public final static String INCIDENTPROCESS = "incidentm/saveAndSubmit.html";
	public final static String BPCATEGORYLIST = "incidentm/getBpCategoryById.html";
	public final static String BPILIST = "incidentm/getBpiList.html";
	public final static String IMPACTLIST = "incidentm/getImpactListForBp.html";
	public final static String URGENTLIST = "incidentm/getUrgentListForBp.html";
	public final static String SERVICELEVELLIST = "incidentm/getServiceLevelByBpId.html";
	public final static String CLOSECODELIST = "incidentm/getBpCloseCodesByBp.html";

	public final static String PROBLEMLIST = "problemm/listTodos.html";
	public final static String PROBLEMDETAIL = "problemm/loadById.html";
	public final static String PROBLEMPROCESS = "problemm/saveAndSubmit.html";

	public final static String INSPECTIONTASKLIST = "taskPlanm/getTaskPlanList.html";
	public final static String INSPECTIONTASKDETAIL = "taskPlanm/forView.html";
	public final static String INSPECTIONITEMLIST = "taskPlanm/getAllCheckItem.html";
	public final static String INSPECTIONCISAVE = "taskPlanm/saveInspectionCi.html";
	public final static String INSPECTIONSAVE = "taskPlanm/saveInspection.html";

	// 资产
	public final static String CILIST = "sacmm/listEquipments.html";
	public final static String CIDETAIL = "sacmm/loadEquipmentById.html";
	public final static String BIZSYSTEMLIST = "sacmm/getBizSystemList.html";

	private API() {
	}

}
