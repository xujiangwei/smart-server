package smart.old.action.alarm;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
import org.json.JSONObject;

import smart.old.action.AbstractListener;
import smart.old.api.API;
import smart.old.api.host.AlarmHostConfig;
import smart.old.api.host.HostConfig;
import smart.old.api.host.HostConfigContext;
import smart.old.mast.action.Action;

/**
 * 轮询获取变动的告警列表
 * 
 * @author yanbo
 * 
 */
public class AlarmChangeListListener extends AbstractListener {

	public AlarmChangeListListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		Executors.newScheduledThreadPool(4).scheduleWithFixedDelay(
				new Runnable() {

					@Override
					public void run() {
						// URL
						HostConfig config = new AlarmHostConfig();
						HostConfigContext context = new HostConfigContext(
								config);
						StringBuilder url = new StringBuilder(
								context.getAPIHost()).append("/").append(
								API.ALARMBUFFER);

						// 创建请求
						Request request = getHttpClient().newRequest(
								url.toString());
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

						JSONObject jo = null;
						switch (response.getStatus()) {
						case HttpStatus.OK_200:
							byte[] bytes = response.getContent();
							if (null != bytes) {

								// 获取从Web服务器上返回的数据
								String content = new String(bytes,
										Charset.forName("UTF-8"));
								try {
									jo = new JSONObject(content);
									if (!jo.get("result").equals("fail"))
									if (jo.getJSONArray("list").length() != 0) {
										jo.remove("result");
										jo.put("status", 300);
										JSONArray ja = jo.getJSONArray("list");
										DateFormat df = new SimpleDateFormat(
												"yyyy-MM-dd HH:mm:ss");
										for (int i = 0; i < ja.length(); i++) {
											JSONObject json = ja.getJSONObject(i);
											if (json.get("cleartime") != null && !json.get("cleartime").equals(null)) {
												json.put("cleartime", df.parse(json.getString("cleartime")).getTime());
											} else {
												json.put("cleartime", 0);
											}
											if (json.get("latesttime") != null && !json.get("latesttime").equals(null)) {
												json.put("latesttime", df.parse(json.getString("latesttime")).getTime());
											} else {
												json.put("latesttime", 0);
											}
											if (json.get("issuppressed").equals(true)) {
												json.put("issuppressed", "Y");
											} else {
												json.put("issuppressed", "N");
											}
										}
										System.out.println("获取的变动数据："+jo);
										// 设置参数
										params.addProperty(new ObjectProperty(
												"data", jo));
										
										// 响应动作，即向客户端发送ActionDialect
										// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
										response(Action.ALARMBUFFER, params);
									} 

								} catch (JSONException e) {
									e.printStackTrace();
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}
							break;
						default:
							Logger.w(AlarmListListener.class, "返回响应码："
									+ response.getStatus());
							jo = new JSONObject();
							try {
								jo.put("status", 900);
							} catch (JSONException e) {
								e.printStackTrace();
							}
							// 设置参数
							params.addProperty(new ObjectProperty("data", jo));

							// 响应动作，即向客户端发送 ActionDialect
							response(Action.ALARMBUFFER, params);
							break;
						}
					}

				}, 60, 60, TimeUnit.SECONDS);
	}

}
