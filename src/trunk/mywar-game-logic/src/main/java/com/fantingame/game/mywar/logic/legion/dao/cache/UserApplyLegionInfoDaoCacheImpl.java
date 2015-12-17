package com.fantingame.game.mywar.logic.legion.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.legion.dao.UserApplyLegionInfoDao;
import com.fantingame.game.mywar.logic.legion.dao.mysql.UserApplyLegionInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.legion.model.UserApplyLegionInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UserApplyLegionInfoDaoCacheImpl extends BaseCacheMapDao<UserApplyLegionInfo>
		implements UserApplyLegionInfoDao {

	private UserApplyLegionInfoDaoMysqlImpl userApplyLegionInfoDaoMysqlImpl;
	
	public UserApplyLegionInfoDaoMysqlImpl getUserApplyLegionInfoDaoMysqlImpl() {
		return userApplyLegionInfoDaoMysqlImpl;
	}

	public void setUserApplyLegionInfoDaoMysqlImpl(UserApplyLegionInfoDaoMysqlImpl userApplyLegionInfoDaoMysqlImpl) {
		this.userApplyLegionInfoDaoMysqlImpl = userApplyLegionInfoDaoMysqlImpl;
	}

	@Override
	protected Map<String, UserApplyLegionInfo> loadFromDb(String userId) {
		Map<String, UserApplyLegionInfo> map = Maps.newConcurrentMap();
		List<UserApplyLegionInfo> list = this.userApplyLegionInfoDaoMysqlImpl.getUserApplyLegionInfoList(userId);
		for (UserApplyLegionInfo applyInfo : list) {
			map.put(applyInfo.getLegionId(), applyInfo);
		}
		
		return map;
	}

	@Override
	public List<UserApplyLegionInfo> getUserApplyLegionInfoList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public boolean addUserApplyLegionInfo(UserApplyLegionInfo userApplyLegionInfo) {
		if (this.userApplyLegionInfoDaoMysqlImpl.addUserApplyLegionInfo(userApplyLegionInfo)) {		
			super.addMapValues(userApplyLegionInfo.getUserId(), userApplyLegionInfo.getLegionId(), userApplyLegionInfo);
			
			return true;
		}
		
		return false;
	}

	@Override
	public UserApplyLegionInfo getUserApplyLegionInfo(String userId, String legionId) {
		return super.getByKey(userId, legionId);
	}

	@Override
	public boolean deleteUserApplyLegionInfo(String userId, String legionId) {
		if (this.userApplyLegionInfoDaoMysqlImpl.deleteUserApplyLegionInfo(userId, legionId)) {
			super.deleteKey(userId, legionId);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteUserApplyLegionInfo(String userId, Date endTime) {
		if (this.userApplyLegionInfoDaoMysqlImpl.deleteUserApplyLegionInfo(userId, endTime)) {
			if (super.isExitKey(userId)) {
				List<UserApplyLegionInfo> applyList = super.getMapList(userId);
				for (UserApplyLegionInfo legionInfo : applyList) {
					if (legionInfo.getUpdatedTime().getTime() <= endTime.getTime())
						super.deleteKey(userId, legionInfo.getLegionId());
				}				
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<UserApplyLegionInfo> getUserApplyLegionInfoList(String legionId, Date endTime) {
		return this.userApplyLegionInfoDaoMysqlImpl.getUserApplyLegionInfoList(legionId, endTime);
	}

	@Override
	public boolean deleteUserApplyLegionInfo(String legionId, List<UserApplyLegionInfo> applyList) {
		if (this.userApplyLegionInfoDaoMysqlImpl.deleteUserApplyLegionInfo(legionId, applyList)) {
			for (UserApplyLegionInfo applyInfo : applyList) {
				if (super.isExitKey(applyInfo.getUserId())) 
					super.deleteKey(applyInfo.getUserId(), applyInfo.getLegionId());
			}
			
			return true;
		}		
		
		return false;
	}

	@Override
	public List<UserApplyLegionInfo> getListByLegionId(String legionId) {
		return this.userApplyLegionInfoDaoMysqlImpl.getListByLegionId(legionId);
	}

	@Override
	public boolean deleteUserApplyLegionInfo(String userId) {
		if (this.userApplyLegionInfoDaoMysqlImpl.deleteUserApplyLegionInfo(userId)) {
			super.delete(userId);
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<UserApplyLegionInfo> getUserApplyLegionInfoListByUserId(String userId, Date endTime) {
		List<UserApplyLegionInfo> list = super.getMapList(userId);
		List<UserApplyLegionInfo> returnList = Lists.newArrayList();
		for (UserApplyLegionInfo applyInfo : list) {
			if (applyInfo.getUpdatedTime().getTime() <= endTime.getTime())
				returnList.add(applyInfo);
		}		 
		
		return returnList;
	}

}
