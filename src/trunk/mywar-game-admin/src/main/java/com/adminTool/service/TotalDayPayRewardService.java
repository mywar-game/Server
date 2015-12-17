package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.TotalDayPayReward;
import com.adminTool.dao.TotalDayPayRewardDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;

/**
 * 充值累积天数Service
 * 
 * @author yezp
 */
public class TotalDayPayRewardService {

	private TotalDayPayRewardDao totalDayPayRewardDao;

	/**
	 * 获取充值累计天数列表
	 * 
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<TotalDayPayReward> findTotalDayPayRewardPageList(int activityId, int toPage,
			int defaultPagesize) {
		totalDayPayRewardDao.closeSession(DBSource.CFG);
		IPage<TotalDayPayReward> list = totalDayPayRewardDao.findPage(
				"from TotalDayPayReward where activityId = " + activityId, new ArrayList<Object>(),
				defaultPagesize, toPage);
		return list;
	}

	/**
	 * 删除配置
	 */
	public void delTotalDayPayReward(int activityId) {
		totalDayPayRewardDao.closeSession(DBSource.CFG);
		totalDayPayRewardDao.execute("delete TotalDayPayReward where activityId = " + activityId);
	}
	
	public List<TotalDayPayReward> findAll(int activityId) {
		//totalDayPayRewardDao.closeSession();
		return totalDayPayRewardDao.find("from TotalDayPayReward where activityId = " + activityId, DBSource.CFG);
	}

	/**
	 * 添加配置
	 * 
	 * @param totalDayPayReward
	 */
	public void addTotalDayPayReward(TotalDayPayReward totalDayPayReward) {
		totalDayPayRewardDao.save(totalDayPayReward, DBSource.CFG);
	}

	public TotalDayPayRewardDao getTotalDayPayRewardDao() {
		return totalDayPayRewardDao;
	}

	public void setTotalDayPayRewardDao(
			TotalDayPayRewardDao totalDayPayRewardDao) {
		this.totalDayPayRewardDao = totalDayPayRewardDao;
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("充值累积天数模块  同步删除-system_total_day_pay_reward开始");
		totalDayPayRewardDao.closeSession(DBSource.CFG);
		totalDayPayRewardDao.executeSQL_("delete from system_total_day_pay_reward where activity_id = " + activityId);
		LogSystem.info("充值累积天数模块 同步删除-system_total_day_pay_reward结束");
	}
	
	public void synSaveAll(List<TotalDayPayReward> list) {
		LogSystem.info("充值累积天数模块 同步保存-system_total_day_pay_reward开始");
		totalDayPayRewardDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("充值累积天数模块 同步保存-system_total_day_pay_reward结束");
	}

}
