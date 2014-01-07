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
 * 告警级别统计监听
 * 
 * @author yanbo
 */
public final class AlarmLevelListener extends AbstractListener {

	public AlarmLevelListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {
		// URL
		HostConfig config = new MonitorSystemHostConfig();
		HostConfigContext context = new HostConfigContext(config);
		StringBuilder url = new StringBuilder(context.getAPIHost()).append("/")
				.append(API.ALARMLEVEL);
		
		System.out.println("URL: "+url.toString());

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

		JSONObject jo = null;
		switch (response.getStatus()) {
		case HttpStatus.OK_200:
			byte[] bytes = response.getContent();
			if (null != bytes) {

				// 获取从Web服务器上返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));
				try {
					jo = new JSONObject(content);
					
					if ("success".equals(jo.getString("status"))) {
						jo.put("status", 300);
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String s = jo.getJSONObject("stat").getString("statTime");
						jo.getJSONObject("stat").put("statTime", df.parse(s).getTime());
						JSONObject json = jo.getJSONObject("stat").getJSONObject("statData");
						for (int i = 1; i < 6; i++) {
							if (json.getJSONObject("severity"+i).has("occurTime")) {
								String str = json.getJSONObject("severity"+i).getString("occurTime");
								json.getJSONObject("severity"+i).put("occurTime", df.parse(str).getTime());
							}
						}
						jo.put("errorInfo", "");
					} else {
						jo.put("status", 395);
						jo.put("errorInfo", jo.getString("errMsg"));
						jo.remove("errMsg");
					}
					
					// 设置参数
					params.addProperty(new ObjectProperty("data", jo));

					// 响应动作，即向客户端发送ActionDialect
					// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
					this.response(Action.ALARMLEVEL, params);

				} catch (JSONException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				this.reportHTTPError(Action.ALARMLEVEL);
			}
			break;
		default:
			Logger.w(AlarmListListener.class, "返回响应码：" + response.getStatus());
			jo = new JSONObject();
			try {
				jo.put("status", 900);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			// 设置参数
			params.addProperty(new ObjectProperty("data", jo));

			// 响应动作，即向客户端发送 ActionDialect
			this.response(Action.ALARMLEVEL, params);
			break;
		}
	}
}
