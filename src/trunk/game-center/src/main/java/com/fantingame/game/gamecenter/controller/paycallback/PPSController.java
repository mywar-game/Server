package com.fantingame.game.gamecenter.controller.paycallback;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.alibaba.fastjson.JSON;
import com.fantingame.game.gamecenter.constant.PartenerEnum;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.pps.PPSPaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;
import com.fantingame.game.gamecenter.util.json.Json;

@Controller
public class PPSController {
	private static Logger LOG = Logger.getLogger(PPSController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;
	
	@RequestMapping(value = "/webApi/ppsPayment.do")
	public ModelAndView ppsPayment(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		PPSPaymentObj paymentObj = new PPSPaymentObj();
		paymentObj.setUser_id(req.getParameter("user_id"));
		paymentObj.setOrder_id(req.getParameter("order_id"));
		paymentObj.setMoney(req.getParameter("money"));
		paymentObj.setRole_id(req.getParameter("role_id"));
		paymentObj.setSign(req.getParameter("sign"));
		paymentObj.setTime(req.getParameter("time"));
		paymentObj.setUserData(req.getParameter("userData"));
		LOG.info("PPS payment:"+JSON.toJSONString(paymentObj));
		if(StringUtils.isNotBlank(paymentObj.getUser_id())
				&& StringUtils.isNotBlank(paymentObj.getMoney())
				&& StringUtils.isNotBlank(paymentObj.getSign())){
			PartnerService ps = serviceFactory.getBean(PartenerEnum.Pps.getPartenerId());
			if(ps.doPayment(paymentObj)){
				map.put("result",0);
				map.put("message","成功");
			}else{
				map.put("result",-1);
				map.put("message","sign加密出错");
			}
			
		}else{
			map.put("result",-2);
			map.put("message","参数错误");
		}
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		LOG.debug(Json.toJson(map));
		return modelView;
	}
}
