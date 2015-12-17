package com.fantingame.game.mywar.logic.friend.dao.impl.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.friend.dao.UserFriendInfoDao;
import com.fantingame.game.mywar.logic.friend.dao.impl.mysql.UserFriendInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.friend.model.UserFriendInfo;
import com.google.common.collect.Maps;

public class UserFriendInfoDaoCacheImpl extends BaseCacheMapDao<UserFriendInfo> implements UserFriendInfoDao {

	@Autowired
	private UserFriendInfoDaoMysqlImpl userFriendInfoDaoMysqlImpl;
	
	public UserFriendInfoDaoMysqlImpl getUserFriendInfoDaoMysqlImpl() {
		return userFriendInfoDaoMysqlImpl;
	}

	public void setUserFriendInfoDaoMysqlImpl(
			UserFriendInfoDaoMysqlImpl userFriendInfoDaoMysqlImpl) {
		this.userFriendInfoDaoMysqlImpl = userFriendInfoDaoMysqlImpl;
	}



	@Override
	public List<UserFriendInfo> getUserFriendList(String userId, int status) {
		List<UserFriendInfo> list = super.getMapList(userId);
		List<UserFriendInfo> infoList = new ArrayList<UserFriendInfo>();
		for (UserFriendInfo info : list) {
			if (info.getStatus() == status)
				infoList.add(info);
		}
		
		return infoList;
	}

	@Override
	public int getUserFriendCount(String userId) {		
		return getUserFriendList(userId, 1).size();
	}

	@Override
	public UserFriendInfo getUserFriendInfo(String userId, String targetUserId) {
		return super.getByKey(userId, targetUserId);
	}

	@Override
	public boolean add(UserFriendInfo userFriendInfo) {
		if (this.userFriendInfoDaoMysqlImpl.add(userFriendInfo)) {
			super.addMapValues(userFriendInfo.getUserId(), userFriendInfo.getUserFriendId(), userFriendInfo);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteFriend(String userId, String targetUserId) {
		if (this.userFriendInfoDaoMysqlImpl.deleteFriend(userId, targetUserId)) {
			super.deleteKey(userId, targetUserId);
			super.deleteKey(targetUserId, userId);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateUserFriendStatus(UserFriendInfo info, int status) {
		if (this.userFriendInfoDaoMysqlImpl.updateUserFriendStatus(info, status)){
			if (super.isExitKey(info.getUserId())) {
				UserFriendInfo userFriendInfo = super.getByKey(info.getUserId(), info.getUserFriendId());
				userFriendInfo.setStatus(status);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	protected Map<String, UserFriendInfo> loadFromDb(String key) {
		List<UserFriendInfo> infoList = userFriendInfoDaoMysqlImpl.getUserFriendInfoList(key);
		Map<String, UserFriendInfo> map = Maps.newConcurrentMap();
		for (UserFriendInfo info : infoList) {
			map.put(info.getUserFriendId(), info);
		}
		
		return map;
	}
}
