package com.fantingame.game.gamecenter.controller.paycallback;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.dao.PaymentOrderDao;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.mzw.MzwPaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;

/**
 * 拇指玩支付回调接口
 * 
 * @author yezp
 */
@Controller
public class MzwController {

	private static Logger LOG = Logger.getLogger(MzwController.class);

	@Autowired
	private PartnerServiceFactory serviceFactory;
	
	@Autowired
	private PaymentOrderDao paymentOrderDao;
	
	@RequestMapping(value = "/webApi/mzwPayment.do", method = {RequestMethod.GET })
	public ModelAndView mzwCallBack(HttpServletRequest req,
			HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.Mzw.getPartenerId());
		MzwPaymentObj payment = new MzwPaymentObj();
		
		try {
			/*String code = req.getParameter("code");
			Object data = req.getAttribute("data");
			LOG.info("Mzw payment call back data :" + req.getAttribute("data"));
			LOG.info("Mzw payment call back data :" + req.getParameter("data"));*/
			String appkey = req.getParameter("appkey");
			String orderID = req.getParameter("orderID");
			String productName = req.getParameter("productName");
			String productDesc = req.getParameter("productDesc");
			String productID = req.getParameter("productID");
			String money = req.getParameter("money");
			String uid = req.getParameter("uid");
			String extern = req.getParameter("extern");
			String sign = req.getParameter("sign");
			payment = new MzwPaymentObj();
			
			payment.setAppkey(appkey);
			payment.setOrderID(orderID);
			
			String productNameDecode = productName;
			productNameDecode = new String(productNameDecode.getBytes("ISO_8859-1"));
			LOG.info("mzwController productName = " + productName);
			LOG.info("mzwController productNameDecode = " + productNameDecode);
			
			String productDescDecode = productDesc;
			productDescDecode = new String(productDescDecode.getBytes("ISO_8859-1"));
			LOG.info("mzwController productDesc = " + productDesc);
			LOG.info("mzwController productDescDecode = " + productDescDecode);
			
			payment.setProductName(productName);
			payment.setProductDesc(productDesc);
			
			payment.setProductID(productID);
			payment.setMoney(money);
			payment.setUid(uid);
			payment.setExtern(extern);
			payment.setSign(sign);
			
			if (!ps.doPayment(payment)) {
				LOG.error("Mzw payment ----- failed");
				return null;
			}
			
			res.getWriter().write("SUCCESS");
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
}
