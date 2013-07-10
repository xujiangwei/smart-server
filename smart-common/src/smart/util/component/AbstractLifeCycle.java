package smart.util.component;

/**
 * 生命周期描述对象抽象层。
 * 
 * @author Jiangwei Xu
 *
 */
public class AbstractLifeCycle implements LifeCycle {

	public static final String STARTING = "STARTING";

	public AbstractLifeCycle() {
	}

	@Override
	public void start() throws Exception {
		doStart();
	}

	@Override
	public void stop() throws Exception {
		doStop();
	}

	@Override
	public boolean isStarted() {
		return false;
	}

	@Override
	public boolean isStarting() {
		return false;
	}

	@Override
	public boolean isStopped() {
		return false;
	}

	@Override
	public boolean isStopping() {
		return false;
	}

	@Override
	public boolean isRunning() {
		return false;
	}

	@Override
	public boolean isFailed() {
		return false;
	}

	protected void doStart() throws Exception {
	}

	protected void doStop() throws Exception {
	}
}
