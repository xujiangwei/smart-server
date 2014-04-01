package smart.util;

/**
 * 生命周期接口。
 * 
 * @author Jiangwei Xu
 *
 */
public interface LifeCycle {

	public void start();

	public void stop();

	public boolean isRunning();

	public boolean isFailed();

	public boolean isStarting();

	public boolean isStarted();

	public boolean isStopping();

	public boolean isStopped();
}
