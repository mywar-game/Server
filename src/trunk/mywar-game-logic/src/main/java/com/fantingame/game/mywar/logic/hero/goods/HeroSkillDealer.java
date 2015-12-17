package com.fantingame.game.mywar.logic.hero.goods;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.deal.IDealGoods;
import com.fantingame.game.mywar.logic.hero.service.HeroService;

public class HeroSkillDealer implements IDealGoods {
    @Autowired
	private HeroService heroService;
	@Override
	public GoodsType getGoodsType() {
		return GoodsType.HeroSkill;
	}

	@Override
	public void sendGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		for(int i=0;i<goodsNum;i++){
			heroService.addHeroSkill(userId, goodsId, 1,GoodsUseType, drop);
		}
	}
	@Override
	public void reduceGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		throw new RuntimeException("暂没有实现英雄技能的删除功能");
	}
}
