package smart.action.task;

import java.io.UnsupportedEncodingException;
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
import smart.api.host.HostConfig;
import smart.api.host.HostConfigContext;
import smart.api.host.ServiceDeskHostConfig;
import smart.mast.action.Action;

public class ProblemProcessListener extends AbstractListener{

	public ProblemProcessListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {


		// 使用同步方式进行请求。
		// 因为 onAction 方法是由 Cell Cloud 的 action dialect 进行回调的，
		// 该方法独享一个线程，因此可以在此线程里进行阻塞式的调用。
		// 因此，可以用同步方式请求 HTTP API 。

		// URL
		HostConfig  serviceDeskConfig=new ServiceDeskHostConfig();
		HostConfigContext context=new HostConfigContext(serviceDeskConfig);
		StringBuilder url = new StringBuilder(context.getAPIHost()).append("/").append(API.PROBLEMPROCESS);
	
		JSONObject json = null;
		String bpiId=null;

		String description=null;
		String category=null;
		String urgent=null;
		String impact=null;
		
		String isMajor=null;
		String comment=null;
		String reviewType=null;
		String reason=null;
		
		String resolution=null;
		String closeCode=null;
		String jbpmTransition=null;
		
		try {
			json = new JSONObject(action.getParamAsString("data"));
			bpiId = json.getString("problemId");

			description = json.getString("description");
			category = json.getString("category");
			urgent = json.getString("urgent");
			impact = json.getString("impact");
			closeCode = json.getString("closeCode");
			isMajor = json.getString("isMajor");
			comment = json.getString("comment");
			reviewType = json.getString("reviewType");// 是否有效解决
			reason = json.getString("reason");
			resolution = json.getString("resolution");
			jbpmTransition=json.getString("jbpmTransition");

		} catch (JSONException e) {
			e.printStackTrace();
		} 
			// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		System.out.println("问题工单处理提交的URL："+url.toString());
		request.method(HttpMethod.GET);
		
		DeferredContentProvider dcp = new DeferredContentProvider();
		RequestContentCapsule capsule = new RequestContentCapsule();
		capsule.append("bpiId", bpiId);
		capsule.append("description", description);
		capsule.append("category", category);
		capsule.append("urgent", urgent);
		capsule.append("impact", impact);
		capsule.append("isMajor", isMajor);
		capsule.append("comment", comment);
		capsule.append("reviewType", reviewType);
		capsule.append("reason", reason);
		capsule.append("resolution", resolution);
		capsule.append("closeCode", closeCode);
		capsule.append("jbpmTransition", jbpmTransition);
		dcp.offer(capsule.toBuffer());
		dcp.close();
		request.content(dcp);
	
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
				System.out.println("工单保存："+content);
				try {
					jo = new JSONObject(content);
					System.out.println("响应问题工单保存数据的返回值为：:" + jo);

					// 设置参数
					params.addProperty(new ObjectProperty("data", jo));

					// 响应动作，即向客户端发送 ActionDialect
					this.response(Action.PROBLEMPROCESS, params);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				this.reportHTTPError(Action.PROBLEMPROCESS);
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
			this.response(Action.PROBLEMPROCESS, params);
			break;
		}
			
	}

}
