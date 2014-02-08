package smart.old.action.form;

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
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONML;
import org.json.JSONObject;

import smart.old.action.AbstractListener;
import smart.old.action.LoginListener;
import smart.old.api.API;
import smart.old.api.host.HostConfig;
import smart.old.api.host.HostConfigContext;
import smart.old.api.host.MonitorSystemHostConfig;
import smart.old.mast.action.Action;

/**
 * 主机cpu的topN
 * 
 * @author yanbo
 *
 */
public class HostCpuListener extends AbstractListener {

	public HostCpuListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {
		// URL
		HostConfig config = new MonitorSystemHostConfig();
		HostConfigContext context = new HostConfigContext(config);
		StringBuilder url = new StringBuilder(context.getAPIHost()).append("/")
				.append(API.HOSTTOPCPU);

		System.out.println("访问的Url：" + url.toString());

		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.GET);
		url = null;

		Properties params = new Properties();

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

		JSONObject data = null;
		switch (response.getStatus()) {
		case HttpStatus.OK_200:
			byte[] bytes = response.getContent();
			if (null != bytes) {

				// 获取从 Web 服务器上返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));
				JSONObject json = null;
				try {
					json = JSONML.toJSONObject(content);
					JSONArray jsay = json.getJSONArray("childNodes");
					JSONArray dataJsid = new JSONArray();
					JSONArray dataJsif = new JSONArray();
					JSONObject dataJoif = new JSONObject();
					if (jsay.length() > 0) {
						for (int i = 0; i < jsay.length(); i++) {
							if ("series".equals(jsay.getJSONObject(i).get(
									"tagName"))) {

								JSONArray jsid = jsay.getJSONObject(i)
										.getJSONArray("childNodes");

								for (int j = 0; j < jsid.length(); j++) {
									JSONObject dataJoid = new JSONObject();
									JSONObject jsob = jsid.getJSONObject(j);
									dataJoid.put("moId", jsob.get("xid"));
									dataJoid.put("moName",
											jsob.getJSONArray("childNodes")
													.get(0));

									dataJsid.put(dataJoid);
								}

							}

							if ("graphs".equals(jsay.getJSONObject(i).get(
									"tagName"))) {
								JSONArray jsif = jsay.getJSONObject(i)
										.getJSONArray("childNodes");

								for (int j = 0; j < jsif.length(); j++) {
									JSONObject joif = jsif.getJSONObject(j);
									dataJoif.put("title", joif.get("title"));
									dataJoif.put("alais", joif.get("gid"));

									JSONArray jsifAy = joif
											.getJSONArray("childNodes");
									for (int k = 0; k < jsifAy.length(); k++) {
										JSONObject dataJo = new JSONObject();
										JSONObject jode = jsifAy
												.getJSONObject(k);
										dataJo.put("color", jode.get("color"));
										dataJo.put("description",
												jode.get("description"));
										dataJo.put("moId", jode.get("xid"));
										dataJo.put("url", jode.get("url"));
										dataJo.put("ip", jode.get("ip"));
										dataJo.put("usage",
												jode.getJSONArray("childNodes")
														.get(0));

										for (int l = 0; l < dataJsid.length(); l++) {
											JSONObject jo = dataJsid
													.getJSONObject(l);
											if (jo.get("moId").equals(
													dataJo.get("moId"))) {
												dataJo.put("moName",
														jo.get("moName"));
											}
										}

										dataJsif.put(dataJo);
									}
									dataJoif.put("list", dataJsif);
									if (dataJsif.length()>0) {
										dataJoif.put("status", 300);
										dataJoif.put("errorInfo", "");
									} else {
										dataJoif.put("status", 712);
										dataJoif.put("errorInfo",
												"未获取到主机CPU topN数据");
									}
								}
							}
						}
					}
					System.out.println("memtopN：" + dataJoif);

					// 设置参数
					params.addProperty(new ObjectProperty("data", dataJoif));

					// 响应动作，即向客户端发送 ActionDialect
					// 参数 tracker 是一次动作的追踪标识，这里可以直接使用用户名。
					this.response(Action.HOSTTOPCPU, params);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				this.reportHTTPError(Action.HOSTTOPCPU);
			}

			break;
		default:
			Logger.w(LoginListener.class, "返回响应码：" + response.getStatus());
			data = new JSONObject();
			try {
				data.put("status", 900);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 设置参数
			params.addProperty(new ObjectProperty("data", data));

			// 响应动作，即向客户端发送 ActionDialect
			// 参数 tracker 是一次动作的追踪标识，这里可以直接使用用户名。
			this.response(Action.HOSTTOPCPU, params);
			break;
		}
	}

}
