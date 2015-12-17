package com.fantingame.game.mywar.logic.hero.dao.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBase;
import com.fantingame.game.mywar.logic.hero.dao.SystemSkillLevelDao;
import com.fantingame.game.mywar.logic.hero.dao.mysql.SystemSkillLevelDaoMysqlImpl;
import com.fantingame.game.mywar.logic.hero.model.SystemSkillLevel;

/**
 * 团长技能等级经验配置
 * 
 * @author yezp
 */
public class SystemSkillLevelDaoCacheImpl extends StaticDataDaoBase implements
		SystemSkillLevelDao {

	@Autowired
	private SystemSkillLevelDaoMysqlImpl systemSkillLevelDaoMysqlImpl;

	private List<SystemSkillLevel> systemSkillLevelList;
	
	@Override
	public void startup() throws Exception {
		initCache();
	}

	@Override
	public void reload() {
		initCache();
	}

	private void initCache() {
		systemSkillLevelList = this.systemSkillLevelDaoMysqlImpl.getSystemSkillLevelList();
	}
	
	public SystemSkillLevelDaoMysqlImpl getSystemSkillLevelDaoMysqlImpl() {
		return systemSkillLevelDaoMysqlImpl;
	}

	public void setSystemSkillLevelDaoMysqlImpl(SystemSkillLevelDaoMysqlImpl systemSkillLevelDaoMysqlImpl) {
		this.systemSkillLevelDaoMysqlImpl = systemSkillLevelDaoMysqlImpl;
	}

	@Override
	public List<SystemSkillLevel> getSystemSkillLevelList() {
		if (systemSkillLevelList == null || systemSkillLevelList.size() == 0)
			return this.systemSkillLevelDaoMysqlImpl.getSystemSkillLevelList();
			
		return systemSkillLevelList;
	}

	@Override
	public SystemSkillLevel getSystemSkillLevel(int exp, int color) {
//		SystemSkillLevel skillLevel = null;
//		for (SystemSkillLevel keyExp : systemSkillLevelList) {
//			if (skillLevel == null) {
//				skillLevel = keyExp;
//			} else {
//				if (keyExp.getColor() == color && keyExp.getExp() <= exp && keyExp.getLevel() > skillLevel.getLevel()) {
//					skillLevel = keyExp;
//				}
//			}
//		}		
		return this.systemSkillLevelDaoMysqlImpl.getSystemSkillLevel(exp, color);
	}

	@Override
	public SystemSkillLevel getSkillExp(int level, int color) {
		if (systemSkillLevelList == null || systemSkillLevelList.size() == 0)
			return this.systemSkillLevelDaoMysqlImpl.getSkillExp(level, color);
		
		for (SystemSkillLevel skillLevel : systemSkillLevelList) {
			if (skillLevel.getLevel() == level && skillLevel.getColor() == color) 
				return skillLevel;
		}
		return null;
	}

}
