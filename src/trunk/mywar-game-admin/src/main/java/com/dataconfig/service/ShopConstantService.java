package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.BShopConstant;
import com.dataconfig.dao.ShopConstantDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class ShopConstantService {
	
	private ShopConstantDao shopConstantDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<BShopConstant> findPageList(Integer currentPage, Integer pageSize) {
		shopConstantDao.closeSession(DBSource.CFG);
		return shopConstantDao.findPage("from BShopConstant", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setShopConstantDao(ShopConstantDao shopConstantDao) {
		this.shopConstantDao = shopConstantDao;
	}

	public ShopConstantDao getShopConstantDao() {
		return shopConstantDao;
	}

}
