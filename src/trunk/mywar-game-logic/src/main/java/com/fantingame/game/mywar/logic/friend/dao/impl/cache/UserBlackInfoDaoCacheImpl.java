package com.fantingame.game.mywar.logic.friend.dao.impl.cache;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.friend.dao.UserBlackInfoDao;
import com.fantingame.game.mywar.logic.friend.dao.impl.mysql.UserBlackInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.friend.model.UserBlackInfo;
import com.google.common.collect.Maps;

public class UserBlackInfoDaoCacheImpl extends BaseCacheMapDao<UserBlackInfo> implements
		UserBlackInfoDao {

	@Autowired
	private UserBlackInfoDaoMysqlImpl userBlackInfoDaoMysqlImpl;	
	
	public UserBlackInfoDaoMysqlImpl getUserBlackInfoDaoMysqlImpl() {
		return userBlackInfoDaoMysqlImpl;
	}
	
	public void setUserBlackInfoDaoMysqlImpl(
			UserBlackInfoDaoMysqlImpl userBlackInfoDaoMysqlImpl) {
		this.userBlackInfoDaoMysqlImpl = userBlackInfoDaoMysqlImpl;
	}

	@Override
	public List<UserBlackInfo> getUserBlackInfoList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public UserBlackInfo getUserBlackInfo(String userId, String userBlackId) {		 
		return super.getByKey(userId, userBlackId);
	}

	@Override
	public int getUserBlackListCount(String userId) {
		return super.getMapList(userId).size();
	}

	@Override
	public boolean add(UserBlackInfo userBlackInfo) {
		if (this.userBlackInfoDaoMysqlImpl.add(userBlackInfo)) {
			if (super.isExitKey(userBlackInfo.getUserId())) {
				super.addMapValues(userBlackInfo.getUserId(), userBlackInfo.getUserBlackId(), userBlackInfo);
				
				return true;
			}
						
		}
		
		return false;
	}

	@Override
	public boolean deleteBlackInfo(String userId, String userBlackId) {
		if (this.userBlackInfoDaoMysqlImpl.deleteBlackInfo(userId, userBlackId)) {
			super.deleteKey(userId, userBlackId);
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<UserBlackInfo> getInTargetBlackList(String userId) {
		return this.userBlackInfoDaoMysqlImpl.getInTargetBlackList(userId);
	}

	@Override
	protected Map<String, UserBlackInfo> loadFromDb(String userId) {
		Map<String, UserBlackInfo> map = Maps.newConcurrentMap();
		List<UserBlackInfo> infoList = userBlackInfoDaoMysqlImpl.getUserBlackInfoList(userId);
		for (UserBlackInfo info : infoList) {
			map.put(info.getUserBlackId(), info);
		}
		
		return map;
	}

//	@Override
//	public List<String> getInTargetBlackIds(String userId) {
//		return this.userBlackInfoDaoMysqlImpl.getInTargetBlackIds(userId);
//	}

}
