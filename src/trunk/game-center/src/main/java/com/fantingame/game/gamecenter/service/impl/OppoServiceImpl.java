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
import com.fantingame.game.gamecenter.partener.oppo.OppoPaymentObj;
import com.fantingame.game.gamecenter.partener.oppo.OppoSdk;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.BasePartnerService;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;
import com.nearme.oauth.model.AccessToken;
import com.nearme.oauth.open.AccountAgent;

/**
 * Oppo
 * 
 * @author yezp
 */
public class OppoServiceImpl extends BasePartnerService {

	@Autowired
	private ServerListDao serverListDao;

	private static final Logger logger = Logger
			.getLogger(OppoServiceImpl.class);

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
			String access_token = tokenArr[0];
			String access_secret = tokenArr[1];
			// String access_secret = OppoSdk.instance().getAppSecret();
			
			String gcUserInfo = AccountAgent.getInstance().getGCUserInfo(new AccessToken(access_token, access_secret));
			Map<String, Map<String, String>> ret = Json.toObject(gcUserInfo, Map.class);
			
			if (!ret.containsKey("BriefUser")) {
				logger.error("Oppo 返回数据不正确，登录验证失败");
				throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "Oppo 返回数据不正确，登录验证失败");
			}
			
			Map<String, String> map = ret.get("BriefUser");
			if (!map.containsKey("id")) {
				logger.error("Oppo 返回数据不正确，userId不存在，登录验证失败");
				throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "Oppo 返回数据不正确，userId不存在，登录验证失败");
			}
			
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
			UserToken userToken = GameApiSdk.getInstance().loadUserToken(map.get("id"), partnerId, serverId, "0", gameServer, params);
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

		OppoPaymentObj pb = (OppoPaymentObj) paymentObj;
		logger.info("game id:" + pb.getPartnerOrder());
		PaymentOrder order = paymentOrderDao.get(pb.getPartnerOrder());
		BigDecimal finishAmount = new BigDecimal(pb.getPrice() * pb.getCount()).divide(new BigDecimal(100));

		if (!OppoSdk.instance().checkPayCallbackSign(pb)) {
			logger.error("签名不正确" + Json.toJson(paymentObj));
			this.paymentOrderDao.updateStatus(order.getOrderId(),
					OrderStatus.STATUS_ERROR, pb.getPartnerOrder(),
					finishAmount, "");
			return false;
		}

		logger.info("应用订单：" + Json.toJson(order));
		if (order == null) {
			logger.error("订单为空：" + Json.toJson(pb));
			return false;
		}

		if (order.getStatus() == OrderStatus.STATUS_FINISH) {
			logger.info("订单已经完成" + Json.toJson(pb));
			return true;
		}
        //因oppo创建订单后 金额是不可被更改的  又不太明确 price*count到底是否为玩家确实支付的钱 所以加这个判断  以防万一
		if (order.getAmount().compareTo(finishAmount) != 0) {
			logger.error("订单金额不符：" + Json.toJson(pb));
			return false;
		}

		int diamond = (int) (finishAmount.doubleValue() * 10);
		// 更新订单状态
		if (this.paymentOrderDao.updateStatus(order.getOrderId(),
				OrderStatus.STATUS_FINISH, pb.getNotifyId(), finishAmount,
				"")) {
			GameServer gameServer = serverListDao
					.getServerByServerIdAndPartenerId(order.getServerId(),
							order.getPartnerId());
			// 请求游戏服，发放游戏货币
			if (!GameApiSdk.getInstance().doPayment(order.getPartnerId(),
					order.getServerId(), order.getPartnerUserId(), "",
					order.getOrderId(), finishAmount, diamond, "", "",
					gameServer)) {
				// 如果失败，要把订单置为未支付
				this.paymentOrderDao.updateStatus(order.getOrderId(),
						OrderStatus.STATUS_NOT_PAY, pb.getNotifyId(),
						finishAmount, "");
				logger.error("发货失败：" + Json.toJson(pb));
				return false;
			} else {
				logger.info("支付成功：" + Json.toJson(pb));
				return true;
			}
		}

		this.paymentOrderDao.updateStatus(order.getOrderId(),
				OrderStatus.STATUS_ERROR, pb.getNotifyId(), finishAmount,
				"");
		logger.error("充值失败：" + Json.toJson(pb));
		return false;
	}

	@Override
	public String createOrder(String partnerId, String serverId,
			String partnerUserId, BigDecimal amount, String tradeName) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId,
				amount, tradeName);
		info.setNotifyUrl("http://wapi.andr.hw.fantingame.com:80/webApi/oppoPayment.do");
		logger.info("Oppo orderInfo:" + info.getNotifyUrl());
		return Json.toJson(info);
	}
	
	@Override
	public String createOrder(String partnerId, String serverId,
			String partnerUserId, BigDecimal amount, String tradeName, String qn) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId,
				amount, tradeName, qn);
		info.setNotifyUrl("http://wapi.andr.hw.fantingame.com:80/webApi/oppoPayment.do");
		logger.info("Oppo orderInfo:" + info.getNotifyUrl());
		return Json.toJson(info);
	}

	@Override
	public PartenerEnum getPatenerEnum() {
		return PartenerEnum.OPPO;
	}

}
