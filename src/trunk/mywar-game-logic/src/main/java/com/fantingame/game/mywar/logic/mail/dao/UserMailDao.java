package com.fantingame.game.mywar.logic.mail.dao;

import java.util.Date;
import java.util.List;

import com.fantingame.game.mywar.logic.mail.model.UserMail;

public interface UserMailDao {

	/**
	 * 获取最后领取时间
	 * 
	 * @param userId
	 * @return
	 */
	public Date getLastReceiveTime(String userId);

	/**
	 * 设置最后领取时间
	 * 
	 * @param userId
	 * @param now
	 * @return
	 */
	public boolean setLastReceiveTime(String userId, Date now);
	
	/**
	 * 获取用户邮件
	 * 
	 * @param userId
	 * @param systemMailId
	 * @return
	 */
	public UserMail getBySystemMailId(String userId, String systemMailId);
	
	/**
	 * 批量添加用户邮件
	 * 
	 * @param userMailList
	 * @return
	 */
	public boolean add(String userId, List<UserMail> userMailList);
	
	/**
	 * 获取用户邮件列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserMail> getList(String userId);
	
	/**
	 * 更新状态
	 * 
	 * @param userId
	 * @param userMailId
	 * @param status
	 * @return
	 */
	public boolean updateStatus(String userId, int userMailId, int status);
	
	/**
	 * 根据用户Id和用户邮件id获取
	 * 
	 * @param userId
	 * @param userMailId
	 * @return
	 */
	public UserMail get(String userId, int userMailId);
	
	/**
	 * 更新领取状态
	 * 
	 * @param userId
	 * @param userMailId
	 * @param status
	 * @return
	 */
	public boolean updateReceiveStatus(String userId, int userMailId, int status);
	
	/**
	 * 删除用户邮件
	 * 
	 * @param userId
	 * @param userMailId
	 * @return
	 */
	public boolean delete(String userId, int userMailId);
	
	/**
	 * 添加用户邮件
	 * 
	 * @param userMail
	 * @return
	 */
	public boolean add(UserMail userMail);
	
	/**
	 * 根据邮件状态获取用户邮件列表
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<UserMail> getList(String userId, int status);
	
	/**
	 * 改变用户邮件类型
	 * 
	 * @param userId
	 * @param userMailId
	 * @param emailType
	 * @return
	 */
	public boolean updateUserMail(String userId, int userMailId, int emailType);
	
	/**
	 * 获取用户邮件id最大值
	 * 
	 * @param userId
	 * @return
	 */
	public int getMaxUserMailId(String userId);
}
