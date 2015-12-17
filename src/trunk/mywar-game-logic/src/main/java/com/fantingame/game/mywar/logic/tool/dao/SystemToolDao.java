package com.fantingame.game.mywar.logic.tool.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.tool.model.SystemTool;


/**
 * 系统道具dao
 * 
 * @author mengchao
 * 
 */
public interface SystemToolDao {

	/**
	 * 获取系统道具
	 * 
	 * @param toolId
	 * @return
	 */
	public SystemTool get(int toolId);

	/**
	 * 获取系统道具列表
	 * 
	 * @return
	 */
	public List<SystemTool> getSystemToolList();
	/**
	 * 获取开启宝箱需要的钥匙 0为不需要
	 * @return
	 */
	public int getGiftBoxKey(int giftBoxToolId);
	
}
