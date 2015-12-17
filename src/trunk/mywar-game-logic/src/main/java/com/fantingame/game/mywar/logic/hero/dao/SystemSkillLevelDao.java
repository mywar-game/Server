package com.fantingame.game.mywar.logic.hero.dao;

import java.util.List;

import com.fantingame.game.mywar.logic.hero.model.SystemSkillLevel;

public interface SystemSkillLevelDao {

	/**
	 * 获取团长技能升级经验列表
	 * 
	 * @return
	 */
	public List<SystemSkillLevel> getSystemSkillLevelList();
	
	/**
	 * 根据经验获取团长技能等级经验
	 * 
	 * @param exp
	 * @return
	 */
	public SystemSkillLevel getSystemSkillLevel(int exp, int color);
	
	/**
	 * 根据等级、品质获取技能等级经验
	 * 
	 * @param level
	 * @return
	 */
	public SystemSkillLevel getSkillExp(int level, int color);
}
