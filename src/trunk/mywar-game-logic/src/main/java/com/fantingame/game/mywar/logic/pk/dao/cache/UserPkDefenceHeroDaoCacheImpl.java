package com.fantingame.game.mywar.logic.pk.dao.cache;

import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.pk.dao.UserPkDefenceHeroDao;
import com.fantingame.game.mywar.logic.pk.dao.mysql.UserPkDefenceHeroDaoMysqlImpl;
import com.fantingame.game.mywar.logic.pk.model.UserPkDefenceHero;
import com.google.common.collect.Maps;

public class UserPkDefenceHeroDaoCacheImpl extends
		BaseCacheMapDao<UserPkDefenceHero> implements UserPkDefenceHeroDao {

	private UserPkDefenceHeroDaoMysqlImpl userPkDefenceHeroDaoMysqlImpl;

	public UserPkDefenceHeroDaoMysqlImpl getUserPkDefenceHeroDaoMysqlImpl() {
		return userPkDefenceHeroDaoMysqlImpl;
	}

	public void setUserPkDefenceHeroDaoMysqlImpl(
			UserPkDefenceHeroDaoMysqlImpl userPkDefenceHeroDaoMysqlImpl) {
		this.userPkDefenceHeroDaoMysqlImpl = userPkDefenceHeroDaoMysqlImpl;
	}

	@Override
	protected Map<String, UserPkDefenceHero> loadFromDb(String userId) {
		Map<String, UserPkDefenceHero> map = Maps.newConcurrentMap();
		List<UserPkDefenceHero> list = this.userPkDefenceHeroDaoMysqlImpl.getUserPkDefenceHeroList(userId);
		for (UserPkDefenceHero userPkDefenceHero : list) {
			map.put(userPkDefenceHero.getUserHeroId(), userPkDefenceHero);
		}
		
		return map;
	}

	@Override
	public List<UserPkDefenceHero> getUserPkDefenceHeroList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public boolean deleteUserPkDefenceHero(String userId) {
		if (this.userPkDefenceHeroDaoMysqlImpl.deleteUserPkDefenceHero(userId)) {
			super.delete(userId);
			
			return true;
		}
		return false;
	}

	@Override
	public boolean addUserPkDefenceHero(List<UserPkDefenceHero> defenceList) {
		if (this.userPkDefenceHeroDaoMysqlImpl.addUserPkDefenceHero(defenceList)) {
			for (UserPkDefenceHero userPkDefenceHero : defenceList) {
				super.addMapValues(userPkDefenceHero.getUserId(), userPkDefenceHero.getUserHeroId(), userPkDefenceHero);
			}
			
			return true;
		}
		
		return false;
	}

}
