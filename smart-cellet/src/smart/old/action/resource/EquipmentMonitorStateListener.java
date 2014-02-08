package smart.old.action.resource;

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
import smart.old.mast.action.Action;

/**
 * 改变监控状态监听器
 */
public final class EquipmentMonitorStateListener extends AbstractListener {

	public EquipmentMonitorStateListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 使用同步的方法进行请求
		// 注意：onAction方法是由Cell Cloud 的action dialect进行回调的
		// 该方法独享一个线程，可以在这里进行阻塞式调用
		// 因此，在这里可以用同步的方式请求HTTP API

		// URL
		StringBuilder url = new StringBuilder(API.EQUIPMENTMONITORSTATE);

		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.POST);
		url = null;

		// 获取参数
		JSONObject json = null;
		long eqptId = 0;
		int isMonitoring = 0;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			eqptId = json.getLong("eqptId");
			isMonitoring = json.getInt("isMonitoring");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// 填写数据内容
		DeferredContentProvider dcp = new DeferredContentProvider();

		RequestContentCapsule capsule = new RequestContentCapsule();
		capsule.append("eqptId", eqptId);
		capsule.append("isMonitoring", isMonitoring);
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
			if (null != bytes) {
				
				// 获取从web服务器上返回的数据
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
				this.response(Action.EQUIPMENTMONITORSTATE, params);
			} else {
				this.reportHTTPError(Action.EQUIPMENTMONITORSTATE);
			}
			break;
		default:
			Logger.w(EquipmentMonitorStateListener.class,
					"返回响应码" + response.getStatus());
			try {
				data = new JSONObject();
				data.put("status", 900);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 设置参数
			params.addProperty(new ObjectProperty("data", data));

			// 响应动作，即向客户端发送 ActionDialect
			this.response(Action.EQUIPMENTMONITORSTATE, params);
			break;
		}
	}

}
