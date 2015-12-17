package com.fantingame.game.mywar.logic.mail.dao;

import java.util.Date;
import java.util.List;

import com.fantingame.game.mywar.logic.mail.model.SystemMail;

public interface SystemMailDao {

	/**
	 * 获取邮件
	 * 
	 * @param systemMailId
	 * @return
	 */
	public SystemMail getSystemMail(String systemMailId);
	
	/**
	 * 添加邮件
	 * 
	 * @param systemMail
	 * @return
	 */
	public boolean add(SystemMail systemMail);

	/**
	 * 获取时间节点之后的邮件
	 * 
	 * @param date
	 * @return
	 */
	public List<SystemMail> getSystemMailByTime(Date date);
}
