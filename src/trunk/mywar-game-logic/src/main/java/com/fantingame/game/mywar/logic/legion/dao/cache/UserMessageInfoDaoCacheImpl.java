package com.fantingame.game.mywar.logic.legion.dao.cache;

import java.util.Date;
import java.util.List;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.legion.dao.UserMessageInfoDao;
import com.fantingame.game.mywar.logic.legion.dao.mysql.UserMessageInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.legion.model.UserMessageInfo;
import com.google.common.collect.Lists;

public class UserMessageInfoDaoCacheImpl extends BaseCacheDao<List<UserMessageInfo>> implements
		UserMessageInfoDao {

	private UserMessageInfoDaoMysqlImpl userMessageInfoDaoMysqlImpl;
	
	public UserMessageInfoDaoMysqlImpl getUserMessageInfoDaoMysqlImpl() {
		return userMessageInfoDaoMysqlImpl;
	}

	public void setUserMessageInfoDaoMysqlImpl(UserMessageInfoDaoMysqlImpl userMessageInfoDaoMysqlImpl) {
		this.userMessageInfoDaoMysqlImpl = userMessageInfoDaoMysqlImpl;
	}

	@Override
	protected List<UserMessageInfo> loadFromDb(String legionId) {
		return this.userMessageInfoDaoMysqlImpl.getUserMessageInfoList(legionId);
	}

	@Override
	public List<UserMessageInfo> getUserMessageInfoList(String legionId) {
		return super.getEntry(legionId);
	}

	@Override
	public boolean addUserMessageInfo(UserMessageInfo userMessageInfo) {
		if (this.userMessageInfoDaoMysqlImpl.addUserMessageInfo(userMessageInfo)) {
			if (super.isExitKey(userMessageInfo.getLegionId())) {
				super.getEntry(userMessageInfo.getLegionId()).add(userMessageInfo);
			} else {
				List<UserMessageInfo> infoList = Lists.newArrayList();
				infoList.add(userMessageInfo);
				super.addEntry(userMessageInfo.getLegionId(), infoList);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteUserMessageInfo(String legionId) {
		if (this.userMessageInfoDaoMysqlImpl.deleteUserMessageInfo(legionId)) {
			super.delete(legionId);
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<UserMessageInfo> getUserMessageInfoList(String legionId, Date endTime) {
		List<UserMessageInfo> infoList = super.getEntry(legionId);
		List<UserMessageInfo> returnList = Lists.newArrayList();
		
		for (UserMessageInfo info : infoList) {
			if (info.getCreatedTime().after(endTime))
				returnList.add(info);
		}
		
		return returnList;
	}

}
