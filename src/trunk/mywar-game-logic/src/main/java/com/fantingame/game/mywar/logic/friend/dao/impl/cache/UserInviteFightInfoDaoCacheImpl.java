package com.fantingame.game.mywar.logic.friend.dao.impl.cache;

import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.friend.dao.UserInviteFightInfoDao;
import com.fantingame.game.mywar.logic.friend.dao.impl.mysql.UserInviteFightInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.friend.model.UserInviteFightInfo;
import com.google.common.collect.Maps;

public class UserInviteFightInfoDaoCacheImpl extends BaseCacheMapDao<UserInviteFightInfo>
		implements UserInviteFightInfoDao {

	private UserInviteFightInfoDaoMysqlImpl userInviteFightInfoDaoMysqlImpl;
	
	public UserInviteFightInfoDaoMysqlImpl getUserInviteFightInfoDaoMysqlImpl() {
		return userInviteFightInfoDaoMysqlImpl;
	}

	public void setUserInviteFightInfoDaoMysqlImpl(
			UserInviteFightInfoDaoMysqlImpl userInviteFightInfoDaoMysqlImpl) {
		this.userInviteFightInfoDaoMysqlImpl = userInviteFightInfoDaoMysqlImpl;
	}

	@Override
	protected Map<String, UserInviteFightInfo> loadFromDb(String userId) {		
		Map<String, UserInviteFightInfo> map = Maps.newConcurrentMap();
		List<UserInviteFightInfo> list = this.userInviteFightInfoDaoMysqlImpl.getUserInviteFightInfoList(userId);
		for (UserInviteFightInfo info : list) {
			map.put(info.getInviteUserId(), info);
		}		
		
		return map;
	}

	@Override
	public List<UserInviteFightInfo> getUserInviteFightInfoList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public boolean deleteInviteFightList(String userId) {
		if (this.userInviteFightInfoDaoMysqlImpl.deleteInviteFightList(userId)) {
			super.delete(userId);
			
			return true;
		}
		
		return false;
	}

	@Override
	public UserInviteFightInfo getUserInviteFightInfo(String userId, String userFriendId) {
		return super.getByKey(userId, userFriendId);
	}

	@Override
	public boolean addUserInviteFightInfo(UserInviteFightInfo inviteFight) {
		return super.addMapValues(inviteFight.getUserId(), inviteFight.getInviteUserId(), inviteFight);
	}

}
