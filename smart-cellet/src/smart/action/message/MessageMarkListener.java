package smart.action.message;

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

import smart.action.AbstractListener;
import smart.api.API;
import smart.api.RequestContentCapsule;
import smart.mast.action.Action;

/**
 * 消息标记监听
 *
 */
public final class MessageMarkListener extends AbstractListener {

	public MessageMarkListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {
		// 使用同步的方法进行请求
		// 因为onAction 是由cell cloud 的 action dialect进行回调的
		// 该方法独享一个线程，因此可以在次线程里进行阻塞式调用
		// 因此，可以用同步的方式请求HTTP API

		// URL
		StringBuilder url = new StringBuilder(this.getHost())
				.append(API.MESSAGEMARK);
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.POST);
		url = null;

		// 获取参数
		JSONObject json = null;
		long messageId = 0;
		int markId = 0;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			messageId = json.getLong("messageId");
			markId = json.getInt("markId");
		} catch (JSONException jsone) {
			jsone.printStackTrace();
		}

		// 填写数据内容
		DeferredContentProvider dcp = new DeferredContentProvider();

		RequestContentCapsule capsule = new RequestContentCapsule();
		capsule.append("messageId", messageId);
		capsule.append("markId", markId);
		dcp.offer(capsule.toBuffer());
		dcp.close();
		request.content(dcp);

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
			if (bytes != null) {
				// 获取从web服务器上返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));
				try {
					data = new JSONObject(content);
					System.out.println("dealMark" + data);
					// 设置参数
					params.addProperty(new ObjectProperty("data", data));
				} catch (JSONException jsone) {
					jsone.printStackTrace();
				}

				// 响应动作，即向客户端发送ActionDialect
				// 参数tracker是一次动作的追踪标识
				this.response(Action.MESSAGEMARK, params);
			} else {
				this.reportHTTPError(Action.MESSAGEMARK);
			}
			break;
		default:
			Logger.w(MessageMarkListener.class,
					"返回响应码：" + request.getContent());
			data = new JSONObject();
			try {
				data.put("status", 900);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 设置参数
			params.addProperty(new ObjectProperty("data", data));
			// 响应动作，即向客户端发送 ActionDialect
			this.response(Action.MESSAGEMARK, params);
			break;
		}
	}
}
