package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.BMapConstant;
import com.dataconfig.dao.MapConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class MapConstantService {
	
	private MapConstantDao mapConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<BMapConstant> findPageList(Integer currentPage, Integer pageSize) {
		mapConstantDao.closeSession(DBSource.CFG);
		return mapConstantDao.findPage("from BMapConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setMapConstantDao(MapConstantDao mapConstantDao) {
		this.mapConstantDao = mapConstantDao;
	}

	public MapConstantDao getMapConstantDao() {
		return mapConstantDao;
	}

}
