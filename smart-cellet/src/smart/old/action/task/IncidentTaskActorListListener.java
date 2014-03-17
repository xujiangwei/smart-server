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
import org.json.JSONException;
import org.json.JSONObject;

import smart.old.action.AbstractListener;
import smart.old.api.API;
import smart.old.api.RequestContentCapsule;
import smart.old.api.host.HostConfig;
import smart.old.api.host.HostConfigContext;
import smart.old.api.host.ServiceDeskHostConfig;
import smart.old.mast.action.Action;


/** 
* @Description :  获取故障工单参与人
* @author      :  dwg
* @date        :  Oct 22, 2013 11:07:11 AM 
*  
*/
public final class IncidentTaskActorListListener extends AbstractListener {


	public IncidentTaskActorListListener(Cellet cellet) {
		super(cellet);
	}
	
	@Override
	public void onAction(ActionDialect action) {

		HostConfig  serviceDeskConfig=new ServiceDeskHostConfig();
		HostConfigContext context=new HostConfigContext(serviceDeskConfig);
		StringBuilder url = new StringBuilder(context.getAPIHost()).append("/").append(API.TASKACTORLIST);
	
		JSONObject json = null;
		String bpiId=null;
		String jbpmTransition=null;
		String token=null;
		
		try {
			 json = new JSONObject(action.getParamAsString("data"));
			 bpiId=json.getString("bpiId");
			 jbpmTransition=json.getString("jbpmTransition");
			 token=json.getString("token");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// 创建请求
		Request request = this.getHttpClient().newRequest(url.toString());
		request.method(HttpMethod.GET);
		
		DeferredContentProvider dcp = new DeferredContentProvider();
		RequestContentCapsule capsule = new RequestContentCapsule();
		capsule.append("bpiId", bpiId);
		capsule.append("jbpmTransition", jbpmTransition);
		capsule.append("token", token);
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
				try {
					jo = new JSONObject(content);
					java.util.logging.Logger logger=java.util.logging.Logger.getLogger("smart-cellet");
					logger.info("获取节点下一步处理人：:" + jo);
					// 设置参数
					params.addProperty(new ObjectProperty("data", jo));

					// 响应动作，即向客户端发送 ActionDialect
					this.response(Action.TASKACTORLIST, params);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				this.reportHTTPError(Action.TASKACTORLIST);
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
			this.response(Action.TASKACTORLIST, params);
			break;
		}
	}

}
