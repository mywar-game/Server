package com.fantingame.game.gamecenter.controller.paycallback.yxcq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.constant.WebApiCode;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.yxcq.qihoo.PayCallbackObject;
import com.fantingame.game.gamecenter.partener.yxcq.qihoo.YXCQQiHooSdk;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

@Controller
public class YXCQQiHooController {
	private static Logger LOG = Logger.getLogger(YXCQQiHooController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;
	
	@RequestMapping(value = "/webApi/yxcqqhRefreshToken.do", method = RequestMethod.GET)
	public ModelAndView qhRefreshToken(HttpServletRequest req){
		String accessToken = req.getParameter("accessToken");
		String refreshToken = req.getParameter("refreshToken");
		Map<String, String> ret = YXCQQiHooSdk.instance().refreshToken(accessToken, refreshToken);
		Map<String, Object> map = new HashMap<String, Object>();
		if(ret != null && ret.containsKey("access_token")){
			map.put("rc", WebApiCode.SUCCESS);
			Map<String, String> data = new HashMap<String, String>();
			data.put("ptk", ret.get("access_token"));
			data.put("rtk", ret.get("refresh_token"));
			map.put("dt", data);
		}else{
			LOG.info("刷新token失败");
			map.put("rc", WebApiCode.UNKNOWN_ERROR);
		}
		
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		LOG.debug(Json.toJson(map));
		return modelView;
	}
	
	@RequestMapping(value = "/webApi/yxcqqhPayment.do", method ={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView qihooCallBack(HttpServletRequest req, HttpServletResponse res) {
		PartnerService ps = serviceFactory.getBean(PartenerEnum.YXCQ_QiHoo.getPartenerId());
		PayCallbackObject cb = new PayCallbackObject();
		cb.setApp_key(req.getParameter("app_key"));
		//针对360以分为单位处理,除以100,得出实际以元为单位的数额
		cb.setAmount(req.getParameter("amount"));
		cb.setApp_ext1(req.getParameter("app_ext1"));
		cb.setApp_ext2(req.getParameter("app_ext2"));
		cb.setApp_order_id(req.getParameter("app_order_id"));
		cb.setApp_uid(req.getParameter("app_uid"));
		cb.setGateway_flag(req.getParameter("gateway_flag"));
		cb.setOrder_id(req.getParameter("order_id"));
		cb.setProduct_id(req.getParameter("product_id"));
		cb.setSign(req.getParameter("sign"));
		cb.setSign_return(req.getParameter("sign_return"));
		cb.setSign_type(req.getParameter("sign_type"));
		cb.setUser_id(req.getParameter("user_id"));
		
		String retVal = "fail";
			
		try {
			if(ps.doPayment(cb)){
				retVal = "ok";
			}
			res.getWriter().write(retVal);
		} catch (IOException e) {
			LOG.error(e);
		}
		return null;
	}
}
