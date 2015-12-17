package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.ValentineReward;
import com.adminTool.dao.ValentineRewardDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class ValentineRewardService {
private ValentineRewardDao valentineRewardDao;

public ValentineRewardDao getValentineRewardDao() {
	return valentineRewardDao;
}

public void setValentineRewardDao(ValentineRewardDao valentineRewardDao) {
	this.valentineRewardDao = valentineRewardDao;
}
public IPage<ValentineReward>findValentineRewardPageList(int toPage,
		int defaultPagesize){
	valentineRewardDao.closeSession(DBSource.CFG);
	IPage<ValentineReward>list=valentineRewardDao.findPage("from ValentineReward", new ArrayList<Object>(), defaultPagesize,
			toPage);
	return list;
}
public void add(int id, Integer num,String rewards){
	this.valentineRewardDao.closeSession(DBSource.CFG);
	
	valentineRewardDao.executeSQL_("insert into system_valentine_reward(id,match_num,rewards) values("+ id + "," + num + ",'" + rewards+"')");
}
public void delete(Integer id){
	this.valentineRewardDao.closeSession(DBSource.CFG);
	valentineRewardDao.executeSQL_("delete from system_valentine_reward where id="+id);
}
	public int findMaxId() {
		this.valentineRewardDao.closeSession(DBSource.CFG);
		List<Object> list = valentineRewardDao.findSQL_("select max(id),rewards from system_valentine_reward");
		for (Object obj : list) {
			Object[] objArr = (Object[]) obj;
			return Integer.valueOf((Integer) objArr[0]);
		}
		return 0;
	}
}
