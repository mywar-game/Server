package com.fantingame.game.mywar.logic.hero.goods;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.deal.IDealGoods;
import com.fantingame.game.mywar.logic.hero.service.HeroService;

public class HeroExpDealer implements IDealGoods {
	@Autowired
	private HeroService heroService;

	@Override
	public GoodsType getGoodsType() {
		return GoodsType.HeroExp;
	}

	@Override
	public void sendGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		heroService.updateHeroExpAndLevel(userId, goodsNum, GoodsUseType, drop);
	}

	@Override
	public void reduceGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		throw new RuntimeException("英雄经验不能被减！哪儿配错了么！！");
	}

}
