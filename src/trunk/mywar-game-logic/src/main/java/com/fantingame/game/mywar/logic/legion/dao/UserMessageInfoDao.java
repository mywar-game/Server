package com.fantingame.game.mywar.logic.legion.dao;

import java.util.Date;
import java.util.List;

import com.fantingame.game.mywar.logic.legion.model.UserMessageInfo;

public interface UserMessageInfoDao {

	/**
	 * 获取军团的留言板信息
	 * 
	 * @param legionId
	 * @return
	 */
	public List<UserMessageInfo> getUserMessageInfoList(String legionId);
	
	/**
	 * 添加留言信息
	 * 
	 * @param userMessageInfo
	 * @return
	 */
	public boolean addUserMessageInfo(UserMessageInfo userMessageInfo);
	
	/**
	 * 删除军团所有留言板信息
	 * 
	 * @param legionId
	 * @return
	 */
	public boolean deleteUserMessageInfo(String legionId);
	
	/**
	 * 获取有效期内的留言
	 * 
	 * @param legionId
	 * @param endTime
	 * @return
	 */
	public List<UserMessageInfo> getUserMessageInfoList(String legionId, Date endTime);
}
