package com.fantingame.game.mywar.logic.legion.dao.cache;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.legion.dao.UserInvestInfoDao;
import com.fantingame.game.mywar.logic.legion.dao.mysql.UserInvestInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.legion.model.UserInvestInfo;

public class UserInvestInfoDaoCacheImpl extends BaseCacheDao<UserInvestInfo> implements
		UserInvestInfoDao {

	private UserInvestInfoDaoMysqlImpl userInvestInfoDaoMysqlImpl;
	
	public UserInvestInfoDaoMysqlImpl getUserInvestInfoDaoMysqlImpl() {
		return userInvestInfoDaoMysqlImpl;
	}

	public void setUserInvestInfoDaoMysqlImpl(UserInvestInfoDaoMysqlImpl userInvestInfoDaoMysqlImpl) {
		this.userInvestInfoDaoMysqlImpl = userInvestInfoDaoMysqlImpl;
	}

	@Override
	protected UserInvestInfo loadFromDb(String userId) {
		return this.userInvestInfoDaoMysqlImpl.getUserInvestInfo(userId);
	}

	@Override
	public UserInvestInfo getUserInvestInfo(String userId) {
		return super.getEntry(userId);
	} 

}
