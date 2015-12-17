package com.fantingame.game.gamecenter.controller.login;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.fantingame.game.gamecenter.dao.DomoUserDao;
import com.fantingame.game.gamecenter.dao.PartnerRateDao;
import com.fantingame.game.gamecenter.factory.PartnerServiceFactory;
import com.fantingame.game.gamecenter.model.DomoUser;
import com.fantingame.game.gamecenter.util.MD5;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;

@Controller
public class DomoController {
	private static Logger LOG = Logger.getLogger(DomoController.class);
	
	@Autowired
	private PartnerServiceFactory serviceFactory;
	@Autowired
	private DomoUserDao domoUserDao;
	@Autowired
	private PartnerRateDao partnerRateDao;

	@RequestMapping(value = "/webApi/domo.do", method = RequestMethod.GET)
	public ModelAndView domoSpread(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		String appId = req.getParameter("appId");
		String mac = req.getParameter("mac");
		String ifa = req.getParameter("ifa");
		String source = req.getParameter("source");
		if(StringUtils.isNotBlank(appId)
				&& (!(StringUtils.isBlank(mac) && StringUtils.isBlank(ifa)))
				&& StringUtils.isNotBlank(source)){
			DomoUser domoUser = domoUserDao.get(mac, ifa);
			try {
				if(domoUser==null){
					domoUser = new DomoUser();
					domoUser.setAppId(appId);
					domoUser.setIfa(ifa==null?"":ifa);
					domoUser.setMac(mac==null?"":mac);
					domoUser.setSource(source);
					domoUser.setActive(0);
					domoUser.setCreatedTime(new Date());
					if(domoUserDao.add(domoUser)){
						res.sendRedirect("https://itunes.apple.com/us/app/re-xue-ying-xiong/id875853448?l=zh&ls=1&mt=8");
					}else{
						map.put("code","error");
						LOG.error("添加多盟用户失败:"+Json.toJson(domoUser));
						res.sendRedirect("https://itunes.apple.com/us/app/re-xue-ying-xiong/id875853448?l=zh&ls=1&mt=8");
					}
				}else{
					res.sendRedirect("https://itunes.apple.com/us/app/re-xue-ying-xiong/id875853448?l=zh&ls=1&mt=8");
				}
			} catch (Exception e) {
				map.put("code","fail");
				LOG.error("decode data error!data:"+Json.toJson(domoUser),e);
				try {
					res.sendRedirect("https://itunes.apple.com/us/app/re-xue-ying-xiong/id875853448?l=zh&ls=1&mt=8");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else{
			map.put("code","paramsErr");
			LOG.error("参数列表不正确:appId"+appId+",mac:"+mac+",ifa:"+ifa+",source:"+source);
		}
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		ModelAndView modelView = new ModelAndView();
		modelView.setView(view);
		return modelView;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/webApi/domoActive.do", method = RequestMethod.GET)
	public ModelAndView domoActive(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		String appId = req.getParameter("appId");
		String udid = req.getParameter("mac");
		String ifa = req.getParameter("ifa");
		String ip = req.getParameter("ip");
		String appversion = req.getParameter("appversion");
		String userid = req.getParameter("userid");
		if(StringUtils.isNotBlank(appId)
				&& (!(StringUtils.isBlank(udid) && StringUtils.isBlank(ifa)))
				&& StringUtils.isNotBlank(ip)
				&& StringUtils.isNotBlank(appversion)){
			try {
				udid = udid==null?"":udid;
				ifa = ifa==null?"":ifa;
				DomoUser domoUser = domoUserDao.get(udid, ifa);
				if(domoUser!=null){
					long acttime = System.currentTimeMillis();
					int returnFormat = 1;
					String sign = MD5.MD5Encode("ea04e8685b943064be122141eb8589b7,"+appId);
					Map<String, String> paraMap = new HashMap<String, String>();
					paraMap.put("appId", appId);
					paraMap.put("udid", udid);
					paraMap.put("ifa", ifa);
					paraMap.put("acttime", acttime+"");
					paraMap.put("returnFormat", returnFormat+"");
					paraMap.put("sign", sign);
					paraMap.put("ip", ip);
					paraMap.put("appversion", appversion);
					paraMap.put("clktime", domoUser.getCreatedTime().getTime()+"");
					String result = UrlRequestUtils.execute("http://e.domob.cn/track/ow/api/postback", paraMap, Mode.GET);
					Map<String, Object> jsonMap = Json.toObject(result, Map.class);
					if(jsonMap.get("success")!=null){
						String suc = jsonMap.get("success").toString();
						if(suc.equals("true")){//激活成功
							domoUserDao.update(udid, ifa, 1, appversion, ip, userid);
							map.put("rt",1000);
							map.put("msg","激活成功");
						}else{
							map.put("rt",3000);
							map.put("msg","激活失败");
							LOG.error("decode data error!data:"+jsonMap.get("message"));
						}
					}else{
						map.put("rt",3000);
						map.put("msg","激活失败，对方没返回");
					}
				}else{
					map.put("rt",3000);
					map.put("msg","激活失败，多盟用户不存在");
					LOG.error("多盟用户不存在:mac:"+udid+",ifa:"+ifa);
				}
			} catch (Exception e) {
				map.put("rt",3000);
				map.put("msg","激活出现异常");
				LOG.error("domoActive!data:",e);
			}
		}else{
			map.put("rt",2001);
			map.put("msg","参数不正确");
		}
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		ModelAndView modelView = new ModelAndView();
		modelView.setView(view);
		return modelView;
	}
	
	@RequestMapping(value = "/webApi/test.do", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		String partnerId = req.getParameter("partnerId");
		int rate = this.partnerRateDao.getRate(partnerId);
		map.put("rate", rate);
		
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setAttributesMap(map);
		ModelAndView modelView = new ModelAndView();
		modelView.setView(view);		
		return modelView;		
	}
}
