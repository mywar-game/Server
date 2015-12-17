package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.BAttrConstant;
import com.dataconfig.dao.AttrConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class AttrConstantService {
	
	private AttrConstantDao attrConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<BAttrConstant> findPageList(Integer currentPage, Integer pageSize) {
		attrConstantDao.closeSession(DBSource.CFG);
		return attrConstantDao.findPage("from BAttrConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setAttrConstantDao(AttrConstantDao attrConstantDao) {
		this.attrConstantDao = attrConstantDao;
	}

	public AttrConstantDao getAttrConstantDao() {
		return attrConstantDao;
	}

}
