package smart.core;

import smart.util.AbstractLifeCycle;

public final class UserManager extends AbstractLifeCycle {

	private final static UserManager instance = new UserManager();

	private UserManager() {
		
	}

	public static UserManager getInstance() {
		return UserManager.instance;
	}

	@Override
	protected void doStart() {
	}

	@Override
	protected void doStop() {
	}
}
