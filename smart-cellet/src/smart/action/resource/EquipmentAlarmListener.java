package smart.action.resource;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import net.cellcloud.common.Logger;
import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.util.ObjectProperty;
import net.cellcloud.util.Properties;

import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import smart.action.AbstractListener;
import smart.api.API;
import smart.api.host.HostConfig;
import smart.api.host.HostConfigContext;
import smart.api.host.MonitorSystemHostConfig;
import smart.mast.action.Action;

/**
 * 获取设备告警监听器
 */
public final class EquipmentAlarmListener extends AbstractListener {

	public EquipmentAlarmListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 使用同步的方式进行请求
		// 注意：因为 onAction 方法是由 Cell Cloud 的 action dialect 进行回调的，
		// 该方法独享一个线程，因此可以在此线程里进行阻塞式的调用。
		// 因此，这里可以用同步方式请求 HTTP API

		// 获取参数
		JSONObject json = null;
		long moId = 0;
		int pageSize = 0;
		int currentIndex = 0;

		try {
			json = new JSONObject(action.getParamAsString("data"));
			moId = json.getLong("moId");
			if (json.has("pageSize") && json.has("currentIndex")) {
				pageSize = json.getInt("pageSize");
				currentIndex = json.getInt("currentIndex");
			} else {
				pageSize = 0;
				currentIndex = 0;
			}

		} catch (JSONException jsone) {
			jsone.printStackTrace();
		}

		// url
		HostConfig cpuConfig = new MonitorSystemHostConfig();
		HostConfigContext context = new HostConfigContext(cpuConfig);
		StringBuilder url = null;
		if (pageSize != 0 && currentIndex != 0) {
			url = new StringBuilder(context.getAPIHost()).append("/")
					.append(API.EQUIPMENTALARMS).append(moId)
					.append("&pageSize=").append(pageSize)
					.append("&currentIndex=").append(currentIndex);
		} else {
			url = new StringBuilder(context.getAPIHost()).append("/")
					.append(API.EQUIPMENTALARMS).append(moId);
		}

		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.GET);

		/**
		 * // 填写数据内容 DeferredContentProvider dcp = new
		 * DeferredContentProvider();
		 * 
		 * RequestContentCapsule capsule = new RequestContentCapsule();
		 * capsule.append("moId", moId); dcp.offer(capsule.toBuffer());
		 * dcp.close(); request.content(dcp);
		 */

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
		switch (response.getStatus()) {
		case HttpStatus.OK_200:
			byte[] bytes = response.getContent();
			if (null != bytes) {
				// 获取从web服务器上返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));
				try {
					data = new JSONObject(content);
					System.out.println("eqptAlarm 源数据      " + data);
					if ("success".equals(data.get("status"))) {
						if (!"".equals(data.get("stat"))
								&& null != data.get("stat")) {
							DateFormat df = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");

							JSONObject stat = data.getJSONObject("stat");
							stat.put("statTime",
									df.parse(stat.getString("statTime"))
											.getTime());
							JSONObject statData = stat
									.getJSONObject("statData");

							statData.put("moId", statData.get("MOSN"));
							statData.remove("MOSN");
							statData.put("moType", Long.parseLong(statData
									.getString("moType")));
							statData.put("rMOType", Long.parseLong(statData
									.getString("rMOType")));
							if (statData.has("occurTime")) {
								statData.put(
										"occurTime",
										df.parse(
												statData.getString("occurTime"))
												.getTime());
							}

						}
					} else {
						data.put("stat", "");
						data.put("status", 616);
						data.put("errorInfo", "没有告警统计数据");
					}

					// 设置参数
					params.addProperty(new ObjectProperty("data", data));
					System.out.println("eqptAlarm   " + data);
					// 响应动作，即向客户端发送ActionDialect
					// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
					this.response(Action.EQUIPMENTALARMS, params);

				} catch (JSONException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				this.reportHTTPError(Action.EQUIPMENTALARMS);
			}
			break;
		default:
			Logger.w(EquipmentAlarmListener.class,
					"返回响应码" + response.getStatus());
			try {
				data = new JSONObject();
				data.put("status", 900);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 设置参数
			params.addProperty(new ObjectProperty("data", data));

			// 响应动作，即向客户端发送 ActionDialect
			this.response(Action.EQUIPMENTALARMS, params);
			break;
		}
	}

}
