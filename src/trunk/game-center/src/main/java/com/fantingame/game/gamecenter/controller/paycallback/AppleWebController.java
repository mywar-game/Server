package com.fantingame.game.gamecenter.controller.paycallback;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
import com.fantingame.game.gamecenter.partener.fantin.service.Md5SignUtil;
import com.fantingame.game.gamecenter.service.PartnerService;

/**
 * 苹果网页版充值
 * 
 * @author yezp
 */
@Controller
public class AppleWebController {

	private static Logger LOG = Logger.getLogger(AppleWebController.class);

	@Autowired
	private PartnerServiceFactory serviceFactory;
	
	@RequestMapping(value = "/webApi/ftApplePayment.do")
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		
		String invoice = req.getParameter("invoice");
		String tradeId = req.getParameter("tradeId");
		String paidFee = req.getParameter("paidFee");
		String tradeStatus = req.getParameter("tradeStatus");
		String payerId = req.getParameter("payerId");
		String appId = req.getParameter("appId");
		String reqFee = req.getParameter("reqFee");
		String sign = req.getParameter("sign");
		String tradeName = req.getParameter("tradeName");
		String notifyDatetime = req.getParameter("notifyDatetime");
		String partnerId = PartenerEnum.AppStoreWeb.getPartenerId()+"";
		PartnerService ps = serviceFactory.getBean(partnerId);

		LOG.info("invoice[" + invoice + "], tradeId[" + tradeId + "], paidFee[" + paidFee + "], tradeStatus[" + tradeStatus + "], payerId[" + payerId + "], sign[" + sign + "], tradeName[" + tradeName
				+ "], notifyDatetime[" + notifyDatetime + "]");
		String retVal = "NOT_OK";
		Map<String, String> map = new HashMap<String, String>();
		map.put("invoice", invoice);
		map.put("tradeId", tradeId);
		map.put("paidFee", paidFee);
		map.put("tradeStatus", tradeStatus);
		map.put("payerId", payerId);
		map.put("appId", appId);
		map.put("reqFee", reqFee);
		map.put("tradeName", tradeName);
		map.put("notifyDatetime", notifyDatetime);
		if (Md5SignUtil.doCheck(map, sign, "FanDinSdk.instance().getPayKey()")) {
			boolean success = false;
			if (StringUtils.equalsIgnoreCase(tradeStatus, "TRADE_SUCCESS")) {
				success = true;
			}
            LOG.info("-------ftApplePayment-------68");
			BigDecimal finishAmount = new BigDecimal(paidFee);
			LOG.info("------ftApplePayment--------70");
			if (ps.doPayment(tradeId, payerId, success, invoice, finishAmount, map)) {
				retVal = "OK";
			}else{
				LOG.info("--------------ftApplePayment返回false");
			}
		} else {
			retVal = "ILLEGAL_SIGN";
		}
		try {
			res.getWriter().print(retVal);
		} catch (IOException e) {
			LOG.error(e);
		}
//		ModelAndView modelView = new ModelAndView("empty", "output", retVal);
		return null;
		
	}
	
}
