package com.dataconfig.service;

import java.util.ArrayList;

import com.dataconfig.bo.PayFeedBack;
import com.dataconfig.dao.PayFeedBackDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class PayFeedBackService {
	
	private PayFeedBackDao payFeedBackDao;
	
	/**
	 * 查询常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<PayFeedBack> findPageList(Integer currentPage, Integer pageSize) {
		payFeedBackDao.closeSession(DBSource.CFG);
		return payFeedBackDao.findPage("from PayFeedBack", new ArrayList<Object>(), pageSize, currentPage);
	}

	public void setPayFeedBackDao(PayFeedBackDao payFeedBackDao) {
		this.payFeedBackDao = payFeedBackDao;
	}

	public PayFeedBackDao getPayFeedBackDao() {
		return payFeedBackDao;
	}

}
