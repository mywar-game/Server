package com.fantingame.game.mywar.logic.mail.dao.impl.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.mail.dao.UserMailDao;
import com.fantingame.game.mywar.logic.mail.dao.impl.mysql.UserMailDaoMysqlImpl;
import com.fantingame.game.mywar.logic.mail.model.UserMail;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UserMailDaoCacheImpl extends BaseCacheMapDao<UserMail> implements UserMailDao {
	
	private UserMailDaoMysqlImpl userMailDaoMysqlImpl;

	public UserMailDaoMysqlImpl getUserMailDaoMysqlImpl() {
		return userMailDaoMysqlImpl;
	}

	public void setUserMailDaoMysqlImpl(UserMailDaoMysqlImpl userMailDaoMysqlImpl) {
		this.userMailDaoMysqlImpl = userMailDaoMysqlImpl;
	}

	@Override
	public Date getLastReceiveTime(String userId) {
		return userMailDaoMysqlImpl.getLastReceiveTime(userId);
	}

	@Override
	public boolean setLastReceiveTime(String userId, Date now) {
		return this.userMailDaoMysqlImpl.setLastReceiveTime(userId, now);
	}

	@Override
	public UserMail getBySystemMailId(String userId, String systemMailId) {
		return this.userMailDaoMysqlImpl.getBySystemMailId(userId, systemMailId);
	}

	@Override
	public boolean add(String userId, List<UserMail> userMailList) {
		if (this.userMailDaoMysqlImpl.add(userId, userMailList)) {
			if (super.isExitKey(userId)) {
				for (UserMail userMail : userMailList) {
					super.addMapValues(userId, userMail.getUserMailId() + "", userMail);
				}
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<UserMail> getList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public boolean updateStatus(String userId, int userMailId, int status) {
		if (this.userMailDaoMysqlImpl.updateStatus(userId, userMailId, status)) {
			if (super.isExitKey(userId)){
				UserMail userMail = super.getByKey(userId, String.valueOf(userMailId));
				userMail.setStatus(status);
			}			
			
			return true;
		}
		
		return false;
	}

	@Override
	public UserMail get(String userId, int userMailId) {
		return super.getByKey(userId, String.valueOf(userMailId));
	}

	@Override
	public boolean updateReceiveStatus(String userId, int userMailId, int receiveStatus) {
		if (this.userMailDaoMysqlImpl.updateReceiveStatus(userId, userMailId, receiveStatus)) {
			if (super.isExitKey(userId)) {
				UserMail userMail = super.getByKey(userId, String.valueOf(userMailId));
				userMail.setReceiveStatus(receiveStatus);				
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean delete(String userId, int userMailId) {
		if (this.userMailDaoMysqlImpl.delete(userId, userMailId)) {
			if (super.isExitKey(userId)){
				super.deleteKey(userId, String.valueOf(userMailId));
			}			
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean add(UserMail userMail) {
		if (this.userMailDaoMysqlImpl.add(userMail)) {
			if (super.isExitKey(userMail.getUserId())) {
				super.addMapValues(userMail.getUserId(), userMail.getUserMailId() + "", userMail);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<UserMail> getList(String userId, int status) {
		List<UserMail> userMailList = super.getMapList(userId);
		List<UserMail> list = Lists.newArrayList();
		for (UserMail userMail : userMailList) {
			if (userMail.getStatus() == status)				
				list.add(userMail);
		}
		
		return list;
	}

	@Override
	public boolean updateUserMail(String userId, int userMailId, int emailType) {
		if (userMailDaoMysqlImpl.updateUserMail(userId, userMailId, emailType)) {
			if (super.isExitKey(userId)) {
				UserMail userMail = super.getByKey(userId, String.valueOf(userMailId));
				userMail.setEmailType(emailType);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	protected Map<String, UserMail> loadFromDb(String userId) {
		Map<String, UserMail> map = Maps.newConcurrentMap();
		List<UserMail> list = this.userMailDaoMysqlImpl.getList(userId);
		for (UserMail userMail : list) {
			map.put(userMail.getUserMailId() + "", userMail);
		}
		
		return map;
	}

	@Override
	public int getMaxUserMailId(String userId) {
		return this.userMailDaoMysqlImpl.getMaxUserMailId(userId);
	}

}
