package smart.action.resource;

import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import net.cellcloud.common.Logger;
import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.util.ObjectProperty;
import net.cellcloud.util.Properties;

import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.DeferredContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import smart.action.AbstractListener;
import smart.api.API;
import smart.api.RequestContentCapsule;
import smart.api.host.HostConfig;
import smart.api.host.HostConfigContext;
import smart.api.host.MonitorSystemHostConfig;
import smart.mast.action.Action;

/**
 * ] 主机配置信息监听器
 * 
 * @author Administrator
 */
public class HostConfigListener extends AbstractListener {

	public HostConfigListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {
		// 使用同步的方式进行请求
		// 注意：因为onAction方法是由Cell Cloud的action dialect进行回调的
		// 该方法独享一个线程，因此可以在此线程里进行阻塞式的调用
		// 因此，这里可以用同步的方式请求HTTP API

		// 获取参数
		JSONObject json = null;
		long moId = 0;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			moId = json.getInt("moId");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		System.out.println("参数1: " + moId);

		HostConfig cpuConfig = new MonitorSystemHostConfig();
		HostConfigContext context = new HostConfigContext(cpuConfig);
		StringBuilder url = new StringBuilder(context.getAPIHost()).append("/")
				.append(API.HOSTCONFIG).append("/").append(moId);

		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.GET);

		// 填写数据内容
		DeferredContentProvider dcp = new DeferredContentProvider();
		RequestContentCapsule capsule = new RequestContentCapsule();
		capsule.append("moId", moId);
		dcp.offer(capsule.toBuffer());
		dcp.close();
		request.content(dcp);

		// 发送请求
		ContentResponse response = null;

