package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.ActivityDrawPos;
import com.adminTool.dao.ActivityDrawPosDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;

/**
 * 抽奖轮盘
 * 
 * @author yezp
 */
public class ActivityDrawPosService {

	private ActivityDrawPosDao activityDrawPosDao;

	/**
	 * 获取抽奖轮盘列表
	 * 
	 * @param activityId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<ActivityDrawPos> findDrawPosPageList(int activityId,
			int toPage, int defaultPagesize) {
		activityDrawPosDao.closeSession(DBSource.CFG);
		IPage<ActivityDrawPos> list = activityDrawPosDao.findPage(
				"from ActivityDrawPos where activityId = " + activityId + " order by pos asc",
				new ArrayList<Object>(), defaultPagesize, toPage);
		return list;
	}

	/**
	 * 删除轮盘配置
	 * 
	 * @param activityId
	 */
	public void delDrawPosByActivityId(int activityId) {
		activityDrawPosDao.closeSession(DBSource.CFG);
		activityDrawPosDao.execute("delete ActivityDrawPos where activityId = "
				+ activityId);
	}
	
	public List<ActivityDrawPos> findAll(Integer activityId) {
		return activityDrawPosDao.find("from ActivityDrawPos where activityId = " + activityId, DBSource.CFG);
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("抽奖 同步删除-system_activity_draw_pos开始");
		activityDrawPosDao.closeSession(DBSource.CFG);
		activityDrawPosDao.executeSQL_("delete from system_activity_draw_pos where activity_id = " + activityId);
		LogSystem.info("抽奖 同步删除-system_activity_draw_pos结束");
	}
	
	public void synSaveAll(List<ActivityDrawPos> list) {
		LogSystem.info("抽奖 同步保存-system_activity_draw_pos开始");
		activityDrawPosDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("抽奖 同步保存-system_activity_draw_pos结束");
	}

	/**
	 * 添加轮盘位置
	 * 
	 * @param activityDrawPos
	 */
	public void addDrawPos(ActivityDrawPos activityDrawPos) {
		activityDrawPosDao.save(activityDrawPos, DBSource.CFG);
	}

	public ActivityDrawPosDao getActivityDrawPosDao() {
		return activityDrawPosDao;
	}

	public void setActivityDrawPosDao(ActivityDrawPosDao activityDrawPosDao) {
		this.activityDrawPosDao = activityDrawPosDao;
	}

}
