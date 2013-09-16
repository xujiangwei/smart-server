/*
-------------------------------------------------------------------------------
This source file is part of Mast Engine.
-------------------------------------------------------------------------------
*/

package smart.mast.action;

import java.util.TreeMap;
import java.util.Vector;

import net.cellcloud.talk.dialect.ActionDelegate;
import net.cellcloud.talk.dialect.ActionDialect;
import smart.mast.core.SystemCategory;

/** 动作分发器。
 * 
 * @author Jiangwei Xu
 */
public final class ActionDispatcher implements ActionDelegate {

	private final static ActionDispatcher instance = new ActionDispatcher();

	private TreeMap<String, Vector<ActionListener>> listeners;

	private ActionDispatcher() {
		this.listeners = new TreeMap<String, Vector<ActionListener>>();
	}

	public synchronized static ActionDispatcher getInstance() {
		return ActionDispatcher.instance;
	}

	/** 添加监听器。
	 */
	public synchronized void addListener(String action, ActionListener listener) {
		if (this.listeners.containsKey(action)) {
			Vector<ActionListener> list = this.listeners.get(action);
			list.add(listener);
		}
		else {
			Vector<ActionListener> list = new Vector<ActionListener>();
			list.add(listener);
			this.listeners.put(action, list);
		}
	}

	/** 分发
	 */
	public void dispatch(SystemCategory system, ActionDialect action) {
		action.setCustomContext(system);
		action.act(this);
	}

	@Override
	public void doAction(ActionDialect dialect) {
		Vector<ActionListener> listeners = this.listeners.get(dialect.getAction());
		if (null != listeners) {
			for (ActionListener listener : listeners) {
				listener.setSourceTag(dialect.getOwnerTag());
				listener.onAction(dialect);
			}
		}
	}
}
