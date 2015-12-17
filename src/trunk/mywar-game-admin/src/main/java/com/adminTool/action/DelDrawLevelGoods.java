package com.adminTool.action;

import com.adminTool.service.ActivityDrawLevelGoodsService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 删除等级抽奖替换物品
 * 
 * @author yezp
 */
public class DelDrawLevelGoods extends ALDAdminActionSupport {

	private static final long serialVersionUID = 5409811148203366738L;

	private Integer id;

	public void executeDel() {
		ActivityDrawLevelGoodsService service = ServiceCacheFactory
				.getServiceCache().getService(
						ActivityDrawLevelGoodsService.class);
		service.delDrawLevelGoods(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
