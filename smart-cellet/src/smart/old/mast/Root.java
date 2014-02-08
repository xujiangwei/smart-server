/*
-------------------------------------------------------------------------------
This source file is part of Mast Engine.
-------------------------------------------------------------------------------
*/

package smart.old.mast;

import java.util.HashMap;

import smart.old.mast.core.SystemCategory;

import net.cellcloud.core.Cellet;

/** “桅杆”引擎内核。
 * 
 * @author Jiangwei Xu
 */
public final class Root {

	private final static Root instance = new Root();

	private HashMap<SystemCategory, Cellet> systemCellets;

	private Root() {
		this.systemCellets = new HashMap<SystemCategory, Cellet>();
	}

	public synchronized static Root getInstance() {
		return Root.instance;
	}

	/** 启动系统对应的 Cellet 。
	 */
	public void startup(SystemCategory category, Cellet cellet) {
		this.systemCellets.put(category, cellet);
	}

	/** 关闭引擎系统。
	 */
	public void shutdown() {
		
	}
}
