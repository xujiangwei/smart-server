package smart.action.task;import java.nio.charset.Charset;import java.util.concurrent.ExecutionException;import java.util.concurrent.TimeoutException;import net.cellcloud.common.Logger;import net.cellcloud.core.Cellet;import net.cellcloud.talk.dialect.ActionDialect;import net.cellcloud.util.ObjectProperty;import net.cellcloud.util.Properties;import org.eclipse.jetty.client.api.ContentResponse;import org.eclipse.jetty.client.api.Request;import org.eclipse.jetty.http.HttpMethod;import org.eclipse.jetty.http.HttpStatus;import org.json.JSONException;import org.json.JSONObject;import smart.action.AbstractListener;import smart.api.API;import smart.mast.action.Action;/** * 查看事故列表监听 */public class IncidentListListener extends AbstractListener {	public IncidentListListener(Cellet cellet) {		super(cellet);	}	@Override	public void onAction(ActionDialect action) {				// 使用同步方式进行请求。		// 因为 onAction 方法是由 Cell Cloud 的 action dialect 进行回调的，		// 该方法独享一个线程，因此可以在此线程里进行阻塞式的调用。		// 因此，可以用同步方式请求 HTTP API 。		// URL		StringBuilder url = new StringBuilder("http://10.10.152.84:8080").append(API.INCIDENTLIST);		// 创建请求		Request request = this.getHttpClient().newRequest(url.toString());		request.method(HttpMethod.GET);		Properties params = new Properties();		// 发送请求		ContentResponse response = null;		try {			response = request.send();		} catch (InterruptedException e1) {			e1.printStackTrace();		} catch (TimeoutException e1) {			e1.printStackTrace();		} catch (ExecutionException e1) {			e1.printStackTrace();		}		JSONObject jo = null;				switch (response.getStatus()) {		case HttpStatus.OK_200:			byte[] bytes = response.getContent();			if (null != bytes) {				// 获取从 Web 服务器上返回的数据				String content = new String(bytes, Charset.forName("UTF-8"));				try {					jo = new JSONObject(content);					// 设置参数					params.addProperty(new ObjectProperty("data", jo));					// 响应动作，即向客户端发送 ActionDialect					this.response(Action.INCIDENTLIST, params);				} catch (JSONException e) {					e.printStackTrace();				}			} else {				this.reportHTTPError(Action.INCIDENTLIST);			}			break;		default:			Logger.w(IncidentListListener.class,					"返回响应码：" + response.getStatus());			jo = new JSONObject();			try {				jo.put("status", 900);			} catch (JSONException e) {				e.printStackTrace();			}			// 设置参数			params.addProperty(new ObjectProperty("data", jo));			// 响应动作，即向客户端发送 ActionDialect			this.response(Action.INCIDENTLIST, params);			break;		}	}}