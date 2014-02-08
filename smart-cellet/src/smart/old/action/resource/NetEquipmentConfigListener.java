package smart.old.action.resource;

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

import smart.old.action.AbstractListener;
import smart.old.api.API;
import smart.old.api.RequestContentCapsule;
import smart.old.api.host.HostConfig;
import smart.old.api.host.HostConfigContext;
import smart.old.api.host.MonitorSystemHostConfig;
import smart.old.mast.action.Action;

/**
 * 网络设备配置信息监听器
 * 
 * @author Lianghai Li
 */
public class NetEquipmentConfigListener extends AbstractListener {

	public NetEquipmentConfigListener(Cellet cellet) {
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
		int currentIndex = 0;
		int pageSize = 10;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			moId = json.getInt("moId");
			moId = json.getInt("moId");
			currentIndex = json.getInt("currentIndex");
			// pageSize = json.getInt("pageSize");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		HostConfig cpuConfig = new MonitorSystemHostConfig();
		HostConfigContext context = new HostConfigContext(cpuConfig);
		StringBuilder url = new StringBuilder(context.getAPIHost()).append("/")
				.append(API.NETEQUIPMENTCONFIG).append("/").append(moId);

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

					// System.out.println("netConfig 源数据         " + data);
					// if ("success".equals(data.get("status"))) {
					if (!"".equals(data.get("data"))
							&& data.get("data") != null) {
						JSONArray ja = data.getJSONArray("data");

						config = new JSONObject();
						JSONArray jaIf = new JSONArray();
						JSONArray jaMem = new JSONArray();
						JSONArray jaCpu = new JSONArray();
						JSONArray jaBoard = new JSONArray();
						for (int i = 0; i < ja.length(); i++) {
							if ((i >= (currentIndex - 1) * pageSize)
									&& (i < (currentIndex * pageSize))) {
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

									joat.put("bandwidth", Long.parseLong(joattr
											.getString("带宽")));
									joat.put("mac", joattr.getString("物理地址"));
									joat.put("isBlock",
											joattr.getString("是否允许阻断"));
									joat.put("panel", Integer.parseInt(joattr
											.getString("所属面板")));
									joat.put("describe",
											joattr.getString("接口描述"));
									joat.put("index", Integer.parseInt(joattr
											.getString("接口索引")));
									joat.put("maxDatagramLength", Integer
											.parseInt(joattr
													.getString("最大数据报长度")));
									joat.put("alias", joattr.getString("接口别名"));
									joat.put("moId", Long.parseLong(joattr
											.getString("MOSN")));

									jo.remove("attr");
									jo.remove("joattr");
									jo.put("attr", joat);

									jaIf.put(jo);

									// jaIf.put(joattr);

									// long if_mosn = jo.getLong("mosn");
									// String if_name = jo.getString("name");
									// String if_type = jo.getString("type");
									//
									// JSONObject joa =
									// jo.getJSONObject("attr");
									// long if_bandwidth = joa.getLong("带宽");
									// String if_mac = joa.getString("物理地址");
									// String if_isblock =
									// joa.getString("是否允许阻断");
									// int if_panel = joa.getInt("所属面板");
									// String if_describe =
									// joa.getString("接口描述");
									// int if_index = joa.getInt("接口索引");
									// int if_maxdatagramlength = joa
									// .getInt("最大数据报长度");
									// String if_alias = joa.getString("接口别名");
									// long if_moId = joa.getLong("MOSN");

									// InterfaceManager ifm = InterfaceManager
									// .getInstance();
									// ifm.saveIfInfo(if_mosn, if_name, if_type,
									// if_bandwidth, if_mac, if_isblock,
									// if_panel, if_describe, if_index,
									// if_maxdatagramlength, if_alias,
									// if_moId);

								} else if ("内存".equals(jo.getString("type"))) {
									JSONArray jaattr = jo.getJSONArray("attr");
									JSONObject joattr = new JSONObject();
									JSONObject joat = new JSONObject();
									for (int j = 0; j < jaattr.length(); j++) {
										JSONArray ja2 = jaattr.getJSONArray(j);
										String key = (String) ja2.get(0);
										Object value = ja2.get(1);

										joattr.put(key, value);
									}

									joat.put("sign", joattr.getString("内存标识"));
									joat.put("moId", Long.parseLong(joattr
											.getString("MOSN")));

									jo.remove("attr");
									jo.remove("joattr");
									jo.put("attr", joat);

									jaMem.put(jo);

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

									joat.put("index", Integer.parseInt(joattr
											.getString("索引")));
									joat.put("tabindex", Integer
											.parseInt(joattr.getString("值表索引")));
									joat.put("tabindex2",
											Integer.parseInt(joattr
													.getString("值表索引2")));
									joat.put("location", joattr.getString("位置"));
									joat.put("describe", joattr.getString("描述"));
									joat.put("number", Integer.parseInt(joattr
											.getString("同级编号")));
									joat.put("isPlug", joattr.getString("可否插拔"));
									joat.put("category", Integer
											.parseInt(joattr.getString("类别")));
									joat.put("cName", joattr.getString("名称"));
									joat.put("alais", joattr.getString("别名"));
									joat.put("sign", joattr.getString("CPU标识"));
									joat.put("serialNum",
											joattr.getString("实体序列号"));
									joat.put("moId", Long.parseLong(joattr
											.getString("MOSN")));

									jo.remove("attr");
									jo.remove("joattr");
									jo.put("attr", joat);

									jaCpu.put(jo);

								} else if ("Board".equals(jo.getString("type"))) {
									JSONArray jaattr = jo.getJSONArray("attr");
									JSONObject joattr = new JSONObject();
									JSONObject joat = new JSONObject();
									for (int j = 0; j < jaattr.length(); j++) {
										JSONArray ja2 = jaattr.getJSONArray(j);
										String key = (String) ja2.get(0);
										Object value = ja2.get(1);

										joattr.put(key, value);
									}

									joat.put("index", Integer.parseInt(joattr
											.getString("索引")));
									joat.put("tabindex", Integer
											.parseInt(joattr.getString("值表索引")));
									joat.put("tabindex2",
											Integer.parseInt(joattr
													.getString("值表索引2")));
									joat.put("location", joattr.getString("位置"));
									joat.put("describe", joattr.getString("描述"));
									joat.put("number", Integer.parseInt(joattr
											.getString("同级编号")));
									joat.put("isPlug", joattr.getString("可否插拔"));
									joat.put("category", Integer
											.parseInt(joattr.getString("类别")));
									joat.put("bName", joattr.getString("名称"));
									joat.put("alais", joattr.getString("别名"));
									joat.put("sign",
											joattr.getString("Board标识"));
									joat.put("serialNum",
											joattr.getString("实体序列号"));
									joat.put("moId", Long.parseLong(joattr
											.getString("MOSN")));

									jo.remove("attr");
									jo.remove("joattr");
									jo.put("attr", joat);

									jaBoard.put(jo);

								}

								config.put("interfaceConfig", jaIf);
								config.put("memoryConfig", jaMem);
								config.put("cpuConfig", jaCpu);
								config.put("boardConfig", jaBoard);
							}
						}

						data.put("moId", data.get("mosn"));
						data.remove("mosn");
						data.remove("data");
						data.put("config", config);
						if (data.getJSONObject("config").length() != 0) {
							data.put("status", 300);
							data.put("errorInfo", "");
						} else {
							data.put("status", 333);
							data.put("errorInfo", "就这些数据啦，没有更多的信息！");
						}
					} else {
						data.remove("data");
						data.put("status", 603);
						data.put("config", "");
						data.put("errorInfo", "未获取到网络设备配置数据");
					}
					// } else {
					// data.put("errorInfo", "未获取到相关kpi数据");
					// }

					System.out.println("netEqpt data：      " + data);
					// 设置参数
					params.addProperty(new ObjectProperty("data", data));
				} catch (JSONException e) {
					e.printStackTrace();
				}

				// 响应动作，即向客户端发送ActionDialect
				// 参数tracker是一次动作的追踪标识符
				this.response(Action.NETEQUIPMENTCONFIG, params);
			} else {
				this.reportHTTPError(Action.NETEQUIPMENTCONFIG);
			}
			break;
		default:
			Logger.w(NetEquipmentConfigListener.class,
					"返回响应码:" + response.getStatus());

			try {
				data = new JSONObject();
				data.put("status", 900);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 设置参数
			params.addProperty(new ObjectProperty("data", data));

			// 响应动作，即向客户端发送 ActionDialect
			this.response(Action.NETEQUIPMENTCONFIG, params);
			break;

		}

	}

}
