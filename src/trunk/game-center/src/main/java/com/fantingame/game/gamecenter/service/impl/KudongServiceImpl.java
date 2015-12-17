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
import com.fantingame.game.gamecenter.partener.kudong.KudongPaymentObj;
import com.fantingame.game.gamecenter.partener.kudong.KudongSdk;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.BasePartnerService;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 酷动
 * @author Administrator
 *
 */
public class KudongServiceImpl extends BasePartnerService {
	
	private static final Logger logger = Logger.getLogger(KudongServiceImpl.class);
	@Autowired
	private ServerListDao serverListDao;
	
	@Override
	public UserToken login(String token, String partnerId, String serverId,
			long timestamp, String sign, Map<String, String> params) {
		
		if (StringUtils.isBlank(token) || StringUtils.isBlank(partnerId) || StringUtils.isBlank(serverId) || timestamp == 0 || StringUtils.isBlank(sign)) {
			throw new ServiceException(PartnerService.PARAM_ERROR, "参数不正确");
		}

		checkSign(token, partnerId, serverId, timestamp, sign);

		try {
			String[] tokenArr = token.split(":");
			String mid = tokenArr[0];
			String kudongToken = tokenArr[1];
			logger.info("KudongServiceImpl token=" + token);
			
			if (KudongSdk.instance().verifyLogin(mid, kudongToken)) {
				GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
				UserToken userToken = GameApiSdk.getInstance().loadUserToken(mid, partnerId, serverId, "0", gameServer, params);
				return userToken;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "登录验证失败");
		}
		throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "登录验证失败");
	}

	@Override
	public boolean doPayment(String orderId, String partnerUserId,
			boolean success, String partnerOrderId, BigDecimal finishAmount,
			Map<String, String> reqInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doPayment(PaymentObj paymentObj) {
		if (paymentObj == null) {
			logger.error("paymentObj为空");
			return false;
		}

		KudongPaymentObj pb = (KudongPaymentObj) paymentObj;
		
		PaymentOrder order = paymentOrderDao.get(pb.getEif());
		BigDecimal finishAmount = new BigDecimal(pb.getGold());

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
		if (this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_FINISH, pb.getEif(), finishAmount, "")) {
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(order.getServerId(), order.getPartnerId());
			// 请求游戏服，发放游戏货币
			if (!GameApiSdk.getInstance().doPayment(order.getPartnerId(), order.getServerId(), order.getPartnerUserId(), "", order.getOrderId(), finishAmount, diamond, "", "", gameServer)) {
				// 如果失败，要把订单置为未支付
				this.paymentOrderDao.updateStatus(order.getOrderId(),OrderStatus.STATUS_NOT_PAY, pb.getEif(), finishAmount, "");
				logger.error("发货失败：" + Json.toJson(pb));
				return false;
			} else {
				logger.info("支付成功：" + Json.toJson(pb));
				return true;
			}
		}

		this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, pb.getEif(), finishAmount, "");
		logger.error("充值失败：" + Json.toJson(pb));
		return false;
	}

	@Override
	public String createOrder(String partnerId, String serverId,
			String partnerUserId, BigDecimal amount, String tradeName) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId,
				amount, tradeName);
		info.setNotifyUrl("http://wapi.andr.hw.fantingame.com:80/webApi/kudongPayment.do");
		//info.setNotifyUrl("http://203.195.190.121:8089/webApi/kudongPayment.do");
		logger.info("Kudong orderInfo:" + info.getNotifyUrl());
		return Json.toJson(info);
	}
	
	@Override
	public String createOrder(String partnerId, String serverId,
			String partnerUserId, BigDecimal amount, String tradeName, String qn) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId,
				amount, tradeName, qn);
		info.setNotifyUrl("http://wapi.andr.hw.fantingame.com:80/webApi/kudongPayment.do");
		//info.setNotifyUrl("http://203.195.190.121:8089/webApi/kudongPayment.do");
		logger.info("Kudong orderInfo:" + info.getNotifyUrl());
		return Json.toJson(info);
	}
	
	@Override
	public PartenerEnum getPatenerEnum() {
		return PartenerEnum.Kudong;
	}
}
