package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.ActivityDrawLevelGoods;
import com.adminTool.dao.ActivityDrawLevelGoodsDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;

/**
 * 抽奖等级物品替换Service
 * 
 * @author yezp
 */
public class ActivityDrawLevelGoodsService {

	private ActivityDrawLevelGoodsDao activityDrawLevelGoodsDao;

	/**
	 * 获取等级替换物品
	 * 
	 * @param activityId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<ActivityDrawLevelGoods> findDrawLevelGoodsPageList(
			int activityId, int toPage, int defaultPagesize) {
		activityDrawLevelGoodsDao.closeSession(DBSource.CFG);
		IPage<ActivityDrawLevelGoods> list = activityDrawLevelGoodsDao
				.findPage("from ActivityDrawLevelGoods where activityId = "
						+ activityId + " order by level asc",
						new ArrayList<Object>(), defaultPagesize, toPage);
		return list;
	}

	/**
	 * 添加抽奖等级物品替换
	 * 
	 * @param activityDrawLevelGoods
	 */
	public void addDrawLevelGoods(ActivityDrawLevelGoods activityDrawLevelGoods) {
		activityDrawLevelGoodsDao.save(activityDrawLevelGoods, DBSource.CFG);
	}

	/**
	 * 修改抽奖等级物品替换
	 * 
	 * @param activityDrawLevelGoods
	 */
	public void updateDrawLevelGoods(
			ActivityDrawLevelGoods activityDrawLevelGoods) {
		activityDrawLevelGoodsDao.update(activityDrawLevelGoods, DBSource.CFG);
	}

	/**
	 * 删除抽奖等级物品替换
	 * 
	 * @param id
	 */
	public void delDrawLevelGoods(Integer id) {
		activityDrawLevelGoodsDao.remove(findOneDrawLevelGoods(id),
				DBSource.CFG);
	}

	/**
	 * 根据id查找抽奖等级物品替换
	 * 
	 * @param id
	 * @return
	 */
	public ActivityDrawLevelGoods findOneDrawLevelGoods(Integer id) {
		return activityDrawLevelGoodsDao.loadBy("id", id, DBSource.CFG);
	}
	
	public List<ActivityDrawLevelGoods> findAll(Integer activityId) {
		return activityDrawLevelGoodsDao.find("from ActivityDrawLevelGoods where activityId = " + activityId, DBSource.CFG);
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("抽奖 同步删除-system_activity_draw_level_goods开始");
		activityDrawLevelGoodsDao.closeSession(DBSource.CFG);
		activityDrawLevelGoodsDao.executeSQL_("delete from system_activity_draw_level_goods where activity_id = " + activityId);
		LogSystem.info("抽奖 同步删除-system_activity_draw_level_goods结束");
	}
	
	public void synSaveAll(List<ActivityDrawLevelGoods> list) {
		LogSystem.info("抽奖 同步保存-system_activity_draw_level_goods开始");
		activityDrawLevelGoodsDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("抽奖 同步保存-system_activity_draw_level_goods结束");
	}

	public ActivityDrawLevelGoodsDao getActivityDrawLevelGoodsDao() {
		return activityDrawLevelGoodsDao;
	}

	public void setActivityDrawLevelGoodsDao(
			ActivityDrawLevelGoodsDao activityDrawLevelGoodsDao) {
		this.activityDrawLevelGoodsDao = activityDrawLevelGoodsDao;
	}

}
