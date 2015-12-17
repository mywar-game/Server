package com.log.service;

import java.util.ArrayList;

import com.admin.util.Tools;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.log.bo.UserBuildingLog;
import com.log.dao.UserBuildingLogDao;

public class UserBuildingLogService {

	private UserBuildingLogDao userBuildingLogDao; 
	
	public UserBuildingLogDao getUserBuildingLogDao() {
		return userBuildingLogDao;
	}

	public void setUserBuildingLogDao(UserBuildingLogDao userBuildingLogDao) {
		this.userBuildingLogDao = userBuildingLogDao;
	}

	/**
	 * 玩家建筑日志分页列表
	 * @param userId
	 * @param searchOperation
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<UserBuildingLog> getUserBuildingLogPageList(String userId, Integer searchBuildingId, String searchOperation, Integer currentPage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("select new UserBuildingLog(log.userBuildingId, log.userId, user.name, log.buildingId, log.operation, log.lastLevel, log.cost, log.speedSeconds, log.createTime) from UserBuildingLog log, User user where log.userId = user.userId ");
		if (userId != null) {
			hql.append(" and log.userId = '").append(userId).append("'");
		}
		if (searchBuildingId != null) {
			hql.append(" and log.buildingId = ").append(searchBuildingId);
		}
		if (!Tools.isEmpty(searchOperation)) {
			hql.append(" and log.operation = '").append(searchOperation).append("'");
		}
		hql.append(" order by log.createTime DESC");
		userBuildingLogDao.closeSession(DBSource.LOG);
		return userBuildingLogDao.findPage(hql.toString(), new ArrayList<UserBuildingLog>(), pageSize, currentPage);
	}
}
