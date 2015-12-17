package com.fantingame.game.mywar.logic.charge.dao.mysql;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.charge.dao.PaymentLogDao;
import com.fantingame.game.mywar.logic.charge.model.PaymentLog;

public class PaymentLogDaoMysqlImpl implements PaymentLogDao {

	public final static String table = "payment_log";

	@Autowired
	private Jdbc jdbcUser;

	@Override
	public void add(PaymentLog paymentLog) {
		this.jdbcUser.insert(paymentLog);
	}

	@Override
	public int getPaymentTotal(String userId) {

		String sql = "SELECT sum(amount) FROM " + table + " WHERE user_id = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);

		return this.jdbcUser.getInt(sql, parameter);
	}

	@Override
	public int getPaymentTotalGold(String userId) {

		String sql = "SELECT sum(gold) FROM " + table + " WHERE user_id = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);

		return this.jdbcUser.getInt(sql, parameter);
	}

	@Override
	public int getPaymentTotalByTime(String userId, Date startTime, Date endTime) {

		String sql = "SELECT sum(amount) FROM " + table + " WHERE user_id = ? AND created_time BETWEEN ? AND ?";

		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		parameter.setString(startTime.toString());
		parameter.setString(endTime.toString());
		return this.jdbcUser.getInt(sql, parameter);
	}

	@Override
	public List<PaymentLog> getPaymenList(String userId, Date startTime, Date endTime, int payMoney, int nextPayMoney) {
		String sql = "SELECT * FROM " + table + " WHERE user_id = ? and created_time >= ? and created_time < ? and amount >= ? and amount < ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		parameter.setObject(startTime);
		parameter.setObject(endTime);
		parameter.setInt(payMoney);
		parameter.setInt(nextPayMoney);
		return this.jdbcUser.getList(sql, PaymentLog.class, parameter);
	}

	@Override
	public List<PaymentLog> getPaymenList(String userId, Date startTime, Date endTime, int payMoney) {
		String sql = "SELECT * FROM " + table + " WHERE user_id = ? and created_time >= ? and created_time < ? and amount >= ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		parameter.setObject(startTime);
		parameter.setObject(endTime);
		parameter.setInt(payMoney);
		return this.jdbcUser.getList(sql, PaymentLog.class, parameter);
	}

	@Override
	public List<PaymentLog> getPaymentList(String userId, Date startTime, Date endTime) {
		String sql = "SELECT * FROM " + table + " WHERE user_id = ? and created_time >= ? and created_time < ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		parameter.setObject(startTime);
		parameter.setObject(endTime);
		return this.jdbcUser.getList(sql, PaymentLog.class, parameter);
	}

	@Override
	public List<PaymentLog> getPaymentLogList(Date startTime, Date endTime) {
		String sql = "SELECT * FROM payment_log WHERE  created_time BETWEEN ? AND ? GROUP BY user_id having sum(amount) > 0";
		SqlParameter parameter = new SqlParameter();
		parameter.setObject(startTime);
		parameter.setObject(endTime);
		
		return this.jdbcUser.getList(sql, PaymentLog.class, parameter);
	}
	
	

}
