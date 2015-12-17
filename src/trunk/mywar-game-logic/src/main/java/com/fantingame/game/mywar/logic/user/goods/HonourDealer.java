package com.fantingame.game.mywar.logic.user.goods;


import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.deal.IDealGoods;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.user.service.UserService;

public class HonourDealer implements IDealGoods {
    @Autowired
	private UserService userService;
	@Override
	public GoodsType getGoodsType() {
		return GoodsType.honour;
	}

	@Override
	public void sendGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		boolean success = userService.addHonour(userId, goodsNum,GoodsUseType);
		if(success){
			GoodsHelper.addCommonDropGoodsBean(drop, goodsType, goodsId, goodsNum);
		}
	}

	@Override
	public void reduceGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		boolean success = userService.reduceHonour(userId, goodsNum, GoodsUseType);
		if(success){
			GoodsHelper.addCommonDropGoodsBean(drop, goodsType, goodsId, goodsNum);
		}
	}

}
