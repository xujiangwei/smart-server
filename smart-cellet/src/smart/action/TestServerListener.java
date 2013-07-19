package smart.action;

import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.util.ObjectProperty;
import net.cellcloud.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import cn.com.dhcc.mast.action.Action;

/**
 * 测试服务器监听器
 */
public final class TestServerListener extends AbstractListener {

	public TestServerListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 进入此listener说明已经连上服务器直接返回成功状态
		JSONObject jo = new JSONObject();
		try {
			jo.put("status", 300);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Properties params = new Properties();

		// 设置参数
		params.addProperty(new ObjectProperty("data", jo));

		// 响应动作，即向客户端发送ActionDialect
		// action是一次追踪的动作
		this.response(Action.TESTSERVER, params);

	}

}
