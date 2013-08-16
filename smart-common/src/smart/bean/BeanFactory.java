package smart.bean;

import smart.dao.HostDao;
import smart.dao.impl.HostDaoImpl;


/**
 * Bean 工厂。
 * 
 * @author Jiangwei Xu
 */
public final class BeanFactory {

	private static final BeanFactory instance = new BeanFactory();

	private HostDao hostDao;

	private BeanFactory() {
		this.hostDao = new HostDaoImpl();
	}

	public static BeanFactory getInstance() {
		return BeanFactory.instance;
	}

	/**
	 * 创建主机实体。
	 * @param id
	 * @return
	 */
	public Host getHost(long id) {
		synchronized (this.hostDao) {
			return this.hostDao.getHostById(id);
		} 
	}
}
