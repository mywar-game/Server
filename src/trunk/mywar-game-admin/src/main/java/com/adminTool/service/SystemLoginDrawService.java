package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemLoginDraw;
import com.adminTool.dao.SystemLoginDrawDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

public class SystemLoginDrawService {

	private SystemLoginDrawDao systemLoginDrawDao;

	public List<SystemLoginDraw> findAll() {
		systemLoginDrawDao.closeSession(DBSource.CFG);
		return systemLoginDrawDao.findAll();
	}

	public SystemLoginDrawDao getSystemLoginDrawDao() {
		return systemLoginDrawDao;
	}

	public void setSystemLoginDrawDao(SystemLoginDrawDao systemLoginDrawDao) {
		this.systemLoginDrawDao = systemLoginDrawDao;
	}
	
	public void update(SystemLoginDraw draw) {
		systemLoginDrawDao.update(draw, DBSource.CFG);
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll() {
		LogSystem.info("登陆抽奖活动 同步删除-system_login_draw开始");
		systemLoginDrawDao.closeSession(DBSource.CFG);
		systemLoginDrawDao.executeSQL_("delete from system_login_draw where 1=1");
		LogSystem.info("登陆抽奖活动 同步删除-system_login_draw结束");
	}
	
	public void synSaveAll(List<SystemLoginDraw> list) {
		LogSystem.info("登陆抽奖活动 同步保存-system_login_draw开始");
		systemLoginDrawDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("登陆抽奖活动 同步保存-system_login_draw结束");
	}
}
