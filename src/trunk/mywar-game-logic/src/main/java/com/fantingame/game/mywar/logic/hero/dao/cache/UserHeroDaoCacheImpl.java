package com.fantingame.game.mywar.logic.hero.dao.cache;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.hero.dao.UserHeroDao;
import com.fantingame.game.mywar.logic.hero.dao.mysql.UserHeroDaoMysqlImpl;
import com.fantingame.game.mywar.logic.hero.model.UserHero;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UserHeroDaoCacheImpl extends BaseCacheMapDao<UserHero> implements UserHeroDao{

	private UserHeroDaoMysqlImpl userHeroDaoMysqlImpl;
	
	@Override
	public List<UserHero> getUserHeroList(String userId) {
		return super.getMapList(userId);
	}
	
	@Override
	public boolean addUserHero(UserHero userHero) {
		if(userHeroDaoMysqlImpl.addUserHero(userHero)){
			if(super.isExitKey(userHero.getUserId())){
				super.addMapValues(userHero.getUserId(), userHero.getUserHeroId(), userHero);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean addBatchUserHero(String userId, List<UserHero> userHero) {
		if(userHeroDaoMysqlImpl.addBatchUserHero(userId, userHero)){
			if(super.isExitKey(userId)){
				for(UserHero hero:userHero){
					super.addMapValues(userId, hero.getUserHeroId(), hero);
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateHeroSystemHeroId(String userId, String userHeroId,
			int systemHeroId) {
		if(userHeroDaoMysqlImpl.updateHeroSystemHeroId(userId, userHeroId, systemHeroId)){
			if(super.isExitKey(userId)){
				UserHero hero = super.getByKey(userId, userHeroId);
				hero.setSystemHeroId(systemHeroId);
				hero.setUpdatedTime(new Date());
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateHeroExpAndLevel(String userId, String userHeroId,
			int exp, int level) {
		if(userHeroDaoMysqlImpl.updateHeroExpAndLevel(userId, userHeroId, exp, level)){
			if(super.isExitKey(userId)){
				UserHero hero = super.getByKey(userId, userHeroId);
				hero.setExp(exp);
				hero.setLevel(level);
				hero.setUpdatedTime(new Date());
			}
			return true;
		}
		return false;
	}
	
	@Override
	protected Map<String, UserHero> loadFromDb(String key) {
		Map<String,UserHero> map = Maps.newConcurrentMap();
		List<UserHero> list = userHeroDaoMysqlImpl.getUserHeroList(key);
		for(UserHero userHero:list){
			map.put(userHero.getUserHeroId(), userHero);
		}
		return map;
	}
	
	@Override
	public UserHero getUserHero(String userId, String userHeroId) {
		return super.getByKey(userId, userHeroId);
	}
	
	public UserHeroDaoMysqlImpl getUserHeroDaoMysqlImpl() {
		return userHeroDaoMysqlImpl;
	}
	
	public void setUserHeroDaoMysqlImpl(UserHeroDaoMysqlImpl userHeroDaoMysqlImpl) {
		this.userHeroDaoMysqlImpl = userHeroDaoMysqlImpl;
	}
	
	@Override
	public boolean updateHeroEffective(String userId, String userHeroId,
			int effective) {
		if(userHeroDaoMysqlImpl.updateHeroEffective(userId, userHeroId, effective)){
			if(super.isExitKey(userId)){
				UserHero hero = super.getByKey(userId, userHeroId);
				hero.setEffective(effective);
				hero.setUpdatedTime(new Date());
			}
			return true;
		}
		return false;
	}
	
	@Override
	public UserHero getUserHeroByPos(String userId, int pos) {
		//不能查询位置没有上阵的英雄
		if(pos<=0){
			return null;
		}
		Collection<UserHero> list = super.getMapValues(userId);
		if(list!=null&&list.size()>0){
			for(UserHero userHero:list){
				if(userHero.getPos()==pos){
					return userHero;
				}
			}
		}
		return null;
	}	

	@Override
	public UserHero getTeamLeaderHero(String userId, int teamLeader) {
		Collection<UserHero> list = super.getMapValues(userId);
		if (list != null && list.size() > 0) {
			for (UserHero userHero : list) {
				if (userHero.getIsScene() == teamLeader) {
					return userHero;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 获取在阵上的英雄或不在阵上的英雄   type=0 不在阵上  type=1在阵上
	 * @param userId
	 * @param type
	 * @return
	 */
	public List<UserHero> getUserHeroListInBattleOrNotInBattle(String userId, int type) {
		List<UserHero> result = Lists.newArrayList();
		Collection<UserHero> list = super.getMapValues(userId);
		if(list!=null&&list.size()>0){
			for(UserHero userHero:list){
				if(type==0){
					if(userHero.getPos()==0){
						result.add(userHero);
					}
				}else{
					if(userHero.getPos()>0){
						result.add(userHero);
					}
				}
			}
		}
		return result;
	}
	
	@Override
	public boolean changePos(String userId, String userHeroId, int pos) {
		if(userHeroDaoMysqlImpl.changePos(userId, userHeroId, pos)){
			if(super.isExitKey(userId)){
				UserHero hero = super.getByKey(userId, userHeroId);
				hero.setPos(pos);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean changeSystemHeroId(String userId, String userHeroId,
			int systemHeroId) {
		if(userHeroDaoMysqlImpl.changeSystemHeroId(userId, userHeroId, systemHeroId)){
			if(super.isExitKey(userId)){
				UserHero hero = super.getByKey(userId, userHeroId);
				hero.setSystemHeroId(systemHeroId);			
			}
			
			return true;
		}
		return false;
	}

	@Override
	public int getUserHeroCount(String userId, int status) {
		List<UserHero> list = super.getMapList(userId);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (UserHero userHero : list) {
				if (userHero.getStatus() == 1)
					count++;
			}
		}
		
		return count;
	}

	@Override
	public UserHero getUserHero(String userId, int systemHeroId) {
		Collection<UserHero> list = super.getMapValues(userId);
		if(list != null && list.size() > 0){
			for (UserHero userHero : list) {
				if (userHero.getSystemHeroId() == systemHeroId)
					return userHero;
			}			
		}
		
		return null;
	}

	@Override
	public boolean changeTeamLeader(String userId, String userHeroId, int teamLeader) {
		if (userHeroDaoMysqlImpl.changeTeamLeader(userId, userHeroId, teamLeader)) {
			if (super.isExitKey(userId)) {
				UserHero hero = super.getByKey(userId, userHeroId);
				hero.setIsScene(teamLeader);
			}
		
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateHeroStatus(String userId, String userHeroId, int status) {
		if (userHeroDaoMysqlImpl.updateHeroStatus(userId, userHeroId, status)) {
			if (super.isExitKey(userId)) {
				UserHero hero = super.getByKey(userId, userHeroId);
				hero.setStatus(status);
			}
			
			return true;
		}		
		
		return false;
	}

	@Override
	public boolean updateHeroStar(String userId, String userHeroId, int star) {
		if (this.userHeroDaoMysqlImpl.updateHeroStar(userId, userHeroId, star)) {
			if (super.isExitKey(userId)) {
				UserHero userHero = super.getByKey(userId, userHeroId);
				userHero.setStar(star);
			}
			
			return true;
		}
		
		return false;
	}
	
}
