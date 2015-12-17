package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.FHelpConstant;
import com.dataconfig.dao.HelpConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class HelpConstantService {
	
	private HelpConstantDao helpConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<FHelpConstant> findPageList(Integer currentPage, Integer pageSize) {
		helpConstantDao.closeSession(DBSource.CFG);
		return helpConstantDao.findPage("from FHelpConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setHelpConstantDao(HelpConstantDao helpConstantDao) {
		this.helpConstantDao = helpConstantDao;
	}

	public HelpConstantDao getHelpConstantDao() {
		return helpConstantDao;
	}

}
