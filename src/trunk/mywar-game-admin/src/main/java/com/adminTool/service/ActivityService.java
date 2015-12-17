package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.Activity;
import com.adminTool.dao.ActivityDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

/**
 * 活动Service
 * 
 * @author yezp
 */
public class ActivityService {

	private ActivityDao activityDao;

	/**
	 * 查询活动列表
	 * 
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<Activity> findActivityPageList(int toPage, int defaultPagesize) {
		activityDao.closeSession(DBSource.CFG);
		IPage<Activity> activityList = activityDao.findPage("from Activity",
				new ArrayList<Object>(), defaultPagesize, toPage);
		return activityList;
	}

	/**
	 * 添加活动
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		activityDao.save(activity, DBSource.CFG);
	}

	/**
	 * 更新活动信息
	 * 
	 * @param activity
	 */
	public void updateActivity(Activity activity) {
		activityDao.update(activity, DBSource.CFG);
	}

	/**
	 * 删除活动
	 * 
	 * @param activityId
	 */
	public void delActivity(Integer activityId) {
		// activityDao.remove(findOneActivity(activityId), DBSource.CFG);
		activityDao.closeSession(DBSource.CFG);
		activityDao.executeSQL_("delete from system_activity where activity_id = " + activityId);
	}
	
	/**
	 * 查找活动
	 * 
	 * @param activityId
	 */
	public Activity findOneActivity(Integer activityId) {
		return this.activityDao.loadBy("activityId", activityId, DBSource.CFG);
	}

	/**
	 * 查询最后一条记录
	 */
	public Activity finLastActivity() {
		List<Activity> activityList = activityDao
				.find("FROM Activity ORDER BY activityId DESC LIMIT 1 ",
						DBSource.CFG);
		if (activityList == null || activityList.size() <= 0) {
			return null;
		}

		return activityList.get(0);
	}
	
	public void updateDelStatus(Integer activityId) {
		activityDao.closeSession(DBSource.CFG);
		activityDao.executeSQL_("update system_activity set status = 2 where activity_id = " + activityId);
	}

	/**
	 * 查找活动列表
	 * 
	 * @return
	 */
	public List<Activity> getActivityList() {
		return activityDao.find("from Activity", DBSource.CFG);
	}

	public void saveActivity(Activity activity) {
		activityDao.save(activity, DBSource.CFG);
	}

	public ActivityDao getActivityDao() {
		return activityDao;
	}

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

}
