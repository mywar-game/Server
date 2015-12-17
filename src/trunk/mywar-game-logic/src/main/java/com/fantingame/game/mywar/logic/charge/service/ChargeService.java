package com.fantingame.game.mywar.logic.charge.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.log.service.LogService;
import com.fantingame.game.mywar.logic.charge.dao.PaymentLogDao;
import com.fantingame.game.mywar.logic.charge.dao.SystemGoldSetDao;
import com.fantingame.game.mywar.logic.charge.model.PaymentLog;
import com.fantingame.game.mywar.logic.charge.model.SystemGoldSet;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.user.constant.UserPushProperties;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.collect.Lists;

public class ChargeService implements ModuleEventHandler{
	@Autowired
	private SystemGoldSetDao systemGoldSetDao;
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private PaymentLogDao paymentLogDao;
	@Autowired
	private LogService logService;
	
	@Override
	public void handler(String handlerType, ModuleEventBase eventBase) {
		//充值事件监听
		if(handlerType.equals(ModuleEventConstant.Charge)){
			String userId =  eventBase.getStringValue("userId", "");
			String partnerId =  eventBase.getStringValue("partnerId", "");
			String serverId = eventBase.getStringValue("serverId", "");
			String partnerUserId = eventBase.getStringValue("partnerUserId", "");
			String channel = eventBase.getStringValue("channel", "");
			String orderId = eventBase.getStringValue("orderId", "");
			BigDecimal amount = (BigDecimal)eventBase.getObjectValue("amount");
			int gold = eventBase.getIntValue("gold", 0);
			String ip =  eventBase.getStringValue("userIp", "");
			String remark = eventBase.getStringValue("remark", "");
	       
			User user = userService.getByUserId(userId);
			if(user==null){
				ServiceException e = new ServiceException(SystemConstant.FAIL_CODE, "用户没有创建角色，充值失败"+userId+",amount="+amount+",gold="+gold);
				LogSystem.error(e, "");
				throw e;
			}
			
			SystemGoldSet systemGoldSet = this.systemGoldSetDao.getByPayAmount(amount.doubleValue(), partnerId);
			//添加常规money
			boolean success = userService.addMoney(userId, gold, GoodsUseType.ADD_MONEY_PAY);
			if(!success){
				LogSystem.error(new ServiceException(SystemConstant.FAIL_CODE, "充值加钱失败！userId="+userId+",gold="+gold), "");
			}
			
			double payAmount = this.userService.allChargeAmount(userId);// 首冲双倍
			// 是否首冲
			boolean isFirstCharge = (payAmount == 0 ? true : false);
			// 是否在首冲翻n倍的充值区间
			boolean isFirstAdd = false;
			// 是否返还
			boolean isReturn = true;
			if (systemGoldSet != null) {
				if (systemGoldSet.getType() == 1) {
					isFirstAdd = true;
				}
				
				// 第一次充值 并且要首冲翻倍的 则不返回金币
				if (isFirstAdd && isFirstCharge) {
					isReturn = false;
				}
				
				if (isReturn) {
					int returnGold = systemGoldSet.getGold() - gold;
					if (returnGold > 0) {// 有返还金币
						success = userService.addMoney(userId, returnGold, GoodsUseType.ADD_MONEY_RETURN);
						if (!success) {
							ServiceException e =  new ServiceException(SystemConstant.FAIL_CODE, "充值返还加钱失败！userId="+userId+",gold="+returnGold);
							LogSystem.error(e, "");
						}
					}
				}
			}
			
			if (isFirstCharge) {
				int times = 1;
				if (isFirstAdd) {
					times = configDataDao.getInt("first_charge_times", 2);
				}
				int addGlold = gold * (times - 1);
				if (addGlold > 0) {
					success = userService.addMoney(userId, addGlold, GoodsUseType.ADD_MONEY_DOUBLE);
					if(!success) {
						ServiceException e =  new ServiceException(SystemConstant.FAIL_CODE, "充值double加钱失败！userId="+userId+",gold="+addGlold);
						LogSystem.error(e, "");
					}
				}
			}
			
			PaymentLog paymentLog = new PaymentLog();
			paymentLog.setChannel(channel);
			paymentLog.setCreatedTime(new Date());
			paymentLog.setAmount(amount);
			paymentLog.setOrderId(orderId);
			paymentLog.setPartnerId(partnerId);
			paymentLog.setPartnerUserId(partnerUserId);
			paymentLog.setRemark(remark);
			paymentLog.setServerId(serverId);
			paymentLog.setUserId(userId);
			paymentLog.setUserIp(ip);
			paymentLog.setUserName(partnerUserId);
			//用户表日志
			paymentLogDao.add(paymentLog);
			//运营数据日志
			userPayLog(paymentLog, user.getLevel());
			// 刷新客户端相关属性 
			userService.notifyUserProperties(userId,UserPushProperties.MONEY);
		}
	}
	
	@Override
	public List<String> getHandlerType() {
		List<String> list = Lists.newArrayList();
		list.add(ModuleEventConstant.Charge);
		return list;
	}
	
	@Override
	public int order() {
		return 0;
	}
	
    /**
     * 充值日志
     * @param paymentLog
     * @param userLevel
     */
	public void userPayLog(PaymentLog paymentLog, int userLevel) {
		String time = DateUtils.getTimeStr(paymentLog.getCreatedTime());

		String sql = "INSERT INTO payment_log(PARTNER_ID,SERVER_ID,PARTNER_USER_ID,USER_ID,USER_NAME,USER_LEVEL,CHANNEL,ORDER_ID,AMOUNT,GOLD,USER_IP,REMARK,CREATED_TIME) " + "VALUES('" + paymentLog.getPartnerId()
				+ "','" + paymentLog.getServerId() + "','" + paymentLog.getPartnerUserId() + "','" + paymentLog.getUserId() + "','" + paymentLog.getUserName() + "'," + userLevel + ",'" + paymentLog.getChannel() + "','"
				+ paymentLog.getOrderId() + "'," + paymentLog.getAmount() + "," + paymentLog.getGold() + ",'" + paymentLog.getUserIp() + "','" + paymentLog.getRemark() + "','" + time + "')";
		logService.synInsertLog(sql);
	}
}
