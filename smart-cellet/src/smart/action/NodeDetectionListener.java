package smart.action;

import java.util.List;

import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.util.ObjectProperty;
import net.cellcloud.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import smart.core.NodeTraverse;
import smart.mast.action.Action;

/**
 * 节点监测监听器
 * 
 * @author yanbo
 */
public final class NodeDetectionListener extends AbstractListener {

	public NodeDetectionListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		Properties params = new Properties();

		// 获取输入节点
		JSONObject json;
		long nodeOne = 0;
		long nodeTwo = 0;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			nodeOne = json.getLong("nodeOne");
			nodeTwo = json.getLong("nodeTwo");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		List<Long> listOne = NodeTraverse.getInstance().getParents(nodeOne);
		List<Long> listTwo = NodeTraverse.getInstance().getParents(nodeTwo);
		JSONArray ja = new JSONArray();
		if (listOne.size() != 0 && listTwo.size() != 0) {
			for (int i = 0; i < listOne.size(); i++) {
				for (int j = 0; j < listTwo.size(); j++) {
					if (listOne.get(i) == listTwo.get(j)) {
						System.out.println("节点" + nodeOne + "和节点" + nodeTwo
								+ "的公共父节点为：" + listTwo.get(j));
						ja.put(listTwo.get(j));
					}
				}
			}
		}
		
		JSONObject jo = new JSONObject();
		try {
			if (ja.length() == 0) {
				jo.put("status", 601);
				jo.put("errorInfo", "没找到公共节点");
				jo.put("nodeInfo", ja);
			}else{
				jo.put("status", 300);
				jo.put("errorInfo", "");
				jo.put("nodeInfo", ja);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// 设置参数
		params.addProperty(new ObjectProperty("data", jo));

		// 响应动作，即向客户端发送ActionDialect
		// action是一次追踪的动作
		this.response(Action.NODEDETECTION, params);
	}

}
