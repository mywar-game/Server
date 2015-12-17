package com.stats.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.User;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserDistribution;
import com.stats.service.UserDistributionService;

/**
 *  角色分布统计
 *
 *	主角名称           		数量          占百分比
 *	| 10033 罗罗亚|      1503 |   1503/四者之合
 *	| 10036 阿隆    |      948  |   948/四者之合
 *	| 10041 马克    |      1843 |   1843/四者之合
 *	| 10046 草丛伦|      332  |   332/四者之合
 */
public class UserDistributionList extends ALDAdminStatsDatePageActionSupport{

	private static final long serialVersionUID = 1L;
	private float count = 0; // 总数
	private List<UserDistribution> userDistributionList = new ArrayList<UserDistribution>();
	private Map<Integer, Integer> map;
	private static int LUOLUOYA = 10033;
	private static int ALONG = 10036;
	private static int MARK = 10041;
	private static int CAO = 10046;
	
	public String execute() {
		UserDistributionService userDistributionService = ServiceCacheFactory.getServiceCache().getService(UserDistributionService.class);
		List<User> userList = userDistributionService.count();
		map = new HashMap<Integer, Integer>();
		if (userList != null && userList.size() != 0) {
			count = userList.size();
		}
		for (User user : userList) {
			if (user.getMainRoleId() == null) {
				// 正常情况不为空
				continue;
			}
			if (user.getMainRoleId() == LUOLUOYA) {
				map.put(LUOLUOYA, map.get(LUOLUOYA) != null ? map.get(LUOLUOYA) + 1 : 1);
			} else if (user.getMainRoleId() == ALONG) {
				map.put(ALONG, map.get(ALONG) != null ? map.get(ALONG) + 1 : 1);
			} else if (user.getMainRoleId() == MARK) {
				map.put(MARK, map.get(MARK) != null ? map.get(MARK) + 1 : 1);
			} else if (user.getMainRoleId() == CAO) {
				map.put(CAO, map.get(CAO) != null ? map.get(CAO) + 1 : 1);
			}
		}
		
		Iterator<Integer> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			int key = iter.next();
			int value = map.get(key);
			if (key == LUOLUOYA) {
				userDistributionList.add(new UserDistribution(LUOLUOYA, "罗罗亚", value, value / count * 100 + "%"));
			} else if (key == ALONG) {
				userDistributionList.add(new UserDistribution(ALONG, "阿隆", value, value / count * 100 + "%"));
			} else if (key == MARK) {
				userDistributionList.add(new UserDistribution(MARK, "马克 ", value, value / count * 100 + "%"));
			} else if (key == CAO) {
				userDistributionList.add(new UserDistribution(CAO, "草丛伦", value, value / count * 100 + "%"));
			}
		}
		return SUCCESS;
	}

	public List<UserDistribution> getUserDistributionList() {
		return userDistributionList;
	}

	public void setUserDistributionList(List<UserDistribution> userDistributionList) {
		this.userDistributionList = userDistributionList;
	}
}
