package com.fantingame.game.gamecenter.controller.paycallback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.partener.chinamobile.ChinaMobileSdk;
import com.fantingame.game.gamecenter.partener.chinamobile.UserInfo;

@Controller
public class ChinaMobileLoginController {
	private static Logger LOG = Logger.getLogger(ChinaMobileLoginController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;

	@RequestMapping(value = "/webApi/chinaMobileLogin.do")
	public ModelAndView payCallback(HttpServletRequest req, HttpServletResponse res) {
		try {
			String userId = req.getParameter("userId");
			String key = req.getParameter("key");
			String channelId = req.getParameter("channelId");
			String region = req.getParameter("region");
			String cpparam = req.getParameter("cpparam");
			LOG.info("token:"+cpparam+",userId:"+userId+",key:"+key+",channelId:"+channelId+",region:"+region);
			if(StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(key)
					&& StringUtils.isNotBlank(channelId) && StringUtils.isNotBlank(region)
					&& StringUtils.isNotBlank(cpparam)){
				UserInfo userInfo = new UserInfo();
				userInfo.setChannelId(channelId);
				userInfo.setCpparam(cpparam);
				userInfo.setKey(key);
				userInfo.setRegion(region);
				userInfo.setUserId(userId);
				ChinaMobileSdk.instance().getUserMap().put(cpparam,userInfo);
				res.setContentType("text/plain; charset=UTF8\r");
				res.getWriter().println(0);
			}
		} catch (Exception e) {
			LOG.error("china mobile login error!",e);
		}
		return null;
	}
}
