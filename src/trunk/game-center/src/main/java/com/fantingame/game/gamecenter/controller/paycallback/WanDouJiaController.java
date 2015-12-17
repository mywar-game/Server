package com.fantingame.game.gamecenter.controller.paycallback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.wandoujia.WDJPaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

@Controller
public class WanDouJiaController {
	private static Logger LOG = Logger.getLogger(WanDouJiaController.class);

	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/wanDouJiaPayment.do")
	public ModelAndView payCallback(HttpServletRequest req,
			HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.WanDouJia
				.getPartenerId());
		try {
			String content = req.getParameter("content");
			String signType = req.getParameter("signType");
			String sign = req.getParameter("sign");

			LOG.info("Wandoujia content:" + content);
			LOG.info("Wandoujia signType:" + signType);
			LOG.info("Wandoujia sign:" + sign);
			if (StringUtils.isNotBlank(content)
					&& StringUtils.isNotBlank(signType)
					&& StringUtils.isNotBlank(sign)) {
				WDJPaymentObj paymentObj = Json.toObject(content, WDJPaymentObj.class);

				paymentObj.setSign(sign);
				paymentObj.setContent(content);

				if (ps.doPayment(paymentObj)) {
					res.getWriter().print("success");
				} else {
					res.getWriter().print("fail");
				}
			}

		} catch (Exception e) {
			LOG.error("WanDouJia payment error!", e);
		}

		return null;
	}

}
