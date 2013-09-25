package smart.action;

import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.util.ObjectProperty;
import net.cellcloud.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import smart.mast.action.Action;

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

		Properties params = new Properties();
		if (token != null) {

			JSONObject data = new JSONObject();
			try {
					int status = 300;
					data.put("status", status);
					data.put("errorInfo", "");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			//设置参数
			params.addProperty(new ObjectProperty("data", data));

			// 响应动作，即向客户端发送 ActionDialect
			// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
			this.response(token, Action.LOGOUT, params);
			
//			// 组装URL
//			StringBuilder url = new StringBuilder(API.LOGOUT);
//
//			// 创建请求
//			Request request = this.getHttpClient().newRequest(url.toString());
//			request.method(HttpMethod.GET);
//			url = null;
//
//			// 填写数据内容
//			DeferredContentProvider dcp = new DeferredContentProvider();
//			RequestContentCapsule capsule = new RequestContentCapsule();
//			capsule.append("token", token);
//			dcp.offer(capsule.toBuffer());
//			dcp.close();
//			request.content(dcp);
//
//			// 发送请求
//			ContentResponse response = null;
//			try {
//				response = request.send();
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			} catch (TimeoutException e1) {
//				e1.printStackTrace();
//			} catch (ExecutionException e1) {
//				e1.printStackTrace();
//			}
//
//			JSONObject jo = null;
//			switch (response.getStatus()) {
//			case HttpStatus.OK_200:
//				byte[] bytes = response.getContent();
//				if (null != bytes) {
//
//					// 获取从web服务器上返回的数据
//					String content = new String(bytes, Charset.forName("UTF-8"));
//					try {
//						jo = new JSONObject(content);
//
//						// 设置参数
//						params.addProperty(new ObjectProperty("data", jo));
//
//						// 响应动作，即向客户端发送 ActionDialect
//						// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
//						this.response(token, Action.LOGOUT, params);
//
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//				} else {
//					this.reportHTTPError(token, Action.LOGOUT);
//				}
//				break;
//			default:
//				Logger.w(LogoutListener.class, "返回响应码：" + response.getContent());
//				jo = new JSONObject();
//				try {
//					jo.put("status", 900);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				// 设置参数
//				params.addProperty(new ObjectProperty("data", jo));
//
//				// 响应动作，即向客户端发送 ActionDialect
//				this.response(token, Action.LOGOUT, params);
//				break;
//			}
//		} else {
//			JSONObject jo = new JSONObject();
//			try {
//				jo.put("status", 800);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			params.addProperty(new ObjectProperty("data", jo));
//			this.response(Action.LOGOUT, params);
		}
	}
}
