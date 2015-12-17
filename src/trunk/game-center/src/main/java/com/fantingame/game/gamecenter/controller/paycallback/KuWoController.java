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
import com.fantingame.game.gamecenter.partener.kuwo.KuWoPaymentObj;
import com.fantingame.game.gamecenter.partener.kuwo.KuWoSdk;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class KuWoController {
	private static Logger LOG = Logger.getLogger(KuWoController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;
	
	@RequestMapping(value = "/webApi/kuwoPayment.do")
	public ModelAndView ppsPayment(HttpServletRequest req,HttpServletResponse res){
		KuWoPaymentObj paymentObj = new KuWoPaymentObj();
		paymentObj.setServerid(req.getParameter("serverid"));
		paymentObj.setUserid(req.getParameter("userid"));
		paymentObj.setOrderid(req.getParameter("orderid"));
		paymentObj.setAmount(req.getParameter("amount"));
		paymentObj.setSign(req.getParameter("sign"));
		paymentObj.setTime(req.getParameter("time"));
		paymentObj.setExt1(req.getParameter("ext1"));
		paymentObj.setExt2(req.getParameter("ext2"));
//		paymentObj.setServerid("2");
//		paymentObj.setUserid("127634795");
//		paymentObj.setOrderid("20131206Sghhr00052");
//		paymentObj.setAmount("5");
//		paymentObj.setSign("78F1881802EF60E6FE71D687E7AEE364");
//		paymentObj.setTime("1386301620551");
//		paymentObj.setExt1("0110222013120600000008");
//		paymentObj.setExt2("");
		LOG.info("kuwo payment:"+JSON.toJSONString(paymentObj));
		try {
			if(StringUtils.isNotBlank(paymentObj.getUserid())
					&& StringUtils.isNotBlank(paymentObj.getAmount())
					&& StringUtils.isNotBlank(paymentObj.getSign())
					&& StringUtils.isNotBlank(paymentObj.getExt1())){
				PartnerService ps = serviceFactory.getBean(PartenerEnum.KuWo.getPartenerId());
				if(ps.doPayment(paymentObj)){
					res.getOutputStream().println("0");
				}else{
					res.getOutputStream().println("-1");
				}
				
			}else{
				res.getOutputStream().println("-2");
			}
		} catch (Exception e) {
			LOG.error("KuWo check pay error!",e);
		}
		return null;
	}
}
