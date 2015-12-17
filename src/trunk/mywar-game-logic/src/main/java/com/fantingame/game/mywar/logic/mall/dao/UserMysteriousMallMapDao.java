package com.fantingame.game.mywar.logic.mall.dao;

import com.fantingame.game.mywar.logic.mall.model.UserMysteriousMallMap;

public interface UserMysteriousMallMapDao {

	public UserMysteriousMallMap getUserMysteriousMallMap(int mapId);

	/**
	 * 获取神秘商店所在地图
	 * 
	 * @return
	 */
	public UserMysteriousMallMap getUserMysteriousMallMap();
	
	/**
	 * 删除神秘商店相关信息
	 * 
	 * @return
	 */
	public boolean delete(int mapId);	
	
	/**
	 * 保存神秘商店信息
	 * 
	 * @param userMysteriousMallMap
	 * @return
	 */
	public boolean save(UserMysteriousMallMap userMysteriousMallMap);
}
