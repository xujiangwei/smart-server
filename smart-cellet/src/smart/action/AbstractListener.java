package smart.action;

import java.util.Collection;
import java.util.Iterator;

import net.cellcloud.core.Cellet;
import net.cellcloud.talk.dialect.ActionDialect;
import net.cellcloud.talk.dialect.DialectEnumerator;
import net.cellcloud.util.BooleanProperty;
import net.cellcloud.util.IntegerProperty;
import net.cellcloud.util.LongProperty;
import net.cellcloud.util.Properties;
import net.cellcloud.util.PropertyReference;
import net.cellcloud.util.StringProperty;

import org.eclipse.jetty.client.HttpClient;

import smart.mast.action.ActionListener;

/*
 * 抽象监听器
 */
public abstract class AbstractListener implements ActionListener {

	private Cellet cellet;
	private HttpClient httpClient;
	private String sourceTag;
	
	public AbstractListener(Cellet cellet) {
		this.cellet=cellet;
	}
	@Override
	public void setSourceTag(String tag) {
		 this.sourceTag=tag;
		
	}

	@Override
	public String getSourceTag() {
		return this.sourceTag;
	}

	/*返回Cellet实例
	 */
	public Cellet getCellet(){
		return this.cellet;
	}
	
	/*返回HttpClient实例
	 */
	public HttpClient getHttpClient(){
		return this.httpClient;
	}
	
	/*设置HttpClient 实例
	 */
	public void setHttpClient(HttpClient httpClient){
		this.httpClient=httpClient;
	}
	
	/*
	 * 向指定的客户端回送方言
	 */
	public void response(String tracker,
			String action, Properties properties) {
		// 创建方言
		ActionDialect dialect = (ActionDialect) DialectEnumerator.getInstance()
				.getFactory(ActionDialect.DIALECT_NAME).create(tracker);
		// 设置动作
		dialect.setAction(action);

		if (null != properties) {
			// 读取所有属性，并添加到方言中
			Collection<PropertyReference> params = properties.getPropertyCollection();
			Iterator<PropertyReference> iter = params.iterator();
			while (iter.hasNext()) {
				PropertyReference pr = iter.next();
				if (pr instanceof StringProperty) {
					StringProperty sp = (StringProperty) pr;
					dialect.appendParam(sp.getKey(), sp.getValueAsString());
				}
				else if (pr instanceof IntegerProperty) {
					IntegerProperty ip = (IntegerProperty) pr;
					dialect.appendParam(ip.getKey(), ip.getValueAsInt());
				}
				else if (pr instanceof LongProperty) {
					LongProperty lp = (LongProperty) pr;
					dialect.appendParam(lp.getKey(), lp.getValueAsLong());
				}
				else if (pr instanceof BooleanProperty) {
					BooleanProperty bp = (BooleanProperty) pr;
					dialect.appendParam(bp.getKey(), bp.getValueAsBoolean());
				}
				else {
					dialect.appendParam(pr.getKey(), pr.getValue().toString());
				}
			}
		}

		// 发送方言
		this.cellet.talk(this.getSourceTag(), dialect);
	}

	public void response(
			String action, Properties properties) {
		// 创建方言
		ActionDialect dialect = (ActionDialect) DialectEnumerator.getInstance()
				.getFactory(ActionDialect.DIALECT_NAME).create("response");
		// 设置动作
		dialect.setAction(action);

		if (null != properties) {
			// 读取所有属性，并添加到方言中
			Collection<PropertyReference> params = properties.getPropertyCollection();
			Iterator<PropertyReference> iter = params.iterator();
			while (iter.hasNext()) {
				PropertyReference pr = iter.next();
				if (pr instanceof StringProperty) {
					StringProperty sp = (StringProperty) pr;
					dialect.appendParam(sp.getKey(), sp.getValueAsString());
				}
				else if (pr instanceof IntegerProperty) {
					IntegerProperty ip = (IntegerProperty) pr;
					dialect.appendParam(ip.getKey(), ip.getValueAsInt());
				}
				else if (pr instanceof LongProperty) {
					LongProperty lp = (LongProperty) pr;
					dialect.appendParam(lp.getKey(), lp.getValueAsLong());
				}
				else if (pr instanceof BooleanProperty) {
					BooleanProperty bp = (BooleanProperty) pr;
					dialect.appendParam(bp.getKey(), bp.getValueAsBoolean());
				}
				else {
					dialect.appendParam(pr.getKey(), pr.getValue().toString());
				}
			}
		}

		// 发送方言
		this.cellet.talk(this.getSourceTag(), dialect);
	}
	
	/* 报告 HTTP 错误
	 */
	protected void reportHTTPError(String tracker, String action) {
	}
	protected void reportHTTPError(String action) {
	}

}
