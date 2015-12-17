package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.BaTipsConstant;
import com.dataconfig.dao.BaTipsConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class BaTipsConstantService {
	
	private BaTipsConstantDao baTipsConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<BaTipsConstant> findPageList(Integer currentPage, Integer pageSize) {
		baTipsConstantDao.closeSession(DBSource.CFG);
		return baTipsConstantDao.findPage("from BaTipsConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 获取 baTipsConstantDao 
	 */
	public BaTipsConstantDao getBaTipsConstantDao() {
		return baTipsConstantDao;
	}

	/**
	 * 设置 baTipsConstantDao 
	 */
	public void setBaTipsConstantDao(BaTipsConstantDao baTipsConstantDao) {
		this.baTipsConstantDao = baTipsConstantDao;
	}

}
