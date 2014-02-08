/*
-------------------------------------------------------------------------------
This source file is part of Mast Engine.
-------------------------------------------------------------------------------
*/

package smart.old.mast.action;

import net.cellcloud.talk.dialect.ActionDialect;

/** 动作监听器。
 * 
 * @author Jiangwei Xu
 */
public interface ActionListener {

	/** 设置动作发起的源标签。
	 */
	public void setSourceTag(String tag);

	/** 返回动作发起的源标签。
	 */
	public String getSourceTag();

	/** 当动作发生时被调用的回调方法。
	 */
	public void onAction(ActionDialect action);
}
