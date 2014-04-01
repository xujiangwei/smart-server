package smart;

import smart.action.SignInListener;
import smart.core.Root;
import smart.core.UserManager;
import net.cellcloud.core.Cellet;
import net.cellcloud.core.CelletFeature;
import net.cellcloud.core.CelletVersion;
import net.cellcloud.talk.Primitive;
import net.cellcloud.talk.dialect.ActionDialect;

/**
 * 服务台。
 * 
 * @author Jiangwei Xu
 *
 */
public final class ServiceDeskServer extends Cellet {

	public final static String NAME = "ServiceDesk";

	// 产品服务器地址
	public static String ADDRESS = "127.0.0.1";
	// 产品服务器端口
	public static int PORT = 8080;

	public ServiceDeskServer() {
		super(new CelletFeature(ServiceDeskServer.NAME, new CelletVersion(1, 0, 0)));
	}

	@Override
	public void activate() {
		// 启动根对象
		Root.getInstance().start();

		// 启动用户管理器
		UserManager.getInstance().start();

		// 注册监听器
		this.registerListeners();
	}

	@Override
	public void deactivate() {
		// 关闭用户管理器
		UserManager.getInstance().stop();

		// 关闭根对象
		Root.getInstance().stop();
	}

	@Override
	public void dialogue(String tag, Primitive primitive) {
		if (!primitive.isDialectal()) {
			return;
		}

		if (!(primitive.getDialect() instanceof ActionDialect)) {
			return;
		}

		ActionDialect dialect = (ActionDialect) primitive.getDialect();
		dialect.act(Root.getInstance());
	}

	@Override
	public void contacted(String tag) {
		// Nothing
	}

	@Override
	public void quitted(String tag) {
		// Nothing
	}

	private void registerListeners() {
		Root root = Root.getInstance();
		// 登录
		root.addListener(new SignInListener(this));
	}
}
