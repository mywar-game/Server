package com.fantingame.game.mywar.logic.user.dao.cache;

import java.util.Date;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.user.dao.UserMapInfoDao;
import com.fantingame.game.mywar.logic.user.dao.mysql.UserMapInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.user.model.UserMapInfo;

public class UserMapInfoDaoCacheImpl extends BaseCacheDao<UserMapInfo>
		implements UserMapInfoDao {

	private UserMapInfoDaoMysqlImpl userMapInfoDaoMysqlImpl;

	public UserMapInfoDaoMysqlImpl getUserMapInfoDaoMysqlImpl() {
		return userMapInfoDaoMysqlImpl;
	}

	public void setUserMapInfoDaoMysqlImpl(
			UserMapInfoDaoMysqlImpl userMapInfoDaoMysqlImpl) {
		this.userMapInfoDaoMysqlImpl = userMapInfoDaoMysqlImpl;
	}

	@Override
	protected UserMapInfo loadFromDb(String userId) {
		return userMapInfoDaoMysqlImpl.getUserMapInfo(userId);
	}

	@Override
	public UserMapInfo getUserMapInfo(String userId) {
		return super.getEntry(userId);
	}

	@Override
	public boolean addUserMapInfo(UserMapInfo userMapInfo) {
		if (this.userMapInfoDaoMysqlImpl.addUserMapInfo(userMapInfo)) {
			super.addEntry(userMapInfo.getUserId(), userMapInfo);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateUserMapInfo(String userId, String maps) {
		if (this.userMapInfoDaoMysqlImpl.updateUserMapInfo(userId, maps)) {
			if (super.isExitKey(userId)) {
				UserMapInfo userMapInfo = super.getEntry(userId);
				userMapInfo.setMaps(maps);
				userMapInfo.setUpdatedTime(new Date());
			}
			
			return true;
		}
		
		return false;
	}

}
