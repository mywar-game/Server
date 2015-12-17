package com.stats.service;

import java.util.List;

import com.adminTool.bo.User;
import com.adminTool.dao.UserDao;
import com.framework.common.DBSource;

public class UserDistributionService {

	private UserDao userDao;
	
	// 统计
	public List<User> count() {
		List<User> userList = userDao.find("from User", DBSource.LOG);
		return userList;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
