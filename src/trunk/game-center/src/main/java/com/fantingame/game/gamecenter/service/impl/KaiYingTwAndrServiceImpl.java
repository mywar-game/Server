package com.fantingame.game.gamecenter.service.impl;

import java.math.BigDecimal;
import java.net.DatagramSocket;
import java.net.URLDecoder;
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
import com.fantingame.game.gamecenter.partener.kaiying.KaiYingTwPaymentObj;
import com.fantingame.game.gamecenter.partener.kaiying.KaiYingTwSdk;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.BasePartnerService;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;
import com.kingnet.udplog.LogEntryClient;
import com.kingnet.udplog.bean.LogEntry;
import com.kingnet.udplog.bean.LogUserInfo;
import com.kingnet.udplog.bean.LogConstants.PAY_TYPE;
import com.kingnet.udplog.protocol.LogFactory;
import com.kingnet.udplog.protocol.LogSender;
import com.kingnet.udplog.protocol.UdpLogSender;


public class KaiYingTwAndrServiceImpl extends BasePartnerService {

	private static final Logger logger = Logger.getLogger(KaiYingTwAndrServiceImpl.class);
	@Autowired
	private ServerListDao serverListDao;
	@Override
	public UserToken login(String token, String partnerId, String serverId, long timestamp, String sign, Map<String, String> params) {
		if (StringUtils.isBlank(token) || StringUtils.isBlank(partnerId) || StringUtils.isBlank(serverId) || timestamp == 0 || StringUtils.isBlank(sign)) {
			throw new ServiceException(PartnerService.PARAM_ERROR, "参数不正确");
		}
		checkSign(token, partnerId, serverId, timestamp, sign);

		try {
			String[] tokenArr = token.split(":");
//			String uid = tokenArr[0];
			String session = tokenArr[1];	
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(serverId, partnerId);
			UserToken userToken = GameApiSdk.getInstance().loadUserToken(session, partnerId, serverId, "0",gameServer,params);
			sendLoginLog(userToken);
			return userToken;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(PartnerService.LOGIN_VALID_FAILD, "登录验证失败");
		}
	}
	
	public void sendLoginLog(UserToken userToken){
		try {
			LogSender sender = UdpLogSender.getInstace("192.168.4.200", 10600,new DatagramSocket());
			LogEntryClient client = LogEntryClient.getInstace(sender);
			LogUserInfo userInfo = new LogUserInfo(userToken.getPartnerUserId(),userToken.getUserId(),userToken.getServerId());
			LogEntry logEntry = LogFactory.getLoginLog(1172028l, userInfo, "","", 0, 1) ;
			client.sendLog(logEntry);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void sendPayLog(GameServer gameServer,PaymentOrder order,BigDecimal finishAmount,int gold,KaiYingTwPaymentObj cb){
		try {
			LogSender sender = UdpLogSender.getInstace("192.168.4.200", 10601,new DatagramSocket());
			LogEntryClient client = LogEntryClient.getInstace(sender);
			Map<String, Object> map = GameApiSdk.getInstance().getUser(order.getPartnerUserId(), order.getServerId(), order.getPartnerId(), gameServer);
			if(map==null || map.get("roleid")==null){
				logger.error("发送充值日志失败,用户信息不存在partnerUserId="+order.getPartnerUserId()+",serverId="+order.getServerId()+",partnerId="+order.getPartnerId());
				return;
			}
			String str = URLDecoder.decode(cb.getKda(), "utf-8");
			String[] arr = str.split("\\|");
			String[] orderArr = arr[0].split("#");
			LogUserInfo userInfo = new LogUserInfo(order.getPartnerUserId(),map.get("roleid").toString(),order.getServerId()) ;
			LogEntry logEntry = LogFactory.getPayLog(1172028l, userInfo, orderArr[0], Double.valueOf(arr[1]), Integer.parseInt(arr[2]), orderArr[1],arr[3],PAY_TYPE.PAY, 1);
			client.sendLog(logEntry);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public boolean doPayment(String orderId, String partnerUserId, boolean success, String partnerOrderId, BigDecimal finishAmount, Map<String, String> reqInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean doPayment(PaymentObj paymentObj) {
		if (paymentObj == null) {
			logger.error("paymentObj为空");
			return false;
		}

		KaiYingTwPaymentObj cb = (KaiYingTwPaymentObj) paymentObj;

		if (!KaiYingTwSdk.instance().checkPayCallbackSign(cb)) {
			logger.error("签名不正确" + Json.toJson(paymentObj));
			return false;
		}
		logger.info("game order id:" + cb.getAppExtra1());
		//appExtra1字段传游戏这边的订单号
		PaymentOrder order = paymentOrderDao.get(cb.getAppExtra1());

		logger.info("应用订单：" + Json.toJson(order));
		if (order == null) {
			String payRef = cb.getPayRef();
			if(StringUtils.isBlank(payRef)){
				logger.error("订单为空：" + Json.toJson(cb));
				return false;
			}
			//恺英海外汇款    手动创建订单
			TradeInfo info = createOrderInfo(getPatenerEnum().getPartenerId()+"", KaiYingTwSdk.instance().getServerId(Integer.parseInt(cb.getSid())), cb.getUserId(), new BigDecimal(cb.getAmount()), "");
			order = paymentOrderDao.get(info.getTradeId());
			if(order == null){
				logger.error("订单为空：" + Json.toJson(cb));
				return false;
			}
		}

		if (order.getStatus() == OrderStatus.STATUS_FINISH) {
			logger.info("订单已经完成" + Json.toJson(cb));
			return true;
		}

		BigDecimal finishAmount = new BigDecimal(cb.getAmount());

		int gold = Integer.parseInt(cb.getNumber());
		// 更新订单状态
		if (this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_FINISH, cb.getPartnerOrderId(), finishAmount, "")) {
			GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(order.getServerId(), order.getPartnerId());
			// 请求游戏服，发放游戏货币
			if (!GameApiSdk.getInstance().doPayment(order.getPartnerId(), order.getServerId(), order.getPartnerUserId(), "", order.getOrderId(), finishAmount, gold, "", "",gameServer)) {
				// 如果失败，要把订单置为未支付
				this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_NOT_PAY, cb.getPartnerOrderId(), finishAmount, "");
				logger.error("发货失败：" + Json.toJson(cb));
				return false;
			} else {
				logger.info("支付成功：" + Json.toJson(cb));
				sendPayLog(gameServer, order, finishAmount, gold, cb);
				return true;
			}
		}
		this.paymentOrderDao.updateStatus(order.getOrderId(), OrderStatus.STATUS_ERROR, cb.getPartnerOrderId(), finishAmount, "");
		logger.error("充值失败：" + Json.toJson(cb));
		return false;
	}

	@Override
	public String createOrder(String partnerId, String serverId, String partnerUserId, BigDecimal amount, String tradeName) {
		TradeInfo info = createOrderInfo(partnerId, serverId, partnerUserId, amount, tradeName);
		return Json.toJson(info);
	}
	
	@Override
	public PartenerEnum getPatenerEnum() {
		return PartenerEnum.KaiYingTwAndr;
	}
	
}
