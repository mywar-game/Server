package com.fantingame.game.mywar.logic.tool.goods;


import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.deal.IDealGoods;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.tool.service.ToolService;

public class ToolDealer implements IDealGoods {
    @Autowired
	private ToolService toolService;
	@Override
	public GoodsType getGoodsType() {
		return GoodsType.tool;
	}
	@Override
	public void sendGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		boolean success = toolService.addTool(userId, goodsId, goodsNum, GoodsUseType);
		if(success){
			GoodsHelper.addCommonDropGoodsBean(drop, goodsType, goodsId, goodsNum);
		}
	}

	@Override
	public void reduceGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		boolean success = toolService.reduceTool(userId, goodsId, goodsNum, GoodsUseType);
		if(success){
			GoodsHelper.addCommonDropGoodsBean(drop, goodsType, goodsId, goodsNum);
		}
	}

}
