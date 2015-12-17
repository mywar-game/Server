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
import com.fantingame.game.gamecenter.partener.fantin.util.TradeInfo;
import com.fantingame.game.gamecenter.partener.pada.PadaPaymentObj;
import com.fantingame.game.gamecenter.partener.pada.PadaSdk;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.BasePartnerService;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 艺果
 * @author Administrator
 *
 */
public class PadaServiceImpl extends BasePartnerService {
	
	@Autowired
	private ServerListDao serverListDao;
	
	private static final Logger logger = Logger.getLogger(PadaServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public UserToken login(String token, String partnerId, String serverId,
			long timestamp, String sign, Map<String, String> params) {
		
		if (StringUtils.isBlank(token) || StringUtils.isBlank(partnerId) || StringUtils.isBlank(serverId) || timestamp == 0 || StringUtils.isBlank(sign)) {
			throw new ServiceException(PartnerService.PARAM_ERROR, "参数不正确");
		}
		checkSign(token, partnerId, serverId, timestamp, sign);
		
		try {
			String[] tokenArr = token.split(":");
			String roleId = tokenArr[0];
			String roleToken = tokenArr[1];
			
			boolean flag = PadaSdk.instance().valifyLogin(roleId, roleToken);
			
			if (!flag) {
				logger.error("Pada 返回数据不正确，登录验证失败");
				throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "Pada 返回数据不正确，登录验证失败");
			}
			
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
			UserToken userToken = GameApiSdk.getInstance().loadUserToken(roleId, partnerId, serverId, "0", gameServer, params);
			return userToken;
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "登录验证失败");
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

		PadaPaymentObj pb = (PadaPaymentObj) paymentObj;
		
		PaymentOrder order = paymentOrderDao.get(pb.getCpOrderId());
		BigDecimal finishAmount = new BigDecimal(pb.getPayFee()).divide(new BigDecimal(10));

		logger.info("应用订单：" + Json.toJson(order));
		if (order == null) {
			logger.error("订单为空：" + Json.toJson(pb));
			return false;
		}

		if (order.getStatus() == OrderStatus.STATUS_FINISH) {
			logger.info("订单已经完成" + Json.toJson(pb));
			return true;
		}
		
		int diamond = (int) (finishAmount.doubleValue() * 10);
		// 更新订单状态
		if (this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_FINISH, pb.getOrderId(), finishAmount, "")) {
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(order.getServerId(), order.getPartnerId());
			// 请求游戏服，发放游戏货币
			if (!GameApiSdk.getInstance().doPayment(order.getPartnerId(), order.getServerId(), order.getPartnerUserId(), "", order.getOrderId(), finishAmount, diamond, "", "", gameServer)) {
				// 如果失败，要把订单置为未支付
				this.paymentOrderDao.updateStatus(order.getOrderId(),OrderStatus.STATUS_NOT_PAY, pb.getOrderId(), finishAmount, "");
				logger.error("发货失败：" + Json.toJson(pb));
				return false;
			} else {
				logger.info("支付成功：" + Json.toJson(pb));
				return true;
			}
		}

		this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, pb.getOrderId(), finishAmount, "");
		logger.error("充值失败：" + Json.toJson(pb));
		return false;
	}

	@Override
	public String createOrder(String partnerId, String serverId,
			String partnerUserId, BigDecimal amount, String tradeName) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId,
				amount, tradeName);
		info.setNotifyUrl("http://wapi.andr.hw.fantingame.com:80/webApi/padaPayment.do");
		//info.setNotifyUrl("http://203.195.190.121:8089/webApi/padaPayment.do");
		logger.info("Pada orderInfo:" + info.getNotifyUrl());
		return Json.toJson(info);
	}
	
	@Override
	public String createOrder(String partnerId, String serverId,
			String partnerUserId, BigDecimal amount, String tradeName, String qn) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId,
				amount, tradeName, qn);
		info.setNotifyUrl("http://wapi.andr.hw.fantingame.com:80/webApi/padaPayment.do");
		//info.setNotifyUrl("http://203.195.190.121:8089/webApi/padaPayment.do");
		logger.info("Pada orderInfo:" + info.getNotifyUrl());
		return Json.toJson(info);
	}

	@Override
	public PartenerEnum getPatenerEnum() {
		return PartenerEnum.PaDa;
	}
}
