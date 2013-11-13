package smart.action.alarm;

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

import smart.action.AbstractListener;
import smart.api.API;
import smart.api.host.AlarmHostConfig;
import smart.api.host.HostConfig;
import smart.api.host.HostConfigContext;
import smart.mast.action.Action;

/**
 * 轮询获取变动的告警列表
 * 
 * @author Administrator
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
									// JSONArray ja = new JSONArray();
									// if ("成功".equals(jo.get("result"))) {
									// ja = jo.getJSONArray("almlist");
									// JSONArray jar = new JSONArray();
									// List<String> list =
									// Arrays.asList("almId", "moId",
									// "rootMoId", "parentMoId", "typeCode",
									// "almCause", "isSuppressed", "severity",
									// "extraInfo", "almStatus", "trend",
									// "occurTime",
									// "lastTime", "count", "detail",
									// "originalInfo",
									// "confirmTime", "confirmUserId",
									// "confirmUser",
									// "moIp", "moName", "causeAlias",
									// "location");
									// int m = 0;
									// if (ja.length() > 18) {
									// m = 18;
									// } else {
									// m = ja.length();
									// }
									// for (int i = 0; i < m; i++) {
									// JSONObject job = new JSONObject();
									//
									// for (int j = 0; j <
									// ja.getJSONArray(i).length(); j++) {
									// for (int k = 0; k < list.size(); k++) {
									// if (k == j) {
									// String key = list.get(k);
									// String value = ja.getJSONArray(i)
									// .get(j).toString();
									// if ("almId".equals(key)
									// || "moId".equals(key)
									// || ("confirmUserId".equals(key) && (!""
									// .equals(value)))) {
									// job.put(key, Long.valueOf(value));
									// } else if ("occurTime".equals(key)
									// || "lastTime".equals(key)
									// || ("confirmTime".equals(key) && !""
									// .equals(value))) {
									// DateFormat df = new SimpleDateFormat(
									// "yyyy-MM-dd HH:mm:ss");
									// Date date = null;
									// date = df.parse(value);
									// job.put(key, date.getTime());
									// } else if ("count".equals(key)
									// || "severity".equals(key)) {
									// job.put(key,
									// Integer.parseInt(value));
									// } else if (("confirmUserId".equals(key)
									// || "confirmTime"
									// .equals(key))
									// && "".equals(value)) {
									// job.put(key, 0);
									// } else {
									// job.put(key, value);
									// }
									// }
									// }
									// }
									// jar.put(job);
									// }
									// System.out.println("最新的" + jar.length() +
									// ": "
									// + jar);
									// jo.remove("result");
									// jo.remove("almlist");
									// jo.put("almList", jar);
									// jo.put("status", 300);
									// jo.put("errorInfo", "");
									// } else {
									// jo.remove("result");
									// jo.remove("almlist");
									// jo.put("almList", "");
									// jo.put("status", 301);
									// jo.put("errorInfo", "找不到符合条件的相关告警列表");
									// }

									if (jo.getJSONArray("list").length() != 0) {
										JSONArray jay = new JSONArray();
										JSONArray ja = jo.getJSONArray("list");
										DateFormat df = new SimpleDateFormat(
												"yyyy-MM-dd HH:mm:ss");
										for (int i = 0; i < ja.length(); i++) {
											JSONObject jt = ja.getJSONObject(i);
											JSONObject job = new JSONObject();
											job.put("almId", Long.valueOf(jt.getString("falmsn")));
											job.put("moId", Long.valueOf(jt.getString("mosn")));
											job.put("rootMoId", Long.valueOf(jt.getString("frmosn")));
											job.put("parentMoId", Long.valueOf(jt.getString("fpmosn")));
											job.put("typeCode", Long.valueOf(jt.getString("fmotype").substring(0, 4)));
											job.put("almCause", jt.getString("fcause"));
											job.put("isSuppressed", jt.getString("fsuppressed"));
											job.put("severity", Integer.valueOf(jt.getString("fseverity")));
											job.put("extraInfo", jt.getString("faddinfo"));
											job.put("almStatus", jt.getString("fstatus"));
											job.put("trend", jt.getString("ftrend"));
											job.put("occurTime", df.parse(jt.getString("foccurtime")).getTime());
											job.put("lastTime", df.parse(jt.getString("flasttime")).getTime());
											job.put("count", jt.getString("fcount"));
											job.put("detail", jt.get("fdetail"));
											job.put("originalInfo", jt.getString("forigininfo"));
											if (!jt.get("fconfirmtime").equals(null)) {
												job.put("confirmTime", df.parse(jt.getString("fconfirmtime")).getTime());
											} else {
												job.put("confirmTime", 0);
											}
											if (!jt.get("fconfirmuserid").equals(null)) {
												job.put("confirmUserId", Long.valueOf(jt.getString("fconfirmuserid")));
											} else {
												job.put("confirmUserId", jt.get("fconfirmuserid"));
											}
											job.put("moIp", jt.get("fmoip"));
											job.put("moName", jt.get("fmoalias"));
											job.put("causeAlias", jt.get("fcausealias"));
											job.put("location", jt.get("falmlocator"));
											jay.put(job);
										}
										jo.remove("list");
										System.out.println("获取的变动数据："+jay);
										jo.put("almList", jay);
										// 设置参数
										params.addProperty(new ObjectProperty(
												"data", jo));
										
										// 响应动作，即向客户端发送ActionDialect
										// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
										response(Action.ALARMLIST, params);
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
							response(Action.ALARMLIST, params);
							break;
						}
					}

				}, 10, 8, TimeUnit.SECONDS);
	}

}
