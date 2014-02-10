package smart;

import net.cellcloud.cell.Cell;
import net.cellcloud.core.Nucleus;
import net.cellcloud.core.NucleusConfig;
import net.cellcloud.exception.SingletonException;

/**
 * 程序入口。
 * 
 * @author Jiangwei Xu
 *
 */
public final class Main {

	public static void main(String[] args) {
		if (null == args || args.length == 0) {
			// 配置内核参数
			NucleusConfig config = new NucleusConfig();

			// 设置 block 大小
			config.talk.block = 131072;

			try {
				Nucleus nucleus = new Nucleus(config);
				// 注册 Cellet
				nucleus.registerCellet(new ServiceDeskServer());
			} catch (SingletonException e) {
				e.printStackTrace();
				return;
			}

			Cell.main(new String[]{"start"});
		}
	}
}
