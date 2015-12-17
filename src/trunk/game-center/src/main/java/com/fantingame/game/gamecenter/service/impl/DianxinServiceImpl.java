package com.fantingame.game.gamecenter.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.gamecenter.bo.UserToken;
import com.fantingame.game.gamecenter.constant.OrderStatus;
import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.dao.ServerListDao;
import com.fantingame.game.gamecenter.exception.ServiceException;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.model.PaymentOrder;
import com.fantingame.game.gamecenter.partener.PaymentObj;
import com.fantingame.game.gamecenter.partener.dianxin.DianxinPayMsgObj;
import com.fantingame.game.gamecenter.partener.dianxin.DianxinSdk;
import com.fantingame.game.gamecenter.partener.fantin.util.TradeInfo;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.BasePartnerService;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 电信
 * 
 * @author 
 */
public class DianxinServiceImpl extends BasePartnerService {
	
	@Autowired
	private ServerListDao serverListDao;
	
	private static final Logger logger = Logger.getLogger(DianxinServiceImpl.class);

	@Override
	public UserToken login(String token, String partnerId, String serverId,
			long timestamp, String sign, Map<String, String> params) {
		if (StringUtils.isBlank(token) || StringUtils.isBlank(partnerId) || StringUtils.isBlank(serverId) || timestamp == 0 || StringUtils.isBlank(sign)) {
			throw new ServiceException(PartnerService.PARAM_ERROR, "参数不正确");
		}
		checkSign(token, partnerId, serverId, timestamp, sign);
		
		try {
			logger.info("DianxinServiceImpl login : token=" + token);
			Map<String, String> content = DianxinSdk.instance().getUserInfo(token);
			logger.info("DianxinServiceImpl login content = " + content);
			
			if (content != null && content.get("error_code") != null && content.get("error_code").equals("-5")) {
				logger.error("DianxinServiceImpl login failed content = " + content);
				throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "登录验证失败");
			}
			if (content != null) {
				params.putAll(content); // 返回这些参数
				GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
				UserToken userToken = GameApiSdk.getInstance().loadUserToken(content.get("user_id"), partnerId, serverId, "0", gameServer, params);
				
				return userToken;
			} else {
				throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "登录验证失败");
			}			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(PartnerService.PARAM_ERROR, "参数不正确");
		}
	}

	@Override
	public boolean doPayment(String orderId, String partnerUserId,
			boolean success, String partnerOrderId, BigDecimal finishAmount,
			Map<String, String> reqInfo) {
		return false;
	}

	@Override
	public boolean doPayment(PaymentObj paymentObj) {
		if (paymentObj == null) {
			logger.error("paymentObj为空");
			return false;
		}
		DianxinPayMsgObj rsp = (DianxinPayMsgObj) paymentObj;
		
		// 将自己的订单号放在ccparam
		PaymentOrder order = paymentOrderDao.get(rsp.getCp_order_id());
		if (order == null) {
			logger.error("订单为空：" + Json.toJson(rsp));
			return false;
		}

		if (order.getStatus() == OrderStatus.STATUS_FINISH) {
			logger.info("订单已经完成" + Json.toJson(rsp));
			return true;
		}

		BigDecimal finishAmount = new BigDecimal(rsp.getFee());
		
		int gold = (int) (finishAmount.doubleValue() * 10);

		// 更新订单状态
		if (this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_FINISH, rsp.getCp_order_id(), finishAmount, "")) {
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(order.getServerId(), order.getPartnerId());
			// 请求游戏服，发放游戏货币
			if (!GameApiSdk.getInstance().doPayment(order.getPartnerId(), order.getServerId(), order.getPartnerUserId(), "", order.getOrderId(), finishAmount, gold, "", "", gameServer)) {
				// 如果失败，要把订单置为未支付
				return this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_NOT_PAY, rsp.getCp_order_id(), finishAmount, "");
			} else {
				logger.info("支付成功：" + Json.toJson(rsp));
				return true;
			}
		}
		logger.error("充值失败：" + Json.toJson(rsp));		
		return false;
	}
	
	@Override
	public String createOrder(String partnerId, String serverId,
			String partnerUserId, BigDecimal amount, String tradeName) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId,
				amount, tradeName);
		return Json.toJson(info);
	}
	
	@Override
	public String createOrder(String partnerId, String serverId,
			String partnerUserId, BigDecimal amount, String tradeName, String qn) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId,
				amount, tradeName, qn);
		return Json.toJson(info);
	}

	@Override
	public PartenerEnum getPatenerEnum() {
		return PartenerEnum.Dianxin;
	}
}
