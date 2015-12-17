package com.fantingame.game.gamecenter.controller.paycallback;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.meizu.MeizuPaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;

/**
 * 魅族支付回调
 * @author Administrator
 *
 */
@Controller
public class MeizuController {

	private static Logger LOG = Logger.getLogger(LenovoController.class);
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/meizuPayment.do", method = RequestMethod.POST)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		
		PartnerService ps = serviceFactory.getBean(PartenerEnum.Meizu.getPartenerId());
		
		res.setContentType("text/html;charset=UTF-8");
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			LOG.error(e1);
		}
		
		String username = req.getParameter("username");
		String change_id = req.getParameter("change_id");
		String money = req.getParameter("money");
		String hash = req.getParameter("hash");
		String object = req.getParameter("object");
		
		MeizuPaymentObj paymentObject = new MeizuPaymentObj();
		paymentObject.setUsername(username);
		paymentObject.setChange_id(change_id);
		paymentObject.setMoney(money);
		paymentObject.setHash(hash);
		paymentObject.setObject(object);
		
		LOG.info("MeizuController username = " + username + " change_id = " + change_id + " money = " + money + " hash = " + hash + " object = " + object);
		try {
			if (!StringUtils.isBlank(username) && !StringUtils.isBlank(change_id) && !StringUtils.isBlank(money) && !StringUtils.isBlank(hash)) {		
				if (ps.doPayment(paymentObject)) {
					res.getWriter().write("1");
				} else {
					res.getWriter().write("0");
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}
}
