package smart.old.action.task;

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

import smart.old.action.AbstractListener;
import smart.old.api.API;
import smart.old.api.RequestContentCapsule;
import smart.old.api.host.HostConfig;
import smart.old.api.host.HostConfigContext;
import smart.old.api.host.ServiceDeskHostConfig;
import smart.old.mast.action.Action;

public class InspectionCiSaveListener extends AbstractListener {

	public InspectionCiSaveListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 使用同步方式进行请求。
		// 因为 onAction 方法是由 Cell Cloud 的 action dialect 进行回调的，
		// 该方法独享一个线程，因此可以在此线程里进行阻塞式的调用。
		// 因此，可以用同步方式请求 HTTP API 。
		Properties params = new Properties();
		// URL
		HostConfig  serviceDeskConfig=new ServiceDeskHostConfig();
		HostConfigContext context=new HostConfigContext(serviceDeskConfig);
		StringBuilder url = new StringBuilder(context.getAPIHost()).append("/").append(API.INSPECTIONCISAVE);
	
		JSONObject json = null;
		String inspectionCiId=null;
		JSONArray report=null;
		

		try {
			json = new JSONObject(action.getParamAsString("data"));
			inspectionCiId=json.getString("inspectionCiId");
			report=json.getJSONArray("report");
		
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
//		url.append("&inspectionCiId=").append(inspectionCiId);
//		url.append("&report=").append(report.toString()).append("'");

		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		System.out.println("巡检项处理提交的URL："+url.toString());
		request.method(HttpMethod.GET);
		url = null;
		
		DeferredContentProvider dcp = new DeferredContentProvider();
		RequestContentCapsule capsule = new RequestContentCapsule();
		capsule.append("inspectionCiId", inspectionCiId);
		capsule.append("report", report);
		System.out.println(capsule.toBuffer().toString());
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
				// 获取从 Web 服务器上返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));
				System.out.println("巡检项保存："+content);
				try {
					jo = new JSONObject(content);
					System.out.println("巡检项保存数据的返回值为：:" + jo);

					// 设置参数
					params.addProperty(new ObjectProperty("data", jo));

					// 响应动作，即向客户端发送 ActionDialect
					this.response(Action.INSPECTIONCISAVE, params);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				this.reportHTTPError(Action.INSPECTIONCISAVE);
			}

			break;
		default:
			Logger.w(IncidentListListener.class,
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
			this.response(Action.INSPECTIONCISAVE, params);
			break;
		}
			
			
	}

}
