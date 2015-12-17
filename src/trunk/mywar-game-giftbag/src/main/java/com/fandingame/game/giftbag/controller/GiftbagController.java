package com.fandingame.game.giftbag.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.fandingame.game.giftbag.constant.ServiceReturnCode;
import com.fandingame.game.giftbag.service.SystemGiftbagService;

/**
 * 礼包码服务器
 * 
 * @author yezp
 */
@Controller
public class GiftbagController {
	
	@Autowired
	private SystemGiftbagService systemGiftbagService;
	
	/**
	 * 检查礼包码
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/giftbagServer/checkGiftCode.do")
	public ModelAndView checkGiftCode(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String userId = req.getParameter("userId");
		String levelStr = req.getParameter("level");
		String vipLevelStr = req.getParameter("vipLevel");
		String serverId = req.getParameter("serverId");
		String code = req.getParameter("code");

		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isBlank(userId) || StringUtils.isBlank(levelStr) || StringUtils.isBlank(code)
				|| StringUtils.isBlank(vipLevelStr) || StringUtils.isBlank(serverId)) {
			map.put("rc", ServiceReturnCode.PARAM_ERROR);
			view.setAttributesMap(map);
			modelView.setView(view);
			return modelView;
		}
		
		int level = Integer.parseInt(levelStr);
		int vipLevel = Integer.parseInt(vipLevelStr);
		map = systemGiftbagService.checkGiftCode(userId, code, level, vipLevel, serverId);
		
		view.setAttributesMap(map);
		modelView.setView(view);		
		return modelView;		
	}
	
	@RequestMapping("/giftbagServer/recordGiftCodeLog.do")
	public ModelAndView recordGiftCodeLog(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String userId = req.getParameter("userId");
		String qn = req.getParameter("qn");
		String partnerId = req.getParameter("partnerId");
		String giftbagIdStr = req.getParameter("giftbagId");
		String serverId = req.getParameter("serverId");
		String code = req.getParameter("code");

		Map<String, Object> map = new HashMap<String, Object>();
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		if (StringUtils.isBlank(userId) || StringUtils.isBlank(qn) || StringUtils.isBlank(code)
				|| StringUtils.isBlank(partnerId) || StringUtils.isBlank(serverId) || StringUtils.isBlank(giftbagIdStr)) {
			map.put("rc", ServiceReturnCode.PARAM_ERROR);
			
			view.setAttributesMap(map);
			modelView.setView(view);
			return modelView;
		}
		
		int giftbagId = Integer.parseInt(giftbagIdStr);
		map = systemGiftbagService.recordGiftCodeLog(userId, code, giftbagId, partnerId, qn, serverId);
		view.setAttributesMap(map);
		modelView.setView(view);		
		return modelView;
	}
}
