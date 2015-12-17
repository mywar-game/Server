package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.EvoutionGraphConstant;
import com.dataconfig.dao.EvoutionGraphConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class EvoutionGraphConstantService {

	private EvoutionGraphConstantDao evoutionGraphConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<EvoutionGraphConstant> findPageList(Integer currentPage, Integer pageSize) {
		evoutionGraphConstantDao.closeSession(DBSource.CFG);
		return evoutionGraphConstantDao.findPage("from EvoutionGraphConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setEvoutionGraphConstantDao(EvoutionGraphConstantDao evoutionGraphConstantDao) {
		this.evoutionGraphConstantDao = evoutionGraphConstantDao;
	}

	public EvoutionGraphConstantDao getEvoutionGraphConstantDao() {
		return evoutionGraphConstantDao;
	}
}
