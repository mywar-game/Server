package com.fantingame.game.gamecenter.controller.paycallback.yxcq;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.yxcq.xiaomi.XiaomiPaymentObj;
import com.fantingame.game.gamecenter.partener.yxcq.xiaomi.YXCQXiaomiSdk;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

import org.apache.log4j.Logger;

@Controller
public class YXCQXiaomiController {
	private static Logger LOG = Logger.getLogger(YXCQXiaomiController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/yxcqxiaomiPayment.do", method = RequestMethod.GET)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.YXCQ_XiaoMi.getPartenerId());
		XiaomiPaymentObj data = new XiaomiPaymentObj();

		data.setAppId(req.getParameter("appId"));
		data.setCpOrderId(req.getParameter("cpOrderId"));
		data.setCpUserInfo(req.getParameter("cpUserInfo"));
		data.setOrderId(req.getParameter("orderId"));
		data.setOrderStatus(req.getParameter("orderStatus"));
		data.setPayFee(req.getParameter("payFee"));
		data.setPayTime(req.getParameter("payTime"));
		data.setProductCode(req.getParameter("productCode"));
		data.setProductCount(req.getParameter("productCount"));
		data.setProductName(req.getParameter("productName"));
		data.setSignature(req.getParameter("signature"));
		data.setUid(req.getParameter("uid"));

		try {
			if (!YXCQXiaomiSdk.instance().checkPayCallbackSign(data)) {
				LOG.error("签名不正确" + Json.toJson(data));
				res.getWriter().write("{\"errcode\":\"1525\"}");
				// map.put("errcode", 200);
				return null;
			}
			
			if (ps.doPayment(data)) {
				res.getWriter().write("{\"errcode\":\"200\"}");
				// map.put("errcode", 200);
			}else{
				res.getWriter().write("{\"errcode\":\"3515\"}");
				// map.put("errcode", 3515);
			}
		} catch (Exception e) {
			LOG.error("XiaomiSdk doPayment error");
		}
		
//		MappingJacksonJsonView view = new MappingJacksonJsonView();
//		view.setAttributesMap(map);
//		ModelAndView modelView = new ModelAndView();
//		modelView.setView(view);
		return null;
	}
}
