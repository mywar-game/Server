package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.HHeroIntimacyConstant;
import com.dataconfig.dao.HeroIntimacyConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class HeroIntimacyConstantService {
	
	private HeroIntimacyConstantDao heroIntimacyConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<HHeroIntimacyConstant> findPageList(Integer currentPage, Integer pageSize) {
		heroIntimacyConstantDao.closeSession(DBSource.CFG);
		return heroIntimacyConstantDao.findPage("from HHeroIntimacyConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setHeroIntimacyConstantDao(HeroIntimacyConstantDao heroIntimacyConstantDao) {
		this.heroIntimacyConstantDao = heroIntimacyConstantDao;
	}

	public HeroIntimacyConstantDao getHeroIntimacyConstantDao() {
		return heroIntimacyConstantDao;
	}

}
