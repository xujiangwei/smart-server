package smart.old.core;

import java.util.ArrayList;
import java.util.List;

import smart.old.bean.BeanFactory;
import smart.old.bean.Host;
import smart.old.entity.Entity;

/**
 * 树节点遍历
 * 
 * @author yanbo
 */
public class NodeTraverse {

	private static final NodeTraverse instance = new NodeTraverse();
	private List<Host> l = null;
	
	public static NodeTraverse getInstance(){
		return NodeTraverse.instance;
	}
	
	private NodeTraverse() {
		l = BeanFactory.getInstance().getHostList();
	}

	// 根据子节点id遍历所有父节点id
	// 先根据childId找到对应的实体节点
	public List<Long> getParents(long childId){
		List<Long> ls = new ArrayList<Long>();
		for (int i = 0; i < l.size(); i++) {
			List<Entity> list = l.get(i).getElders();
			if (l.get(i).getId() == childId) {
				System.out.println(childId + "有父亲节点，其id为：");
				for (int k = 0; k < list.size(); k++) {
					System.out.print(list.get(k).getId() + "  ");
					ls.add(list.get(k).getId());
				}
			} else if (l.get(i).getChildren().containsKey(childId)) {
				System.out.println(childId + "有父亲节点，其id为：");
				System.out.println(l.get(i).getId() + " ");
				ls.add(l.get(i).getId());
				for (int k = 0; k < list.size(); k++) {
					System.out.print(list.get(k).getId() + "  ");
					ls.add(list.get(k).getId());
				}
			}
		}
		return ls;
	}
}
