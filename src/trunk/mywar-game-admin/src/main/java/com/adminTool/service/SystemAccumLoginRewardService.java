package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemAccumLoginReward;
import com.adminTool.dao.SystemAccumLoginRewardDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

public class SystemAccumLoginRewardService {
private SystemAccumLoginRewardDao systemAccumLoginRewardDao;

public SystemAccumLoginRewardDao getSystemAccumLoginRewardDao() {
	return systemAccumLoginRewardDao;
}

public void setSystemAccumLoginRewardDao(
		SystemAccumLoginRewardDao systemAccumLoginRewardDao) {
	this.systemAccumLoginRewardDao = systemAccumLoginRewardDao;
}

public void update(SystemAccumLoginReward reward){
	systemAccumLoginRewardDao.update(reward, DBSource.CFG);
}
public List<SystemAccumLoginReward> findAll(int activityId){
	systemAccumLoginRewardDao.closeSession(DBSource.CFG);
	return systemAccumLoginRewardDao.find("from SystemAccumLoginReward a where a.activityId = " + activityId, DBSource.CFG);
}
public void deleteAll(int activityId){
	systemAccumLoginRewardDao.closeSession(DBSource.CFG);
	systemAccumLoginRewardDao.executeSQL_("delete from system_mid_autumn_reward where activity_id = " + activityId);
	
}
public void deleteById(Integer id){
	systemAccumLoginRewardDao.closeSession(DBSource.CFG);
	systemAccumLoginRewardDao.executeSQL_("delete from system_mid_autumn_reward where id=" + id);
}

public void save(SystemAccumLoginReward tools){
	systemAccumLoginRewardDao.save(tools, DBSource.CFG);
}

public void synDeleteAll(int activityId){
	LogSystem.info("累计登陆 同步删除-system_mid_autumn_reward开始");
	systemAccumLoginRewardDao.closeSession(DBSource.CFG);
	systemAccumLoginRewardDao.executeSQL_("delete from system_mid_autumn_reward where activity_id = " + activityId);
	LogSystem.info("累计登陆 同步删除-system_mid_autumn_reward结束");
}

public void synSaveAll(List<SystemAccumLoginReward> list){
	LogSystem.info("累计登陆 同步保存-system_mid_autumn_reward开始");
	systemAccumLoginRewardDao.saveBatch(list, DBSource.CFG);
	LogSystem.info("累计登陆 同步保存-system_mid_autumn_reward结束");
}

}
