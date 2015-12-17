package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.ActivityDrawConfig;
import com.adminTool.dao.ActivityDrawConfigDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;

/**
 * 抽奖配置Service
 * 
 * @author yezp
 */
public class ActivityDrawConfigService {

	private ActivityDrawConfigDao activityDrawConfigDao;

	/**
	 * 获取抽奖次数配置
	 * 
	 * @param activityId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<ActivityDrawConfig> findDrawConfigPageList(int activityId,
			int toPage, int defaultPagesize) {
		activityDrawConfigDao.closeSession(DBSource.CFG);
		IPage<ActivityDrawConfig> list = activityDrawConfigDao.findPage(
				"from ActivityDrawConfig where activityId = " + activityId + " order by drawTimes asc", new ArrayList<Object>(),
				defaultPagesize, toPage);
		return list;
	}

	/**
	 * 删除抽奖次数配置
	 * 
	 * @param activityId
	 */
	public void delDrawConfigByActivityId(int activityId) {
		activityDrawConfigDao.closeSession(DBSource.CFG);
		activityDrawConfigDao
				.execute("delete from ActivityDrawConfig where activityId = "
						+ activityId);
	}

	public List<ActivityDrawConfig> findAll(Integer activityId) {
		return activityDrawConfigDao.find("from ActivityDrawConfig where activityId = " + activityId, DBSource.CFG);
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("抽奖 同步删除-system_activity_draw_config开始");
		activityDrawConfigDao.closeSession(DBSource.CFG);
		activityDrawConfigDao.executeSQL_("delete from system_activity_draw_config where activity_id = " + activityId);
		LogSystem.info("抽奖 同步删除-system_activity_draw_config结束");
	}
	
	public void synSaveAll(List<ActivityDrawConfig> list) {
		LogSystem.info("抽奖 同步保存-system_activity_draw_config开始");
		activityDrawConfigDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("抽奖 同步保存-system_activity_draw_config结束");
	}
	/**
	 * 添加抽奖次数配置
	 * 
	 * @param activityDrawConfig
	 */
	public void addDrawConfig(ActivityDrawConfig activityDrawConfig) {
		activityDrawConfigDao.save(activityDrawConfig, DBSource.CFG);
	}

	public ActivityDrawConfigDao getActivityDrawConfigDao() {
		return activityDrawConfigDao;
	}

	public void setActivityDrawConfigDao(
			ActivityDrawConfigDao activityDrawConfigDao) {
		this.activityDrawConfigDao = activityDrawConfigDao;
	}

}
