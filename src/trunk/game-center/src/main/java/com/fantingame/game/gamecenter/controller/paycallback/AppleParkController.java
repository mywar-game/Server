package com.fantingame.game.gamecenter.controller.paycallback;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.apple.AppleParkSdk;
import com.fantingame.game.gamecenter.partener.ppassistant.PPAssistantPaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

@Controller
public class AppleParkController {
	private static Logger LOG = Logger.getLogger(AppleParkController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/appleParkPayment.do")
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.ApplePark.getPartenerId());
		PPAssistantPaymentObj data = new PPAssistantPaymentObj();
		try {
			data.setAccount(URLDecoder.decode(req.getParameter("account"),"utf-8"));
			data.setAmount(URLDecoder.decode(req.getParameter("amount"),"utf-8"));
			data.setApp_id(URLDecoder.decode(req.getParameter("app_id"),"utf-8"));
			data.setBillno(URLDecoder.decode(req.getParameter("billno"),"utf-8"));
			data.setOrder_id(URLDecoder.decode(req.getParameter("order_id"),"utf-8"));
			data.setRoleid(URLDecoder.decode(req.getParameter("roleid"),"utf-8"));
			data.setSign(URLDecoder.decode(req.getParameter("sign"),"utf-8"));
			data.setStatus(URLDecoder.decode(req.getParameter("status"),"utf-8"));
			data.setUuid(URLDecoder.decode(req.getParameter("uuid"),"utf-8"));
			data.setZone(URLDecoder.decode(req.getParameter("zone"),"utf-8"));
			
//			data.setAccount("0120042014010300000075");
//			data.setAmount("5.00");
//			data.setApp_id("2359");
//			data.setBillno("0120042014010300000075");
//			data.setOrder_id("2014010337073143");
//			data.setRoleid("0");
//			data.setSign("0aaf61b9608fa7abf660acf8f98a5a1d");
//			data.setStatus("0");
//			data.setUuid("");
//			data.setZone("6729");
			LOG.info("ApplePark PaymentObj info:"+Json.toJson(data));
			if(ps.doPayment(data)){
				res.getWriter().print("success");
			}else{
				res.getWriter().print("fail");
			}
		} catch (Exception e) {
			LOG.error("PPAssistant payment error!",e);
		}
		return null;
	}
}
