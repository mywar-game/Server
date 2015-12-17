package com.fantingame.game.mywar.logic.achievement.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.achievement.dao.UserAchievementDao;
import com.fantingame.game.mywar.logic.achievement.dao.mysql.UserAchievementDaoMysqlImpl;
import com.fantingame.game.mywar.logic.achievement.model.UserAchievement;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UserAchievementDaoCacheImpl extends
		BaseCacheMapDao<UserAchievement> implements UserAchievementDao {

	private UserAchievementDaoMysqlImpl userAchievementDaoMysqlImpl;

	public UserAchievementDaoMysqlImpl getUserAchievementDaoMysqlImpl() {
		return userAchievementDaoMysqlImpl;
	}

	public void setUserAchievementDaoMysqlImpl(UserAchievementDaoMysqlImpl userAchievementDaoMysqlImpl) {
		this.userAchievementDaoMysqlImpl = userAchievementDaoMysqlImpl;
	}

	@Override
	protected Map<String, UserAchievement> loadFromDb(String userId) {
		Map<String, UserAchievement> map = Maps.newConcurrentMap();
		List<UserAchievement> list = this.userAchievementDaoMysqlImpl.getUserAchievementList(userId);
		for (UserAchievement userAchievement : list) {
			map.put(userAchievement.getAchievementId() + "", userAchievement);
		}
		
		return map;
	}

	@Override
	public List<UserAchievement> getUserAchievementList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public UserAchievement getUserAchievement(String userId, int achievementId) {
		return super.getByKey(userId, Integer.toString(achievementId));
	}

	@Override
	public boolean updateUserAchievement(String userId, int achievementId, int finishTimes, int status) {
		if (this.userAchievementDaoMysqlImpl.updateUserAchievement(userId, achievementId, finishTimes, status)) {
			if (super.isExitKey(userId)) {
				UserAchievement userAchievement = super.getByKey(userId, Integer.toString(achievementId));
				userAchievement.setFinishTimes(finishTimes);
				userAchievement.setStatus(status);
				userAchievement.setUpdatedTime(new Date());				
			}
			
			return true;
		}		
		
		return false;
	}

	@Override
	public List<UserAchievement> getUserAchievementList(String userId, int status) {
		List<UserAchievement> achievementList = super.getMapList(userId);
		List<UserAchievement> returnList = Lists.newArrayList();
		
		for (UserAchievement userAchievement : achievementList) {
			if (userAchievement.getStatus() == status)
				returnList.add(userAchievement);
		}		
		
		return returnList;
	}

	@Override
	public boolean addList(String userId, List<UserAchievement> achievementList) {
		if (this.userAchievementDaoMysqlImpl.addList(userId, achievementList)) {
			for (UserAchievement userAchievement : achievementList) {
				super.addMapValues(userId, userAchievement.getAchievementId() + "", userAchievement);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public Map<String, UserAchievement> getUserAchievementMap(String userId) {
		Map<String, UserAchievement> map = super.getMap(userId);
		if (map == null)
			map =Maps.newConcurrentMap();
		return map;
	}

}
