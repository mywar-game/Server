package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.AlchemyConstant;
import com.dataconfig.dao.AlchemyConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class AlchemyConstantService {
	
	private AlchemyConstantDao alchemyConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<AlchemyConstant> findPageList(Integer currentPage, Integer pageSize) {
		alchemyConstantDao.closeSession(DBSource.CFG);
		return alchemyConstantDao.findPage("from AlchemyConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setAlchemyConstantDao(AlchemyConstantDao alchemyConstantDao) {
		this.alchemyConstantDao = alchemyConstantDao;
	}

	public AlchemyConstantDao getAlchemyConstantDao() {
		return alchemyConstantDao;
	}

}
