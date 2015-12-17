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
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.mobilemm.MobileMMPaymentObj;
import com.fantingame.game.gamecenter.partener.mobilemm.MobileMMSdk;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class MobileMMController {
	private static Logger LOG = Logger.getLogger(MobileMMController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/mobileMMPayment.do")
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		try {
			PartnerService ps = serviceFactory.getBean(MobileMMSdk.instance().getPartnerId());
			MobileMMPaymentObj data = new MobileMMPaymentObj();
			data.setActionID(req.getParameter("ActionID"));
			data.setActionTime(req.getParameter("ActionTime"));
			data.setAppID(req.getParameter("AppID"));
			data.setChannelID(req.getParameter("ChannelID"));
			data.setCheckID(req.getParameter("CheckID"));
			data.setDestAddress(req.getParameter("Dest_Address"));
			data.setExData(req.getParameter("ExData"));
			data.setFeeMsisdn(req.getParameter("FeeMSISDN"));
			data.setMd5Sign(req.getParameter("MD5Sign"));
			data.setMsgType(req.getParameter("MsgType"));
			data.setMsisdn(req.getParameter("MSISDN"));
			data.setOrderID(req.getParameter("OrderID"));
			data.setOrderType(req.getParameter("OrderType"));
			data.setPayCode(req.getParameter("PayCode"));
			data.setPrice(req.getParameter("Price"));
			data.setSendAddress(req.getParameter("Send_Address"));
			data.setSubsNumb(req.getParameter("SubsNumb"));
			data.setSubsSeq(req.getParameter("SubsSeq"));
			data.setTotalPrice(req.getParameter("TotalPrice"));
			data.setTradeID(req.getParameter("TradeID"));
			data.setVersion(req.getParameter("Version"));
			LOG.info("mobileMM info:"+JSON.toJSONString(data));
			if (StringUtils.isNotEmpty(data.getOrderID())
					&& StringUtils.isNotEmpty(data.getMd5Sign())&&
					ps.doPayment(data)) {
				res.getWriter().print("0");
			}else{
				res.getWriter().print("1");
			}
		} catch (Exception e) {
			LOG.error("anzhi payment error!",e);
		}
		return null;
	}
}
