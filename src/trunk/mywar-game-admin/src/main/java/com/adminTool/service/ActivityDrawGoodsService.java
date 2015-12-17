package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.ActivityDrawGoods;
import com.adminTool.dao.ActivityDrawGoodsDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;

/**
 * 抽奖物品配置Service
 * 
 * @author yezp
 */
public class ActivityDrawGoodsService {

	private ActivityDrawGoodsDao activityDrawGoodsDao;

	public IPage<ActivityDrawGoods> findDrawGoodsPageList(int activityId,
			int toPage, int defaultPagesize) {
		activityDrawGoodsDao.closeSession(DBSource.CFG);
		IPage<ActivityDrawGoods> list = activityDrawGoodsDao.findPage(
				"from ActivityDrawGoods where activityId = " + activityId
						+ " order by pos asc", new ArrayList<Object>(),
				defaultPagesize, toPage);
		return list;
	}

	/**
	 * 添加物品
	 * 
	 * @param drawGoods
	 */
	public void addDrawGoods(ActivityDrawGoods drawGoods) {
		activityDrawGoodsDao.save(drawGoods, DBSource.CFG);
	}

	/**
	 * 修改物品
	 * 
	 * @param drawGoods
	 */
	public void updateDrawGoods(ActivityDrawGoods drawGoods) {
		activityDrawGoodsDao.update(drawGoods, DBSource.CFG);
	}

	/**
	 * 删除物品
	 * 
	 * @param goodsId
	 */
	public void delDrawGoods(Integer goodsId) {
		activityDrawGoodsDao.remove(findOneDrawGoods(goodsId), DBSource.CFG);
	}

	/**
	 * 根据id获取物品
	 * 
	 * @param goodsId
	 * @return
	 */
	public ActivityDrawGoods findOneDrawGoods(Integer goodsId) {
		return activityDrawGoodsDao.loadBy("goodsId", goodsId, DBSource.CFG);
	}
	
	public List<ActivityDrawGoods> findAll(Integer activityId) {
		return activityDrawGoodsDao.find("from ActivityDrawGoods where activityId = " + activityId, DBSource.CFG);
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("抽奖 同步删除-system_activity_draw_goods开始");
		activityDrawGoodsDao.closeSession(DBSource.CFG);
		activityDrawGoodsDao.executeSQL_("delete from system_activity_draw_goods where activity_id = " + activityId);
		LogSystem.info("抽奖 同步删除-system_activity_draw_goods结束");
	}
	
	public void synSaveAll(List<ActivityDrawGoods> list) {
		LogSystem.info("抽奖 同步保存-system_activity_draw_goods开始");
		activityDrawGoodsDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("抽奖 同步保存-system_activity_draw_goods结束");
	}

	public ActivityDrawGoodsDao getActivityDrawGoodsDao() {
		return activityDrawGoodsDao;
	}

	public void setActivityDrawGoodsDao(
			ActivityDrawGoodsDao activityDrawGoodsDao) {
		this.activityDrawGoodsDao = activityDrawGoodsDao;
	}

}
