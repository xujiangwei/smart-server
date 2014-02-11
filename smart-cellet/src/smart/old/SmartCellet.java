package smart.old;

import net.cellcloud.common.LogLevel;
import net.cellcloud.common.Logger;
import net.cellcloud.core.Cellet;
import net.cellcloud.core.CelletFeature;
import net.cellcloud.talk.Primitive;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.talk.dialect.Dialect;

import org.eclipse.jetty.client.HttpClient;

import smart.old.action.ConnectionCheckListener;
import smart.old.action.LoginListener;
import smart.old.action.LogoutListener;
import smart.old.action.NodeDetectionListener;
import smart.old.action.alarm.AddAlarmExperienceListener;
import smart.old.action.alarm.AddAlarmOpInfoListener;
import smart.old.action.alarm.AlarmChangeListListener;
import smart.old.action.alarm.AlarmDealListener;
import smart.old.action.alarm.AlarmDetailListener;
import smart.old.action.alarm.AlarmExperienceListener;
import smart.old.action.alarm.AlarmLevelListener;
import smart.old.action.alarm.AlarmListListener;
import smart.old.action.alarm.AlarmOpInfoListener;
import smart.old.action.attention.AddAttentionListener;
import smart.old.action.attention.AttentionListListener;
import smart.old.action.attention.CancelAttentionListener;
import smart.old.action.attention.DeleteAttentionListener;
import smart.old.action.ci.BusinessSystemListListener;
import smart.old.action.ci.CiDetailListener;
import smart.old.action.ci.CiListListener;
import smart.old.action.form.HostCpuListener;
import smart.old.action.form.HostMemTopListener;
import smart.old.action.message.ContactsListener;
import smart.old.action.message.MessageDeleteListener;
import smart.old.action.message.MessageDetailListener;
import smart.old.action.message.MessageListListener;
import smart.old.action.message.MessageSendListener;
import smart.old.action.resource.CPUUsageListener;
import smart.old.action.resource.DataBaseListener;
import smart.old.action.resource.DiskFreeListener;
import smart.old.action.resource.DiskUsedListener;
import smart.old.action.resource.EquipmentAlarmListener;
import smart.old.action.resource.EquipmentBasicListener;
import smart.old.action.resource.EquipmentConfigLitener;
import smart.old.action.resource.EquipmentListListener;
import smart.old.action.resource.FileSystemFreeListener;
import smart.old.action.resource.FileSystemUsageListener;
import smart.old.action.resource.FileSystemUsedListener;
import smart.old.action.resource.HostConfigListener;
import smart.old.action.resource.InterfInFlowListener;
import smart.old.action.resource.InterfOutFlowListener;
import smart.old.action.resource.InterfaceKpiListener;
import smart.old.action.resource.MemoryUsageListener;
import smart.old.action.resource.NetEqptBoardListener;
import smart.old.action.resource.NetEquipmentConfigListener;
import smart.old.action.resource.PingDelayListener;
import smart.old.action.task.BpCloseCodeListListener;
import smart.old.action.task.BpiListListener;
import smart.old.action.task.IncidentCategoryListListener;
import smart.old.action.task.IncidentDetailListener;
import smart.old.action.task.IncidentListListener;
import smart.old.action.task.IncidentProcessListener;
import smart.old.action.task.IncidentTaskActorListListener;
import smart.old.action.task.InspectionCiSaveListener;
import smart.old.action.task.InspectionDetailListener;
import smart.old.action.task.InspectionItemListener;
import smart.old.action.task.InspectionListListener;
import smart.old.action.task.InspectionSaveListener;
import smart.old.action.task.OperationListListener;
import smart.old.action.task.ProblemDetailListener;
import smart.old.action.task.ProblemListListener;
import smart.old.action.task.ProblemProcessListener;
import smart.old.action.task.SlaImpactListListener;
import smart.old.action.task.SlaServiceLevelListListener;
import smart.old.action.task.SlaUrgentListListener;
import smart.old.action.task.UserListListener;
import smart.old.action.time.CurrentTimeListener;
import smart.old.mast.Root;
import smart.old.mast.action.Action;
import smart.old.mast.action.ActionDispatcher;
import smart.old.mast.core.SystemCategory;

