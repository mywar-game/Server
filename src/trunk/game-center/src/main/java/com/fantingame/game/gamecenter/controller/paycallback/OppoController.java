package com.fantingame.game.gamecenter.controller.paycallback;

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
import com.fantingame.game.gamecenter.partener.oppo.OppoPaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class OppoController {
	private static Logger LOG = Logger.getLogger(OppoController.class);

	@Autowired
	private PartnerServiceFactory serviceFactory;
	
	@RequestMapping(value = "/webApi/oppoPayment.do", method ={RequestMethod.POST})	
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.OPPO.getPartenerId());
		try {	
			String notifyId = req.getParameter("notifyId");
			String partnerOrder = req.getParameter("partnerOrder");
			String productName = req.getParameter("productName");
			String productDesc = req.getParameter("productDesc");
			String price = req.getParameter("price");
			String count = req.getParameter("count");
			String attach = req.getParameter("attach");
			String sign = req.getParameter("sign");
			if (StringUtils.isNotBlank(partnerOrder) 
					&& StringUtils.isNotBlank(price) && StringUtils.isNotBlank(sign)
					&& StringUtils.isNotBlank(count)) {
				OppoPaymentObj data = new OppoPaymentObj();
				data.setNotifyId(notifyId);
				data.setPartnerOrder(partnerOrder);
				data.setProductName(productName);
				data.setProductDesc(productDesc);
				data.setPrice(Integer.parseInt(price));
				data.setCount(Integer.parseInt(count));
				data.setAttach(attach);
				data.setSign(sign);
			
				res.setCharacterEncoding("UTF-8");
				res.setHeader("Content-type","text/html;charset=UTF-8");
				
				if (ps.doPayment(data)) {
					res.getWriter().print("result=OK&resultMsg=成功");
				} else {
					res.getWriter().print("result=FAIL&resultMsg=签名失败或订单信息不正确");
				}
			} else {
				res.getWriter().print("ERROR_FAIL");
			}			
		} catch (Exception e) {
			LOG.error("oppo payment error!", e);
		}
		
		return null;		
	}	
	
}
