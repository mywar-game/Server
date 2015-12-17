package com.fantingame.game.gamecenter.controller.paycallback.yxcq;

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
import com.fantingame.game.gamecenter.partener.yxcq.duoku.YXCQDuoKuSdk;
import com.fantingame.game.gamecenter.partener.yxcq.duoku.DuokuPaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

@Controller
public class YXCQDuokuController {
	private static Logger LOG = Logger.getLogger(YXCQDuokuController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/yxcqduoKuPayment.do")
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.DuoKu.getPartenerId());
		try {
			String cardtype = req.getParameter("cardtype");
			String amount = req.getParameter("amount");
			String orderid = req.getParameter("orderid");
			String result = req.getParameter("result");
			String aid = req.getParameter("aid");
			String client_secret = req.getParameter("client_secret");
			String timetamp = req.getParameter("timetamp");
			if(StringUtils.isNotBlank(orderid)
					&& StringUtils.isNotBlank(amount)
					&& StringUtils.isNotBlank(client_secret)){
				DuokuPaymentObj data = new DuokuPaymentObj();
				data.setAmount(amount);
				data.setAid(aid);
				data.setClient_secret(client_secret);
				data.setOrderid(orderid);
				data.setResult(result);
				data.setTimetamp(timetamp);
				data.setCardtype(cardtype);
				
				if (!YXCQDuoKuSdk.instance().checkPayCallbackSign(data)) {
					LOG.error("签名不正确" + Json.toJson(data));
					res.getWriter().print("ERROR_SIGN");
					return null;
				} 
				
				if (ps.doPayment(data)) {
					res.getWriter().print("SUCCESS");
				} else {
					res.getWriter().print("ERROR_FAIL");
				}
			} else {
				res.getWriter().print("ERROR_SIGN");
			}
		} catch (Exception e) {
			LOG.error("duoku payment error!",e);
		}
		return null;
	}
}
