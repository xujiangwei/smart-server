package smart.action.resource;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
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
import smart.api.RequestContentCapsule;
import smart.mast.action.Action;

/**
 * 设备列表监听
 */
public class EquipmentListListener extends AbstractListener {

	public EquipmentListListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {
		// 使用同步的方式进行请求
		// 注意：因为onAction方法是由Cell Cloud的action dialect进行回调的
		// 该方法独享一个线程，因此可以在此线程里进行阻塞式的调用
		// 因此，这里可以用同步的方式请求HTTP API

		// 创建请求
		Request request = this.getHttpClient().newRequest(
						"http://10.10.152.20:8080/itims/restws/model/core/mo/list/998/9980000000000000");
		request.method(HttpMethod.GET);

		// 获取参数
		JSONObject json = null;
		int pageSize = 0;
		int currentIndex = 0;
		String orderBy = null;
		String condition = null;

		try {
			json = new JSONObject(action.getParamAsString("data"));
			System.out.println("参数：" + json);
			pageSize = json.getInt("pageSize");
			currentIndex = json.getInt("currentIndex");
			orderBy = json.getString("orderBy");
			condition = json.getString("condition");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		// 填写数据内容
		DeferredContentProvider dcp = new DeferredContentProvider();

		RequestContentCapsule capsule = new RequestContentCapsule();
		capsule.append("pageSize", pageSize);
		capsule.append("currentIndex", currentIndex);
		capsule.append("orderBy", orderBy);
		capsule.append("condition", condition);
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

				// 获取从Web服务器返回的数据
				String content = new String(bytes, Charset.forName("UTF-8"));
				try {
					data = new JSONObject(content);
					JSONArray ja = new JSONArray();
					System.out.println("结果：" + data);
					if ("success".equals(data.getString("status"))) {
						JSONObject vendor = new JSONObject();
						vendor.put("微软", 1);
						vendor.put("IBM", 2);
						vendor.put("惠普", 3);
						vendor.put("联想", 4);
						vendor.put("戴尔", 5);
						vendor.put("Oracle", 6);
						vendor.put("RedHat", 7);
						vendor.put("Ubuntu", 8);
						vendor.put("Fedora", 9);
						vendor.put("Apple", 10);
						vendor.put("华为", 11);
						vendor.put("思科", 12);
						vendor.put("锐捷", 13);
						vendor.put("中兴", 14);
						vendor.put("H3C", 15);
						vendor.put("Juniper", 16);
						vendor.put("Nortel", 17);
						vendor.put("迈普", 18);
						vendor.put("网捷", 19);
						vendor.put("神州数码", 20);
						vendor.put("AIX", 21);
						vendor.put("Solaris", 22);
						vendor.put("0", 0);

						ja = data.getJSONArray("moList");
						JSONArray jar = new JSONArray();
						List<String> list = Arrays.asList("moId", "typePath",
								"moIp", "moName", "alias", "typeCode",
								"typeName", "vendor", "model", "mgrStatus",
								"almStatus");
						for (int i = 0; i < ja.length(); i++) {
							JSONObject jo = new JSONObject();
							JSONObject job = new JSONObject();
							for (int j = 0; j < ja.getJSONArray(i).length(); j++) {
								for (int k = 0; k < list.size(); k++) {
									if (k == j) {
										if ("vendor".equals(list.get(k))
												&& ("".equals(ja
														.getJSONArray(i).get(j)) || ja
														.getJSONArray(i).get(j) == null)) {
											job.put(list.get(k), "0");
										} else {
											job.put(list.get(k), ja
													.getJSONArray(i).get(j));
										}
									}
								}
							}
							jo.put("moId", job.getLong("moId"));
							if (job.get("vendor") == null
									|| "null".equals(job.get("vendor"))
									|| "null".equals(job.get("vendor"))
									|| job.get("vendor").equals(null)) {
								job.put("vendor", "0");
							}
							jo.put("vendorID",
									vendor.getInt(job.get("vendor").toString()));

							jo.put("typeCode", job.getLong("typeCode"));
							jo.put("typeName",
									job.getString("typePath").split(" > ")[1]);
							job.remove("moId");
							job.remove("vendor");
							job.remove("typeName");
							jo.put("base_info", job);

							jar.put(jo);
						}
						System.out.println("jsonArray:" + jar.length() + jar);
						data.remove("moList");
						data.put("status", 300);
						data.put("moList", jar);
						data.put("errorInfo", "");
					} else {
						data.remove("moList");
						data.put("status", 456);
						data.put("moList", "");
						data.put("errorInfo", "获取设备列表出错");
					}

					// 设置参数
					params.addProperty(new ObjectProperty("data", data));
				} catch (JSONException e) {
					e.printStackTrace();
				}

				// 响应动作，即向客户端发送ActionDialect
				// 参数tracker是一次动作的追踪标识符
				this.response(Action.DEVICE, params);
			} else {
				this.reportHTTPError(Action.DEVICE);
			}
			break;
		default:
			Logger.w(HostListener.class, "返回响应码:" + response.getStatus());
			try {
				data = new JSONObject();
				data.put("status", 900);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 设置参数
			params.addProperty(new ObjectProperty("data", data));

			// 响应动作，即向客户端发送 ActionDialect
			this.response(Action.DEVICE, params);
			break;
		}

	}

}
