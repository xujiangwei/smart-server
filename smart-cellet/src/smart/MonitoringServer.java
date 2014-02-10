package smart;

import net.cellcloud.core.Cellet;
import net.cellcloud.core.CelletFeature;
import net.cellcloud.core.CelletVersion;

/**
 * 监控系统。
 * 
 * @author Jiangwei Xu
 *
 */
public final class MonitoringServer extends Cellet {

	public MonitoringServer(CelletFeature feature) {
		super(new CelletFeature("Monitoring", new CelletVersion(1, 0, 0)));
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}
}
