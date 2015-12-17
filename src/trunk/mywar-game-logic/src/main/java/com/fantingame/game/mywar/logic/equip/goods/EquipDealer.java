package com.fantingame.game.mywar.logic.equip.goods;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.mywar.logic.equip.service.EquipService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.deal.IDealGoods;

public class EquipDealer implements IDealGoods {

	@Autowired
	private EquipService equipService;
	
	@Override
	public GoodsType getGoodsType() {
		return GoodsType.equip;
	}

	@Override
	public void sendGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		for (int i = 0; i < goodsNum; i++) {
			equipService.addEquip(userId, goodsId, false, GoodsUseType, drop);
		}
	}

	@Override
	public void reduceGoods(String userId, int goodsType, int goodsId,
			int goodsNum, CommonGoodsBeanBO drop, int GoodsUseType) {
		throw new RuntimeException("还没有实现装备的减法！");
	}

}
