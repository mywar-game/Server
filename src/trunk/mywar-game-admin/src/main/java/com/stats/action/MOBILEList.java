package com.stats.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 机型统计
 * @author Administrator
 *
 */
public class MOBILEList extends ALDAdminActionSupport {
	private static final long serialVersionUID = 1L;

	private Map<String, Integer> mobileMap = new HashMap<String, Integer>();
	
	public String execute() throws Exception {
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		List<Object> list = userService.findByMobile();
		// mobileMap.put("mobile统计", count);
		for (Object obj : list) {
			String str = (String) obj;
			if (mobileMap.get(str) == null) {
				mobileMap.put(str, 1);
			} else {
				mobileMap.put(str, mobileMap.get(str) + 1);
			}
		}
		return SUCCESS;
	}

	public Map<String, Integer> getMobileMap() {
		return mobileMap;
	}

	public void setMobileMap(Map<String, Integer> mobileMap) {
		this.mobileMap = mobileMap;
	}

}
