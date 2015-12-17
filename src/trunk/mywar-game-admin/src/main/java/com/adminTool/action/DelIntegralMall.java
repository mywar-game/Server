package com.adminTool.action;

import com.adminTool.service.IntegralMallService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class DelIntegralMall extends ALDAdminActionSupport {
	private static final long serialVersionUID = 9040581215855872230L;
	private Integer mall_id;

	public void executeDel() {
		IntegralMallService service = ServiceCacheFactory.getServiceCache()
				.getService(IntegralMallService.class);
		service.delIntegralMall(mall_id);
	}

	public Integer getMall_id() {
		return mall_id;
	}

	public void setMall_id(Integer mall_id) {
		this.mall_id = mall_id;
	}

}
