package com.log.service;

import java.util.ArrayList;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.log.bo.UserSuggestLog;
import com.log.dao.UserSuggestLogDao;

public class UserSuggestLogService {
	
	private UserSuggestLogDao userSuggestLogDao;
	
	public void setUserSuggestLogDao(UserSuggestLogDao userSuggestLogDao) {
		this.userSuggestLogDao = userSuggestLogDao;
	}

	public UserSuggestLogDao getUserSuggestLogDao() {
		return userSuggestLogDao;
	}
	
	public IPage<UserSuggestLog> findSuggestLogPageListByCondition(Integer currentPage, Integer pageSize) {
		String str = "from UserSuggestLog order by id desc";
		userSuggestLogDao.closeSession(DBSource.LOG);
		return userSuggestLogDao.findPage(str, new ArrayList<Object>(), pageSize, currentPage);
	}
}
