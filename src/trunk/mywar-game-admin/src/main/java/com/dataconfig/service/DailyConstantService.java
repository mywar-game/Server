package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.DailyConstant;
import com.dataconfig.dao.DailyConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class DailyConstantService {
	
	private DailyConstantDao dailyConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<DailyConstant> findPageList(Integer currentPage, Integer pageSize) {
		dailyConstantDao.closeSession(DBSource.CFG);
		return dailyConstantDao.findPage("from DailyConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 获取 dailyConstantDao 
	 */
	public DailyConstantDao getDailyConstantDao() {
		return dailyConstantDao;
	}

	/**
	 * 设置 dailyConstantDao 
	 */
	public void setDailyConstantDao(DailyConstantDao dailyConstantDao) {
		this.dailyConstantDao = dailyConstantDao;
	}

}
