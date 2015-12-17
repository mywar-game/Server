package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.ActiveEvent;
import com.dataconfig.dao.ActiveEventDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class ActiveEventService {
	
	private ActiveEventDao activeEventDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<ActiveEvent> findPageList(Integer currentPage, Integer pageSize) {
		activeEventDao.closeSession(DBSource.CFG);
		return activeEventDao.findPage("from ActiveEvent", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setActiveEventDao(ActiveEventDao activeEventDao) {
		this.activeEventDao = activeEventDao;
	}

	public ActiveEventDao getActiveEventDao() {
		return activeEventDao;
	}

}
