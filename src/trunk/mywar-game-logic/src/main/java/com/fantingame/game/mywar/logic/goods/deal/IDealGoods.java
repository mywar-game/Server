package com.fantingame.game.mywar.logic.goods.deal;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;

public interface IDealGoods {
	 public GoodsType getGoodsType();
	 /**
	  * 给物品给玩家
	  * @param goodsType
	  * @param goodsId
	  * @param goodsNum
	  * @param commonDrop
	  */
     public void sendGoods(String userId,int goodsType,int goodsId,int goodsNum,CommonGoodsBeanBO drop,int GoodsUseType);
     /**
      * 减玩家物品
      * @param userId
      * @param goodsType
      * @param goodsId
      * @param goodsNum
      * @param commonDrop
      */
     public void reduceGoods(String userId,int goodsType,int goodsId,int goodsNum,CommonGoodsBeanBO drop,int GoodsUseType);
}
