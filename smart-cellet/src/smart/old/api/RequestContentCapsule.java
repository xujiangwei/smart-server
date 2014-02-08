/*
-------------------------------------------------------------------------------
This source file is part of DHC Monitoring System.
-------------------------------------------------------------------------------
*/

package smart.old.api;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

/** 参数封装。
 */
public final class RequestContentCapsule {

	private JSONObject json;

	public RequestContentCapsule() {
		this.json = new JSONObject();
	}

	public void append(String name, Object value) {
		try {
			this.json.put(name, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public ByteBuffer toBuffer() {
		return ByteBuffer.wrap(this.json.toString().getBytes(Charset.forName("UTF-8")));
	}
}
