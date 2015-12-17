package com.stats.service;

import java.util.List;
import java.math.BigInteger;

import com.framework.common.DBSource;
import com.stats.dao.PayListDao;

/**
 * 付费账号数统计
 * @author Administrator
 *
 */
public class PayListService {

	private PayListDao payListDao;

	/**
	 * 查找最大，最小日期
	 */
	public List<Object> findLastAndLeastTime() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT MAX(CREATED_TIME), MIN(CREATED_TIME) FROM payment_log");
		payListDao.closeSession(DBSource.LOG);
		List<Object> list = payListDao.findSQL_(sb.toString());
		return list;
	}
	
	public BigInteger findInDate(String bDate, String eDate) {
		BigInteger count = null;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(DISTINCT(USER_ID)) FROM payment_log where CREATED_TIME between '");
		sb.append(bDate);
		sb.append("' AND '");
		sb.append(eDate);
		sb.append("'");
		payListDao.closeSession(DBSource.LOG);
		List<Object> list = payListDao.findSQL_(sb.toString());
		if (list != null && list.size() != 0) {
			count = (BigInteger) list.get(0);
		}
		return count;
	}
	
	public PayListDao getPayListDao() {
		return payListDao;
	}

	public void setPayListDao(PayListDao payListDao) {
		this.payListDao = payListDao;
	}
}
