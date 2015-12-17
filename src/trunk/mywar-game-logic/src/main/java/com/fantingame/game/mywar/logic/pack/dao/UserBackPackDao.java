package com.fantingame.game.mywar.logic.pack.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.pack.model.UserBackPack;

public interface UserBackPackDao {
	 /**
	  * 获取用户背包物品列表
	  * @param userId
	  * @return
	  */
     public List<UserBackPack> getPackGoodsList(String userId);
     
     /**
      * 往背包中添加一个物品
      * @param userBackPack
      * @return
      */
     public boolean addUserBackPackGoods(UserBackPack userBackPack);
     /**
      * 
      * @param userId
      * @param goodsType
      * @param userGoodsId
      * @return
      */
     public boolean deleteBackPackGoods(String userId,int userBackPackId);
     /**
      * 添加物品列表
      * @param userId
      * @param userBackPack
      * @return
      */
     public boolean addUserBackPackGoodsList(String userId,List<UserBackPack> userBackPack);
     /**
      * 更新位置信息
      * @param userId
      * @param goodsType
      * @param userGoodsId
      * @param pos
      * @return
      */
     public boolean updateUserGoodsPos(String userId,int userBackPackId,int pos);
     
}
