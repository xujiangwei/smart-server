package smart.old.action.alarm;

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
 * 查看告警影响范围
 * 
 * @author yanbo
 */
public final class AlarmCoverageListener extends AbstractListener {

	public AlarmCoverageListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 使用同步方式进行请求
		// 因为onAction方法是由cell cloud 的action dialect 进行回调的，
		// 该方法独享一个线程，因此可以在此线程里进行阻塞式的调用。
		// 设置请求HTTP API方式

		// 获取请求参数
		JSONObject json;
		String token = null;
		long almId = 0;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			token = json.getString("token");
			almId = json.getLong("almId");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Properties params = new Properties();
		if (token != null && !"".equals(token)) {

			// URL
			StringBuilder url = new StringBuilder(API.ALARMCOVERAGE);

			// 创建请求
			Request request = this.getHttpClient().newRequest(url.toString());
			request.method(HttpMethod.GET);
			url = null;

			// 填写数据内容
			DeferredContentProvider dcp = new DeferredContentProvider();

			RequestContentCapsule capsule = new RequestContentCapsule();
			capsule.append("token", token);
			capsule.append("almId", almId);
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

					// 获取从Web服务器上返回的数据
					String content = new String(bytes, Charset.forName("UTF-8"));

					try {
						jo = new JSONObject(content);

//						if (!"".equals(jo.getJSONObject("affectedMoInfo").get(
//								"related"))) {
//							JSONArray ja = jo.getJSONObject("affectedMoInfo")
//									.getJSONArray("related");
//							long moId = jo.getJSONObject("affectedMoInfo")
//									.getLong("moId");
//							for (int i = 0; i < ja.length(); i++) {
//								long relatedMoId = ja.getJSONObject(i).getLong(
//										"relatedMoId");
//								String relatedMoIp = ja.getJSONObject(i)
//										.getString("relatedMoIp");
//								String relatedMoName = ja.getJSONObject(i)
//										.getString("relatedMoName");
//								int level = ja.getJSONObject(i).getInt("level");
//								if (!AlarmManager.getInstance().isExist(moId,
//										relatedMoId)) {
//									AlarmManager.getInstance().signInCoverage(
//											moId, relatedMoId, relatedMoIp,
//											relatedMoName, level);
//								}
//							}
//						}

						// 设置参数
						params.addProperty(new ObjectProperty("data", jo));

						// 响应动作，即向客户端发送ActionDialect
						// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
						this.response(token, Action.ALARMCOVERAGE, params);

					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					this.reportHTTPError(token, Action.ALARMCOVERAGE);
				}
				break;
			default:
				Logger.w(AlarmCoverageListener.class,
						"返回响应码：" + response.getStatus());
				jo = new JSONObject();
				try {
					jo.put("status", 900);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				// 设置参数
				params.addProperty(new ObjectProperty("data", jo));

				// 响应动作，即向客户端发送 ActionDialect
				this.response(token, Action.ALARMCOVERAGE, params);
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
			this.response(Action.ALARMCOVERAGE, params);
		}
	}
}