package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.SectionConstant;
import com.dataconfig.dao.SectionConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class SectionConstantService {
	
	private SectionConstantDao sectionConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<SectionConstant> findPageList(Integer currentPage, Integer pageSize) {
		sectionConstantDao.closeSession(DBSource.CFG);
		return sectionConstantDao.findPage("from SectionConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	/**
	 * 获取 sectionConstantDao 
	 */
	public SectionConstantDao getSectionConstantDao() {
		return sectionConstantDao;
	}

	/**
	 * 设置 sectionConstantDao 
	 */
	public void setSectionConstantDao(SectionConstantDao sectionConstantDao) {
		this.sectionConstantDao = sectionConstantDao;
	}

}
