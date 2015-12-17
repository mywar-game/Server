package com.log.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.DBSource;
import com.log.bo.UserRegLinkLog;
import com.log.dao.UserRegLinkLogDao;

public class UserRegLinkLogService {
	
	private UserRegLinkLogDao userRegLinkLogDao;
	
	public Map<String, Integer> findMap(String regLinks) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		 List<UserRegLinkLog> list = userRegLinkLogDao.find("from UserRegLinkLog where regLinkId in (" + regLinks + ")", DBSource.LOG);
		 if (list != null && list.size() > 0) {
			for (UserRegLinkLog log : list) {
				if (map.get(log.getRegLinkId()) == null) {
					map.put(log.getRegLinkId(), 1);
				} else {
					map.put(log.getRegLinkId(), map.get(log.getRegLinkId())+1);
				}
			}
		}
		 return map;
	}

	public void setUserRegLinkLogDao(UserRegLinkLogDao userRegLinkLogDao) {
		this.userRegLinkLogDao = userRegLinkLogDao;
	}

	public UserRegLinkLogDao getUserRegLinkLogDao() {
		return userRegLinkLogDao;
	}

}
