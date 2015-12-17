package com.fantingame.game.cuser.service;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventManager;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.IDGenerator;
import com.fantingame.game.cuser.dao.UserMapperDao;
import com.fantingame.game.cuser.model.UserMapper;
import com.fantingame.game.log.service.LogService;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.collect.Lists;



public class UserMapperService {

	public final static int USER_NOT_EXIST = 2001;
	@Autowired
	private UserMapperDao userMapperDao;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired	
	private LogService logService;

	public UserMapperDao getUserMapperDao() {
		return userMapperDao;
	}

	public void setUserMapperDao(UserMapperDao userMapperDao) {
		this.userMapperDao = userMapperDao;
	}

	
	public UserMapper getUserMapperByUserId(String userId){
		return userMapperDao.get(userId);
	}
	

	public UserMapper loadUserMapper(String partnerUserId, String serverId,
			String partnerId, String qn, String imei, String mac, String idfa,
			String ip, String mobile) {
		if (!StringUtils.hasText(partnerUserId) || !StringUtils.hasText(serverId)
				|| !StringUtils.hasText(partnerId)) {
			return null;
		}
		UserMapper userMapper = userMapperDao.getByPartnerUserId(partnerUserId,
				partnerId, serverId);
		if (userMapper == null) {
			userMapper = new UserMapper();
			userMapper.setPartnerId(partnerId);
			userMapper.setPartnerUserId(partnerUserId);
			userMapper.setServerId(serverId);
			userMapper.setUserId(IDGenerator.getID());
			userMapper.setQn(qn);
			userMapper.setImei(imei);
			userMapper.setIdfa(idfa);
			userMapper.setMac(mac);
			userMapper.setCreatedTime(new Date());
			userMapper.setUpdatedTime(new Date());
			userMapperDao.save(userMapper);
			// 用户注册事件
			ModuleEventBase baseModuleEvent = new ModuleEventBase();
			baseModuleEvent.addObjectValue("userMapper", userMapper);
			ModuleEventManager.getInstance().dispatchEvent("after_user_reg_event", baseModuleEvent);
			userRegLog(userMapper.getUserId(), partnerUserId,
					partnerId, qn, new Date(), ip, mac, imei, idfa, mobile);
		}
		return userMapper;
	}

	public boolean doPayment(String partnerId, String serverId,
			String partnerUserId, String channel, String orderId,
			BigDecimal amount, final int gold, String userIp, String remark) {
		UserMapper userMapper = this.userMapperDao.getByPartnerUserId(
				partnerUserId, partnerId, serverId);
		if (userMapper == null) {
			String message = "充值失败,用户不存在.partnerId[" + partnerId
					+ "], serverId[" + serverId + "], partnerUserId["
					+ partnerUserId + "]";
			ServiceException serviceException = new ServiceException(USER_NOT_EXIST, message);
			LogSystem.error(serviceException,message);
			throw serviceException;
		}
        ModuleEventBase eventBase = new ModuleEventBase();
        eventBase.addStringValue("userId", userMapper.getUserId());
        eventBase.addStringValue("partnerId", partnerId);
        eventBase.addStringValue("serverId", serverId);
        eventBase.addStringValue("partnerUserId", partnerUserId);
        eventBase.addStringValue("channel", channel);
        eventBase.addStringValue("orderId", orderId);
        eventBase.addObjectValue("amount", amount);
        eventBase.addIntValue("gold", gold);
        eventBase.addStringValue("userIp", userIp);
        eventBase.addStringValue("remark", remark);
		ModuleEventManager.getInstance().dispatchEvent("charge_event", eventBase);
		return true;
	}
	
    /**
     * 注册日志 创建了userMapper
     * @param userId
     * @param userName
     * @param parteneId
     * @param qn
     * @param regTime
     * @param ip
     * @param mac
     * @param imei
     * @param idfa
     * @param mobile
     */
	public void userRegLog(String userId, String userName, String parteneId, String qn, Date regTime,String ip,String mac,String imei,String idfa,String mobile) {
		String time = DateUtils.getTimeStr(regTime);
		userName = userName.replaceAll("\'","\\\\'");
		String sql = "INSERT INTO user_reg_log(USER_ID,USER_NAME,CHANNEL,SMALL_CHANNEL,REG_TIME,REG_IP,REG_MAC,REG_IMEI,REG_IDFA,REG_MOBILE) VALUES('" + userId + "','" + userName + "','" + parteneId + "','" + qn + "','" + time + "'"+
		",'" + ip + "','" + mac + "','" + imei + "','" + idfa + "','" + mobile + "'"
				+ ")";
		logService.synInsertLog(sql);
	}
	
	/**
	 * 根据渠道号获取
	 * 
	 * @param partnerId
	 * @return
	 */
	public List<UserMapper> getUserMapperByPartner(String partnerId) {
		return this.userMapperDao.getUserMapperByPartner(partnerId);
	}
	
	public List<String> getUserIdListByPartner(String partnerId) {
		List<UserMapper> userMapperList = getUserMapperByPartner(partnerId);
		List<String> userIdList = Lists.newArrayList();
		for (UserMapper userMapper : userMapperList) {
			userIdList.add(userMapper.getUserId());
		}
		
		return userIdList;
	}
}
