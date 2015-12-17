package com.fantingame.game.mywar.logic.hero.dao.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroSkill;

public class SystemHeroSkillDaoCache extends StaticDataDaoBaseT<String, SystemHeroSkill> {

	private Map<Integer, List<SystemHeroSkill>> toolIdMap = new HashMap<Integer, List<SystemHeroSkill>>();
	
	public SystemHeroSkill getSystemHeroSkill(int systemHeroSkillId,int skillLevel){
		return super.getValue(systemHeroSkillId+"_"+skillLevel);
	}
	
	@Override
	protected String getCacheKey(SystemHeroSkill v) {
		return v.getSystemHeroSkillId()+"_"+v.getSkillLevel();
	}
	
	@Override
	public void reload(){
		super.reload();
		toolIdMap.clear();
	}
	
}
