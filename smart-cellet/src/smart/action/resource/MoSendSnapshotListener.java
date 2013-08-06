package smart.action.resource;

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
import cn.com.dhcc.mast.action.Action;

/*
 * 发送快照监听器
 */
public final class MoSendSnapshotListener extends AbstractListener {

	public MoSendSnapshotListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 使用同步的方式进行请求
		// 注意：因为onAction 方法是有cell cloud的action dialect 进行回调的
		// 该方法独享一个县线程，因此可以在此线程里进行阻塞式的调用
		// 因此，这里可以使用同步方式进行请求HTTP API

		// URL
		StringBuilder url = new StringBuilder(this.getHost())
				.append(API.MOSENDSNAPSHOT);
		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.POST);
		url = null;

		// 获取参数
		JSONObject json = null;
		String contents = null;
		String time = null;
		String sender = null;
		String receiver = null;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			contents = json.getString("contents");
			time = json.getString("time");
			sender = json.getString("sender");
			receiver = json.getString("receiver");
		} catch (JSONException jsone) {
			jsone.printStackTrace();
		}

		// 填写数据内容
		DeferredContentProvider dcp = new DeferredContentProvider();

		RequestContentCapsule capsule = new RequestContentCapsule();
		capsule.append("contents", contents);
		capsule.append("time", time);
		capsule.append("sender", sender);
		capsule.append("receiver", receiver);
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

		Properties params = new Properties();
		JSONObject data = null;
		switch (response.getStatus()) {
		case HttpStatus.OK_200:
			byte[] bytes = response.getContent();
			Logger.i(this.getClass(),
					"\r\n" + new String(bytes, Charset.forName("gb2312")));
			if (null != bytes) {
				// 获取从web服务器上返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));
				try {
					data = new JSONObject(content);
					System.out.println("moSendSnapshot<---" + data);
					// 设置参数
					params.addProperty(new ObjectProperty("data", data));
				} catch (JSONException jsone) {
					jsone.printStackTrace();
				}

				// 响应动作，即向客户端发送ActionDialect
				// 参数tracker 是一次动作的追踪标识
				this.response(Action.MOSENDSNAPSHOT, params);
			} else {
				this.reportHTTPError(Action.MOSENDSNAPSHOT);
			}
			break;
		default:
			Logger.w(MoSendSnapshotListener.class,
					"返回响应码：" + response.getStatus());
			try {
				data = new JSONObject();
				data.put("status", 900);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 设置参数
			params.addProperty(new ObjectProperty("data", data));

			// 响应动作，即向客户端发送 ActionDialect
			this.response(Action.MOSENDSNAPSHOT, params);
			break;
		}
	}
}
