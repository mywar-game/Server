package com.fantingame.game.cuser.dao;

import java.util.List;

import com.fantingame.game.cuser.model.UserMapper;


public interface UserMapperDao {
	/**
	 * 保存用户mapper
	 * 
	 * @param userMapper
	 * @return
	 */
	public boolean save(UserMapper userMapper);

	/**
	 * 根据partnerUserId获取用户mapper信息
	 * 
	 * @param partnerUserId
	 * @param serverId
	 * @return
	 */
	public UserMapper getByPartnerUserId(String partnerUserId, String partnerId, String serverId);

	/**
	 * 根据userId获取UserMapper表信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserMapper get(String userId);
	
	/**
	 * 根据渠道号获取用户
	 * 
	 * @param partnerId
	 * @return
	 */
	public List<UserMapper> getUserMapperByPartner(String partnerId);
}
