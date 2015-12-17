package com.fantingame.game.gamecenter.controller.paycallback;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.json.MappingJacksonJsonView;


import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.xiaomi.XiaomiPaymentObj;
import com.fantingame.game.gamecenter.partener.xiaomi.XiaomiPaymentObjNew;
import com.fantingame.game.gamecenter.partener.xiaomi.XiaomiSdk;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

import org.apache.log4j.Logger;

@Controller
public class XiaomiController {
	private static Logger LOG = Logger.getLogger(XiaomiController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/xiaomiPayment.do", method = RequestMethod.GET)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.XiaoMi.getPartenerId());
		String partnerGiftConsume = req.getParameter("partnerGiftConsume");
		if (partnerGiftConsume == null) {
			// 没有用礼券购买
			XiaomiPaymentObj data = new XiaomiPaymentObj();

			data.setAppId(req.getParameter("appId"));
			data.setCpOrderId(req.getParameter("cpOrderId"));
			data.setCpUserInfo(req.getParameter("cpUserInfo"));
			data.setOrderId(req.getParameter("orderId"));
			data.setOrderStatus(req.getParameter("orderStatus"));
			data.setPayFee(req.getParameter("payFee"));
			data.setPayTime(req.getParameter("payTime"));
			data.setProductCode(req.getParameter("productCode"));
			data.setProductCount(req.getParameter("productCount"));
			data.setProductName(req.getParameter("productName"));
			data.setSignature(req.getParameter("signature"));
			data.setUid(req.getParameter("uid"));
			
			try {
				if (!XiaomiSdk.instance().checkPayCallbackSign(data)) {
					LOG.error("签名不正确" + Json.toJson(data));
					res.getWriter().write("{\"errcode\":\"1525\"}");
					return null;
				}
				
				if (ps.doPayment(data)) {
					res.getWriter().write("{\"errcode\":\"200\"}");
				}else{
					res.getWriter().write("{\"errcode\":\"3515\"}");
				}
			} catch (Exception e) {
				LOG.error("XiaomiSdk doPayment error");
			}
			
		} else {
			
			// 使用了礼券
			XiaomiPaymentObjNew data = new XiaomiPaymentObjNew();

			data.setAppId(req.getParameter("appId"));
			data.setCpOrderId(req.getParameter("cpOrderId"));
			data.setCpUserInfo(req.getParameter("cpUserInfo"));
			data.setOrderId(req.getParameter("orderId"));
			data.setOrderStatus(req.getParameter("orderStatus"));
			data.setPayFee(req.getParameter("payFee"));
			data.setPayTime(req.getParameter("payTime"));
			data.setProductCode(req.getParameter("productCode"));
			data.setProductCount(req.getParameter("productCount"));
			data.setProductName(req.getParameter("productName"));
			data.setSignature(req.getParameter("signature"));
			data.setUid(req.getParameter("uid"));
			data.setPartnerGiftConsume(Long.valueOf(partnerGiftConsume)); // 礼券
			
			try {
				if (!XiaomiSdk.instance().checkPayCallbackSignNew(data)) {
					LOG.error("签名不正确" + Json.toJson(data));
					res.getWriter().write("{\"errcode\":\"1525\"}");
					return null;
				}
				
				if (ps.doPayment(data)) {
					res.getWriter().write("{\"errcode\":\"200\"}");
				} else {
					res.getWriter().write("{\"errcode\":\"3515\"}");
				}
			} catch (Exception e) {
				LOG.error("XiaomiSdk doPayment error");
			}
		}
		return null;
	}
}