		try {
			response = request.send();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (TimeoutException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}

		Properties params = new Properties();
		JSONObject data = null;
		JSONObject config = null;
		switch (response.getStatus()) {

		case HttpStatus.OK_200:
			byte[] bytes = response.getContent();
			if (null != bytes) {

				// 获取从Web服务器返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));

				try {
					data = new JSONObject(content);
					System.out.println("hostCfg 源数据：" + data);
					// if ("success".equals(data.get("status"))) {
					if (!"".equals(data.get("data"))
							&& data.get("data") != null) {
						JSONArray ja = data.getJSONArray("data");

						config = new JSONObject();
						JSONArray jaIf = new JSONArray();
						JSONArray jaDisk = new JSONArray();
						JSONArray jaCpu = new JSONArray();
						JSONArray jaFs = new JSONArray();
						for (int i = 0; i < ja.length(); i++) {
							JSONObject jo = ja.getJSONObject(i);

							if ("接口".equals(jo.getString("type"))) {
								JSONArray jaattr = jo.getJSONArray("attr");
								JSONObject joattr = new JSONObject();
								JSONObject joat = new JSONObject();
								for (int j = 0; j < jaattr.length(); j++) {
									JSONArray ja2 = jaattr.getJSONArray(j);
									String key = (String) ja2.get(0);
									Object value = ja2.get(1);

									joattr.put(key, value);
								}

								joat.put("bandwidth", joattr.get("带宽"));
								joat.put("mac", joattr.get("物理地址"));
								joat.put("isDlock", joattr.get("是否允许阻断"));
								joat.put("panel", joattr.get("所属面板"));
								joat.put("describe", joattr.get("接口描述"));
								joat.put("index", joattr.get("接口索引"));
								joat.put("maxDatagramLength",
										joattr.get("最大数据报长度"));
								joat.put("alias", joattr.get("接口别名"));
								joat.put("eqptMosn", joattr.get("MOSN"));

								jo.remove("attr");
								jo.remove("joattr");
								jo.put("attr", joat);

								if (jaIf.length() < 10) {
									jaIf.put(jo);
								}

								config.put("interfaceConfig", jaIf);
								// long if_mosn = jo.getLong("mosn");
								// String if_name = jo.getString("name");
								// String if_type = jo.getString("type");
								//
								// JSONObject joa = jo.getJSONObject("attr");
								// long if_bandwidth = joa.getLong("带宽");
								// String if_mac = joa.getString("物理地址");
								// String if_isblock = joa.getString("是否允许阻断");
								// int if_panel = joa.getInt("所属面板");
								// String if_describe = joa.getString("接口描述");
								// int if_index = joa.getInt("接口索引");
								// int if_maxdatagramlength = joa
								// .getInt("最大数据报长度");
								// String if_alias = joa.getString("接口别名");
								// long if_eqptmosn = joa.getLong("MOSN");
								//
								// InterfaceManager ifm = InterfaceManager
								// .getInstance();
								// ifm.saveIfInfo(if_mosn, if_name, if_type,
								// if_bandwidth, if_mac, if_isblock,
								// if_panel, if_describe, if_index,
								// if_maxdatagramlength, if_alias,
								// if_eqptmosn);

							} else if ("磁盘".equals(jo.getString("type"))) {
								JSONArray jaattr = jo.getJSONArray("attr");
								JSONObject joattr = new JSONObject();
								JSONObject joat = new JSONObject();
								for (int j = 0; j < jaattr.length(); j++) {
									JSONArray ja2 = jaattr.getJSONArray(j);
									String key = (String) ja2.get(0);
									Object value = ja2.get(1);

									joattr.put(key, value);
								}

								joat.put("path", joattr.get("盘符"));
								joat.put("partitionSign", joattr.get("分区标签"));
								joat.put("partitionSize", joattr.get("分区容量"));
								joat.put("eqptMosn", joattr.get("MOSN"));

								jo.remove("attr");
								jo.remove("joattr");
								jo.put("attr", joat);

								if (jaDisk.length() < 10) {
									jaDisk.put(jo);
								}
								config.put("diskConfig", jaDisk);
								// long disk_mosn = jo.getLong("mosn");
								// String disk_name = jo.getString("name");
								// String disk_type = jo.getString("type");
								//
								// JSONObject joa = jo.getJSONObject("attr");
								// String disk_path = joa.getString("盘符");
								// String disk_partitionsign = joa
								// .getString("分区标签");
								// int disk_partitionsize = joa.getInt("分区容量");
								// long disk_eqptmosn = joa.getLong("MOSN");
								//
								// DiskManager dm = DiskManager.getInstance();
								// dm.saveDiskInfo(disk_mosn, disk_name,
								// disk_type, disk_path,
								// disk_partitionsign, disk_partitionsize,
								// disk_eqptmosn);

							} else if ("CPU".equals(jo.getString("type"))) {
								JSONArray jaattr = jo.getJSONArray("attr");
								JSONObject joattr = new JSONObject();
								JSONObject joat = new JSONObject();
								for (int j = 0; j < jaattr.length(); j++) {
									JSONArray ja2 = jaattr.getJSONArray(j);
									String key = (String) ja2.get(0);
									Object value = ja2.get(1);

									joattr.put(key, value);
								}

								joat.put("catch", joattr.get("CPU缓存"));
								joat.put("sign", joattr.get("CPU标识"));
								joat.put("mhz", joattr.get("主频(MHz)"));
								joat.put("model", joattr.get("CPU类型"));
								joat.put("bits", joattr.get("CPU位数"));
								joat.put("vender", joattr.get("厂家"));
								joat.put("eqptMosn", joattr.get("MOSN"));

								jo.remove("attr");
								jo.remove("joattr");
								jo.put("attr", joat);

								if (jaCpu.length() < 10) {
									jaCpu.put(jo);
								}
								config.put("cpuConfig", jaCpu);

								// long hcpu_mosn = jo.getLong("mosn");
								// String hcpu_name = jo.getString("name");
								// String hcpu_type = jo.getString("type");
								//
								// JSONObject joa = jo.getJSONObject("attr");
								// String hcpu_catch = joa.getString("CPU缓存");
								// String hcpu_sign = joa.getString("CPU标识");
								// double hcpu_mhz = joa.getDouble("主频(MHz)");
								// String hcpu_model = joa.getString("CPU类型");
								// String hcpu_vender = joa.getString("厂家");
								// long hcpu_eqptmosn = joa.getLong("MOSN");
								//
								// HostCPUManager hcm = HostCPUManager
								// .getInstance();
								// hcm.saveHostCPUInfo(hcpu_mosn, hcpu_name,
								// hcpu_type, hcpu_catch, hcpu_sign,
								// hcpu_mhz, hcpu_model, hcpu_vender,
								// hcpu_eqptmosn);

							} else if ("文件系统".equals(jo.getString("type"))) {
								JSONArray jaattr = jo.getJSONArray("attr");
								JSONObject joattr = new JSONObject();
								JSONObject joat = new JSONObject();
								for (int j = 0; j < jaattr.length(); j++) {
									JSONArray ja2 = jaattr.getJSONArray(j);
									String key = (String) ja2.get(0);
									Object value = ja2.get(1);

									joattr.put(key, value);
								}

								joat.put("hangNode", joattr.get("挂结点"));
								joat.put("fsName", joattr.get("文件系统名称"));
								joat.put("fsType", joattr.get("文件类型"));
								joat.put("nodeNum", joattr.get("节点总数"));
								joat.put("capacity", joattr.get("容量"));
								joat.put("eqptMosn", joattr.get("MOSN"));

								jo.remove("attr");
								jo.remove("joattr");
								jo.put("attr", joat);

								if (jaFs.length() < 10) {
									jaFs.put(jo);
								}
								config.put("filesysConfig", jaFs);
								// long fs_mosn = jo.getLong("mosn");
								// String fs_name = jo.getString("name");
								// String fs_type = jo.getString("type");
								//
								// JSONObject joa = jo.getJSONObject("attr");
								// String fs_hangnode = joa.getString("挂结点");
								// String fs_fsname = joa.getString("文件系统名称");
								// String fs_fstype = joa.getString("文件类型");
								// int fs_nodenum = joa.getInt("节点总数");
								// int fs_capacity = joa.getInt("容量");
								// long fs_eqptmosn = joa.getLong("MOSN");
								//
								// FilesystemManager fsm = FilesystemManager
								// .getInstance();
								// fsm.saveFsInfo(fs_mosn, fs_name, fs_type,
								// fs_hangnode, fs_fsname, fs_fstype,
								// fs_nodenum, fs_capacity, fs_eqptmosn);

							}

							// data.put("config", config);
							// data.remove("data");
						}
						data.put("config", config);
						data.remove("data");
					}
					// } else {
					// data.put("errorInfo", "未获取到相关kpi数据");
					// }

					System.out.println("hostCfg data：      " + data);
					// 设置参数
					params.addProperty(new ObjectProperty("data", data));
				} catch (JSONException e) {
					e.printStackTrace();
				}

				// 响应动作，即向客户端发送ActionDialect
				// 参数tracker是一次动作的追踪标识符
				this.response(Action.HOSTCONFIG, params);
			} else {
				this.reportHTTPError(Action.HOSTCONFIG);
			}
			break;
		default:
			Logger.w(HostConfigListener.class, "返回响应码:" + response.getStatus());

			try {
				data = new JSONObject();
				data.put("status", 900);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 设置参数
			params.addProperty(new ObjectProperty("data", data));

			// 响应动作，即向客户端发送 ActionDialect
			this.response(Action.HOSTCONFIG, params);
			break;

		}
	}
}
