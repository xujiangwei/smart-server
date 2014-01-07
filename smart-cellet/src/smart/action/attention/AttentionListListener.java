package smart.action.attention;

import java.util.List;

import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.util.ObjectProperty;
import net.cellcloud.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import smart.action.AbstractListener;
import smart.bean.Attention;
import smart.core.AttentionManager;
import smart.mast.action.Action;
import smart.util.DButil;

/**
 * 获取关注列表监听
 * 
 * @author yanbo
 */
public final class AttentionListListener extends AbstractListener {

	public AttentionListListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 获取请求参数
		JSONObject json;
		String token = null;
		long userId = 0;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			token = json.getString("token");
			userId = json.getLong("userId");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Properties params = new Properties();
		if (token != null && !"".equals(token)) {

			JSONObject jo = new JSONObject();
			try {
				if (DButil.getInstance().getConnection() != null) {
					List<Attention> l = AttentionManager.getInstance().getAttention(userId);
					JSONArray ja = null;
					if (l.size() != 0) {
						ja = new JSONArray(l);
						jo.put("status", 300);
						jo.put("dataList", ja);
						jo.put("errorInfo", "");
					} else {
						jo.put("status", 203);
						jo.put("errorInfo", "暂未关注或找不到相应关注列表");
					}
					
				} else {
					jo.put("status", 205);
					jo.put("errorInfo", "数据库连接失败！");
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			// 设置参数
			params.addProperty(new ObjectProperty("data", jo));

			// 响应动作,即向客户端发送ActionDialect
			// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
			this.response(Action.ATTENTIONLIST, params);
		} else {
			this.reportHTTPError(Action.ATTENTIONLIST);
		}
	}
}
