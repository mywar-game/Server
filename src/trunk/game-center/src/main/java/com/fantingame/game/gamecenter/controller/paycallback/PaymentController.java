package com.fantingame.game.gamecenter.controller.paycallback;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.fantingame.game.gamecenter.constant.OrderStatus;
import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.constant.ServiceReturnCode;
import com.fantingame.game.gamecenter.constant.WebApiCode;
import com.fantingame.game.gamecenter.dao.PaymentOrderDao;
import com.fantingame.game.gamecenter.dao.ServerListDao;
import com.fantingame.game.gamecenter.exception.ServiceException;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.help.Assert;
import com.fantingame.game.gamecenter.model.GameServer;
import com.fantingame.game.gamecenter.model.PaymentOrder;
import com.fantingame.game.gamecenter.partener.fantin.service.ClientConfig;
import com.fantingame.game.gamecenter.partener.fantin.service.Md5SignUtil;
import com.fantingame.game.gamecenter.sdk.GameApiSdk;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.EncryptUtil;

/**
 * 游戏服务器WEB
 * 
 * @author CJ
 * 
 */
@Controller
public class PaymentController {

	private static final Logger LOG = Logger.getLogger(PaymentController.class);

	private static final Logger paymentLog = Logger.getLogger("paymentLog");

	private static final String crcKey = "098!@#&^%asdfg*&^%589l";

	@Autowired
	private PartnerServiceFactory serviceFactory;

	@Autowired
	private PaymentOrderDao paymentOrderDao;
	@Autowired
	private ServerListDao serverListDao;
	// private void checkTimestamp(String timestamp) {
	//
	// long timeout =
	// NumberUtils.toLong(ClientConfig.getProperty("ldsg.webapi.timeout",
	// "5000"));
	// long time = NumberUtils.toLong(timestamp);
	// long nowTimestamp = new Date().getTime();
	// long sub = nowTimestamp - time;
	// sub = Math.abs(sub);
	// if (sub > timeout) {
	// String message = "签名验证失败.请求链接失效.nowTimestamp[" + nowTimestamp +
	// "], timestamp[" + timestamp + "]";
	// throw new ServiceException(WebApiService.SIGN_CHECK_ERROR, message);
	// }
	//
	// }

	/**
	 * 查询订单
	 */
	@RequestMapping("/webApi/getOrder.do")
	public ModelAndView getOrder(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		String timestamp = req.getParameter("timestamp");
		String sign = req.getParameter("sign");
		String orderNo = req.getParameter("orderNo");

		int rc = 1000;

		String checkSign = EncryptUtil.getMD5(timestamp + orderNo + crcKey);

		if (!StringUtils.equalsIgnoreCase(sign, checkSign)) {
			rc = 2001;
		} else {
			PaymentOrder paymentOrder = this.paymentOrderDao.get(orderNo);
			if (paymentOrder != null) {
				map.put("order", paymentOrder);
			} else {
				rc = 2002;
			}
		}

		map.put("rc", rc);

		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}
	
	/**
	 * 查询订单, 根据id
	 */
	@RequestMapping("/webApi/getOrderByPartnerUserId.do")
	public ModelAndView getOrderByPartnerUserId(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		String timestamp = req.getParameter("timestamp");
		String sign = req.getParameter("sign");
//		String orderNo = req.getParameter("orderNo");
		String partnerUserId = req.getParameter("partnerUserId");

		int rc = 1000;

//		String checkSign = EncryptUtil.getMD5(timestamp + orderNo + crcKey);
		String checkSign = EncryptUtil.getMD5(timestamp + partnerUserId + crcKey);

		if (!StringUtils.equalsIgnoreCase(sign, checkSign)) {
			rc = 2001;
		} else {
			List<PaymentOrder> paymentOrderList = this.paymentOrderDao.getByPartnerUserId(partnerUserId);
			if (paymentOrderList != null) {
				map.put("orderList", paymentOrderList);
			} else {
				rc = 2002;
			}
//			PaymentOrder paymentOrder = this.paymentOrderDao.get(orderNo);
//			if (paymentOrder != null) {
//				map.put("order", paymentOrder);
//			} else {
//				rc = 2002;
//			}
		}

		map.put("rc", rc);

		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}

