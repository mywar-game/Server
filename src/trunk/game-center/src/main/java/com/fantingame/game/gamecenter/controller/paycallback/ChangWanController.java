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
import com.fantingame.game.gamecenter.partener.changwan.ChangWanPaymentObj;
import com.fantingame.game.gamecenter.partener.changwan.ChangWanSdk;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

@Controller
public class ChangWanController {
	private static Logger LOG = Logger.getLogger(ChangWanController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/cwPayment.do", method = RequestMethod.GET)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.ChangWan.getPartenerId());
		ChangWanPaymentObj data = new ChangWanPaymentObj();
		data.setServerid(req.getParameter("serverid"));
		data.setAmount(req.getParameter("amount"));
		data.setCustominfo(req.getParameter("custominfo"));
		data.setErrdesc(req.getParameter("errdesc"));
		data.setOpenid(req.getParameter("openid"));
		data.setOrdernum(req.getParameter("ordernum"));
		data.setPaytime(req.getParameter("paytime"));
		data.setPaytype(req.getParameter("paytype"));
		data.setSign(req.getParameter("sign"));
		data.setStatus(req.getParameter("status"));
		LOG.info("changwan:"+Json.toJson(data));
		try {
			if(ps.doPayment(data)){
				res.getWriter().print("1");
			}else{
				res.getWriter().print("103");
			}
		} catch (Exception e) {
			LOG.error("anzhi payment error!",e);
		}
		return null;
	}
}
