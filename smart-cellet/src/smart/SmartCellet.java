package smart;

import net.cellcloud.core.Cellet;
import net.cellcloud.core.CelletFeature;

public class SmartCellet extends Cellet {

	public SmartCellet() {
		super(new CelletFeature("SmartITOM", new Version()));
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}
}
