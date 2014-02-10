package smart.sim.resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

public class HostResource extends BaseResource {

	public HostResource() {
	}

	@Override
    protected void doInit() throws ResourceException {
		
	}

	@Override
	public Representation get() {

		JSONObject obj = new JSONObject();
		try {
			obj.put("name", "Ambrose");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		JsonRepresentation representation = new JsonRepresentation(obj);
		return representation;
	}
}
