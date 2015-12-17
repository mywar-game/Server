package com.adminTool.action;

import com.adminTool.service.UpdateDropToolService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class DelDropTool extends ALDAdminActionSupport{

	private Integer giftbagDropToolId;
	
	public Integer getGiftbagDropToolId() {
		return giftbagDropToolId;
	}

	public void setGiftbagDropToolId(Integer giftbagDropToolId) {
		this.giftbagDropToolId = giftbagDropToolId;
	}

	public void executeDel() {
		UpdateDropToolService service = ServiceCacheFactory.getServiceCache()
				.getService(UpdateDropToolService.class);
		service.deleteByDropId(giftbagDropToolId);
	}
}
