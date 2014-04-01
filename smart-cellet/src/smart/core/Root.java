package smart.core;

import java.util.HashMap;

import net.cellcloud.common.LogLevel;
import net.cellcloud.common.Logger;
import net.cellcloud.talk.dialect.ActionDelegate;
import net.cellcloud.talk.dialect.ActionDialect;

import org.eclipse.jetty.client.HttpClient;

import smart.action.ActionListener;
import smart.util.AbstractLifeCycle;
import smart.util.Observable;

/**
 * 根对象。
 * 
 * @author Jiangwei Xu
 *
 */
public final class Root extends AbstractLifeCycle implements ActionDelegate {

	private final static Root instance = new Root();

	private HashMap<String, Observable> observables;

	private HttpClient client;

	private Root() {
		this.observables = new HashMap<String, Observable>();
		this.client = new HttpClient();
	}

	public static Root getInstance() {
		return Root.instance;
	}

	/**
	 * 返回可用的 HTTP 客户端。
	 * @return
	 */
	public HttpClient getHttpClient() {
		return this.client;
	}

	@Override
	protected void doStart() {
		try {
			this.client.setConnectTimeout(10000);
			this.client.start();
		} catch (Exception e) {
			Logger.log(this.getClass(), e, LogLevel.ERROR);
		}
	}

	@Override
	protected void doStop() {
		try {
			this.client.stop();
		} catch (Exception e) {
			Logger.log(this.getClass(), e, LogLevel.ERROR);
		}
	}

	@Override
	public void doAction(ActionDialect dialect) {
		synchronized (this.observables) {
			Observable observable = this.observables.get(dialect.getCelletIdentifier());
			if (null != observable) {
				observable.notifyObservers(dialect.getAction(), dialect);
			}
		}
	}

	/**
	 * 添加监听器。
	 * 
	 * @param cellet
	 * @param listener
	 */
	public void addListener(ActionListener listener) {
		synchronized (this.observables) {
			Observable observable = this.observables.get(listener.getCelletIdentifier());
			if (null != observable) {
				observable.addObserver(listener.getAction(), listener);
			}
			else {
				observable = new Observable();
				observable.addObserver(listener.getAction(), listener);
				this.observables.put(listener.getCelletIdentifier(), observable);
			}
		}
	}
}
