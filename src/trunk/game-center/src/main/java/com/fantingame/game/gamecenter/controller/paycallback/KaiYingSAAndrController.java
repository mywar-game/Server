package com.fantingame.game.gamecenter.controller.paycallback;

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
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.kaiying.KaiYingTwPaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class KaiYingSAAndrController {
	private static Logger LOG = Logger.getLogger(KaiYingSAAndrController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/kaiYingSAAndrPayment.do", method = RequestMethod.POST)
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		PartnerService ps = serviceFactory.getBean(PartenerEnum.KaiYingSAAndr.getPartenerId());
		KaiYingTwPaymentObj data = new KaiYingTwPaymentObj();
		data.setTs(req.getParameter("ts"));
		data.setSig(req.getParameter("sig"));
		data.setKda(req.getParameter("kda"));
		data.setUserId(req.getParameter("user_id"));
		data.setSid(req.getParameter("sid"));
		data.setNumber(req.getParameter("number"));
		data.setAmount(req.getParameter("amount"));
		data.setRoleId(req.getParameter("role_id"));
		data.setPartnerOrderId(req.getParameter("order_id"));
		data.setActive1(req.getParameter("active1"));
		data.setActive2(req.getParameter("active2"));
		data.setPayRef(req.getParameter("pay_ref"));
		data.setAppExtra1(req.getParameter("app_extra1"));
		data.setAppExtra2(req.getParameter("app_extra2"));
		try {
			if(ps.doPayment(data)){
				map.put("ret", 0);
				map.put("msg", "充值成功");
			}else{
				map.put("ret", -1);
				map.put("msg", "充值失败,请查看日志");
			}
		} catch (Exception e) {
			LOG.error("kaiyingtw payment error!",e);
			map.put("ret", -1);
			map.put("msg", "游戏服出现异常");
		}
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		ModelAndView modelView = new ModelAndView();
		modelView.setView(view);
		return modelView;
	}
}
