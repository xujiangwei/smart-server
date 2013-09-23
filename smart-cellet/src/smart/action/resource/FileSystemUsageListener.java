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

public class FileSystemUsageListener extends AbstractListener {

	public FileSystemUsageListener(Cellet cellet) {
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
		int rangeInHour = 0;

		try {
			json = new JSONObject(action.getParamAsString("data"));
			System.out.println("参数：" + json);
			moId = json.getInt("moId");
			rangeInHour = json.getInt("rangeInHour");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		// URL
		HostConfig fileSysConfig = new MonitorSystemHostConfig();
		HostConfigContext context = new HostConfigContext(fileSysConfig);
		StringBuilder url = new StringBuilder(context.getAPIHost()).append("/")
				.append(API.FILESYSTEM).append("/").append(moId)
				.append("/fUsedRatio/?rangeInHour=").append(rangeInHour);

		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.GET);

		// 填写数据内容
		DeferredContentProvider dcp = new DeferredContentProvider();
		RequestContentCapsule capsule = new RequestContentCapsule();
		capsule.append("moId", moId);
		capsule.append("rangeInHour", rangeInHour);
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
		switch (response.getStatus()) {
		case HttpStatus.OK_200:
			byte[] bytes = response.getContent();
			if (null != bytes) {

				// 获取从Web服务器返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));
				try {
					data = new JSONObject(content);
					if ("success".equals(data.get("status"))) {
						if (!"".equals(data.get("dataList"))
								&& data.get("dataList") != null) {
							JSONArray ja = data.getJSONArray("dataList");
							DateFormat df = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							for (int i = 0; i < ja.length(); i++) {
								JSONArray ja1 = ja.getJSONObject(i)
										.getJSONArray("data");
								JSONArray ja2 = new JSONArray();

								for (int j = 0; j < ja1.length(); j++) {
									JSONObject jo = new JSONObject();
									jo.put("usage", Float.valueOf(ja1
											.getJSONArray(j).getString(0)));

									jo.put("collectTime",
											df.parse(
													ja1.getJSONArray(j)
															.getString(1))
													.getTime());

									ja2.put(jo);

								}
								ja.getJSONObject(i).remove("data");
								ja.getJSONObject(i).put("data", ja2);
								ja.getJSONObject(i).put("data", ja2);
								String s = ja.getJSONObject(i).getString(
										"moPath");
								ja.getJSONObject(i).put(
										"name",
										s.substring(s.indexOf("> ") + 1,
												s.lastIndexOf("(")));
								ja.getJSONObject(i).remove("kpi");
								ja.getJSONObject(i).remove("kpiName");
								ja.getJSONObject(i).put("kpiName", ja2);
								ja.getJSONObject(i).remove("mosn");
								ja.getJSONObject(i).put("mosn", ja2);
							}
							JSONObject jo = new JSONObject();
							jo.put("dataList", ja);
							jo.put("resourceId", moId);

							data.remove("dataList");
							data.put("data", jo);
							data.put("status", 300);
							data.put("errorInfo", "");
						}
					} else {
						data.put("errorInfo", "未获取到相关kpi数据");
					}

					System.out.println("结果：" + data);
					// 设置参数
					params.addProperty(new ObjectProperty("data", data));
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}

				// 响应动作，即向客户端发送ActionDialect
				// 参数tracker是一次动作的追踪标识符
				this.response(Action.FILESYSTEM, params);
			} else {
				this.reportHTTPError(Action.FILESYSTEM);
			}
			break;
		default:
			Logger.w(HostListener.class, "返回响应码:" + response.getStatus());
			try {
				data = new JSONObject();
				data.put("status", 900);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 设置参数
			params.addProperty(new ObjectProperty("data", data));

			// 响应动作，即向客户端发送 ActionDialect
			this.response(Action.FILESYSTEM, params);
			break;
		}
	}

}
