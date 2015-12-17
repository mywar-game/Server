package com.fantingame.game.mywar.logic.gemstone.goods;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.mywar.logic.gemstone.service.GemstoneService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.deal.IDealGoods;

public class GemstoneDealer implements IDealGoods {

	@Autowired
	private GemstoneService gemstoneService;
	
	@Override
	public GoodsType getGoodsType() {
		return GoodsType.Gemstone;
	}

	@Override
	public void sendGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		for (int i = 0; i < goodsNum; i++) {
			gemstoneService.addGemstone(userId, goodsId, false, GoodsUseType, drop);
		}
	}

	@Override
	public void reduceGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		throw new RuntimeException("还没有实现宝石的减法！");
	}

}
