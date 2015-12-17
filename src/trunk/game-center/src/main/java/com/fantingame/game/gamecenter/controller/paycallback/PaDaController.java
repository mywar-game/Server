package com.fantingame.game.gamecenter.controller.paycallback;

import java.io.IOException;

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
import com.fantingame.game.gamecenter.partener.pada.PadaPaymentObj;
import com.fantingame.game.gamecenter.partener.pada.PadaSdk;
import com.fantingame.game.gamecenter.service.PartnerService;

/**
 * 艺果
 * @author Administrator
 *
 */
@Controller
public class PaDaController {
	
	private static Logger LOG = Logger.getLogger(PaDaController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/padaPayment.do", method = RequestMethod.GET)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		
		PartnerService ps = serviceFactory.getBean(PartenerEnum.PaDa.getPartenerId());
		
//		Map<String, Object> map = new HashMap<String, Object>();
		
		PadaPaymentObj obj = new PadaPaymentObj();
		obj.setAppId(req.getParameter("appId"));
		obj.setCpOrderId(req.getParameter("cpOrderId"));
		obj.setCpExInfo(req.getParameter("cpExInfo"));
		obj.setRoleId(req.getParameter("roleId"));
		obj.setOrderId(req.getParameter("orderId"));
		obj.setOrderStatus(req.getParameter("orderStatus"));
		obj.setPayFee(req.getParameter("payFee"));
		obj.setProductCode(req.getParameter("productCode"));
		obj.setProductName(req.getParameter("productName"));
		obj.setProductCount(req.getParameter("productCount"));
		obj.setPayTime(req.getParameter("payTime"));
		obj.setSign(req.getParameter("sign"));
		
		LOG.info(obj.toString());
		
		if (!PadaSdk.instance().valifyPay(obj)) {
			LOG.error("PadaController doPayment error");
			try {
				res.getWriter().write("{\"rescode\":\"5\",\"resmsg\":\"支付失败\"}");
			} catch (IOException e) {
				LOG.error("PadaController doPayment error " + e);
			}
			return null;
		}
		
		try {
			if (ps.doPayment(obj)) {
				res.getWriter().write("{\"rescode\":\"0\",\"resmsg\":\"支付成功\"}");
			} else {
				res.getWriter().write("{\"rescode\":\"5\",\"resmsg\":\"支付失败\"}");
			}
		} catch (Exception e) {
			LOG.error("PadaController doPayment error");
		}
		return null;
	}
}
