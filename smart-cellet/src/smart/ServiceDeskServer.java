package smart;

import net.cellcloud.core.Cellet;
import net.cellcloud.core.CelletFeature;
import net.cellcloud.core.CelletVersion;

/**
 * 服务台。
 * 
 * @author Jiangwei Xu
 *
 */
public final class ServiceDeskServer extends Cellet {

	public ServiceDeskServer() {
		super(new CelletFeature("ServiceDesk", new CelletVersion(1, 0, 0)));
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}
}
