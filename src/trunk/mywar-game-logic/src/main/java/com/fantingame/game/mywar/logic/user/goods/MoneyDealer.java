package com.fantingame.game.mywar.logic.user.goods;


import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.deal.IDealGoods;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.user.service.UserService;

public class MoneyDealer implements IDealGoods {
    @Autowired
	private UserService userService;
	@Override
	public GoodsType getGoodsType() {
		return GoodsType.MONEY;
	}

	@Override
	public void sendGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop,int goodsUseType) {
		boolean  success = userService.addMoney(userId, goodsNum, goodsUseType);
		if(success){
			GoodsHelper.addCommonDropGoodsBean(drop, goodsType, goodsId, goodsNum);
		}
	}

	@Override
	public void reduceGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop,int goodsUseType) {
		boolean success = userService.reduceMoney(userId, goodsNum, goodsUseType);
		if(success){
			GoodsHelper.addCommonDropGoodsBean(drop, goodsType, goodsId, goodsNum);
		}
	}

}
