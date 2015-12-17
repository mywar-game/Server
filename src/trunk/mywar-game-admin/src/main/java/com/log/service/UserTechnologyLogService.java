package com.log.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.log.bo.UserTechnologyLog;
import com.log.dao.UserTechnologyLogDao;

public class UserTechnologyLogService {

	private UserTechnologyLogDao userTechnologyLogDao;
	
	public IPage<UserTechnologyLog> findUserTechnologyLogListByCondition(String userId, Integer searchId, Integer searchType,Date startDate, Date endDate, Integer currentPage, Integer pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append("select new UserTechnologyLog(log.technologyId, log.technologyLevel, log.userId, user.name, log.type, log.costType, log.costNum, log.speedSeconds, log.createTime) from UserTechnologyLog log, User user where log.userId = user.userId ");
		if (userId != null) {
			hql.append(" and log.userId = '").append(userId).append("'");
		}
		if (searchId != null) {
			hql.append(" and log.technologyId = ").append(searchId);
		}
		if (searchType != null) {
			hql.append(" and log.type = ").append(searchType);
		}
		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			hql.append(" and log.createTime BETWEEN '").append(sdf.format(startDate)).append("' AND '").append(sdf.format(endDate)).append("'");
		}
//		hql.append(" order by log.createTime DESC");
		userTechnologyLogDao.closeSession(DBSource.LOG);
		return userTechnologyLogDao.findPage(hql.toString(), new ArrayList<UserTechnologyLog>(), pageSize, currentPage);
	}

	public void setUserTechnologyLogDao(UserTechnologyLogDao userTechnologyLogDao) {
		this.userTechnologyLogDao = userTechnologyLogDao;
	}

	public UserTechnologyLogDao getUserTechnologyLogDao() {
		return userTechnologyLogDao;
	}
}
