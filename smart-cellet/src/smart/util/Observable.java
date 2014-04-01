package smart.util;

import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 观察者模式封装。
 * 
 * @author Jiangwei Xu
 *
 */
public class Observable {

	private ConcurrentHashMap<String, InnerObservable> observables;

	public Observable() {
		this.observables = new ConcurrentHashMap<String, InnerObservable>();
	}

	public void addObserver(String name, Observer o) {
		InnerObservable obse = this.observables.get(name);
		if (null != obse) {
			obse.addObserver(o);
		}
		else {
			obse = new InnerObservable();
			obse.addObserver(o);
			this.observables.put(name, obse);
		}
	}

	public void deleteObserver(String name) {
		this.observables.remove(name);
	}

	public void notifyObservers(String name, Object arg) {
		InnerObservable obse = this.observables.get(name);
		if (null != obse) {
			obse.notifyAllObservers(arg);
		}
	}

	/**
	 * 
	 * @author Jiangwei Xu
	 *
	 */
	protected class InnerObservable extends java.util.Observable {
		protected InnerObservable() {
		}

		public void notifyAllObservers(Object arg) {
			this.setChanged();
			this.notifyObservers(arg);
		}
	}
}