	/**
	 * 补单
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/webApi/fillOrder.do")
	public ModelAndView fillOrder(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();

		String timestamp = req.getParameter("timestamp");
		String sign = req.getParameter("sign");
		String orderNo = req.getParameter("orderNo");
		String partnerOrderId = req.getParameter("partnerOrderId");

		int rc = 1000;

		String checkSign = EncryptUtil.getMD5(timestamp + orderNo + partnerOrderId + crcKey);

		if (!StringUtils.equalsIgnoreCase(sign, checkSign)) {
			rc = 2001;
		} else {
			PaymentOrder paymentOrder = this.paymentOrderDao.get(orderNo);
			if (paymentOrder != null) {

				if (paymentOrder.getStatus() == OrderStatus.STATUS_NEW) {
					String orderId = paymentOrder.getOrderId();
					BigDecimal finishAmount = paymentOrder.getAmount();
					int gold = 0;
					// 更新订单状态
					if (this.paymentOrderDao.updateStatus(orderId, OrderStatus.STATUS_FINISH, partnerOrderId, finishAmount, "")) {
						GameServer gameServer = serverListDao.getServerByServerIdAndPartenerId(paymentOrder.getServerId(), paymentOrder.getPartnerId());
						if(paymentOrder.getPartnerId().equals(PartenerEnum.KaiYingTw.getPartenerId()+"") || paymentOrder.getPartnerId().equals(PartenerEnum.KaiYingTwAndr.getPartenerId()+"")){
							//恺英渠道充值补单
							gold = GameApiSdk.getInstance().getSystemGold(paymentOrder.getServerId(), Double.toString(finishAmount.intValue()), gameServer);
						}else{
							gold = (int) (finishAmount.doubleValue() * 10);
						}
						// 请求游戏服，发放游戏货币
						if (!GameApiSdk.getInstance().doPayment(paymentOrder.getPartnerId(), paymentOrder.getServerId(), paymentOrder.getPartnerUserId(), "", paymentOrder.getOrderId(), finishAmount, gold, "", "",gameServer)) {
							// 如果失败，要把订单置为未支付
							this.paymentOrderDao.updateStatus(orderId, OrderStatus.STATUS_NOT_PAY, orderId, finishAmount, "");
							LOG.error("发货失败");
							rc = 2003;
						} else {
							LOG.info("支付成功");
						}
					}
				} else {
					rc = 2004;
				}

			} else {
				rc = 2002;
			}
		}

		map.put("rc", rc);

		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}

	@RequestMapping("/webApi/createOrder.do")
	public ModelAndView createOrder(HttpServletRequest req, HttpServletResponse res) {
		String timestamp = req.getParameter("timestamp");
		String partnerId = req.getParameter("partnerId");
		String serverId = req.getParameter("serverId");
		String partnerUserId = req.getParameter("partnerUserId");
		String amount = req.getParameter("amount");
		String tradeName = req.getParameter("tradeName");
		String sign = req.getParameter("sign");
		String qn = req.getParameter("esqn");
		LOG.error("createOrder : tradeName = " + tradeName + "------------------------------------");

		PartnerService ps = serviceFactory.getBean(partnerId);
		Map<String, Object> map = new HashMap<String, Object>();
		int rc = ServiceReturnCode.SUCCESS;
		try {

			Assert.notEmtpy(timestamp, WebApiCode.PARAM_ERROR, "时间戳不能为空");
			Assert.notEmtpy(sign, WebApiCode.PARAM_ERROR, "检验key不能为空");
			LOG.info("createOrder,partnerId:" + partnerId + ",amount:" + amount+",partenerUserId="+partnerUserId);
			// String key = ClientConfig.getProperty("ldsg.webapi.secertKey");

			// this.checkTimestamp(timestamp);

			// String serverSign = EncryptUtil.getMD5((partnerId + serverId +
			// partnerUserId + timestamp + key));
			// /*
			// * if (false && !StringUtils.equalsIgnoreCase(sign, serverSign)) {
			// * String message = "签名验证失败.partnerId[" + partnerId +
			// "], serverId["
			// * + serverId + "], partnerUserId[" + partnerUserId + "], amount["
			// +
			// * amount + "], timestamp[" + timestamp + "], sign[" + sign + "]";
			// * throw new ServiceException(WebApiService.SIGN_CHECK_ERROR,
			// * message); }
			// */

			String data;
			if (StringUtils.isEmpty(qn)) {
				data = ps.createOrder(partnerId, serverId, partnerUserId, new BigDecimal(amount), tradeName);
				LOG.info("订单号:" + data);
			} else {
				data = ps.createOrder(partnerId, serverId, partnerUserId, new BigDecimal(amount), tradeName, qn);
				LOG.info("订单号:" + data);
			}

			map.put("data", data);

		} catch (ServiceException se) {
			LOG.error(se.getMessage(), se);
			rc = se.getCode();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			rc = ServiceReturnCode.FAILD;
		}

		map.put("rc", rc);

		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}

	@RequestMapping(value = "/webApi/payment.do", method = RequestMethod.POST)
	public ModelAndView payment(HttpServletRequest req, HttpServletResponse res) {

		String invoice = req.getParameter("invoice");
		String tradeId = req.getParameter("tradeId");
		String paidFee = req.getParameter("paidFee");
		String tradeStatus = req.getParameter("tradeStatus");
		String payerId = req.getParameter("payerId");
		String appId = req.getParameter("appId");
		String reqFee = req.getParameter("reqFee");
		String sign = req.getParameter("sign");
		String tradeName = req.getParameter("tradeName");
		String notifyDatetime = req.getParameter("notifyDatetime");
		String partnerId = req.getParameter("partnerId");
		PartnerService ps = serviceFactory.getBean(partnerId);

		paymentLog.info("invoice[" + invoice + "], tradeId[" + tradeId + "], paidFee[" + paidFee + "], tradeStatus[" + tradeStatus + "], payerId[" + payerId + "], sign[" + sign + "], tradeName[" + tradeName
				+ "], notifyDatetime[" + notifyDatetime + "]");

		String retVal = "NOT_OK";

		Map<String, String> map = new HashMap<String, String>();
		map.put("invoice", invoice);
		map.put("tradeId", tradeId);
		map.put("paidFee", paidFee);
		map.put("tradeStatus", tradeStatus);
		map.put("payerId", payerId);
		map.put("appId", appId);
		map.put("reqFee", reqFee);
		map.put("tradeName", tradeName);
		map.put("notifyDatetime", notifyDatetime);

		if (Md5SignUtil.doCheck(map, sign, ClientConfig.getProperty("secertKey"))) {

			boolean success = false;
			if (StringUtils.equalsIgnoreCase(tradeStatus, "TRADE_SUCCESS")) {
				success = true;
			}

			BigDecimal finishAmount = new BigDecimal(paidFee);

			if (ps.doPayment(tradeId, payerId, success, invoice, finishAmount, map)) {
				retVal = "OK";
			}

		} else {
			retVal = "ILLEGAL_SIGN";
		}

		ModelAndView modelView = new ModelAndView("empty", "output", retVal);

		return modelView;
	}

}
