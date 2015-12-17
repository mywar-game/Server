package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.PrisonConstant;
import com.dataconfig.dao.PrisonConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class PrisonConstantService {
	
	private PrisonConstantDao prisonConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<PrisonConstant> findPageList(Integer currentPage, Integer pageSize) {
		prisonConstantDao.closeSession(DBSource.CFG);
		return prisonConstantDao.findPage("from PrisonConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 获取 prisonConstantDao 
	 */
	public PrisonConstantDao getPrisonConstantDao() {
		return prisonConstantDao;
	}

	/**
	 * 设置 prisonConstantDao 
	 */
	public void setPrisonConstantDao(PrisonConstantDao prisonConstantDao) {
		this.prisonConstantDao = prisonConstantDao;
	}

}
