package com.adminTool.service;

import java.util.List;

import com.adminTool.dao.ComparePaymentDao;

public class ComparePaymentService {
	private ComparePaymentDao comparePaymentDao;

	public ComparePaymentDao getComparePaymentDao() {
		return comparePaymentDao;
	}

	public void setComparePaymentDao(ComparePaymentDao comparePaymentDao) {
		this.comparePaymentDao = comparePaymentDao;
	}

	public List<Object> find(int targetId) {
		comparePaymentDao.closeSession(targetId);
		return comparePaymentDao.findSQL_("select order_id from payment_order");
	}
}
