package smart.action.alarm;

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

/**
 * 查看告警维护经验
 */
public final class AlarmExperienceListener extends AbstractListener {

	public AlarmExperienceListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 使用同步方式进行请求
		// 因为onAction方法是由cell cloud 的action dialect 进行回调的，
		// 该方法独享一个线程，因此可以在此线程里进行阻塞式的调用。
		// 设置请求HTTP API方式

		// 获取请求参数
		JSONObject json;
		String token = null;
//		long userId = 0;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			token = json.getString("token");
//			userId = json.getLong("userId");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		Properties params = new Properties();
		if (token != null && !"".equals(token)) {

			// URL
			HostConfig config = new MonitorSystemHostConfig();
			HostConfigContext context = new HostConfigContext(config);
			StringBuilder url = new StringBuilder(context.getAPIHost())
					.append("/").append(API.ALARMEXPERIENCE)
					.append("/get?DMSN=998&userID=9980000000000000").append("&op=get");

			// 创建请求
			Request request = this.getHttpClient().newRequest(url.toString());
			
			System.out.println("维护经验："+url.toString());
			request.method(HttpMethod.GET);
			url = null;

			// 填写数据内容
			DeferredContentProvider dcp = new DeferredContentProvider();
			RequestContentCapsule capsule = new RequestContentCapsule();
//			capsule.append("userId", userId);
			capsule.append("token", token);
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

			JSONObject jo = null;
			switch (response.getStatus()) {
			case HttpStatus.OK_200:
				byte[] bytes = response.getContent();
				if (null != bytes) {

					// 获取从Web服务器上返回的数据
					String content = new String(bytes, Charset.forName("UTF-8"));
					try {
						jo = new JSONObject(content);
						if ("成功".equals(jo.getString("result"))) {
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							JSONArray ja = jo.getJSONArray("list");
							for (int i = 0; i < ja.length(); i++) {
								JSONObject job = ja.getJSONObject(i);
								job.put("dealTime", df.parse(job.getString("foffertime")).getTime());
								job.put("dealUserId", Long.valueOf(job.getString("fofferuserid")));
								job.put("dealInfo", job.getString("fmtnexper"));
								if (job.get("falmtype") == null || job.get("falmtype").equals(null)) {
									job.put("almType", "");
								} else {
									job.put("almType", job.get("falmtype"));
								}
								job.put("moId", Long.valueOf(job.getString("fmoidlist")));
								job.remove("fid");
								job.remove("fverifyuserid");
								job.remove("fmtnexper");
								job.remove("fmotype");
								job.remove("fcause");
								job.remove("fremark");
								job.remove("foffertime");
								job.remove("fmoidlist");
								job.remove("fofferuserid");
								job.remove("fverifytime");
								job.remove("falmtype");
							}
							jo.remove("result");
							jo.put("status", 300);
							jo.put("errorInfo", "");
						} else {
							jo.remove("result");
							jo.put("status", 326);
							jo.put("errorInfo", jo.get("msg"));
						}
						// 设置参数
						params.addProperty(new ObjectProperty("data", jo));

						// 响应动作，即向客户端发送ActionDialect
						// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
						this.response(token, Action.ALARMEXPERIENCE, params);

					} catch (JSONException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					this.reportHTTPError(token, Action.ALARMEXPERIENCE);
				}
				break;
			default:
				Logger.w(AlarmExperienceListener.class,
						"返回响应码：" + response.getStatus());
				jo = new JSONObject();
				try {
					jo.put("status", 900);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				// 设置参数
				params.addProperty(new ObjectProperty("data", jo));

				// 响应动作，即向客户端发送 ActionDialect
				this.response(token, Action.ALARMEXPERIENCE, params);
				break;
			}
		} else {
			JSONObject jo = new JSONObject();
			try {
				jo.put("status", 800);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			params.addProperty(new ObjectProperty("data", jo));
			this.response(Action.ALARMEXPERIENCE, params);
		}
	}
}