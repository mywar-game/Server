package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.System7LoginReward;
import com.adminTool.bo.TotalPayReward;
import com.adminTool.dao.TotalPayRewardDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;

/**
 * 累积充值Service
 * 
 * @author yezp
 */
public class TotalPayRewardService {

	private TotalPayRewardDao totalPayRewardDao;

	/**
	 * 根据活动id获取累积充值列表
	 * 
	 * @param activityId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<TotalPayReward> findTotalPayRewardPageList(int activityId,
			int toPage, int defaultPagesize) {
		totalPayRewardDao.closeSession(DBSource.CFG);
		IPage<TotalPayReward> list = totalPayRewardDao.findPage(
				"from TotalPayReward where activityId = " + activityId,
				new ArrayList<Object>(), defaultPagesize, toPage);

		return list;
	}

	/**
	 * 根据活动id删除累积充值配置
	 * 
	 * @param activityId
	 */
	public void delTotalPayRewardByActivityId(int activityId) {
		totalPayRewardDao.closeSession(DBSource.CFG);
		totalPayRewardDao.execute("delete TotalPayReward where activityId = "
				+ activityId);
	}
	
	public List<TotalPayReward> findAll(Integer activityId) {
		/*totalPayRewardDao.closeSession(DBSource.CFG);*/
		return totalPayRewardDao.find("from TotalPayReward where activityId = " + activityId, DBSource.CFG);
		// return totalPayRewardDao.findAll();
	}

	/**
	 * 添加累积充值奖励
	 * 
	 * @param totalPayReward
	 */
	public void addTotalPayReward(TotalPayReward totalPayReward) {
		totalPayRewardDao.save(totalPayReward, DBSource.CFG);
	}

	public TotalPayRewardDao getTotalPayRewardDao() {
		return totalPayRewardDao;
	}

	public void setTotalPayRewardDao(TotalPayRewardDao totalPayRewardDao) {
		this.totalPayRewardDao = totalPayRewardDao;
	}
	//system_total_pay_reward

	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("累积充值模块 同步删除-system_total_pay_reward开始");
		totalPayRewardDao.closeSession(DBSource.CFG);
		totalPayRewardDao.executeSQL_("delete from system_total_pay_reward where activity_id = " + activityId);
		LogSystem.info("累积充值模块 同步删除-system_total_pay_reward结束");
	}
	
	public void synSaveAll(List<TotalPayReward> list) {
		LogSystem.info("累积充值模块 同步保存-system_total_pay_reward开始");
		totalPayRewardDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("累积充值模块 同步保存-system_total_pay_reward结束");
	}
}
