package smart.util;

/**
 * 抽象生命周期。
 * 
 * @author Jiangwei Xu
 *
 */
public abstract class AbstractLifeCycle implements LifeCycle {

	protected LifeCycleEnum lifeCycle;

	public AbstractLifeCycle() {
		this.lifeCycle = LifeCycleEnum.STOPPED;
	}

	abstract protected void doStart();

	abstract protected void doStop();

	@Override
	public void start() {
		if (this.lifeCycle == LifeCycleEnum.STARTED
			|| this.lifeCycle == LifeCycleEnum.STARTING
			|| this.lifeCycle == LifeCycleEnum.RUNNING) {
			return;
		}

		this.lifeCycle = LifeCycleEnum.STARTING;

		this.doStart();

		this.lifeCycle = LifeCycleEnum.STARTED;
	}

	@Override
	public void stop() {
		if (this.lifeCycle == LifeCycleEnum.STOPPED
			|| this.lifeCycle == LifeCycleEnum.STOPPING) {
			return;
		}

		this.lifeCycle = LifeCycleEnum.STOPPING;

		this.doStop();

		this.lifeCycle = LifeCycleEnum.STOPPED;
	}

	@Override
	public boolean isRunning() {
		return (LifeCycleEnum.RUNNING == this.lifeCycle);
	}

	@Override
	public boolean isFailed() {
		return (LifeCycleEnum.FAILED == this.lifeCycle);
	}

	@Override
	public boolean isStarting() {
		return (LifeCycleEnum.STARTING == this.lifeCycle);
	}

	@Override
	public boolean isStarted() {
		return (LifeCycleEnum.STARTED == this.lifeCycle);
	}

	@Override
	public boolean isStopping() {
		return (LifeCycleEnum.STOPPING == this.lifeCycle);
	}

	@Override
	public boolean isStopped() {
		return (LifeCycleEnum.STOPPED == this.lifeCycle);
	}
}
