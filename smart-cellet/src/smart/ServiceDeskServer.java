package smart;

import smart.core.Root;
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

	public ServiceDeskServer() {
		super(new CelletFeature(ServiceDeskServer.NAME, new CelletVersion(1, 0, 0)));
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
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
}
