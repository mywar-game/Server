package com.fantingame.game.mywar.logic.mail.dao.impl.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.common.utils.TableUtils;
import com.fantingame.game.mywar.logic.mail.dao.UserMailDao;
import com.fantingame.game.mywar.logic.mail.model.UserMail;
import com.fantingame.game.mywar.logic.mail.model.UserMailLog;

public class UserMailDaoMysqlImpl implements UserMailDao {

	@Autowired
	private Jdbc jdbcUser;
	
	/**
	 * 获取最后领取时间
	 * 
	 * @param userId
	 * @return
	 */
	public Date getLastReceiveTime(String userId) {
		String sql = "SELECT * FROM user_mail_log WHERE user_id = ? LIMIT 1";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		UserMailLog userMailLog = this.jdbcUser.get(sql, UserMailLog.class, param);
		if (userMailLog != null) {
			return userMailLog.getLastReceiveTime();
		}		
		
		return null;
	}

	/**
	 * 设置最后领取时间
	 * 
	 * @param userId
	 * @param now
	 * @return
	 */
	public boolean setLastReceiveTime(String userId, Date now) {
		String sql = "INSERT INTO user_mail_log(user_id, last_receive_time) VALUES(?, ?) ";
		sql += "ON DUPLICATE KEY UPDATE last_receive_time = VALUES(last_receive_time)";
		
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setObject(now);
		return jdbcUser.update(sql, param) > 0;
	}
	
	/**
	 * 获取用户邮件
	 * 
	 * @param userId
	 * @param systemMailId
	 * @return
	 */
	public UserMail getBySystemMailId(String userId, String systemMailId) {
		String table = TableUtils.getUserMailTable(userId);
		String sql = "SELECT * FROM " + table + " WHERE user_id = ? AND system_mail_id = ? ";

		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		parameter.setString(systemMailId);
		return this.jdbcUser.get(sql, UserMail.class, parameter);
	}
	
	/**
	 * 批量添加用户邮件
	 * 
	 * @param userMailList
	 * @return
	 */
	public boolean add(String userId, List<UserMail> userMailList) {
		if (userMailList.isEmpty()) {
			return false;
		}
		
		String table = TableUtils.getUserMailTable(userMailList.get(0).getUserId());		
		return this.jdbcUser.insert(table, userMailList).length > 0;
	}
	
	/**
	 * 获取用户邮件列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserMail> getList(String userId) {
		String table = TableUtils.getUserMailTable(userId);
		String sql = "SELECT * FROM " + table + " WHERE user_id = ? AND status <> 2";

		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		return this.jdbcUser.getList(sql, UserMail.class, parameter);
	}
	
	/**
	 * 更新状态
	 * 
	 * @param userId
	 * @param userMailId
	 * @param status
	 * @return
	 */
	public boolean updateStatus(String userId, int userMailId, int status) {
		String table = TableUtils.getUserMailTable(userId);
		String sql = "UPDATE " + table + " SET status = ? WHERE user_id = ? AND user_mail_id = ? LIMIT 1";

		SqlParameter parameter = new SqlParameter();
		parameter.setInt(status);
		parameter.setString(userId);
		parameter.setInt(userMailId);
		return jdbcUser.update(sql, parameter) > 0;
	}
	
	/**
	 * 根据用户Id和用户邮件id获取
	 * 
	 * @param userId
	 * @param userMailId
	 * @return
	 */
	public UserMail get(String userId, int userMailId) {
		String table = TableUtils.getUserMailTable(userId);
		String sql = "SELECT * FROM " + table + " WHERE user_id = ? AND user_mail_id = ? ";

		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		parameter.setInt(userMailId);
		return this.jdbcUser.get(sql, UserMail.class, parameter);
	}
	
	/**
	 * 更新领取状态
	 * 
	 * @param userId
	 * @param userMailId
	 * @param status
	 * @return
	 */
	public boolean updateReceiveStatus(String userId, int userMailId, int status) {
		String table = TableUtils.getUserMailTable(userId);
		String sql = "UPDATE " + table + " SET receive_status = ? WHERE user_id = ? AND user_mail_id = ? LIMIT 1";

		SqlParameter parameter = new SqlParameter();
		parameter.setInt(status);
		parameter.setString(userId);
		parameter.setInt(userMailId);
		return jdbcUser.update(sql, parameter) > 0;
	}
	
	/**
	 * 删除用户邮件
	 * 
	 * @param userId
	 * @param userMailId
	 * @return
	 */
	public boolean delete(String userId, int userMailId) {
		String table = TableUtils.getUserMailTable(userId);
		String sql = "delete from " + table + " where user_mail_id=?";
		
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(userMailId);;
		return jdbcUser.update(sql, parameter)>0;
	}
	
	/**
	 * 添加用户邮件
	 * 
	 * @param userMail
	 * @return
	 */
	public boolean add(UserMail userMail) {
		String table = TableUtils.getUserMailTable(userMail.getUserId());
		return this.jdbcUser.insert(table, userMail) > 0;
	}
	
	/**
	 * 根据邮件状态获取用户邮件列表
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<UserMail> getList(String userId, int status) {
		String table = TableUtils.getUserMailTable(userId);
		String sql = "select * from " + table + " where user_id = ? and receive_status = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(status);
		
		return this.jdbcUser.getList(sql, UserMail.class, param);
	}
	
	/**
	 * 改变用户邮件类型
	 * 
	 * @param userId
	 * @param userMailId
	 * @param emailType
	 * @return
	 */
	public boolean updateUserMail(String userId, int userMailId, int emailType) {
		String table = TableUtils.getUserMailTable(userId);
		String sql = "update " + table + " set email_type = ?, updated_time = now() where user_id = ? and user_mail_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(emailType);
		param.setString(userId);
		param.setInt(userMailId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public int getMaxUserMailId(String userId) {
		String table = TableUtils.getUserMailTable(userId);
		String sql = "select user_mail_id from " + table + " order by user_mail_id desc limit 1";
		SqlParameter param = new SqlParameter();
		
		return this.jdbcUser.getInt(sql, param);
	}
}
