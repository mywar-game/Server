package com.log.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.util.Tools;
import com.framework.common.DBSource;
import com.log.dao.UserMailChangeLogDao;

public class UserMailChangeLogService {
	
	private UserMailChangeLogDao userMailChangeLogDao;
	
	public Map<Long, Map<Integer, String>> findMapByUserMailIds(String userMailIds){
		Map<Long, Map<Integer, String>> resultMap = new HashMap<Long, Map<Integer,String>>();
		if (Tools.isEmpty(userMailIds)) {
			return resultMap;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT USER_MAIL_ID,CHANGE_TYPE,CHANGE_TIME FROM user_mail_change_log WHERE USER_MAIL_ID IN (");
		sql.append(userMailIds);
		sql.append(") ORDER BY USER_MAIL_ID,CHANGE_TYPE");
		userMailChangeLogDao.closeSession(DBSource.LOG);
		List<Object> list = userMailChangeLogDao.findSQL_(sql.toString());
		if (list == null  || list.size() == 0) {
			return resultMap;
		}
		Long userMailId = 0L;
		Map<Integer, String> map = null;
		for (int i = 0; i < list.size(); i++) {
			if (Long.valueOf(((Object[]) list.get(i))[0].toString()).longValue() != userMailId.longValue()) {
				map = new HashMap<Integer, String>();
			}
			map.put(Integer.valueOf(((Object[]) list.get(i))[1].toString()), ((Object[]) list.get(i))[2].toString());
			userMailId = Long.valueOf(((Object[]) list.get(i))[0].toString());
			resultMap.put(userMailId, map);
		}
		return resultMap;
	}

	public void setUserMailChangeLogDao(UserMailChangeLogDao userMailChangeLogDao) {
		this.userMailChangeLogDao = userMailChangeLogDao;
	}

	public UserMailChangeLogDao getUserMailChangeLogDao() {
		return userMailChangeLogDao;
	}

}
