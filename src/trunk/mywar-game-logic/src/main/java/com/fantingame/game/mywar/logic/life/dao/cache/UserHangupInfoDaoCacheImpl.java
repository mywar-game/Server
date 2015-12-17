package com.fantingame.game.mywar.logic.life.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.life.dao.UserHangupInfoDao;
import com.fantingame.game.mywar.logic.life.dao.mysql.UserHangupInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.life.model.UserHangupInfo;
import com.google.common.collect.Maps;

public class UserHangupInfoDaoCacheImpl extends BaseCacheMapDao<UserHangupInfo>
		implements UserHangupInfoDao {

	private UserHangupInfoDaoMysqlImpl userHangupInfoDaoMysqlImpl;

	@Override
	protected Map<String, UserHangupInfo> loadFromDb(String userId) {
		Map<String, UserHangupInfo> map = Maps.newConcurrentMap();
		List<UserHangupInfo> list = this.userHangupInfoDaoMysqlImpl
				.getUserHangupInfoList(userId);
		for (UserHangupInfo userHangupInfo : list) {
			map.put(userHangupInfo.getCategory() + "", userHangupInfo);
		}

		return map;
	}

	@Override
	public List<UserHangupInfo> getUserHangupInfoList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public UserHangupInfo getUserHangupInfo(String userId, int category) {
		List<UserHangupInfo> list = super.getMapList(userId);
		for (UserHangupInfo info : list) {
			if (info.getCategory() == category)
				return info;
		}

		return null;
	}

	public UserHangupInfoDaoMysqlImpl getUserHangupInfoDaoMysqlImpl() {
		return userHangupInfoDaoMysqlImpl;
	}

	public void setUserHangupInfoDaoMysqlImpl(
			UserHangupInfoDaoMysqlImpl userHangupInfoDaoMysqlImpl) {
		this.userHangupInfoDaoMysqlImpl = userHangupInfoDaoMysqlImpl;
	}

	@Override
	public boolean addUserHangupInfo(UserHangupInfo userHangupInfo) {
		if (this.userHangupInfoDaoMysqlImpl.addUserHangupInfo(userHangupInfo)) {
			if (super.isExitKey(userHangupInfo.getUserId())) {
				super.addMapValues(userHangupInfo.getUserId(), userHangupInfo.getCategory() + "", userHangupInfo);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteUserHangupInfo(String userId, int category) {
		if (this.userHangupInfoDaoMysqlImpl.deleteUserHangupInfo(userId, category)) {
			if (super.isExitKey(userId)) {
				super.deleteKey(userId, category + "");
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateUserHangupInfo(String userId, int category, Date updatedTime, String rewards) {
		if (userHangupInfoDaoMysqlImpl.updateUserHangupInfo(userId, category, updatedTime, rewards)) {
			if (super.isExitKey(userId)) {
				UserHangupInfo userHangupInfo = super.getByKey(userId, category + "");
				userHangupInfo.setUpdatedTime(updatedTime);
				userHangupInfo.setRewards(rewards);
			}
			
			return true;
		}
		
		return false;
	}

}
