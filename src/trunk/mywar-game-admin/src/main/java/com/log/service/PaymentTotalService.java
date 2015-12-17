package com.log.service;

import java.util.List;

import com.framework.common.DBSource;
import com.log.dao.PaymentTotalDao;

/**
 * 充值统计
 * @author Administrator
 *
 */
public class PaymentTotalService {

//	private PaymentTotalDao paymentTotalDao;
//
//	public List<Object> find(String[] regTimeArr, String createdTime) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("select partner_id, sum(amount) from payment_log where user_id in (select user_id from user where reg_time >= '");
//		sb.append(regTimeArr[0]);
//		sb.append("'");
//		sb.append(" and reg_time <='");
//		sb.append(regTimeArr[1]);
//		sb.append(" ) and created_time < '");
//		sb.append(createdTime);
//		sb.append("  group by partner_id");
//		paymentTotalDao.closeSession(DBSource.LOG);
//		return paymentTotalDao.findSQL_(sb.toString());
//	}
//	
//	public PaymentTotalDao getPaymentTotalDao() {
//		return paymentTotalDao;
//	}
//
//	public void setPaymentTotalDao(PaymentTotalDao paymentTotalDao) {
//		this.paymentTotalDao = paymentTotalDao;
//	}
//	
}
