package smart.old.action.attention;

import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.util.ObjectProperty;
import net.cellcloud.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import smart.old.action.AbstractListener;
import smart.old.core.AttentionManager;
import smart.old.mast.action.Action;
import smart.old.util.DButil;

/**
 * 取消关注监听器
 * 
 * @author yanbo
 */
public final class CancelAttentionListener extends AbstractListener {

	public CancelAttentionListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 获取传递参数
		JSONObject json;
		JSONArray ja = null;
		String token = null;
		long userId = 0;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			ja = json.getJSONArray("attentionId");
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
					AttentionManager.getInstance().cancelAttention(ja, userId);
					jo.put("status", 300);
					jo.put("errorInfo", "");
				} else {
					jo.put("status", 204);
					jo.put("errorInfo", "取消关注失败！");
				}

				// 设置参数
				params.addProperty(new ObjectProperty("data", jo));

				// 响应动作，即向客户端发送ActionDialect
				// 参数tracker是一次动作的追踪标识，这里可以使用访问标记token
				this.response(Action.CANCELATTENTION, params);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	}

}
