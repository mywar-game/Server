package com.fantingame.game.mywar.logic.mall.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.mall.dao.UserBuyBackInfoDao;
import com.fantingame.game.mywar.logic.mall.dao.mysql.UserBuyBackInfoDaoMysql;
import com.fantingame.game.mywar.logic.mall.model.UserBuyBackInfo;
import com.google.common.collect.Maps;

public class UserBuyBackInfoDaoCache extends BaseCacheMapDao<UserBuyBackInfo>
		implements UserBuyBackInfoDao {

	private UserBuyBackInfoDaoMysql userBuyBackInfoDaoMysql;

	public UserBuyBackInfoDaoMysql getUserBuyBackInfoDaoMysql() {
		return userBuyBackInfoDaoMysql;
	}

	public void setUserBuyBackInfoDaoMysql(
			UserBuyBackInfoDaoMysql userBuyBackInfoDaoMysql) {
		this.userBuyBackInfoDaoMysql = userBuyBackInfoDaoMysql;
	}

	@Override
	public List<UserBuyBackInfo> getUserBuyBackInfoList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	protected Map<String, UserBuyBackInfo> loadFromDb(String userId) {
		Map<String, UserBuyBackInfo> map = Maps.newConcurrentMap();
		List<UserBuyBackInfo> list = userBuyBackInfoDaoMysql.getUserBuyBackInfoList(userId);
		for (UserBuyBackInfo userBuyBackInfo : list) {
			map.put(userBuyBackInfo.getBuyBackId(), userBuyBackInfo);
		}
		
		return map;
	}

	@Override
	public boolean addUserBuyBackInfo(UserBuyBackInfo userBuyBackInfo) {
		if (userBuyBackInfoDaoMysql.addUserBuyBackInfo(userBuyBackInfo)) {
			if (super.isExitKey(userBuyBackInfo.getUserId())) {
				super.addMapValues(userBuyBackInfo.getUserId(), userBuyBackInfo.getBuyBackId(), userBuyBackInfo);
			}			
			
			return true;
		}
		
		return false;
	}

	@Override
	public UserBuyBackInfo getUserBuyBackInfo(String userId, String buyBackId) {
		return super.getByKey(userId, buyBackId);
	}

	@Override
	public boolean deleteUserBuyBackInfo(String userId, String buyBackId) {
		if (userBuyBackInfoDaoMysql.deleteUserBuyBackInfo(userId, buyBackId)) {
			super.deleteKey(userId, buyBackId);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteUserBuyBackInfo(String userId, Date endTime) {
		if (userBuyBackInfoDaoMysql.deleteUserBuyBackInfo(userId, endTime)) {
			if (super.isExitKey(userId)) {
				List<UserBuyBackInfo> infoList = super.getMapList(userId);
				for (UserBuyBackInfo info : infoList) {
					if (info.getUpdatedTime().before(endTime))
						super.deleteKey(userId, info.getBuyBackId());
				}				
			}
			
			return true;
		}
		
		return false;
	}
}
