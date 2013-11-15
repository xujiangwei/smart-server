package smart.action.alarm;

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
import org.json.JSONException;
import org.json.JSONObject;

import smart.action.AbstractListener;
import smart.api.API;
import smart.api.host.HostConfig;
import smart.api.host.HostConfigContext;
import smart.api.host.MonitorSystemHostConfig;
import smart.mast.action.Action;

/**
 * 添加告警维护经验监听
 * 
 * @author Administrator
 * 
 */
public class AddAlarmExperienceListener extends AbstractListener {

	public AddAlarmExperienceListener(Cellet cellet) {
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
		String jsonData = null;
		long userId = 0;
		String note = null;
		
		try {
			json = new JSONObject(action.getParamAsString("data"));
			jsonData = json.getString("json");
			userId = json.getLong("userId");
			note = json.getString("note");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		Properties params = new Properties();
		// URL
		HostConfig config = new MonitorSystemHostConfig();
		HostConfigContext context = new HostConfigContext(config);
		StringBuilder url = new StringBuilder(context.getAPIHost()).append("/")
				.append(API.ALARMEXPERIENCE).append("/").append(jsonData)
				.append("?DMSN=101&userID=").append(userId)
				.append("&op=save&note=").append(note);
		System.out.println("请求的URL: " + url.toString());

		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.GET);
		url = null;

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
					System.out.println("返回：" + jo);
					if (jo.get("result").equals("成功")) {
						jo.remove("result");
						jo.put("status", 300);
						jo.put("errorInfo", "");
					} else {
						jo.remove("result");
						jo.put("status", 376);
						jo.put("errorInfo", jo.get("msg"));
					}
					// 设置参数
					params.addProperty(new ObjectProperty("data", jo));

					// 响应动作，即向客户端发送ActionDialect
					// 参数tracker 是一次动作的追踪标识，这里可以使用访问标识token
					this.response(Action.ADDALARMEXPERIENCE, params);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				this.reportHTTPError(Action.ADDALARMEXPERIENCE);
			}
			break;
		default:
			Logger.w(AddAlarmOpInfoListener.class,
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
			// 参数 tracker 是一次动作的追踪标识，这里可以使用处理类型。
			this.response(Action.ADDALARMEXPERIENCE, params);
			break;
		}
	}
}
