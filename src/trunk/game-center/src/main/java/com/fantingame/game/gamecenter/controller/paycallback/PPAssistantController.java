package com.fantingame.game.gamecenter.controller.paycallback;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.ppassistant.PPAssistantPaymentObj;
import com.fantingame.game.gamecenter.partener.ppassistant.PPAssistantRSAEncrypt;
import com.fantingame.game.gamecenter.partener.ppassistant.PPAssistantSdk;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

@Controller
public class PPAssistantController {
	private static Logger LOG = Logger.getLogger(PPAssistantController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/25ppPayment.do")
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.PpAssistant.getPartenerId());
		PPAssistantPaymentObj data = new PPAssistantPaymentObj();
		try {
			
			data.setAccount(URLDecoder.decode(req.getParameter("account"),"utf-8"));
			data.setAmount(URLDecoder.decode(req.getParameter("amount"),"utf-8"));
			data.setApp_id(URLDecoder.decode(req.getParameter("app_id"),"utf-8"));
			data.setBillno(URLDecoder.decode(req.getParameter("billno"),"utf-8"));
			data.setOrder_id(URLDecoder.decode(req.getParameter("order_id"),"utf-8"));
			data.setRoleid(URLDecoder.decode(req.getParameter("roleid"),"utf-8"));
//			LOG.info("---pp pay decode before --"+req.getParameter("sign"));
//			LOG.info("---pp pay after decode after --"+URLDecoder.decode(req.getParameter("sign"),"utf-8"));
			data.setStatus(URLDecoder.decode(req.getParameter("status"),"utf-8"));
			data.setUuid(URLDecoder.decode(req.getParameter("uuid"),"utf-8"));
			data.setZone(URLDecoder.decode(req.getParameter("zone"),"utf-8"));
			
			String sign = req.getParameter("sign");
//			data.setAccount("0120042014010300000075");
//			data.setAmount("5.00");
//			data.setApp_id("2359");
//			data.setBillno("0120042014010300000075");
//			data.setOrder_id("2014010337073143");
//			data.setRoleid("0");
//			data.setSign("0aaf61b9608fa7abf660acf8f98a5a1d");
//			data.setStatus("0");
//			data.setUuid("");
//			data.setZone("6729");
			LOG.info("PPAssistantPaymentObj info:"+Json.toJson(data));
            PPAssistantRSAEncrypt rsaEncrypt= new PPAssistantRSAEncrypt();  
            //加载公钥   
            rsaEncrypt.loadPublicKey(PPAssistantSdk.instance().getPayKey());  
            byte[] dcDataStr = Base64.decodeBase64(sign);
            byte[] plainData = rsaEncrypt.decrypt(rsaEncrypt.getPublicKey(), dcDataStr);  
            String checkString =  new String(plainData,"utf-8");
//            LOG.info("解密后的string"+checkString);
            @SuppressWarnings("unchecked")
			Map<String,Object> jsonMap = Json.toObject(checkString,Map.class);
            
            String account = (String)jsonMap.get("account");
            String amount = (String)jsonMap.get("amount");
            String app_id = (String)jsonMap.get("app_id");
            String billno = (String)jsonMap.get("billno");
            String order_id = (String)jsonMap.get("order_id");
            String roleid = (String)jsonMap.get("roleid");
            String status = ((Integer)jsonMap.get("status"))+"";
            String uuid = (String)jsonMap.get("uuid");
            String zone = (String)jsonMap.get("zone");
//            LOG.info("值"+account+"="+amount+"="+app_id+"="+billno+"="+order_id+"="+roleid+"="+status+"="+uuid+"="+zone);
			if (account.equals(data.getAccount())
					&& amount.equals(data.getAmount())
					&& app_id.equals(data.getApp_id())
					&& billno.equals(data.getBillno())
					&& order_id.equals(data.getOrder_id())
					&& roleid.equals(data.getRoleid())
					&& status.equals(data.getStatus())
					&& uuid.equals(data.getUuid())
					&& zone.equals(data.getZone())) {
				if(ps.doPayment(data)){
					res.getWriter().print("success");
				}else{
					res.getWriter().print("fail");
				}
			}else{
				LOG.error("PPAssistant payment 校验失败！");
				res.getWriter().print("fail");
				return null;
			}
		} catch (Exception e) {
			LOG.error("PPAssistant payment error!",e);
		}
		return null;
	}
}
