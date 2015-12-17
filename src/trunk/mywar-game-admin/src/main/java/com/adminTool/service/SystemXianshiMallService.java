package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.SystemXianshiMall;
import com.adminTool.dao.SystemXianshiMallDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;

public class SystemXianshiMallService {

	private SystemXianshiMallDao systemXianshiMallDao;

	public List<SystemXianshiMall> getList(int activityId) {
		return systemXianshiMallDao.find("from SystemXianshiMall where activityId = " + activityId, DBSource.CFG);
	}
	
	public List<SystemXianshiMall> getById(int activityId, int mallId) {
		List<Object> list = new ArrayList<Object>();
		list.add(activityId);
		list.add(mallId);
		systemXianshiMallDao.closeSession(DBSource.CFG);
		return systemXianshiMallDao.find("from SystemXianshiMall where activityId = ? and id = ?", list);
	}
	
	public void insert(SystemXianshiMall mall) {
		systemXianshiMallDao.save(mall, DBSource.CFG);
	}
	
	public void update(SystemXianshiMall mall) {
		systemXianshiMallDao.update(mall, DBSource.CFG);
	}
	/**
	 * 查询限时购买
	 * @return
	 */
	
	public IPage<SystemXianshiMall>findSystemXianshiMallPageList(int toPage,int defaultPagesize){
		systemXianshiMallDao.closeSession(DBSource.CFG);
		IPage<SystemXianshiMall>list=systemXianshiMallDao.findPage("from SystemXianshiMall",new ArrayList<Object>(), defaultPagesize,
				toPage);
		return list;
	}
	public SystemXianshiMallDao getSystemXianshiMallDao() {
		return systemXianshiMallDao;
	}

	public void setSystemXianshiMallDao(SystemXianshiMallDao systemXianshiMallDao) {
		this.systemXianshiMallDao = systemXianshiMallDao;
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll(Integer activityId) {
		LogSystem.info("限时购买模块 同步删除-system_xianshi_mall开始");
		systemXianshiMallDao.closeSession(DBSource.CFG);
		systemXianshiMallDao.executeSQL_("delete from system_xianshi_mall where activity_id = " + activityId);
		LogSystem.info("限时购买模块 同步删除-system_xianshi_mall结束");
	}
	
	public void synSaveAll(List<SystemXianshiMall> list) {
		LogSystem.info("限时购买模块 同步保存-system_xianshi_mall开始");
		systemXianshiMallDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("限时购买模块 同步保存-system_xianshi_mall结束");
	}
}
