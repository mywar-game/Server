package com.fantingame.game.mywar.logic.setting.dao.cache;

import java.util.Date;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.setting.dao.UserSettingInfoDao;
import com.fantingame.game.mywar.logic.setting.dao.mysql.UserSettingInfoDaoMysql;
import com.fantingame.game.mywar.logic.setting.model.UserSettingInfo;

public class UserSettingInfoDaoCache extends BaseCacheDao<UserSettingInfo>
		implements UserSettingInfoDao {

	private UserSettingInfoDaoMysql userSettingInfoDaoMysql;

	public UserSettingInfoDaoMysql getUserSettingInfoDaoMysql() {
		return userSettingInfoDaoMysql;
	}

	public void setUserSettingInfoDaoMysql(UserSettingInfoDaoMysql userSettingInfoDaoMysql) {
		this.userSettingInfoDaoMysql = userSettingInfoDaoMysql;
	}

	@Override
	protected UserSettingInfo loadFromDb(String userId) {
		return userSettingInfoDaoMysql.getUserSettingInfo(userId);
	}

	@Override
	public UserSettingInfo getUserSettingInfo(String userId) {
		return super.getEntry(userId);
	}

	@Override
	public boolean addUserSettingInfo(UserSettingInfo userSettingInfo) {
		if (userSettingInfoDaoMysql.addUserSettingInfo(userSettingInfo)) {
			super.addEntry(userSettingInfo.getUserId(), userSettingInfo);
			
			return true;
		}		
		
		return false;
	}

	@Override
	public boolean updateUserSettingInfo(String userId, int num) {
		if (userSettingInfoDaoMysql.updateUserSettingInfo(userId, num)) {
			if (super.isExitKey(userId)) {
				UserSettingInfo info = super.getEntry(userId);
				info.setDisplayNum(num);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateUserDailyTips(String userId, String tips) {
		if (userSettingInfoDaoMysql.updateUserDailyTips(userId, tips)) {
			if (super.isExitKey(userId)) {
				UserSettingInfo info = super.getEntry(userId);
				info.setDailyTips(tips);
				info.setUpdatedTime(new Date());
			}
			
			return true;
		}
		
		return false;
	}

}
