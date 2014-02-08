package smart.old.action.attention;

import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.util.ObjectProperty;
import net.cellcloud.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import smart.old.action.AbstractListener;
import smart.old.core.AttentionManager;
import smart.old.mast.action.Action;
import smart.old.util.DButil;

/**
 * 添加关注监听
 * 
 * @author yanbo
 */
public final class AddAttentionListener extends AbstractListener {

	public AddAttentionListener(Cellet cellet) {
		super(cellet);
	}

	@Override
	public void onAction(ActionDialect action) {

		// 获取请求参数token
		JSONObject json;
		String token = null;
		long userId = 0;
		long moId = 0;
		try {
			json = new JSONObject(action.getParamAsString("data"));
			token = json.getString("token");
			userId = json.getLong("userId");
			moId = json.getLong("moId");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		Properties params = new Properties();
		if (token != null && !"".equals(token)) {

			JSONObject jo = new JSONObject();
			try {
				if (DButil.getInstance().getConnection() != null) {
					boolean b = AttentionManager.getInstance().addAttention(
							userId, moId);
					if (b) {
						jo.put("status", 300);
						jo.put("errorInfo", "");
					} else {
						jo.put("status", 204);
						jo.put("errorInfo", "添加失败！");
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
			this.response(Action.ADDATTENTION, params);
		}
	}
}
