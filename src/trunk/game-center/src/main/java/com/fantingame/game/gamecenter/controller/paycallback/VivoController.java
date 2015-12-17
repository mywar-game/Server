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
import com.fantingame.game.gamecenter.partener.vivo.VivoPaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 步步高
 * 
 * @author yezp
 */
@Controller
public class VivoController {
	private static Logger LOG = Logger.getLogger(VivoController.class);

	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/vivoPayment.do")
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.VIVO.getPartenerId());
		try {
			String respCode = req.getParameter("respCode");
			String respMsg = req.getParameter("respMsg");
			String signMethod = req.getParameter("signMethod");
			String signature = req.getParameter("signature");
			String storeId = req.getParameter("storeId");
			String storeOrder = req.getParameter("storeOrder");
			String vivoOrder = req.getParameter("vivoOrder");
			String orderAmount = req.getParameter("orderAmount");
			String channel = req.getParameter("channel");
			String channelFee = req.getParameter("channelFee");
			if (StringUtils.isNotBlank(respCode) && StringUtils.isNotBlank(vivoOrder) 
					&& StringUtils.isNotBlank(orderAmount)) {
				VivoPaymentObj paymentObj = new VivoPaymentObj();
				paymentObj.setRespCode(respCode);
				paymentObj.setRespMsg(respMsg);
				paymentObj.setSignMethod(signMethod);
				paymentObj.setSignature(signature);
				paymentObj.setStoreId(storeId);
				paymentObj.setStoreOrder(storeOrder);
				paymentObj.setVivoOrder(vivoOrder);
				paymentObj.setOrderAmount(orderAmount);
				paymentObj.setChannel(channel);
				paymentObj.setChannelFee(channelFee);
				
				LOG.error("Vivo payment callback:" + Json.toJson(paymentObj));
				if (ps.doPayment(paymentObj)) {
					res.getWriter().print("SUCCESS");
				} else if(respCode.equals("0000")) {
					res.getWriter().print("SUCCESS");
				} else {
					res.getWriter().print("ERROR_FAIL");
				}				
			} else{
				res.getWriter().print("ERROR_FAIL");
			}		
		} catch (Exception e) {
			LOG.error("vivo payment error!", e);
		}
		return null;
	}
	
}
