package smart.action.alarm;

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
 * 告警基本信息监听
 */
public final class AlarmDetailListener extends AbstractListener {

	public AlarmDetailListener(Cellet cellet) {
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
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		Properties params = new Properties();
		if (token != null && !"".equals(token)) {

			// URL
			StringBuilder url = new StringBuilder(this.getHost())
					.append(API.ALARMDETAIL);

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

//						if (jo.get("baseInfo") != null && !"".equals(jo.get("baseInfo"))) {
//							String moType = jo.getJSONObject("baseInfo").getString("moType");
//							String location = jo.getJSONObject("baseInfo").getString("location");
//							String detail = jo.getJSONObject("baseInfo").getString("detail");
//							String almStatus = jo.getJSONObject("baseInfo").getString("almStatus");
//							long occurTime = jo.getJSONObject("baseInfo").getLong("occurTime");
//							String trend = jo.getJSONObject("baseInfo").getString("trend");
//							int count = jo.getJSONObject("baseInfo").getInt("count");
//							int upgradeCount = jo.getJSONObject("baseInfo").getInt("upgradeCount");
//							String confirmUser = jo.getJSONObject("baseInfo").getString("confirmUser");
//							long confirmTime = jo.getJSONObject("baseInfo").getLong("confirmTime");
//							String delUser = jo.getJSONObject("baseInfo").getString("delUser");
//							long delTime = jo.getJSONObject("baseInfo").getLong("delTime");
//							AlarmManager.getInstance().signInDetail(almId, moType, location, detail, almStatus, occurTime, trend, count, upgradeCount, confirmUser, confirmTime, delUser, delTime);
//						}
						// 设置参数
						params.addProperty(new ObjectProperty("data", jo));

						// 响应动作，即向客户端发送ActionDialect
						// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
						Logger.i(this.getClass(), params.toString());
						this.response(token, Action.ALARMBASEINFO, params);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					this.reportHTTPError(token, Action.ALARMBASEINFO);
				}
				break;
			default:
				Logger.w(AlarmDetailListener.class,
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
				this.response(token, Action.ALARMBASEINFO, params);
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
			this.response(Action.ALARMBASEINFO, params);
		}
	}
}
