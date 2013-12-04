package smart.action.form;

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
import smart.action.LoginListener;
import smart.api.API;
import smart.api.host.HostConfig;
import smart.api.host.HostConfigContext;
import smart.api.host.MonitorSystemHostConfig;
import smart.mast.action.Action;

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

		JSONObject jo = null;
		switch (response.getStatus()) {
		case HttpStatus.OK_200:
			byte[] bytes = response.getContent();
			if (null != bytes) {

				// 获取从 Web 服务器上返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));

				System.out.println("原生数据：" + content);
				// 设置参数
				params.addProperty(new ObjectProperty("data", content));

				// 响应动作，即向客户端发送 ActionDialect
				// 参数 tracker 是一次动作的追踪标识，这里可以直接使用用户名。
				this.response(Action.HOSTTOPCPU, params);
			} else {
				this.reportHTTPError(Action.HOSTTOPCPU);
			}

			break;
		default:
			Logger.w(LoginListener.class, "返回响应码：" + response.getStatus());
			jo = new JSONObject();
			try {
				jo.put("status", 900);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 设置参数
			params.addProperty(new ObjectProperty("data", jo));

			// 响应动作，即向客户端发送 ActionDialect
			// 参数 tracker 是一次动作的追踪标识，这里可以直接使用用户名。
			this.response(Action.HOSTTOPCPU, params);
			break;
		}
	}

}
