package smart.action;

import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import net.cellcloud.common.Logger;
import net.cellcloud.core.Cellet;
import net.cellcloud.talk.TalkService;
import net.cellcloud.talk.TalkTracker;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.util.ObjectProperty;
import net.cellcloud.util.Properties;

import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import smart.monitoring.api.API;
import cn.com.dhcc.mast.action.Action;
import cn.com.dhcc.mast.core.UserManager;

/**
 * 注销
 */
public final class LogoutListener extends AbstractListener {

	public LogoutListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 使用同步请求方式
		// 因为onAction()方法是由cell cloud的action dialect方法调用的
		// 该方法独享一个线程，因此可以在此线程里进行阻塞式调用
		// 因此，这里使用同步请求方式调用HTTP API

		// 获取请求参数token
		JSONObject json;
		String token = null;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			token = json.getString("token");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// 获取客户端的 IP 地址
		TalkTracker talkTracker = TalkService.getInstance().findTracker(
				this.getCellet(), this.getSourceTag());
		String ip = talkTracker.getEndpoint().getCoordinate().getAddress()
				.getAddress().getHostAddress();

		Properties params = new Properties();
		if (token != null
				&& UserManager.getInstance().getName(token, ip) != null) {

			// 组装URL
			StringBuilder url = new StringBuilder(this.getHost())
					.append(API.LOGOUT);

			// 创建请求
			Request request = this.getHttpClient().newRequest(url.toString());
			request.method(HttpMethod.GET);
			url = null;

			// 填写数据内容
			request.param("token", token);

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

					// 获取从web服务器上返回的数据
					String content = new String(bytes, Charset.forName("UTF-8"));
					try {
						jo = new JSONObject(content);
						UserManager um = new UserManager();
						um.userDestoryed(token, ip);

						// 设置参数
						params.addProperty(new ObjectProperty("data", jo));

						// 响应动作，即向客户端发送 ActionDialect
						// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
						this.response(token, Action.LOGOUT, params);

					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					this.reportHTTPError(token, Action.LOGOUT);
				}
				break;
			default:
				Logger.w(LogoutListener.class, "返回响应码：" + response.getContent());
				jo = new JSONObject();
				try {
					jo.put("status", 900);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				// 设置参数
				params.addProperty(new ObjectProperty("data", jo));

				// 响应动作，即向客户端发送 ActionDialect
				this.response(token, Action.LOGOUT, params);
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
			this.response(Action.LOGOUT, params);
		}
	}
}
