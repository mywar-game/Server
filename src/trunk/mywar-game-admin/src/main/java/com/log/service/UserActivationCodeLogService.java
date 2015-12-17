package com.log.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.log.bo.UserActivationCodeLog;
import com.log.dao.UserActivationCodeLogDao;

public class UserActivationCodeLogService {
	
	private UserActivationCodeLogDao userActivationCodeLogDao;
	
	public Map<String, UserActivationCodeLog> findMap(String codes) {
		Map<String, UserActivationCodeLog> map = new HashMap<String, UserActivationCodeLog>();
		 List<UserActivationCodeLog> list = userActivationCodeLogDao.find("select new UserActivationCodeLog(log.activationCode, log.userId, u.name, log.activationTime) from UserActivationCodeLog log, User u where log.userId = u.userId and log.activationCode in (" + codes + ")", DBSource.LOG);
		 if (list != null && list.size() > 0) {
			for (UserActivationCodeLog log : list) {
				map.put(log.getActivationCode(), log);
			}
		}
		 return map;
	}
	
	public Integer findPlatformCodesUseNum(String codes){
		userActivationCodeLogDao.closeSession(DBSource.LOG);
		return userActivationCodeLogDao.count("from UserActivationCodeLog where activationCode in (" + codes + ")", new ArrayList<Object>());
	}

	public void setUserActivationCodeLogDao(UserActivationCodeLogDao userActivationCodeLogDao) {
		this.userActivationCodeLogDao = userActivationCodeLogDao;
	}

	public UserActivationCodeLogDao getUserActivationCodeLogDao() {
		return userActivationCodeLogDao;
	}

}
