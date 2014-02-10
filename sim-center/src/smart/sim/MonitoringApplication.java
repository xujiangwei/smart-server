package smart.sim;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import smart.sim.resource.HostResource;

public class MonitoringApplication extends Application {

	public MonitoringApplication() {
	}

	@Override
    public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/host", HostResource.class);
		return router;
	}
}