public class SmartCellet extends Cellet {

	private static SmartCellet instance;

	private HttpClient httpClient;

	public SmartCellet() {
		super(new CelletFeature("SmartITOM", new Version()));
		SmartCellet.instance = this;
		this.httpClient = new HttpClient();
	}

	public synchronized static SmartCellet getInstance() {
		return SmartCellet.instance;
	}

	@Override
	public void activate() {
		// 启动引擎
		Root.getInstance().startup(SystemCategory.MONITORING_SYSTEM, this);

		// 配置 Http 客户端
		this.configHttpClient();

		// 初始化监听器
		this.initListeners();
	}

	@Override
	public void deactivate() {
		try {
			this.httpClient.stop();
		} catch (Exception e) {
			Logger.log(this.getClass(), e, LogLevel.WARNING);
		}

		// 关闭引擎
		Root.getInstance().shutdown();
	}

	@Override
	public void dialogue(String tag, Primitive primitive) {
		if (primitive.isDialectal()) {
			Dialect dialect = primitive.getDialect();
			if (dialect.getName().equals(ActionDialect.DIALECT_NAME)) {
				ActionDispatcher.getInstance().dispatch(
						SystemCategory.MONITORING_SYSTEM,
						(ActionDialect) dialect);
			}
		}
	}

	@Override
	public void contacted(String tag) {
	}

	@Override
	public void quitted(String tag) {
	}

	@Override
	public void suspended(String tag) {
	}

	@Override
	public void resumed(String tag) {
	}

	/**
	 * 配置 HTTP 客户端。
	 */
	private void configHttpClient() {

		this.httpClient.setFollowRedirects(false);
		try {
			this.httpClient.start();
		} catch (Exception e) {
			Logger.log(this.getClass(), e, LogLevel.ERROR);
		}
	}

	/**
	 * 初始化所有监听器。
	 */
	private void initListeners() {
		ActionDispatcher dispatcher = ActionDispatcher.getInstance();

		// 服务器连接检测
		ConnectionCheckListener check = new ConnectionCheckListener(this);
		check.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.CONNECTIONCHECK, check);

