package com.fantingame.game.gamecenter.controller.paycallback.yxcq;

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
import com.fantingame.game.gamecenter.partener.yxcq.legame.LegamePaymentObj;
import com.fantingame.game.gamecenter.service.PartnerService;

@Controller
public class LegameController {
	private static Logger LOG = Logger.getLogger(LegameController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;
	
	@RequestMapping(value = "/webApi/yxcqlegamePayment.do")
	public ModelAndView legamePayment(HttpServletRequest req, HttpServletResponse res) {
		
		LegamePaymentObj obj = new LegamePaymentObj();
		obj.setOrder_no(req.getParameter("order_no"));
		obj.setCreate_time(req.getParameter("create_time"));
		obj.setAmount(req.getParameter("amount"));
		obj.setStatus(req.getParameter("status"));
		obj.setSstatus(req.getParameter("sstatus"));
		obj.setCp_id(req.getParameter("cp_id"));
		obj.setGame_id(req.getParameter("game_id"));
		obj.setUid(req.getParameter("uid"));
		obj.setCp_ext(req.getParameter("cp_ext"));
		obj.setChecksign(req.getParameter("checksign"));
		
		LOG.info("Legame payment:" + JSON.toJSONString(obj));
		
		try {
			if (StringUtils.isNotBlank(obj.getStatus()) && obj.getStatus().equals("1")) {
				PartnerService ps = serviceFactory.getBean(PartenerEnum.YXCQ_Legame.getPartenerId());
				if (ps.doPayment(obj)) {
					res.getWriter().write("success");
				} else {
					res.getWriter().write("failure");
				}	
			} else {
				res.getWriter().write("failure");
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}
}
