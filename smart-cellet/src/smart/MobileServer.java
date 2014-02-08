package smart;

import net.cellcloud.core.Cellet;
import net.cellcloud.core.CelletFeature;
import net.cellcloud.core.CelletVersion;

public final class MobileServer extends Cellet {

	public MobileServer() {
		super(new CelletFeature("MobileServer", new CelletVersion(1, 0, 0)));
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}
}
