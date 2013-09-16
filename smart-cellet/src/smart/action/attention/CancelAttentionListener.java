package smart.action.attention;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import smart.action.AbstractListener;
import smart.api.API;
import smart.api.RequestContentCapsule;
import smart.mast.action.Action;

/**
 * 取消关注监听器
 */
public final class CancelAttentionListener extends AbstractListener {

	public CancelAttentionListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 使用同步方式请求
		// 因为onAction方法是由 cell cloud 的action dialect进行回调的
		// 该方法独享一个线程，因此可以在此线程里进行阻塞式调用
		// 这里使用同步方式请求HTTP API

		// URL
		StringBuilder url = new StringBuilder(this.getHost())
				.append(API.CANCELATTENTION);

		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.GET);
		url = null;

		// 获取传递参数
		JSONObject json;
		JSONArray ja;
		String token = null;
		int[] id = new int[7];
		try {
			json = new JSONObject(action.getParamAsString("data"));
			ja = json.getJSONArray("attentionCount");
			token = json.getString("token");

			// 填写数据内容
			RequestContentCapsule capsule = new RequestContentCapsule();
			capsule.append("token", token);
			for (int i = 0; i < ja.length(); i++) {
				id[i] = ja.getJSONObject(i).getInt("attentionId");
				capsule.append("id" + i, id[i]);
			}
			DeferredContentProvider dcp = new DeferredContentProvider();
			dcp.offer(capsule.toBuffer());
			dcp.close();
			request.content(dcp);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Properties params = new Properties();
		if (token != null && !"".equals(token)) {

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

						// 设置参数
						params.addProperty(new ObjectProperty("data", jo));

						// 响应动作，即向客户端发送ActionDialect
						// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
						this.response(token, Action.CANCELATTENTION, params);

					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					this.reportHTTPError(token, Action.CANCELATTENTION);
				}
				break;
			default:
				Logger.w(CancelAttentionListener.class,
						"返回响应码：" + response.getStatus());
				jo = new JSONObject();
				try {
					jo.put("status", 900);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				// 设置参数
				params.addProperty(new ObjectProperty("data", jo));

				// 响应动作，即向客户端发送ActionDialect
				this.response(token, Action.CANCELATTENTION, params);
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
			this.response(Action.CANCELATTENTION, params);
		}
	}

}
