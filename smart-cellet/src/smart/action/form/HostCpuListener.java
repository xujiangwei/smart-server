package smart.action.form;

import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import net.cellcloud.common.Logger;
import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.util.ObjectProperty;
import net.cellcloud.util.Properties;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;

import smart.action.AbstractListener;
import smart.action.LoginListener;
import smart.api.API;
import smart.api.host.HostConfig;
import smart.api.host.HostConfigContext;
import smart.api.host.MonitorSystemHostConfig;
import smart.mast.action.Action;

/**
 * 主机cpu的topN
 * 
 * @author yanbo
 *
 */
public class HostCpuListener extends AbstractListener {

	public HostCpuListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {
		// URL
		HostConfig config = new MonitorSystemHostConfig();
		HostConfigContext context = new HostConfigContext(config);
		StringBuilder url = new StringBuilder(context.getAPIHost()).append("/")
				.append(API.HOSTTOPCPU);

		System.out.println("访问的Url：" + url.toString());

		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.GET);
		url = null;

		Properties params = new Properties();

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

				// 获取从 Web 服务器上返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));
				JSONObject json = (JSONObject) new XMLSerializer().read(content);
				JSONObject job = json.getJSONObject("graphs").getJSONObject("graph");
				JSONObject newJson = new JSONObject();
				newJson.put("alias", job.getString("@gid"));
				newJson.put("title", job.getString("@title"));
				JSONArray ja = job.getJSONArray("value");
				JSONArray newArray = new JSONArray();
				for (int i = 0; i < ja.size(); i++) {
					JSONObject obj = ja.getJSONObject(i);
					obj.put("moId", Long.valueOf(obj.getString("@xid")));
					obj.put("description", obj.getString("@description"));
					obj.put("url", obj.get("@url"));
					obj.put("ip", obj.get("@ip"));
					obj.put("color", obj.get("@color"));
					obj.put("usage", Float.valueOf(obj.getString("#text")));
					
					JSONArray jay = json.getJSONArray("series");
					for (int j = 0; j < jay.size(); j++) {
						JSONObject jot = jay.getJSONObject(j);
						if (jot.getString("@xid").equals(obj.getString("@xid"))) {
							obj.put("moName", jot.getString("#text"));
						}
					}
					
					obj.remove("@xid");
					obj.remove("@description");
					obj.remove("@url");
					obj.remove("@ip");
					obj.remove("@color");
					obj.remove("#text");
					newArray.add(obj);
				}
				newJson.put("list", newArray);
				newJson.put("status", 300);
				System.out.println("原生数据：" + newJson);
				
				// 设置参数
				params.addProperty(new ObjectProperty("data", newJson));

				// 响应动作，即向客户端发送 ActionDialect
				// 参数 tracker 是一次动作的追踪标识，这里可以直接使用用户名。
				this.response(Action.HOSTTOPCPU, params);
			} else {
				this.reportHTTPError(Action.HOSTTOPCPU);
			}

			break;
		default:
			Logger.w(LoginListener.class, "返回响应码：" + response.getStatus());
			jo = new JSONObject();
			jo.put("status", 900);

			// 设置参数
			params.addProperty(new ObjectProperty("data", jo));

			// 响应动作，即向客户端发送 ActionDialect
			// 参数 tracker 是一次动作的追踪标识，这里可以直接使用用户名。
			this.response(Action.HOSTTOPCPU, params);
			break;
		}
	}

}
