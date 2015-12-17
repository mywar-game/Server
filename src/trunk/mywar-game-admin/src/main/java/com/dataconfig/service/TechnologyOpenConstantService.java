package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.BTechnologyOpenConstant;
import com.dataconfig.dao.TechnologyOpenConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class TechnologyOpenConstantService {
	
	private TechnologyOpenConstantDao technologyOpenConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<BTechnologyOpenConstant> findPageList(Integer currentPage, Integer pageSize) {
		technologyOpenConstantDao.closeSession(DBSource.CFG);
		return technologyOpenConstantDao.findPage("from BTechnologyOpenConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setTechnologyOpenConstantDao(TechnologyOpenConstantDao technologyOpenConstantDao) {
		this.technologyOpenConstantDao = technologyOpenConstantDao;
	}

	public TechnologyOpenConstantDao getTechnologyOpenConstantDao() {
		return technologyOpenConstantDao;
	}

}
