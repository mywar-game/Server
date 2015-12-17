package com.fantingame.game.gamecenter.controller.paycallback;

import java.io.IOException;

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
import com.fantingame.game.gamecenter.partener.lenovo.TransData;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class LenovoController {

	private static Logger LOG = Logger.getLogger(LenovoController.class);
	
	private static final String SUCCESS = "SUCCESS";
	private static final String FAILURE = "FAILURE";
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/lenovoPayment.do", method = RequestMethod.POST)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.Lenovo.getPartenerId());
		String retVal = FAILURE;
		String transdata = req.getParameter("transdata");
		String sign = req.getParameter("sign");
		
		LOG.info("");
		if (!StringUtils.isBlank(transdata) && !StringUtils.isBlank(sign)) {
			TransData data = new TransData();
			data.setSign(sign);
			data.setTransData(transdata);
			if(ps.doPayment(data)){
				retVal = SUCCESS;
			}
		}
		
		try {
			res.getWriter().write("SUCCESS");
		} catch (IOException e) {
			//e.printStackTrace();
			LOG.error(e.toString());
		}
		//ModelAndView modelView = new ModelAndView("empty", "output", retVal);
		return null;
	}
}
