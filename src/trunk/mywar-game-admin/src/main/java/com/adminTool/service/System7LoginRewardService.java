package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.System30LoginReward;
import com.adminTool.bo.System7LoginReward;
import com.adminTool.dao.System7LoginRewardDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;


public class System7LoginRewardService {

	private System7LoginRewardDao system7LoginRewardDao;

	public System7LoginRewardDao getSystem7LoginRewardDao() {
		return system7LoginRewardDao;
	}

	public void setSystem7LoginRewardDao(
			System7LoginRewardDao system7LoginRewardDao) {
		this.system7LoginRewardDao = system7LoginRewardDao;
	}

	public List<System7LoginReward> findAll() {
		system7LoginRewardDao.closeSession(DBSource.CFG);
		return system7LoginRewardDao.findAll();
	}
	
	public void update(System7LoginReward reward) {
		system7LoginRewardDao.update(reward, DBSource.CFG);
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll() {
		LogSystem.info("7日连续登陆 同步删除-system_login_reward7开始");
		system7LoginRewardDao.closeSession(DBSource.CFG);
		system7LoginRewardDao.executeSQL_("delete from system_login_reward7 where 1=1");
		LogSystem.info("7日连续登陆 同步删除-system_login_reward7结束");
	}
	
	public void synSaveAll(List<System7LoginReward> list) {
		LogSystem.info("7日连续登陆 同步保存-system_login_reward7开始");
		system7LoginRewardDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("7日连续登陆 同步保存-system_login_reward7结束");
	}
}
