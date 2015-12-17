package com.adminTool.action;

import com.adminTool.service.ActivityDrawGoodsService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 删除抽奖物品
 * 
 * @author yezp
 */
public class DelDrawGoods extends ALDAdminActionSupport {

	private static final long serialVersionUID = -1931637310353538417L;

	private Integer goodsId;

	public void executeDel() {
		ActivityDrawGoodsService service = ServiceCacheFactory
				.getServiceCache().getService(ActivityDrawGoodsService.class);

		service.delDrawGoods(goodsId);
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

}
