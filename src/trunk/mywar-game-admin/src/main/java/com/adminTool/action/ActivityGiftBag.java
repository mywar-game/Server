package com.adminTool.action;

import com.adminTool.service.OnlineGiftbagService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 在线礼包
 * @author Administrator
 *
 */
public class ActivityGiftBag extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 1L;

	public String exceute() {
		ServiceCacheFactory.getServiceCache()
		.getService(OnlineGiftbagService.class);
		
		return SUCCESS;
	}
}
