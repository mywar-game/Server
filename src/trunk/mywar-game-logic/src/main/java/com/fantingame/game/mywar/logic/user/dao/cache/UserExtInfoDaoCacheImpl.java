package com.fantingame.game.mywar.logic.user.dao.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.user.dao.UserExtInfoDao;
import com.fantingame.game.mywar.logic.user.dao.mysql.UserExtInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.user.model.UserExtInfo;

public class UserExtInfoDaoCacheImpl extends BaseCacheDao<UserExtInfo> implements UserExtInfoDao {
    @Autowired
	private UserExtInfoDaoMysqlImpl userExtInfoDaoMysqlImpl;
	@Override
	public UserExtInfo getUserExtInfo(String userId) {
		return super.getEntry(userId);
	}
	
	@Override
	public boolean addAllChargMoney(String userId, int addAmount) {
		if(userExtInfoDaoMysqlImpl.addAllChargMoney(userId, addAmount)){
			if(super.isExitKey(userId)){
				UserExtInfo userExtInfo = super.getEntry(userId);
				userExtInfo.setAllChargeMoney(userExtInfo.getAllChargeMoney()+addAmount);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean addOnLineTime(String userId, int addSeconds) {
		if(userExtInfoDaoMysqlImpl.addOnLineTime(userId, addSeconds)){
			if(super.isExitKey(userId)){
				UserExtInfo userExtInfo = super.getEntry(userId);
				userExtInfo.setAllOnlineTime(userExtInfo.getAllOnlineTime()+addSeconds);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean addPackExtendTimes(String userId, int addTimes) {
		if(userExtInfoDaoMysqlImpl.addPackExtendTimes(userId, addTimes)){
			if(super.isExitKey(userId)){
				UserExtInfo userExtInfo = super.getEntry(userId);
				userExtInfo.setPackExtendTimes(userExtInfo.getPackExtendTimes()+addTimes);
			}
			return true;
		}
		return false;
	}
	
	@Override
	protected UserExtInfo loadFromDb(String key) {
		return userExtInfoDaoMysqlImpl.getUserExtInfo(key);
	}
	
	@Override
	public boolean addUserExtInfo(UserExtInfo userExtInfo) {
		if(userExtInfoDaoMysqlImpl.addUserExtInfo(userExtInfo)){
			if(super.isExitKey(userExtInfo.getUserId())){
				 super.addEntry(userExtInfo.getUserId(), userExtInfo);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateEffective(String userId, int newEffective) {
		if(userExtInfoDaoMysqlImpl.updateEffective(userId, newEffective)){
			if(super.isExitKey(userId)){
				UserExtInfo userExtInfo = super.getEntry(userId);
				userExtInfo.setEffective(newEffective);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updatePrePosition(String userId, String prePosition) {
		if(userExtInfoDaoMysqlImpl.updatePrePosition(userId, prePosition)){
			if(super.isExitKey(userId)){
				UserExtInfo userExtInfo = super.getEntry(userId);
				userExtInfo.setPrePosition(prePosition);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean recordGuideStep(String userId, String recordGuideStep, int guideStep) {
		if (userExtInfoDaoMysqlImpl.recordGuideStep(userId, recordGuideStep, guideStep)) {
			if (super.isExitKey(userId)) {
				UserExtInfo userExtInfo = super.getEntry(userId);
				userExtInfo.setRecordGuideStep(recordGuideStep);
				userExtInfo.setGuideStep(guideStep);
			}
			return true;
		}		
		
		return false;
	}

	@Override
	public List<UserExtInfo> getUserEffectiveRankList(int limit) {
		return this.userExtInfoDaoMysqlImpl.getUserEffectiveRankList(limit);
	}

	@Override
	public int getUserEffectiveRank(int effective) {
		return this.userExtInfoDaoMysqlImpl.getUserEffectiveRank(effective);
	}

	@Override
	public boolean extendStorehouseNum(String userId, int extendNum) {
		if (this.userExtInfoDaoMysqlImpl.extendStorehouseNum(userId, extendNum)) {
			if (super.isExitKey(userId)) {
				UserExtInfo info = super.getEntry(userId);
				info.setStorehouseExtendTimes(info.getStorehouseExtendTimes() + extendNum);
			}
			
			return true;
		}
		
		return false;
	}
	
}
