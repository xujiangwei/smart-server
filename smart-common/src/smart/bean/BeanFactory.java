package smart.bean;

/**
 * Bean 工厂。
 * 
 * @author Jiangwei Xu
 */
public final class BeanFactory {

	private static final BeanFactory instance = new BeanFactory();

	private BeanFactory() {
	}

	public static BeanFactory getInstance() {
		return BeanFactory.instance;
	}

	/**
	 * 创建主机实体。
	 * @param id
	 * @return
	 */
	public Host createHost(String id) {
		return new Host(id);
	}
}
