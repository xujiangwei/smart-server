package smart.action;

import java.util.Observer;

import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;

import org.eclipse.jetty.client.HttpClient;

import smart.MonitoringServer;
import smart.ServiceDeskServer;
import smart.core.Root;

/**
 * 动作监听器。
 * 
 * @author Jiangwei Xu
 *
 */
public abstract class ActionListener implements Observer {

	private String action;
	private Cellet cellet;
	private String sourceTag;

	public ActionListener(Cellet cellet, String action) {
		this.cellet = cellet;
		this.action = action;
	}

	/**
	 * 返回动作名称。
	 * @return
	 */
	public String getAction() {
		return this.action;
	}

	/**
	 * 返回 Cellet 标识符。
	 * @return
	 */
	public String getCelletIdentifier() {
		return this.cellet.getFeature().getIdentifier();
	}

	/**
	 * 返回服务器地址。
	 * @return
	 */
	public String getServerAddress() {
		if (this.cellet.getFeature().getIdentifier().hashCode() == ServiceDeskServer.NAME.hashCode()) {
			return ServiceDeskServer.ADDRESS;
		}
		else if (this.cellet.getFeature().getIdentifier().hashCode() == MonitoringServer.NAME.hashCode()) {
			return MonitoringServer.ADDRESS;
		}
		else {
			return null;
		}
	}

	/**
	 * 返回服务器端口。
	 * @return
	 */
	public int getServerPort() {
		if (this.cellet.getFeature().getIdentifier().hashCode() == ServiceDeskServer.NAME.hashCode()) {
			return ServiceDeskServer.PORT;
		}
		else if (this.cellet.getFeature().getIdentifier().hashCode() == MonitoringServer.NAME.hashCode()) {
			return MonitoringServer.PORT;
		}
		else {
			return 8080;
		}
	}

	/**
	 * 返回可用的 HTTP 客户端。
	 * @return
	 */
	public HttpClient getHttpClient() {
		return Root.getInstance().getHttpClient();
	}

	/**
	 * 回应远程客户端数据。
	 */
	public void respondRemote(ActionDialect dialect) {
		this.cellet.talk(this.sourceTag, dialect);
	}

	@Override
	public final synchronized void update(java.util.Observable o, Object dialect) {
		ActionDialect ad = (ActionDialect) dialect;
		// 获取源标签
		this.sourceTag = ad.getOwnerTag();

		// 调用回调
		this.doAction(ad);
	}

	/**
	 * 当收到来自客户端数据数据时被回调的函数。
	 * @param dialect
	 */
	abstract public void doAction(ActionDialect dialect);
}
