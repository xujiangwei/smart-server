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
import net.cellcloud.util.StringProperty;

import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.DeferredContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import smart.api.API;
import smart.api.RequestContentCapsule;
import smart.auth.User;
import smart.core.UserManager;
import cn.com.dhcc.mast.action.Action;

/**
 * 登录监听器。
 */
public final class LoginListener extends AbstractListener {

	public LoginListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 使用同步方式进行请求。
		// 提示：因为 onAction 方法是由 Cell Cloud 的 action dialect 进行回调的，
		// 该方法独享一个线程，因此可以在此线程里进行阻塞式的调用。
		// 因此，这里可以用同步方式请求 HTTP API 。

		// URL
		StringBuilder url = new StringBuilder(this.getHost()).append(API.LOGIN);

		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.POST);
		url = null;

		// 获取用户名和密码
		JSONObject json;
		String username = null;
		String password = null;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			username = json.getString("username");
			password = json.getString("password");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// 获取客户端的 IP 地址
		TalkTracker talkTracker = TalkService.getInstance().findTracker(
				this.getCellet(), this.getSourceTag());
		String ip = talkTracker.getEndpoint().getCoordinate().getAddress()
				.getAddress().getHostAddress();

		// 获取客户端的端口port
		int port = talkTracker.getEndpoint().getCoordinate().getAddress()
				.getPort();

		Properties params = new Properties();

		// 填写数据内容
		DeferredContentProvider dcp = new DeferredContentProvider();
		
		RequestContentCapsule capsule = new RequestContentCapsule();
		capsule.append("username", username);
		capsule.append("password", password);
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

				// 获取从 Web 服务器上返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));

				try {
					jo = new JSONObject(content);
					String token = "";
					if (!"".equals(jo.get("loginInfo"))) {
						String id = jo.getJSONObject("loginInfo").getString(
								"user_id");
						token = jo.getJSONObject("loginInfo")
								.getString("token");
						if (!UserManager.getInstance().isExist(username, password, ip)) {

							// 将登录成功后的返回对象保存到用户管理器
							User user = new User(id);
							user.setName(username);
							user.setPwd(password);
							user.setToken(token);
							user.setIp(ip);
							user.setPort(port);
//							User user = new User(username, password, id, token,
//									ip, port);
							UserManager.getInstance().userCreated(user);

						}
					}
					jo.remove("loginInfo");
					jo.put("token", token);

					// 设置参数
					params.addProperty(new StringProperty("username", username));
					params.addProperty(new ObjectProperty("data", jo));

					// 响应动作，即向客户端发送 ActionDialect
					// 参数 tracker 是一次动作的追踪标识，这里可以直接使用用户名。
					this.response(username, Action.LOGIN, params);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				this.reportHTTPError(username, Action.LOGIN);
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
			this.response(username, Action.LOGIN, params);
			break;
		}
	}
}
