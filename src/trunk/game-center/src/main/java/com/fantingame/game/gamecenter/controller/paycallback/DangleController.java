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
import com.fantingame.game.gamecenter.partener.dangle.DanglePaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class DangleController {
	private static Logger LOG = Logger.getLogger(DangleController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/dlPayment.do", method = RequestMethod.GET)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.NewDangLe.getPartenerId());
		DanglePaymentObj data = new DanglePaymentObj();
		data.setSignature(req.getParameter("signature"));
		data.setExt(req.getParameter("ext"));
		data.setMid(req.getParameter("mid"));
		data.setMoney(req.getParameter("money"));
		data.setOrder(req.getParameter("order"));
		data.setResult(req.getParameter("result"));
		data.setTime(req.getParameter("time"));
		LOG.info(data.getExt());
		try {
			if(ps.doPayment(data)){
				res.getWriter().print("success");
			}else{
				res.getWriter().print("error");
			}
		} catch (Exception e) {
			LOG.error("dangle payment error!",e);
		}
		return null;
	}
}
