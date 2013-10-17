package smart.core;

import java.util.List;

import org.json.JSONArray;

import smart.bean.Attention;
import smart.dao.AttentionDao;
import smart.dao.impl.AttentionDaoImpl;

public class AttentionManager {

	// 关注单例
	private static final AttentionManager instance = new AttentionManager();
	private AttentionDao dao;
	
	private AttentionManager() {
		dao = new AttentionDaoImpl();
	}

	public static AttentionManager getInstance() {
		return AttentionManager.instance;
	}

	// 添加新关注
	public boolean addAttention(long userId, long moId) {
		boolean b = false;
		if (dao.getPriority(userId) < 8) {
			if (dao.checkMoId(moId, userId) == -1) {
				dao.storeAttention(userId, moId);
				b = true;
			} else if (dao.checkMoId(moId, userId) == 0) {
				dao.changeStatus(moId, userId);
				b = true;
			}
		}
		return b;
	}
	
	// 获取关注列表
	public List<Attention> getAttention(long userId) {
		List<Attention> l = dao.getAttention(userId);
		return l;
	}

	// 取消关注
	public void cancelAttention(JSONArray ja, long userId){
		dao.cancelAttention(ja, userId);
	}
	
	// 移除对应关注Id下的关注
	public void removeAttention(JSONArray ja, long userId) {
		dao.removeAttention(ja, userId);
	}
}
