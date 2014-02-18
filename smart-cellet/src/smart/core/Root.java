package smart.core;

import smart.util.Observable;
import net.cellcloud.talk.dialect.ActionDelegate;
import net.cellcloud.talk.dialect.ActionDialect;

/**
 * 根对象。
 * 
 * @author Jiangwei Xu
 *
 */
public final class Root extends Observable implements ActionDelegate {

	private final static Root instance = new Root();

	private Root() {
		
	}

	public static Root getInstance() {
		return Root.instance;
	}

	@Override
	public void doAction(ActionDialect dialect) {
		this.notifyObservers(dialect.getCelletIdentifier(), dialect);
	}
}
