package smart.old.util.component;

/**
 * 生命周期描述对象抽象层。
 * 
 * @author Jiangwei Xu
 *
 */
public abstract class AbstractLifeCycle implements LifeCycle {

	public static final String STARTING = "STARTING";
	public static final String STARTED = "STARTED";
	public static final String RUNNING = "RUNNING";
	public static final String STOPPING = "STOPPING";
	public static final String STOPPED = "STOPPED";
	public static final String FAILED = "FAILED";

	protected String state = STOPPED;

	public AbstractLifeCycle() {
	}

	@Override
	public void start() throws Exception {
		this.state = STARTING;

		doStart();

		this.state = STARTED;
	}

	@Override
	public void stop() throws Exception {
		this.state = STOPPING;

		doStop();

		this.state = STOPPED;
	}

	@Override
	public boolean isStarted() {
		return (this.state.equals(STARTED));
	}

	@Override
	public boolean isStarting() {
		return (this.state.equals(STARTING));
	}

	@Override
	public boolean isStopped() {
		return (this.state.equals(STOPPED));
	}

	@Override
	public boolean isStopping() {
		return (this.state.equals(STOPPING));
	}

	@Override
	public boolean isRunning() {
		return (this.state.equals(RUNNING));
	}

	@Override
	public boolean isFailed() {
		return (this.state.equals(FAILED));
	}

	public String getState() {
		return this.state;
	}

	protected abstract void doStart() throws Exception;

	protected abstract void doStop() throws Exception;
}
