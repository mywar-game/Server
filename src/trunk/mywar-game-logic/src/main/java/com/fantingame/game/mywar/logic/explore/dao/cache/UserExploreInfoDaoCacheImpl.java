package com.fantingame.game.mywar.logic.explore.dao.cache;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.explore.dao.UserExploreInfoDao;
import com.fantingame.game.mywar.logic.explore.dao.mysql.UserExploreInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.explore.model.UserExploreInfo;

public class UserExploreInfoDaoCacheImpl extends BaseCacheDao<UserExploreInfo>
		implements UserExploreInfoDao {

	@Autowired
	private UserExploreInfoDaoMysqlImpl userExploreInfoDaoMysqlImpl;

	public UserExploreInfoDaoMysqlImpl getUserExploreInfoDaoMysqlImpl() {
		return userExploreInfoDaoMysqlImpl;
	}

	public void setUserExploreInfoDaoMysqlImpl(
			UserExploreInfoDaoMysqlImpl userExploreInfoDaoMysqlImpl) {
		this.userExploreInfoDaoMysqlImpl = userExploreInfoDaoMysqlImpl;
	}

	@Override
	protected UserExploreInfo loadFromDb(String userId) {
		return this.userExploreInfoDaoMysqlImpl.getUserExploreInfo(userId);
	}

	@Override
	public UserExploreInfo getUserExploreInfo(String userId) {
		return super.getEntry(userId);
	}

	@Override
	public boolean addUserExploreInfo(UserExploreInfo info) {
		if (userExploreInfoDaoMysqlImpl.addUserExploreInfo(info)) {
			super.addEntry(info.getUserId(), info);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateUserExploreInfo(String userId, int mapId, int exploreTimes, Date date) {
		if (userExploreInfoDaoMysqlImpl.updateUserExploreInfo(userId, mapId, exploreTimes, date)) {
			if (super.isExitKey(userId)) {
				UserExploreInfo userExploreInfo = super.getEntry(userId);
				userExploreInfo.setMapId(mapId);
				userExploreInfo.setExploreTimes(exploreTimes);
				userExploreInfo.setUpdatedTime(date);
			}
			return true;
		}
		
		return false;
	}

	@Override
	public boolean reduceIntegral(String userId, int integral) {
		if (userExploreInfoDaoMysqlImpl.reduceIntegral(userId, integral)) {
			if (super.isExitKey(userId)) {
				UserExploreInfo info = super.getEntry(userId);
				info.setIntegral(info.getIntegral() - integral);
			}
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateExploreMap(String userId, int mapId, int score) {
		if (userExploreInfoDaoMysqlImpl.updateExploreMap(userId, mapId, score)) {
			if (super.isExitKey(userId)) {
				UserExploreInfo info = super.getEntry(userId);
				info.setMapId(mapId);
				info.setIntegral(info.getIntegral() + score);
			}
			return true;
		}
		
		return false;
	}

}
