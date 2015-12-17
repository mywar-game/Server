package com.fantingame.game.mywar.logic.legion.dao.cache;

import java.util.Date;
import java.util.List;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.legion.dao.UserLegionInfoDao;
import com.fantingame.game.mywar.logic.legion.dao.mysql.UserLegionInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.legion.model.UserLegionInfo;

public class UserLegionInfoDaoCacheImpl extends BaseCacheDao<UserLegionInfo>
		implements UserLegionInfoDao {

	private UserLegionInfoDaoMysqlImpl userLegionInfoDaoMysqlImpl;

	public UserLegionInfoDaoMysqlImpl getUserLegionInfoDaoMysqlImpl() {
		return userLegionInfoDaoMysqlImpl;
	}

	public void setUserLegionInfoDaoMysqlImpl(UserLegionInfoDaoMysqlImpl userLegionInfoDaoMysqlImpl) {
		this.userLegionInfoDaoMysqlImpl = userLegionInfoDaoMysqlImpl;
	}

	@Override
	protected UserLegionInfo loadFromDb(String userId) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDaoMysqlImpl.getUserLegionInfo(userId);
		if (userLegionInfo != null)
			super.addEntry(userId, userLegionInfo);
		
		return userLegionInfo;
	}

	@Override
	public UserLegionInfo getUserLegionInfo(String userId) {
		return super.getEntry(userId);
	}

	@Override
	public boolean addUserLegionInfo(UserLegionInfo userLegionInfo) {
		if (this.userLegionInfoDaoMysqlImpl.addUserLegionInfo(userLegionInfo)) {
			super.addEntry(userLegionInfo.getUserId(), userLegionInfo);
			
			return true;
		}
		
		return false;
	}

	@Override
	public int getLegionCurrentNum(String legionId) {
		return this.userLegionInfoDaoMysqlImpl.getLegionCurrentNum(legionId);
	}

	@Override
	public boolean updateUserlegionInfo(String userId, String legionId, int status, int identity, int contribution) {
		if (this.userLegionInfoDaoMysqlImpl.updateUserlegionInfo(userId, legionId, status, identity, contribution)) {
			if (super.isExitKey(userId)) {
				UserLegionInfo userLegionInfo = this.getEntry(userId);
				userLegionInfo.setStatus(status);
				userLegionInfo.setLegionId(legionId);
				userLegionInfo.setIdentity(identity);
				userLegionInfo.setContribution(contribution);
				userLegionInfo.setUpdatedTime(new Date());				
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<UserLegionInfo> getUserLegionInfoList(String legionId) {
		return this.userLegionInfoDaoMysqlImpl.getUserLegionInfoList(legionId);
	}

	@Override
	public int getLegionCurrentIdentityNum(String legionId, int identity) {
		return this.userLegionInfoDaoMysqlImpl.getLegionCurrentIdentityNum(legionId, identity);
	}

	@Override
	public boolean updateUserlegionInfo(String userId, int identity) {
		if (this.userLegionInfoDaoMysqlImpl.updateUserlegionInfo(userId, identity)) {
			if (super.isExitKey(userId)) {
				UserLegionInfo userLegionInfo = super.getEntry(userId);
				userLegionInfo.setIdentity(identity);
				userLegionInfo.setUpdatedTime(new Date());
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateUserlegionInfoStatus(String userId, int status) {
		if (this.userLegionInfoDaoMysqlImpl.updateUserlegionInfoStatus(userId, status)) {
			if (super.isExitKey(userId)) {
				UserLegionInfo userLegionInfo = super.getEntry(userId);
				userLegionInfo.setStatus(status);
				userLegionInfo.setUpdatedTime(new Date());
			}
			
			return true;
		}		
		
		return false;
	}

	@Override
	public boolean updateUserlegionInfoStatus(String legionId, int status, List<UserLegionInfo> userLegionInfoList) {
		if (this.userLegionInfoDaoMysqlImpl.updateUserlegionInfoStatus(legionId, status, userLegionInfoList)) {
			for (UserLegionInfo info : userLegionInfoList) {
				if (super.isExitKey(info.getUserId())) {
					UserLegionInfo userLegionInfo = super.getEntry(info.getUserId());
					userLegionInfo.setStatus(status);
					userLegionInfo.setUpdatedTime(new Date());
				}
			}
			
			return true;
		}		
		
		return false;
	}

	@Override
	public boolean updateUserContribution(String userId, int contribution) {
		if (this.userLegionInfoDaoMysqlImpl.updateUserContribution(userId, contribution)){
			UserLegionInfo userLegionInfo = super.getEntry(userId);
			userLegionInfo.setContribution(contribution);
			userLegionInfo.setUpdatedTime(new Date());
			
			return true;
		}
		
		return false;
	}

}
