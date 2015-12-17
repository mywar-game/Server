package com.fantingame.game.mywar.logic.pk.dao.cache;

import java.util.Date;
import java.util.List;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.pk.dao.UserPkInfoDao;
import com.fantingame.game.mywar.logic.pk.dao.mysql.UserPkInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.pk.model.UserPkInfo;

public class UserPkInfoDaoCacheImpl extends BaseCacheDao<UserPkInfo> implements UserPkInfoDao{

	private UserPkInfoDaoMysqlImpl userPkInfoDaoMysqlImpl;

	public UserPkInfoDaoMysqlImpl getUserPkInfoDaoMysqlImpl() {
		return userPkInfoDaoMysqlImpl;
	}

	public void setUserPkInfoDaoMysqlImpl(
			UserPkInfoDaoMysqlImpl userPkInfoDaoMysqlImpl) {
		this.userPkInfoDaoMysqlImpl = userPkInfoDaoMysqlImpl;
	}

	@Override
	protected UserPkInfo loadFromDb(String userId) {
		return this.userPkInfoDaoMysqlImpl.getUserPkInfo(userId);
	}

	@Override
	public UserPkInfo getUserPkInfo(String userId) {
		return super.getEntry(userId);
	}

	@Override
	public UserPkInfo getLastUserPkInfo() {
		return this.userPkInfoDaoMysqlImpl.getLastUserPkInfo();
	}

	@Override
	public boolean addUserPkInfo(UserPkInfo userPkInfo) {
		if (this.userPkInfoDaoMysqlImpl.addUserPkInfo(userPkInfo)) {
			super.addEntry(userPkInfo.getUserId(), userPkInfo);
			
			return true;
		}		
		
		return false;
	}

	@Override
	public List<UserPkInfo> findChallengerList(int minRank, int maxRank, String userId) {
		return this.userPkInfoDaoMysqlImpl.findChallengerList(minRank, maxRank, userId);
	}

	@Override
	public boolean updateUserPkInfo(String userId, int hasChallengeTimes, int buyChallengeTimes, int isReset) {
		if (this.userPkInfoDaoMysqlImpl.updateUserPkInfo(userId, hasChallengeTimes, buyChallengeTimes, isReset)) {
			if (super.isExitKey(userId)) {
				UserPkInfo userPkInfo = super.getEntry(userId);
				userPkInfo.setHasChallengeTimes(hasChallengeTimes);
				userPkInfo.setIsReset(isReset);
				userPkInfo.setBuyChallengeTimes(buyChallengeTimes);
				userPkInfo.setUpdatedTime(new Date());
			}
			
			return true;
		}		
		
		return false;
	}

	@Override
	public boolean resetUserWaitingTime(String userId, int isReset) {
		if (this.userPkInfoDaoMysqlImpl.resetUserWaitingTime(userId, isReset)) {
			if (super.isExitKey(userId)) {
				UserPkInfo info = super.getEntry(userId);
				info.setIsReset(isReset);
			}
			
			return true;
		}
		return false;
	}

	@Override
	public List<UserPkInfo> getRankList() {
		return this.userPkInfoDaoMysqlImpl.getRankList();
	}

	@Override
	public boolean updateUserPkInfo(String userId, int challengeTimes, int isReset, Date attackTime) {
		if (this.userPkInfoDaoMysqlImpl.updateUserPkInfo(userId, challengeTimes, isReset, attackTime)) {
			if (super.isExitKey(userId)) {
				UserPkInfo info = super.getEntry(userId);
				info.setHasChallengeTimes(challengeTimes);
				info.setUpdatedTime(attackTime);
				info.setIsReset(isReset);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean changeRank(String userId, String targetUserId, int hasChallengeTimes, int isReset, int highestRank, Date attackTime) {
		if (this.userPkInfoDaoMysqlImpl.changeRank(userId, targetUserId, hasChallengeTimes, isReset, highestRank, attackTime)) {
			super.addEntry(userId, loadFromDb(userId));
			super.addEntry(targetUserId, loadFromDb(targetUserId));
			
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserPkInfoBuyChallengeTimes(String userId, int buyChallengeTimes) {
		if (this.userPkInfoDaoMysqlImpl.updateUserPkInfoBuyChallengeTimes(userId, buyChallengeTimes)) {
			if (super.isExitKey(userId)) {
				UserPkInfo userPkInfo = super.getEntry(userId);
				userPkInfo.setBuyChallengeTimes(buyChallengeTimes);
			}
			
			return true;
		}
		return false;
	}

	@Override
	public int getUserPkInfoCount() {
		return this.userPkInfoDaoMysqlImpl.getUserPkInfoCount();
	}

	@Override
	public boolean addAll(List<UserPkInfo> infoList) {
		if (this.userPkInfoDaoMysqlImpl.addAll(infoList)) {
			for (UserPkInfo info : infoList) {
				super.addEntry(info.getUserId(), info);
			}
			
			return true;
		}		
		
		return false;
	}

}
