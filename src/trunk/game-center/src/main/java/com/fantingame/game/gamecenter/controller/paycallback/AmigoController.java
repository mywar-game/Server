package com.fantingame.game.gamecenter.controller.paycallback;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.amigo.AmigoPaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 金立
 * 
 * @author yezp
 */
@Controller
public class AmigoController {

	private static Logger LOG = Logger.getLogger(AmigoController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/amigoPayment.do", method = RequestMethod.POST)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.Amigo.getPartenerId());
		AmigoPaymentObj paymentObj = new AmigoPaymentObj();
		
		res.setContentType("text/html;charset=UTF-8");
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			LOG.error(e1);
		}
		
		paymentObj.setApiKey(req.getParameter("api_key"));
		paymentObj.setCloseTime(req.getParameter("close_time"));
		paymentObj.setCreateTime(req.getParameter("create_time"));
		paymentObj.setDealPrice(req.getParameter("deal_price"));
		paymentObj.setOutOrderNo(req.getParameter("out_order_no"));
		paymentObj.setPayChannel(req.getParameter("pay_channel"));
		paymentObj.setSubmitTime(req.getParameter("submit_time"));
		paymentObj.setUserId(req.getParameter("user_id"));
		paymentObj.setSign(req.getParameter("sign"));
		
		try {
			LOG.error("Amigo payment callback:" + Json.toJson(paymentObj));
			if (ps.doPayment(paymentObj)) {
				res.getWriter().write("success");
			} else {
				res.getWriter().write("fail");
			}			
		} catch (Exception e) {
			LOG.error("AmigoSdk doPayment error");
		}
		
		return null;
	}
	
}
