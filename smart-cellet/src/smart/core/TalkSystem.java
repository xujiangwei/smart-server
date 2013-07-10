package smart.core;

/**
 * 会话管理系统。
 * @author Jiangwei Xu
 */
public final class TalkSystem {

	private static final TalkSystem instance = new TalkSystem();

	private TalkSystem() {
	}

	public static TalkSystem getInstance() {
		return TalkSystem.instance;
	}
}
