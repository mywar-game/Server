package com.fantingame.game.mywar.logic.legion.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.legion.model.LegionInfo;

public interface LegionInfoDao {

	/**
	 * 获取军团信息
	 * 
	 * @param legionId
	 * @return
	 */
	public LegionInfo getLegionInfo(String legionId);
	
	/**
	 * 是否已存在军团名称
	 * 
	 * @param legionName
	 * @return
	 */
	public boolean isExitLegionName(String legionName);	
	
	/**
	 * 添加军团
	 * 
	 * @param legionInfo
	 * @return
	 */
	public boolean addLegionInfo(LegionInfo legionInfo);
	
	/**
	 * 获取军团列表
	 */
	public List<LegionInfo> getLegionInfoList();
	
	/**
	 * 更新公会的公告
	 * 
	 * @param legionId
	 * @param notice
	 * @return
	 */
	public boolean updateLegionNotice(String legionId, String notice);
	
	/**
	 * 更新公会宣言
	 * 
	 * @param legionId
	 * @param declaration
	 * @return
	 */
	public boolean updateLegionDeclaration(String legionId, String declaration);
	
	/**
	 * 更新军团战斗力
	 * 
	 * @param legionId
	 * @param power
	 * @return
	 */
	public boolean updateLegionPower(String legionId, int power);
	
	/**
	 * 删除军团
	 * 
	 * @param legionId
	 * @return
	 */
	public boolean deleteLegionInfo(String legionId);
	
	/**
	 * 更新经验和等级
	 * 
	 * @param legionId
	 * @param level
	 * @param addExp
	 * @return
	 */
	public boolean updateLevelAndExp(String legionId, int level, int addExp);
}
