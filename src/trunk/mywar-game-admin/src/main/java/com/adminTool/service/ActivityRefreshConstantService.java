package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.ActivityRefreshConstant;
import com.adminTool.dao.ActivityRefreshConstantDao;
import com.framework.common.DBSource;


/**
 * 刷新常量类服务
 * @author Administrator
 *
 */
public class ActivityRefreshConstantService {

	ActivityRefreshConstantDao activityRefreshConstantDao;
	
	public List<ActivityRefreshConstant> getAll() {
		activityRefreshConstantDao.closeSession(DBSource.ADMIN);
		return activityRefreshConstantDao.findAll();
	}

	public ActivityRefreshConstantDao getActivityRefreshConstantDao() {
		return activityRefreshConstantDao;
	}

	public void setActivityRefreshConstantDao(
			ActivityRefreshConstantDao activityRefreshConstantDao) {
		this.activityRefreshConstantDao = activityRefreshConstantDao;
	}

}
