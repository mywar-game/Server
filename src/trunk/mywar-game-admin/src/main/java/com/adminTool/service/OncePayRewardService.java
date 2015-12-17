package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.OncePayReward;
import com.adminTool.dao.OncePayRewardDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;

/**
 * 单笔充值Service
 * 
 * @author yezp
 */
public class OncePayRewardService {

	OncePayRewardDao oncePayRewardDao;

	/**
	 * 根据活动id获取单笔充值列表
	 * 
	 * @param activityId
	 * @param toPage
	 * @param defaultPagesize
	 * @return
	 */
	public IPage<OncePayReward> findOncePayRewardPageList(int activityId,
			int toPage, int defaultPagesize) {
		oncePayRewardDao.closeSession(DBSource.CFG);
		IPage<OncePayReward> list = oncePayRewardDao.findPage(
				"from OncePayReward where activityId =" + activityId,
				new ArrayList<Object>(), defaultPagesize, toPage);

		return list;
	}

	/**
	 * 根据活动id删除单笔充值记录
	 * 
	 * @param activityId
	 */
	public void delOncePayRewardByActivityId(int activityId) {
		oncePayRewardDao.closeSession(DBSource.CFG);
		oncePayRewardDao.execute("delete OncePayReward where activityId = "
				+ activityId);
	}
	
	public List<OncePayReward> findAll(Integer activityId) {
		/*oncePayRewardDao.closeSession(DBSource.CFG);*/
		return oncePayRewardDao.find("from OncePayReward where activityId = " + activityId, DBSource.CFG);
	}

	/**
	 * 添加单笔充值奖励
	 * 
	 * @param oncePayReward
	 */
	public void addOncePayReward(OncePayReward oncePayReward) {
		oncePayRewardDao.save(oncePayReward, DBSource.CFG);
	}

	public OncePayRewardDao getOncePayRewardDao() {
		return oncePayRewardDao;
	}

	public void setOncePayRewardDao(OncePayRewardDao oncePayRewardDao) {
		this.oncePayRewardDao = oncePayRewardDao;
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("单笔充值模块 同步删除-system_once_pay_reward开始");
		oncePayRewardDao.closeSession(DBSource.CFG);
		oncePayRewardDao.executeSQL_("delete from system_once_pay_reward where activity_id = " + activityId);
		LogSystem.info("单笔充值模块 同步删除-system_once_pay_reward结束");
	}
	
	public void synSaveAll(List<OncePayReward> list) {
		LogSystem.info("单笔充值模块 同步保存-system_once_pay_reward开始");
		oncePayRewardDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("单笔充值模块 同步保存-system_once_pay_reward结束");
	}

}
