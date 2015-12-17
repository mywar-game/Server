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
import com.alibaba.fastjson.JSONObject;
import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.yuenan.YueNanPaymentObj;
import com.fantingame.game.gamecenter.partener.yuenan.YueNanSdk;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class YueNanAppleThirdController {
	private static final Logger LOG = Logger.getLogger(YueNanAppleThirdController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;
	
	private final static String URL = "https://pay.appota.com/payment/confirm?api_key="+YueNanSdk.instance().getAppKey()+"&lang=LANG";

	@RequestMapping(value = "/webApi/yueNanAppleThirdPayment.do")
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.YUENAN.getPartenerId());
		try {
			YueNanPaymentObj data = new YueNanPaymentObj();
			data.setStatus(req.getParameter("status"));
			data.setSandbox(req.getParameter("sandbox"));
			data.setTransactionId(req.getParameter("transaction_id"));
			data.setTransactionType(req.getParameter("transaction_type"));
			data.setAmount(req.getParameter("amount"));
			data.setCurrency(req.getParameter("currency"));
			data.setState(req.getParameter("state"));
			data.setTarget(req.getParameter("target"));
			data.setCountryCode(req.getParameter("country_code"));
			data.setHash(req.getParameter("hash"));
			if(StringUtils.isEmpty(data.getTransactionType())){
				res.getOutputStream().print("error");
				return null;
			}
			if("SMS".equals(data.getTransactionType())){
				data.setCode(req.getParameter("code"));
				data.setMessage(req.getParameter("message"));
				data.setPhone(req.getParameter("phone"));
			}else if("CARD".equals(data.getTransactionType())){
				data.setCardCode(req.getParameter("card_code"));
				data.setCardSerial(req.getParameter("card_serial"));
				data.setCardVendor(req.getParameter("card_vendor"));
			}else if("GOOGLE_PLAY".equals(data.getTransactionType())){
				data.setProductId(req.getParameter("productid"));
			}
			data.setApple(true);
			//String aa = "{\"amount\":\"10000\",\"cardCode\":\"75505043302607\",\"cardSerial\":\"BEA339493\",\"cardVendor\":\"VINAPHONE\",\"countryCode\":\"VN\",\"currency\":\"VND\",\"hash\":\"3b023510f4fbb14c9793195ab09304de\",\"sandbox\":\"0\",\"state\":\"0150012014051400000936\",\"status\":\"1\",\"target\":\"username:pichenpua|userid:169686\",\"transactionId\":\"645373191E4EBF9\",\"transactionType\":\"CARD\"}";
			LOG.info("YueNanPayment info:"+JSON.toJSONString(data));
			//data = JSONObject.parseObject(aa, YueNanPaymentObj.class);
			if(ps.doPayment(data)){
				res.getOutputStream().print("OK");
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("status",true);
//				JSONObject object = new JSONObject();
//				object.put("transaction_id",data.getTransactionId());
//				object.put("type",data.getTransactionType());
//				object.put("amount",data.getAmount());
//				object.put("currency",data.getCurrency());
//				object.put("country_code",data.getCountryCode());
//				object.put("target",data.getTarget());
//				object.put("state",data.getState());
//				object.put("time",new Date());
//				jsonObject.put("data", object);
//				Map<String,String> paraMap = new HashMap<String, String>();
//				paraMap.put("transaction_id",jsonObject.toJSONString());
//				UrlRequestUtils.execute(URL, paraMap, UrlRequestUtils.Mode.POST);
			}else{
				res.setStatus(501);
			}
		} catch (Exception e) {
			LOG.error("yuenan payment error!",e);
		}
		return null;
	}
}
