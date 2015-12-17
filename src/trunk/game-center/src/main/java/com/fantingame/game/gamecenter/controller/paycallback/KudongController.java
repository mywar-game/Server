package com.fantingame.game.gamecenter.controller.paycallback;

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
import com.fantingame.game.gamecenter.partener.kudong.KudongPaymentObj;
import com.fantingame.game.gamecenter.partener.kudong.KudongSdk;
import com.fantingame.game.gamecenter.service.PartnerService;

/**
 * 酷动
 * @author Administrator
 *
 */
@Controller
public class KudongController {

	private static Logger LOG = Logger.getLogger(KudongController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/kudongPayment.do", method = RequestMethod.POST)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		
		PartnerService ps = serviceFactory.getBean(PartenerEnum.Kudong.getPartenerId());
		
		KudongPaymentObj obj = new KudongPaymentObj();
		obj.setUid(req.getParameter("uid"));
		obj.setOid(req.getParameter("oid"));
		obj.setGold(req.getParameter("gold"));
		obj.setSid(req.getParameter("sid"));
		obj.setTime(req.getParameter("time"));
		obj.setEif(req.getParameter("eif"));
		obj.setSign(req.getParameter("sign"));
		
		LOG.info(obj);
		
		if (!KudongSdk.instance().verifyPay(obj)) {
			LOG.info("KudongController payCallback failed");
			return null;
		}
		try {
			if (ps.doPayment(obj)) {
				res.getWriter().write("{\"error_code\":\"0\",\"error_message\":\"\"}");
			} else {
				res.getWriter().write("{\"error_code\":\"1\",\"resmsg\":\"支付失败\"}");
			}
		} catch (Exception e) {
			LOG.error("KudongController doPayment error");
		}
		
		return null;
	}
}
