package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemGoldSet;
import com.adminTool.dao.SystemGoldSetDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

/**
 * 充值配置
 * @author Administrator
 *
 */
public class SystemGoldSetService {

	private SystemGoldSetDao systemGoldSetDao;

	public List<SystemGoldSet> getAll() {
		systemGoldSetDao.closeSession(DBSource.CFG);
		return systemGoldSetDao.findAll();
	}
	
	public void deleteAll() {
		systemGoldSetDao.closeSession(DBSource.CFG);
		systemGoldSetDao.execute("delete SystemGoldSet where 1=1");
	}
	
	public SystemGoldSet findLast() {
		List<SystemGoldSet> list = systemGoldSetDao.find("FROM SystemGoldSet ORDER BY systemGoldSetId DESC LIMIT 1", DBSource.CFG); 
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	public void update(SystemGoldSet s) {
		systemGoldSetDao.update(s, DBSource.CFG);
	}
	
	public void save(SystemGoldSet systemGoldSet) {
		systemGoldSetDao.save(systemGoldSet, DBSource.CFG);
	}
	
	public SystemGoldSetDao getSystemGoldSetDao() {
		return systemGoldSetDao;
	}

	public void setSystemGoldSetDao(SystemGoldSetDao systemGoldSetDao) {
		this.systemGoldSetDao = systemGoldSetDao;
	}
	
	
	/** 一下是同步功能 **/
	public void synDeleteAll() {
		LogSystem.info("充值配置 同步删除-system_gold_set开始");
		systemGoldSetDao.closeSession(DBSource.CFG);
		systemGoldSetDao.executeSQL_("delete from system_gold_set");
		LogSystem.info("充值配置 同步删除-system_gold_set结束");
	}
	
	public void synSaveAll(List<SystemGoldSet> list) {
		LogSystem.info("充值配置 同步保存-system_gold_set开始");
		systemGoldSetDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("充值配置 同步保存-system_gold_set结束");
	}
}
