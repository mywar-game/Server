package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.PayBuyPresent;
import com.dataconfig.dao.PayBuyPresentDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class PayBuyPresentService {
	
	private PayBuyPresentDao payBuyPresentDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<PayBuyPresent> findPageList(Integer currentPage, Integer pageSize) {
		payBuyPresentDao.closeSession(DBSource.CFG);
		return payBuyPresentDao.findPage("from PayBuyPresent", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setPayBuyPresentDao(PayBuyPresentDao payBuyPresentDao) {
		this.payBuyPresentDao = payBuyPresentDao;
	}

	public PayBuyPresentDao getPayBuyPresentDao() {
		return payBuyPresentDao;
	}

}
