package smart.old.util.component;

/**
 * 生命周期定义。
 * 
 * @author Jiangwei Xu
 *
 */
public interface LifeCycle {

	public void start() throws Exception;

	public void stop() throws Exception;

	public boolean isStarted();

	public boolean isStarting();

	public boolean isStopped();

	public boolean isStopping();

	public boolean isRunning();

	public boolean isFailed();
}
