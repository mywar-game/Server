package com.fantingame.game.gamecenter.controller.paycallback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.itools.ItoolsIOSSdk;
import com.fantingame.game.gamecenter.partener.itools.ItoolsPaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class ItoolsIOSController {
	private static Logger LOG = Logger.getLogger(ItoolsIOSController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/itoolsPayment.do")
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.Itools.getPartenerId());
		String notifyData=req.getParameter("notify_data");
		String sign=req.getParameter("sign");
		try {
			if(StringUtils.isNotBlank(notifyData) && StringUtils.isNotBlank(sign)){
				ItoolsPaymentObj data = new ItoolsPaymentObj();
				data.setNotify_data(notifyData);
				data.setSign(sign);
				LOG.info("ItoolsIOS payment info:"+JSON.toJSONString(data));
				if (ps.doPayment(data)) {
					res.getWriter().print("success");
				}else{
					res.getWriter().print("fail");
				}
			}else{
				res.getWriter().print("fail");
			}
		} catch (Exception e) {
			LOG.error("iTools payment error!",e);
		}
		return null;
	}
}
