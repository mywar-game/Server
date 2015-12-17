package com.fantingame.game.mywar.logic.user.dao.cache;

import java.util.Collection;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.user.model.SystemTeamExp;

public class SystemTeamExpDaoCache extends StaticDataDaoBaseT<Integer, SystemTeamExp>{
	private int maxLevel;
	public SystemTeamExp getUserLevel(int exp) {
		SystemTeamExp result = null;
		for(SystemTeamExp systemTeamExp:super.values()){
			if(result==null){
				if(systemTeamExp.getExp()<=exp){
					result = systemTeamExp;
				}
			}else{
				if(systemTeamExp.getExp()<=exp&&systemTeamExp.getSystemTeamLevel()>result.getSystemTeamLevel()){
					result = systemTeamExp;
				}
			}
		}
		return result;
	}

	public SystemTeamExp get(int level) {
		return super.getValue(level);
	}
	public int getMaxLevel(){
		return maxLevel;
	}
	
	@Override
	protected Integer getCacheKey(SystemTeamExp v) {
		return v.getSystemTeamLevel();
	}
	
	@Override
	public void startup() throws Exception {
		super.startup();
		Collection<SystemTeamExp> c = super.values();
		for(SystemTeamExp systemTeamExp:c){
			if(systemTeamExp.getSystemTeamLevel()>maxLevel){
				maxLevel = systemTeamExp.getSystemTeamLevel();
			}
		}
	}
}
