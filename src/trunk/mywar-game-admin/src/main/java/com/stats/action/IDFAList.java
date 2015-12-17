package com.stats.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.service.UserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * IDFA统计
 * @author Administrator
 *
 */
public class IDFAList extends ALDAdminActionSupport {

	private static final long serialVersionUID = 1L;

	private Map<String, Integer> idfaMap = new HashMap<String, Integer>();
	
	public String execute() throws Exception {
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		List<Object> list = userService.findCountByIdfa();
		idfaMap.put("idfa统计", list.size());
		return SUCCESS;
	}

	public Map<String, Integer> getIdfaMap() {
		return idfaMap;
	}

	public void setIdfaMap(Map<String, Integer> idfaMap) {
		this.idfaMap = idfaMap;
	}

}
