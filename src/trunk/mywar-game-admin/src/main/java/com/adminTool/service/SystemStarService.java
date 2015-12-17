package com.adminTool.service;

import java.util.List;

import com.adminTool.bo.SystemStar;
import com.adminTool.dao.SystemStarDao;
import com.framework.common.DBSource;
import com.framework.log.LogSystem;

/**
 * 星图
 * @author Administrator
 *
 */
public class SystemStarService {

	private SystemStarDao systemStarDao;

	public List<SystemStar> findAllSystemStarPageList() {
		systemStarDao.closeSession(DBSource.CFG);
		
		return systemStarDao.findAll();
//		IPage<SystemStar> list = systemStarDao.findPage(
//				"from SystemStar", new ArrayList<Object>(),
//				defaultPagesize, toPage);
//		return list;
	}
	
	public List<SystemStar> findByStarId(int starId) {
		return systemStarDao.find("from SystemStar where starId = '" + starId + "'", DBSource.CFG);
	}
	
	public void saveSystemStar(SystemStar star) {
		systemStarDao.update(star, DBSource.CFG);
	}
	
	public List<SystemStar> findAll() {
		systemStarDao.closeSession(DBSource.CFG);
		return systemStarDao.findAll();
	}
	
	/** 一下是同步功能 **/
	public void synDeleteAll() {
		LogSystem.info("星图配置同步删除-system_star开始");
		systemStarDao.closeSession(DBSource.CFG);
		systemStarDao.executeSQL_("delete from system_star where 1=1");
		LogSystem.info("星图配置同步删除-system_star结束");
	}
	
	public void synSaveAll(List<SystemStar> list) {
		LogSystem.info("星图配置同步保存-system_star开始");
		systemStarDao.saveBatch(list, DBSource.CFG);
		LogSystem.info("星图配置同步保存-system_star结束");
	}
	
	public SystemStarDao getSystemStarDao() {
		return systemStarDao;
	}

	public void setSystemStarDao(SystemStarDao systemStarDao) {
		this.systemStarDao = systemStarDao;
	}
	
}