		// 登录
		LoginListener login = new LoginListener(this);
		login.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.LOGIN, login);

		// 获取关注列表
		AttentionListListener attentionList = new AttentionListListener(this);
		attentionList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.ATTENTIONLIST, attentionList);

		// 取消关注
		CancelAttentionListener cancelAttention = new CancelAttentionListener(
				this);
		cancelAttention.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.CANCELATTENTION, cancelAttention);

		AttentionListListener al = new AttentionListListener(this);
		al.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.CANCELATTENTION, al);

		// 删除关注
		DeleteAttentionListener deleteAttention = new DeleteAttentionListener(
				this);
		deleteAttention.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.DELETEATTENTION, deleteAttention);

		AttentionListListener al1 = new AttentionListListener(this);
		al1.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.DELETEATTENTION, al1);

		// 添加关注
		AddAttentionListener addAttention = new AddAttentionListener(this);
		addAttention.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.ADDATTENTION, addAttention);

		// AttentionListListener al2 = new AttentionListListener(this);
		// al2.setHttpClient(this.httpClient);
		// dispatcher.addListener(Action.ADDATTENTION, al2);

		/************** 消息 *************/

		// 获取联系人
		ContactsListener ctl = new ContactsListener(this);
		ctl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.CONTACTS, ctl);

		// 获取消息
		MessageListListener messageList = new MessageListListener(this);
		messageList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.MESSAGELIST, messageList);

		// 获取消息详细
		MessageDetailListener messageDetailListener = new MessageDetailListener(
				this);
		messageDetailListener.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.MESSAGEDETAIL, messageDetailListener);

		// 删除消息
		MessageDeleteListener MessageDeleteListener = new MessageDeleteListener(
				this);
		MessageDeleteListener.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.MESSAGEDELETE, MessageDeleteListener);

		// 发送消息
		MessageSendListener messageSendListener = new MessageSendListener(this);
		messageSendListener.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.MESSAGESEND, messageSendListener);

		/*************** 仪表板 **************/
		// 获取主机负载综合
		HostCpuListener hostCpu = new HostCpuListener(this);
		hostCpu.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTTOP, hostCpu);

		HostMemTopListener hmtl = new HostMemTopListener(this);
		hmtl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTTOP, hmtl);

		/************** 设备 **************/
		// 获取所有设备列表
		EquipmentListListener equipmentList = new EquipmentListListener(this);
		equipmentList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.DEVICE, equipmentList);

		// 获取数据库详细
		DataBaseListener db = new DataBaseListener(this);
		db.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.DATABASE, db);

		// 获取数据库告警
		EquipmentAlarmListener eptAlarm = new EquipmentAlarmListener(this);
		eptAlarm.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.DATABASE, eptAlarm);

		// 获取设备配置信息
		EquipmentConfigLitener ecl = new EquipmentConfigLitener(this);
		ecl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.EQUIPMENTCONFIG, ecl);

		// 获取主机设备配置信息
		HostConfigListener hostcl = new HostConfigListener(this);
		hostcl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTCONFIG, hostcl);

		// 获取网络设备配置信息
		NetEquipmentConfigListener neptcl = new NetEquipmentConfigListener(this);
		neptcl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.NETEQUIPMENTCONFIG, neptcl);

		// 获取主机设备的CPU/内存/文件系统/diskFree/接口的kpi信息，PING延时信息
		CPUUsageListener hcpuul = new CPUUsageListener(this);
		hcpuul.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTKPI, hcpuul);

		MemoryUsageListener hmemul = new MemoryUsageListener(this);
		hmemul.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTKPI, hmemul);

		// 磁盘已用和未用大小
		DiskUsedListener hdul = new DiskUsedListener(this);
		hdul.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTKPI, hdul);

		DiskFreeListener hdfl = new DiskFreeListener(this);
		hdfl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTKPI, hdfl);

		// 文件系统已使用和未使用大小
		FileSystemUsedListener hful = new FileSystemUsedListener(this);
		hful.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTKPI, hful);

		FileSystemFreeListener hffl = new FileSystemFreeListener(this);
		hffl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTKPI, hffl);

		FileSystemUsageListener hfilesul = new FileSystemUsageListener(this);
		hfilesul.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTKPI, hfilesul);

		PingDelayListener hpdl = new PingDelayListener(this);
		hpdl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTKPI, hpdl);

		InterfaceKpiListener hifkl = new InterfaceKpiListener(this);
		hifkl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTKPI, hifkl);

		EquipmentAlarmListener heal = new EquipmentAlarmListener(this);
		heal.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.HOSTKPI, heal);

		// 获取网络设备的CPU/内存/接口的kpi信息
		CPUUsageListener ncpuul = new CPUUsageListener(this);
		ncpuul.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.NETEQPTKPI, ncpuul);

		MemoryUsageListener nmemul = new MemoryUsageListener(this);
		nmemul.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.NETEQPTKPI, nmemul);

		InterfaceKpiListener nifkl = new InterfaceKpiListener(this);
		nifkl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.NETEQPTKPI, nifkl);

		NetEqptBoardListener nbl = new NetEqptBoardListener(this);
		nbl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.NETEQPTKPI, nbl);

		EquipmentAlarmListener neal = new EquipmentAlarmListener(this);
		neal.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.NETEQPTKPI, neal);

		/********************* 测试监听 *************************/
		// 测试--获取设备告警
		EquipmentAlarmListener eal = new EquipmentAlarmListener(this);
		eal.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.EQUIPMENTALARMS, eal);

		// 测试--获取网络接口的入流速
		InterfInFlowListener nfl = new InterfInFlowListener(this);
		nfl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.INTERFACEFLOW, nfl);

		// 测试--获取网络接口的出流速
		InterfOutFlowListener nol = new InterfOutFlowListener(this);
		nol.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.INTERFACEFLOW, nol);

		// 测试--获取接口KPI指标信息
		InterfaceKpiListener ifk = new InterfaceKpiListener(this);
		ifk.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.INTERFACEKPI, ifk);

		// 测试--获取主机ping延迟
		PingDelayListener pdl = new PingDelayListener(this);
		pdl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.PINGDELAY, pdl);

		// 测试--获取主机disk已用和未用
		DiskFreeListener dfl = new DiskFreeListener(this);
		dfl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.DISKFREE, dfl);

		DiskUsedListener dul = new DiskUsedListener(this);
		dul.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.DISKUSED, dul);

		// 测试--获取Linux主机 文件系统 已用和未用
		FileSystemUsedListener ful = new FileSystemUsedListener(this);
		ful.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.FILESYSTEMKPI, ful);

		FileSystemFreeListener ffl = new FileSystemFreeListener(this);
		ffl.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.FILESYSTEMKPI, ffl);

		// 获取设备基本信息
		EquipmentBasicListener eqptBasicListener = new EquipmentBasicListener(
				this);
		eqptBasicListener.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.EQUIPMENTBASIC, eqptBasicListener);

		// 注销
		LogoutListener logout = new LogoutListener(this);
		logout.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.LOGOUT, logout);

		// 当前时间
		CurrentTimeListener currentTime = new CurrentTimeListener(this);
		currentTime.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.CURRENTTIME, currentTime);

		// 获取告警基本信息
		AlarmDetailListener alarmDetail = new AlarmDetailListener(this);
		alarmDetail.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.ALARMDETAIL, alarmDetail);

		// 告警操作
		AlarmDealListener alarmDeal = new AlarmDealListener(this);
		alarmDeal.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.ALARMDEAL, alarmDeal);

		AlarmDetailListener basicInfo = new AlarmDetailListener(this);
		basicInfo.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.ALARMDEAL, basicInfo);

		// 添加告警处理信息
		AddAlarmOpInfoListener dealInfo = new AddAlarmOpInfoListener(this);
		dealInfo.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.ADDALARMOPINFO, dealInfo);

		// 添加告警维护经验
		AddAlarmExperienceListener addExperience = new AddAlarmExperienceListener(
				this);
		addExperience.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.ADDALARMEXPERIENCE, addExperience);

		// 获取告警列表
		AlarmListListener alarmList = new AlarmListListener(this);
		alarmList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.ALARMLIST, alarmList);

		AlarmChangeListListener alarmChange = new AlarmChangeListListener(this);
		alarmChange.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.ALARMLIST, alarmChange);

		// 获取告警处理信息
		AlarmOpInfoListener alarmOpInfo = new AlarmOpInfoListener(this);
		alarmOpInfo.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.ALARMDETAIL, alarmOpInfo);

		// 获取告警维护经验
		AlarmExperienceListener alarmExperience = new AlarmExperienceListener(
				this);
		alarmExperience.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.ALARMDETAIL, alarmExperience);

		// 告警级别统计
		AlarmLevelListener alarmLevel = new AlarmLevelListener(this);
		alarmLevel.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.ALARMLEVEL, alarmLevel);

		// 获取告警影响范围
		// AlarmCoverageListener alarmCoverage = new
		// AlarmCoverageListener(this);
		// alarmCoverage.setHttpClient(this.httpClient);
		// dispatcher.addListener(Action.ALARMDETAIL, alarmCoverage);

		// 获取告警生命周期
		// AlarmLifeCycleListener lifeCycle = new AlarmLifeCycleListener(this);
		// lifeCycle.setHttpClient(this.httpClient);
		// dispatcher.addListener(Action.ALARMDETAIL, lifeCycle);
		// 获取压制的告警
		// SuppressedAlarmListener suppressed = new
		// SuppressedAlarmListener(this);
		// suppressed.setHttpClient(this.httpClient);
		// dispatcher.addListener(Action.ALARMDETAIL, suppressed);

		// 获取告警分布
		// AlarmDistributionListener distribution = new
		// AlarmDistributionListener(
		// this);
		// distribution.setHttpClient(this.httpClient);
		// dispatcher.addListener(Action.ALARMDETAIL, distribution);

		// 节点监测
		NodeDetectionListener nodeDec = new NodeDetectionListener(this);
		nodeDec.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.NODEDETECTION, nodeDec);

		/************** 故障任务 *************/
		// 获取故障列表
		IncidentListListener incidentList = new IncidentListListener(this);
		incidentList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.INCIDENTLIST, incidentList);

		// 获取指定故障任务工单明细
		IncidentDetailListener incidentDetail = new IncidentDetailListener(this);
		incidentDetail.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.INCIDENTDETAIL, incidentDetail);

		// 获取任务操作项
		OperationListListener operationList = new OperationListListener(this);
		operationList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.LISTOPERATION, operationList);

		// 故障任务单处理
		IncidentProcessListener incidentProcess = new IncidentProcessListener(
				this);
		incidentProcess.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.INCIDENTPROCESS, incidentProcess);

		// 获取故障类型
		IncidentCategoryListListener bpCategoryList = new IncidentCategoryListListener(
				this);
		bpCategoryList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.BPCATEGORYLIST, bpCategoryList);

		// 获取所有流程工单列表
		BpiListListener bpiList = new BpiListListener(this);
		bpiList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.BPILIST, bpiList);

		// 获取用户列表
		UserListListener userList = new UserListListener(this);
		userList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.COMMONUSERLIST, userList);

		// 影响程度
		SlaImpactListListener impactList = new SlaImpactListListener(this);
		impactList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.IMPACTLIST, impactList);

		// 紧急程度
		SlaUrgentListListener urgentList = new SlaUrgentListListener(this);
		urgentList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.URGENTLIST, urgentList);

		// 优先级
		SlaServiceLevelListListener serviceLevelList = new SlaServiceLevelListListener(
				this);
		serviceLevelList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.SERVICELEVELLIST, serviceLevelList);

		// 关闭代码
		BpCloseCodeListListener closeCodeList = new BpCloseCodeListListener(
				this);
		closeCodeList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.CLOSECODELIST, closeCodeList);

		// 故障任务参与人
		IncidentTaskActorListListener taskActorList = new IncidentTaskActorListListener(
				this);
		taskActorList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.TASKACTORLIST, taskActorList);

		/************** 问题任务 *************/
		ProblemListListener problemList = new ProblemListListener(this);
		problemList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.PROBLEMLIST, problemList);

		ProblemDetailListener problemDetail = new ProblemDetailListener(this);
		problemDetail.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.PROBLEMDETAIL, problemDetail);

		ProblemProcessListener problemProcess = new ProblemProcessListener(this);
		problemProcess.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.PROBLEMPROCESS, problemProcess);

		/************** 巡检任务 *************/
		InspectionListListener inspectionTaskList = new InspectionListListener(
				this);
		inspectionTaskList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.INSPECTIONTASKLIST, inspectionTaskList);

		InspectionDetailListener inspectionTaskDetail = new InspectionDetailListener(
				this);
		inspectionTaskDetail.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.INSPECTIONTASKDETAIL,
				inspectionTaskDetail);

		InspectionItemListener inspectionItemList = new InspectionItemListener(
				this);
		inspectionItemList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.INSPECTIONITEMLIST, inspectionItemList);

		InspectionCiSaveListener saveInspectionCi = new InspectionCiSaveListener(
				this);
		saveInspectionCi.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.INSPECTIONCISAVE, saveInspectionCi);

		InspectionSaveListener saveInspection = new InspectionSaveListener(this);
		saveInspection.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.INSPECTIONSAVE, saveInspection);

		/*************** 资产管理 **************/
		// 获取设备资产列表数据
		CiListListener ciListener = new CiListListener(this);
		ciListener.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.CILIST, ciListener);

		// 获取所有业务系统列表
		BusinessSystemListListener bizSystemList = new BusinessSystemListListener(
				this);
		bizSystemList.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.BIZSYSTEMLIST, bizSystemList);

		// 获取指定设备资产详细信息
		CiDetailListener ciDetail = new CiDetailListener(this);
		ciDetail.setHttpClient(this.httpClient);
		dispatcher.addListener(Action.CIDETAIL, ciDetail);

	}
}
