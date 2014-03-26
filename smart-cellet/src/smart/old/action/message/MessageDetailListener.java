package smart.old.action.message;

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
import org.eclipse.jetty.client.util.DeferredContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import smart.old.action.AbstractListener;
import smart.old.api.API;
import smart.old.api.RequestContentCapsule;
import smart.old.api.host.HostConfig;
import smart.old.api.host.HostConfigContext;
import smart.old.api.host.ServiceDeskHostConfig;
import smart.old.mast.action.Action;

/**
 * 消息详细内容监听器
 * 
 * @author Lianghai Li
 */
public final class MessageDetailListener extends AbstractListener {

	public MessageDetailListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 使用同步的方式进行请求
		// 注意：因为 onAction 方法是由 Cell Cloud 的 action dialect 进行回调的，
		// 该方法独享一个线程，因此可以在此线程里进行阻塞式的调用。
		// 因此，这里可以用同步方式请求 HTTP API 。

		// 获取参数
		JSONObject json = null;
		String messageId = null;
		String token = null;

		try {
			json = new JSONObject(action.getParamAsString("data"));
			System.out.println("params  " + json);
			Logger.i(this.getClass(), json.toString());
			messageId = json.getString("messageId");
			token = json.getString("token");

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// URL
		HostConfig config = new ServiceDeskHostConfig();
		HostConfigContext context = new HostConfigContext(config);
		StringBuilder url = new StringBuilder(context.getAPIHost()).append("/")
				.append(API.MESSAGEDETAIL);

		url.append("?messageId=").append(messageId);

		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.GET);

		DeferredContentProvider dcp = new DeferredContentProvider();
		RequestContentCapsule capsule = new RequestContentCapsule();
		capsule.append("token", token);
		dcp.offer(capsule.toBuffer());
		dcp.close();
		request.content(dcp);
		
		System.out.println("url  "+url);
		
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

		Properties params = new Properties();
		JSONObject data = null;
		switch (response.getStatus()) {
		case HttpStatus.OK_200:
			byte[] bytes = response.getContent();
			if (null != bytes) {
				// 获取从Web服务器上返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));

				System.out.println("源数据-messageDetail：" + content);
				try {
					data = new JSONObject(content);

					if (data.get("success").equals(true)) {
						// 设置参数
						params.addProperty(new ObjectProperty("data", data));
					} else {
						data.remove("root");
						data.put("root", "");
						data.put("status", 412);
						data.put("errorInfo", "未获取到消息数据");
						params.addProperty(new ObjectProperty("data", data));
					}
					System.out.println("msgDetail  " + data);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				// 响应动作，即想客户端发送ActionDialect
				// 参数tracker 是一次动作的追踪表示。
				this.response(Action.MESSAGEDETAIL, params);
			} else {
				this.reportHTTPError(Action.MESSAGEDETAIL);
			}
			break;
		default:
			Logger.w(MessageDetailListener.class,
					"返回响应码" + response.getContent());
			try {
				data = new JSONObject();
				data.put("status", 900);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 设置参数
			params.addProperty(new ObjectProperty("data", data));
			// 响应动作，即向客户端发送 ActionDialect
			this.response(Action.MESSAGEDETAIL, params);
			break;
		}
	}

}
