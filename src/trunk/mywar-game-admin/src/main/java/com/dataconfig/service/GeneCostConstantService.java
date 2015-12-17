package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.BGeneCostConstant;
import com.dataconfig.dao.GeneCostConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class GeneCostConstantService {
	
	private GeneCostConstantDao geneCostConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<BGeneCostConstant> findPageList(Integer currentPage, Integer pageSize) {
		geneCostConstantDao.closeSession(DBSource.CFG);
		return geneCostConstantDao.findPage("from BGeneCostConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setGeneCostConstantDao(GeneCostConstantDao geneCostConstantDao) {
		this.geneCostConstantDao = geneCostConstantDao;
	}

	public GeneCostConstantDao getGeneCostConstantDao() {
		return geneCostConstantDao;
	}

}
