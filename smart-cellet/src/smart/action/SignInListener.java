package smart.action;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import net.cellcloud.common.LogLevel;
import net.cellcloud.common.Logger;
import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;

import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用户签入系统。
 * 
 * @author Jiangwei Xu
 *
 */
public final class SignInListener extends ActionListener {

	public SignInListener(Cellet cellet) {
		super(cellet, "SignIn");
	}

	@Override
	public void doAction(ActionDialect dialect) {
		// 获取用户名密码
		String username = null;
		String password = null;

		try {
			JSONObject json = new JSONObject(dialect.getParamAsString("data"));
			username = json.getString("username");
			password = json.getString("password");
		} catch (JSONException e) {
			Logger.log(this.getClass(), e, LogLevel.ERROR);
		}

		// 组装 URL
		StringBuilder url = new StringBuilder();
		url.append("http://").append(this.getServerAddress()).append(":").append(this.getServerPort());

		try {
			// 组织发给服务器的数据
			JSONObject body = new JSONObject();
			body.append("username", username);
			body.append("password", password);
			StringContentProvider cp = new StringContentProvider(body.toString());

			// 发送请求
			ContentResponse response = this.getHttpClient().newRequest(url.toString())
				.method(HttpMethod.POST)
				.content(cp)
				.send();

			// 处理结果
			switch (response.getStatus()) {
			case HttpStatus.OK_200:
				// TODO 组织返回数据
				JSONObject data = new JSONObject();
				ActionDialect ret = new ActionDialect();
				ret.setAction(this.getAction());
				ret.appendParam("data", data.toString());
				// 返回数据给客户端
				this.respondRemote(ret);
				break;
			default:
				break;
			}
		} catch (InterruptedException | TimeoutException | ExecutionException e) {
			Logger.log(this.getClass(), e, LogLevel.ERROR);
		} catch (JSONException e) {
			Logger.log(this.getClass(), e, LogLevel.ERROR);
		}
	}
}
