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
import cn.com.dhcc.mast.action.Action;

/**
 * 添加联系人监听器
 * 
 */
public final class MessageContactsListener extends AbstractListener {

	public MessageContactsListener(Cellet cellet) {
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
				.append(API.MESSAGECONTACTS);

		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.GET);
		url = null;

		// 获取参数
		JSONObject json = null;
		int pageSize = 0;
		int currentIndex = 0;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			pageSize = json.getInt("pageSize");
			currentIndex = json.getInt("currentIndex");
		} catch (JSONException jsone) {
			jsone.printStackTrace();
		}

		// 填写数据内容
		DeferredContentProvider dcp = new DeferredContentProvider();

		RequestContentCapsule capsule = new RequestContentCapsule();
		capsule.append("pageSize", pageSize);
		capsule.append("currentIndex", currentIndex);
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
			if (bytes != null) {
				
				// 获取从服务器上返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));
				try {
					data = new JSONObject(content);
					
					// 设置参数
					params.addProperty(new ObjectProperty("data", data));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				// 响应动作，即向客户端发送ActionDialect
				// 参数tracker 是一次动作的追踪标识符
				this.response(Action.MESSAGECONTACTS, params);
			} else {
				this.reportHTTPError(Action.MESSAGECONTACTS);
			}
			break;
		default:
			Logger.w(MessageContactsListener.class,
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
			this.response(Action.MESSAGECONTACTS, params);
			break;
		}
	}
}
