package com.fandingame.game.version.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.version.bo.PackageInfoBO;
import com.fandingame.game.version.service.PackageService;

/**
 * 游戏服务器WEB
 * 
 * @author dogdog
 * 
 */
@Controller
public class PackageController {
	private static Logger LOG = Logger.getLogger(PackageController.class);
	
	@RequestMapping("/webApi/checkVersion.do")
	public ModelAndView checkVersion(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> dt = new HashMap<String, Object>();
		String version = req.getParameter("version");
		String partnerId = req.getParameter("partnerId");
		String qn = req.getParameter("qn");
		if (StringUtils.isEmpty(qn)) {
			qn = "0";
		}
		String fr = req.getParameter("fr");
		String mac = req.getParameter("mac");
		String ip = req.getRemoteAddr();
		PackageService ps = ServiceCacheFactory.getServiceCache().getService(PackageService.class);
		LOG.info("version Check version: " + version + "partnerId:" + partnerId + " qn:" + qn);
		PackageInfoBO bo = ps.checkVersion(version,fr,mac,ip,partnerId,qn);
		dt.put("ver", bo.getVersion());
		dt.put("desc", bo.getDescribe());
		dt.put("resUrl", bo.getResUrl());
		dt.put("apkUrl", bo.getApkUrl());
//		String apkUrl = "";
//		if (StringUtils.isNotBlank(bo.getApkUrl())) {
//			apkUrl = bo.getApkUrl().replace("${qn}", qn) + "?" + UUID.randomUUID().toString();
//			dt.put("apkUrl", apkUrl);
//		}else{
//			dt.put("apkUrl", "");
//		}
		LOG.info("version : " + bo.getVersion() + "apkUrl :" + bo.getApkUrl() + " resUrl : " + bo.getResUrl());
		Map<String, Object> dtt = new HashMap<String, Object>();
		dtt.put("dt", dt);
		map.put("dt", dtt);
		map.put("rc", 1000);
		ModelAndView modelView = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		modelView.setView(view);
		return modelView;
	}
}
