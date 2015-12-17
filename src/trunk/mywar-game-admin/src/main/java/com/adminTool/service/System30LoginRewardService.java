package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.System30LoginReward;
import com.adminTool.dao.System30LoginRewardDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

/**
 * 30天登录奖励
 * @author Administrator
 *
 */
public class System30LoginRewardService {

	private System30LoginRewardDao system30LoginRewardDao;

	public System30LoginRewardDao getSystem30LoginRewardDao() {
		return system30LoginRewardDao;
	}

	public void setSystem30LoginRewardDao(
			System30LoginRewardDao system30LoginRewardDao) {
		this.system30LoginRewardDao = system30LoginRewardDao;
	}

	public List<System30LoginReward> findAll() {
		system30LoginRewardDao.closeSession(DBSource.CFG);
		return system30LoginRewardDao.findAll();
	}
	
	public void update(System30LoginReward reward) {
		system30LoginRewardDao.update(reward, DBSource.CFG);
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll() {
		LogSystem.info("30日累积登陆同步删除-system_30_login_reward开始");
		system30LoginRewardDao.closeSession(DBSource.CFG);
		system30LoginRewardDao.executeSQL_("delete from system_30_login_reward where 1=1");
		LogSystem.info("30日累积登陆同步删除-system_30_login_reward结束");
	}
	
	public void synSaveAll(List<System30LoginReward> list) {
		LogSystem.info("30日累积登陆同步保存-system_30_login_reward开始");
		system30LoginRewardDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("30日累积登陆同步保存-system_30_login_reward结束");
	}
}
