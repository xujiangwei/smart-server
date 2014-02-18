package smart.old;

import net.cellcloud.cell.Cell;
import net.cellcloud.core.Nucleus;
import net.cellcloud.core.NucleusConfig;
import net.cellcloud.exception.SingletonException;

public final class Main {

	public static void main(String[] args) {
		// 配置内核参数
		NucleusConfig config = new NucleusConfig();

		// 设置 block 大小
		config.talk.block = 131072;

		try {
			Nucleus nucleus = Nucleus.createInstance(config);
			// 注册 SmartCellet
			nucleus.registerCellet(new SmartCellet());
		} catch (SingletonException e) {
			e.printStackTrace();
		}

		Cell.main(new String[]{"start"});
	}
}
