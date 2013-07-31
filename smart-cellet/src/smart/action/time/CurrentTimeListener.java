package smart.action.time;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.util.ObjectProperty;
import net.cellcloud.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import smart.action.AbstractListener;
import cn.com.dhcc.mast.action.Action;

/**
 * 时间监听器
 */
public final class CurrentTimeListener extends AbstractListener {

	public CurrentTimeListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		Properties params = new Properties();

		// 返回到客户端的数据是服务器当前时间。
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());

		JSONObject json = new JSONObject();
		try {
			json.put("currentTime", time);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// 设置参数
		params.addProperty(new ObjectProperty("data", json));

		// 响应动作，即向客户端发送ActionDialect
		// action是一次追踪的动作
		this.response(Action.CURRENTTIME, params);
	}

}
