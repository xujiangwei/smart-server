package smart.old.util.component;

import java.util.EventListener;

/**
 * 生命周期监听器。
 * 
 * @author Jiangwei Xu
 *
 */
public interface LifeCycleListener extends EventListener {

	public void lifeCycleStarted(LifeCycle event);

	public void lifeCycleStarting(LifeCycle event);

	public void lifeCycleStopped(LifeCycle event);

	public void lifeCycleStopping(LifeCycle event);

	public void lifeCycleFailure(LifeCycle event, Throwable cause);
}
