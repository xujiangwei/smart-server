package smart.sim;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class Main {

	private Main() {
	}

	public static void main(String[] args) {
		// 创建新的 Component
		Component component = new Component();
		
		// 添加协议
		component.getServers().add(Protocol.HTTP, 8111);

		// 附加程序
		component.getDefaultHost().attach("/nms", new MonitoringApplication());

		// 启动组件
		try {
			component.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
