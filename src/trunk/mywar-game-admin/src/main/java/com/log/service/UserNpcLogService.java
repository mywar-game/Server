package com.log.service;

import java.util.ArrayList;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.log.bo.UserNpcLog;
import com.log.dao.UserNpcLogDao;

public class UserNpcLogService {
	private UserNpcLogDao userNpcLogDao;

	public UserNpcLogDao getUserNpcLogDao() {
		return userNpcLogDao;
	}

	public void setUserNpcLogDao(UserNpcLogDao userNpcLogDao) {
		this.userNpcLogDao = userNpcLogDao;
	}
	
	public IPage<UserNpcLog> findUserNpcLogListByCondition(String userId, Integer currentPage, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append("select new UserNpcLog(log.userId, log.npcId, log.buildingId, log.userBuildingId, log.position, log.operation, log.createTime, user.userName, user.name) from UserNpcLog log, User user where log.userId = user.userId");
		if (userId != null) {
			sql.append(" and log.userId = '").append(userId).append("'");
		}
		sql.append(" order by log.createTime DESC");
		userNpcLogDao.closeSession(DBSource.LOG);
		return userNpcLogDao.findPage(sql.toString(), new ArrayList<UserNpcLog>(), pageSize, currentPage);
	}
}
